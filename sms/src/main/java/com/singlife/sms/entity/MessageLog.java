package com.singlife.sms.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "message_log")
@Data
public class MessageLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;
    @Lob
    @Column(name = "message", length = 16777215 ,nullable = false)
    private String message;
    private String phoneNo;
    private Date messageSendTime;
    private Date jmsSendTime;
    private String status;
    private String responceMessage;
    private String tagName;

}
