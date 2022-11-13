package com.singlife.email.controller;

import com.singlife.email.service.EMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/email")
public class EmailController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailController.class);
    @Autowired
    private EMailService eMailService;

    @GetMapping
    public ResponseEntity getEmail(@RequestParam("id")int id) {
        ResponseEntity responseEntity = null;

        try {
            responseEntity = new ResponseEntity<>(eMailService.getEmailBody(id), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return responseEntity;
    }
}

