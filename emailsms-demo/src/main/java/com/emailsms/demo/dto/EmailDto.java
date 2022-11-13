package com.emailsms.demo.dto;

import lombok.Data;

import java.lang.reflect.Array;
import java.util.List;

@Data
public class EmailDto {
    private Integer mailId;
    private String fromAddress;
    private String toAddress;
    private String ccAddresses;
    private String subject;
    private String body;
    private List<String> parameterArray;


}
