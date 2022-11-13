package com.singlife.sms.service.impl;

import com.singlife.sms.dto.MessageLog;
import com.singlife.sms.dto.Status;
import com.singlife.sms.repository.MessageLogRepository;
import com.singlife.sms.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.MapMessage;
import javax.transaction.Transactional;
import java.util.Date;

@Transactional
@Service
public class SmsServiceImpl implements SmsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SmsServiceImpl.class);
    private static final String MESSAGE_ID = "MESSAGE_ID";
    private static final String MESSAGE = "MESSAGE";
    private static final String PHONE_NO = "PHONE_NO";

    @Autowired
    private MessageLogRepository messageLogRepository;

    @Override
    public void processSms(MapMessage mapMessage) throws Exception {
        MessageLog mLog = new MessageLog();
        mLog.setMessageId(Long.valueOf(mapMessage.getString(MESSAGE_ID)));
        mLog.setPhoneNo(mapMessage.getString(PHONE_NO));
        mLog.setMessage(mapMessage.getString(MESSAGE));
        messageLogRepository.updateStatus(mLog.getMessageId(), Status.MS.toString(), new Date());
        LOGGER.info("SMS Process Success : SMS ID - " + mLog.getMessageId());
        // pass to sms service
    }

    @Override
    public String getSms(Integer id) throws Exception {
        return messageLogRepository.findById(id).get().getMessage();
    }
}
