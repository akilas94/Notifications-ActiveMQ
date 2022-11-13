package com.singlife.email;

import com.singlife.email.componet.EmailSender;
import com.singlife.email.repository.EmailLogRepository;
import com.singlife.email.service.impl.EmailServiceImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.MessageCreator;

import javax.jms.MapMessage;
import javax.jms.Session;

@RunWith(MockitoJUnitRunner.class)
class EmailApplicationTests {
	private static final String EMAIL_LOG_ID = "EMAIL_LOG_ID";
	private static final String FROM_ADDRESS_KEY = "FROM_ADDRESS";
	private static final String TO_ADDRESS_KEY = "TO_ADDRESS";
	private static final String CC_ADDRESS_KEY = "CC_ADDRESS";
	private static final String SUBJECT_KEY = "SUBJECT";
	private static final String BODY_KEY = "BODY";
	private static final String FILE_NAME_LIST_KEY = "FILE_NAME_LIST";
	private static final String CONTENT_TYPE = "CONTENT_TYPE";

	@InjectMocks
	private EmailServiceImpl emailService;

	@Mock
	private EmailSender sender;

	@Mock
	private EmailLogRepository emailLogRepository;

	@SneakyThrows
	@Test
	void sendEmail() {
		Mockito.when(emailLogRepository.updateStatus(Mockito.anyInt(),
				Mockito.anyString(), Mockito.any())).thenReturn(1);
		emailService.processEmail(getMapMessage());
		Mockito.verify(emailLogRepository).updateStatus(Mockito.anyInt(),
				Mockito.anyString(), Mockito.any());

	}

	private MapMessage getMapMessage(){

//		return messageCreator = (Session session) -> {
//
//			MapMessage mapMessage = session.createMapMessage();
//			mapMessage.setObject(EMAIL_LOG_ID, "1");
//			mapMessage.setObject(FROM_ADDRESS_KEY, "akila@gmail.com");
//			mapMessage.setObject(TO_ADDRESS_KEY, "akila@gmail.com");
//			mapMessage.setObject(CC_ADDRESS_KEY, "");// save CC Address
//			mapMessage.setObject(SUBJECT_KEY, "Test");// save Subject
//			mapMessage.setObject(BODY_KEY, "This is a test message");// save Body
//			mapMessage.setObject(CONTENT_TYPE, "");// save Body
//			return mapMessage;
//		};

		return null;
	}

}
