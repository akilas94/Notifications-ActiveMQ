package com.singlife.email.service;

import org.springframework.http.ResponseEntity;

import javax.jms.MapMessage;

public interface EMailService {
    public void processEmail(MapMessage mapMessage) throws Exception;

    public String getEmailBody(Integer id) throws Exception;
}
