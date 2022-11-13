package com.emailsms.demo.service.impl;

import com.emailsms.demo.ActiveMQEmail;
import com.emailsms.demo.dto.EmailDto;
import com.emailsms.demo.dto.Status;
import com.emailsms.demo.entity.EmailLog;
import com.emailsms.demo.entity.MessageTemplate;
import com.emailsms.demo.repository.EmailLogRepository;
import com.emailsms.demo.repository.MessageTemplateRepository;
import com.emailsms.demo.service.EmailService;
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
public class EmailServiceImpl implements EmailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private ActiveMQEmail activeMQEmail;

    @Autowired
    private MessageCreator messageCreator;

    @Autowired
    private MessageTemplateRepository messageTemplateRepository;

    @Autowired
    private EmailLogRepository emailLogRepository;


    @Override
    public String sendEmail(EmailDto emailDto) {
        String status = "";
        Optional<MessageTemplate> templete = messageTemplateRepository.findById(1L);
        String message = messageCreator.makeSmsAndEmailMessage
                (templete.get().getTemplate(), emailDto.getParameterArray());
        emailDto.setBody(message);

        EmailLog emailLog = new EmailLog();
        BeanUtils.copyProperties(emailDto, emailLog);
        emailLog.setSendStatus(Status.JP.toString());
        emailLogRepository.save(emailLog);
        try {
            emailDto.setMailId(emailLog.getMailId());
            activeMQEmail.sendFromEmail(emailDto);
            if (emailLogRepository.updateStatus(emailDto.getMailId(), Status.JS.toString(), new Date()) > 0) {
                status = "Email sent to JMS queue : Email id " + emailDto.getMailId();
            }

        } catch (JMSException e) {
            LOGGER.error(e.getMessage());
        }
        return status;
    }
}
