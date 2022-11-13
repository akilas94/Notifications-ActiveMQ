package com.singlife.sms.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MessageLog {

    private Long messageId;
    private String message;
    private String phoneNo;
    private Date messageSendTime;
    private String Status;

}
