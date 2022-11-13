package com.emailsms.demo.service.impl;

import com.emailsms.demo.ActiveMQSms;
import com.emailsms.demo.dto.SmsDto;
import com.emailsms.demo.dto.Status;
import com.emailsms.demo.entity.MessageLog;
import com.emailsms.demo.entity.MessageTemplate;
import com.emailsms.demo.repository.MessageLogRepository;
import com.emailsms.demo.repository.MessageTemplateRepository;
import com.emailsms.demo.service.MessageService;
import com.emailsms.demo.utility.MessageCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Transactional
@Service
public class MessageServiceImpl implements MessageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Autowired
    private MessageTemplateRepository messageTemplateRepository;

    @Autowired
    private MessageCreator messageCreator;

    @Autowired
    private MessageLogRepository messageLogRepository;

    @Autowired
    private ActiveMQSms activeMQSms;

    @Override
    public String sendSms(SmsDto smsDto) {
        String status = "";
        Optional<MessageTemplate> templete = messageTemplateRepository.findById(2L);
        String message = messageCreator.makeSmsAndEmailMessage
                (templete.get().getTemplate(), smsDto.getParameterArray());
        smsDto.setMessage(message);

        MessageLog messageLog = new MessageLog();
        BeanUtils.copyProperties(smsDto, messageLog);
        messageLog.setStatus(Status.JP.toString());
        messageLog.setJmsSendTime(new Date());
        MessageLog save = messageLogRepository.save(messageLog);
        smsDto.setMessageId(save.getMessageId());
        if(messageLogRepository.updateStatus(save.getMessageId(), Status.JS.toString(), new Date())> 0){
            status = "SMS sent to JMS queue : SMS id " + messageLog.getMessageId();
        }

        try {
            activeMQSms.sendFromSms(smsDto);
        } catch (JMSException e) {
            LOGGER.error(e.getMessage());
        }
        return status;


    }
}
