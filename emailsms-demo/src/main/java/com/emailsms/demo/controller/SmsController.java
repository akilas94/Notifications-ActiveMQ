package com.emailsms.demo.controller;

import com.emailsms.demo.dto.SmsDto;
import com.emailsms.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class SmsController {
    @Autowired
    private MessageService messageService;

    @GetMapping
    private ResponseEntity sendEmail(@RequestBody SmsDto smsDto) {
        return new ResponseEntity<>(messageService.sendSms(smsDto), HttpStatus.OK);
    }
}
