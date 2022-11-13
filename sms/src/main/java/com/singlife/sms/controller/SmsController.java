package com.singlife.sms.controller;

import com.singlife.sms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @GetMapping
    public ResponseEntity getSms(@RequestParam("id")int id) {
        ResponseEntity responseEntity = null;

        try {
            responseEntity = new ResponseEntity<>(smsService.getSms(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseEntity;
    }


}
