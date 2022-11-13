package com.emailsms.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "email_log")
@Data
public class EmailLog {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer mailId;
    private Integer upId;
    private String subject;

    @Lob
    @Column(name = "body", length = 16777215 ,nullable = false)
    private String body;
    private String fromAddress;
    private String toAddress;
    private String ccAddress;
    private Date jmsSentTime;
    private Date mailSentTime;
    private String jmsAttachments;
    private String mailAttachments;
    private Integer jmsRetryAttempts;
    private Integer mailRetryAttempts;
    private String sendStatus;
    private String tagName;


}
