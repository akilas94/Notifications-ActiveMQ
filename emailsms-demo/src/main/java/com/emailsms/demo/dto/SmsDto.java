package com.emailsms.demo.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SmsDto {
    private int messageId;
    private String message;
    private String phoneNo;
    private List<String> parameterArray;
}
