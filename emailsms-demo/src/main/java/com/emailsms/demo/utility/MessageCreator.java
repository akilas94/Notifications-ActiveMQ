package com.emailsms.demo.utility;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageCreator {

    public String makeSmsAndEmailMessage(String msg, List<String> parameterList){
        int place = 1;
        for(String parameter : parameterList){
            msg = msg.replace("?"+place, parameter);
            place++;
        }
        return msg;
    }
}
