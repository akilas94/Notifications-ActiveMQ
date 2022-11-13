package com.emailsms.demo;

import com.emailsms.demo.dto.SmsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Session;

@Component
public class ActiveMQSms {
    private static final String MESSAGE_ID = "MESSAGE_ID";
    private static final String PHONE_NO = "PHONE_NO";
    private static final String MESSAGE = "MESSAGE";

    private String jmsQueueName = "Sms-Queue";

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendFromSms(SmsDto smsDto) throws JMSException {
        MessageCreator messageCreator = (Session session) -> {

            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setObject(MESSAGE_ID, smsDto.getMessageId());
            mapMessage.setObject(PHONE_NO, smsDto.getPhoneNo());
            mapMessage.setObject(MESSAGE, smsDto.getMessage());

            return mapMessage;
        };
        jmsTemplate.send(jmsQueueName, messageCreator);
    }
}
