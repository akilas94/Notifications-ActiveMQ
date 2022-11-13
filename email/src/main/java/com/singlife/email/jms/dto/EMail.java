package com.singlife.email.jms.dto;

import lombok.Data;

import java.io.File;
import java.io.Serializable;
import java.util.List;

@Data
public class EMail implements Serializable {

    private static final long serialVersionUID = 1000L;
    private static final String EMPTY_STRING = "";
    private String fromAddress;
    private String toAddresses;
    private String ccAddresses;
    private String subject;
    private String body;
    private List<File> fileList;
    private String contentType;
    private Integer mailId;
}
