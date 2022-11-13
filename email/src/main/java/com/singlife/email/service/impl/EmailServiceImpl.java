package com.singlife.email.service.impl;

import com.singlife.email.componet.EmailSender;
import com.singlife.email.dto.Status;
import com.singlife.email.jms.dto.EMail;
import com.singlife.email.repository.EmailLogRepository;
import com.singlife.email.service.EMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.MapMessage;
import javax.transaction.Transactional;
import java.util.Date;

@Transactional
@Service
public class EmailServiceImpl implements EMailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);
    private static final String EMAIL_LOG_ID = "EMAIL_LOG_ID";
    private static final String FROM_ADDRESS_KEY = "FROM_ADDRESS";
    private static final String TO_ADDRESS_KEY = "TO_ADDRESS";
    private static final String CC_ADDRESS_KEY = "CC_ADDRESS";
    private static final String SUBJECT_KEY = "SUBJECT";
    private static final String BODY_KEY = "BODY";
    private static final String FILE_NAME_LIST_KEY = "FILE_NAME_LIST";
    private static final String CONTENT_TYPE = "CONTENT_TYPE";

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private EmailLogRepository emailLogRepository;



    @Override
    public void processEmail(MapMessage mapMessage) throws Exception {
        EMail eMail = new EMail();
        eMail.setMailId(Integer.valueOf(mapMessage.getString(EMAIL_LOG_ID)));
        eMail.setFromAddress(mapMessage.getString(FROM_ADDRESS_KEY));
        eMail.setToAddresses(mapMessage.getString(TO_ADDRESS_KEY));
        eMail.setCcAddresses(mapMessage.getString(CC_ADDRESS_KEY));
        eMail.setSubject(mapMessage.getString(SUBJECT_KEY));
        eMail.setBody(mapMessage.getString(BODY_KEY));
        eMail.setContentType(mapMessage.getString(CONTENT_TYPE));
        emailLogRepository.updateStatus(eMail.getMailId(), Status.MS.toString(), new Date());
        LOGGER.info("Email Process Success : Email ID - " + eMail.getMailId());
       // emailSender.sendEmail(eMail);


    }

    @Override
    public String getEmailBody(Integer id) throws Exception {
        return emailLogRepository.findById(id).get().getBody();
    }
}
