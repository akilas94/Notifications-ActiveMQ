package com.emailsms.demo.controller;

import com.emailsms.demo.dto.EmailDto;
import com.emailsms.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @GetMapping
    private ResponseEntity sendEmail(@RequestBody EmailDto emailDto) {
       return new ResponseEntity<>( emailService.sendEmail(emailDto), HttpStatus.OK);
    }
}
