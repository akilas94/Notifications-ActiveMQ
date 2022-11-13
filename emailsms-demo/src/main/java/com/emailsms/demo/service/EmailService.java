package com.emailsms.demo.service;

import com.emailsms.demo.dto.EmailDto;

public interface EmailService {
    String sendEmail(EmailDto emailDto);
}
