package com.emailsms.demo;

import com.emailsms.demo.dto.EmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Session;

@Component
public class ActiveMQEmail {
    private static final String EMAIL_LOG_ID = "EMAIL_LOG_ID";
    private static final String FROM_ADDRESS_KEY = "FROM_ADDRESS";
    private static final String TO_ADDRESS_KEY = "TO_ADDRESS";
    private static final String CC_ADDRESS_KEY = "CC_ADDRESS";
    private static final String SUBJECT_KEY = "SUBJECT";
    private static final String BODY_KEY = "BODY";
    private static final String CONTENT_TYPE = "CONTENT_TYPE";

    private String jmsQueueName = "Email-Queue";

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendFromEmail(EmailDto emailDto) throws JMSException {
        MessageCreator messageCreator = (Session session) -> {

            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setObject(EMAIL_LOG_ID, emailDto.getMailId());
            mapMessage.setObject(FROM_ADDRESS_KEY, emailDto.getFromAddress());
            mapMessage.setObject(TO_ADDRESS_KEY, emailDto.getToAddress());
            mapMessage.setObject(CC_ADDRESS_KEY, emailDto.getCcAddresses());// save CC Address
            mapMessage.setObject(SUBJECT_KEY, emailDto.getSubject());// save Subject
            mapMessage.setObject(BODY_KEY, emailDto.getBody());// save Body
            mapMessage.setObject(CONTENT_TYPE, "");// save Body
            return mapMessage;
        };
        jmsTemplate.send(jmsQueueName, messageCreator);
    }
}
