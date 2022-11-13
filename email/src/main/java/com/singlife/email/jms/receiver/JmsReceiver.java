package com.singlife.email.jms.receiver;

import com.singlife.email.service.EMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.MapMessage;

@Component
public class JmsReceiver {
    @Autowired
    private EMailService eMailService;

    @JmsListener(destination = "Email-Queue", containerFactory = "jmsListenerContainerFactory")
    public void receiveEMailFromQueue(MapMessage mapMessage) {
        Integer mailLogId = 0;
        try {
            eMailService.processEmail(mapMessage);
        } catch (Exception ex) {
            String logString = "JMS EMail Process Failed : EMailLog ID - " + mailLogId;
            throw new RuntimeException(logString, ex);
        }
    }
}
