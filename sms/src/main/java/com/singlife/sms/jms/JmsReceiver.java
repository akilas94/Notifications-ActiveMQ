package com.singlife.sms.jms;

import com.singlife.sms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.MapMessage;

@Component
public class JmsReceiver {
    @Autowired
    private SmsService smsService;

    @JmsListener(destination = "Sms-Queue", containerFactory = "jmsListenerContainerFactory")
    public void receiveSmsFromQueue(MapMessage mapMessage) {

        Integer smsLogId = 0;
        try {
          smsService.processSms(mapMessage);
        } catch (Exception ex) {
            String logString = "SMS Process Failed : SMS ID - " + smsLogId;
            throw new RuntimeException(logString, ex);
        }
    }
}
