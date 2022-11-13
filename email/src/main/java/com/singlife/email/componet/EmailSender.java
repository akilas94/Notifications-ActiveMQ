package com.singlife.email.componet;

import com.singlife.email.jms.dto.EMail;

public interface EmailSender {
    public void sendEmail(EMail eMail) throws Exception;
}
