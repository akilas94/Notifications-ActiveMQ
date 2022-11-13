package com.singlife.sms.service;

import javax.jms.MapMessage;

public interface SmsService {

    public void processSms(MapMessage mapMessage) throws Exception;

    String getSms(Integer id) throws Exception;
}
