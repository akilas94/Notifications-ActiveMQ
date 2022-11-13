package com.singlife.email.componet.impl;

import com.singlife.email.componet.EmailSender;
import com.singlife.email.jms.dto.EMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
public class EmailSenderImpl implements EmailSender {
    private static final String CONST_CONTENT_TYPE_HTML = "text/html";

    @Value("${spring.mail.username}")
    private String mailUser;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(EMail eMail) throws Exception {
        MimeMessagePreparator preparator = getContentWithAttachementMessagePreparator(eMail);
        mailSender.send(preparator);

    }

    private MimeMessagePreparator getContentWithAttachementMessagePreparator(EMail eMail) throws Exception {
        MimeMessagePreparator preparator = (MimeMessage mimeMessage) -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(mailUser);
            helper.setTo(eMail.getToAddresses().split(","));
            if (null != eMail.getCcAddresses() && !eMail.getCcAddresses().isEmpty()) {
                helper.setCc(eMail.getCcAddresses().split(","));
            }
            helper.setSubject(eMail.getSubject());

            if (CONST_CONTENT_TYPE_HTML.equals(eMail.getContentType())) {
                helper.setText(eMail.getBody(), true);
            } else {
                helper.setText(eMail.getBody());
            }

//            for (File file : eMail.getFileList()) {
//                helper.addAttachment(file.getName(), file);
//            }
        };
        return preparator;
    }
}
