package com.emailsms.demo.service;

import com.emailsms.demo.dto.SmsDto;

public interface MessageService {
    String sendSms(SmsDto smsDto);

}
