package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mail.BeanConfig;
import com.mail.EmailServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={BeanConfig.class})
public class TestEmailService {

	@Autowired
	private EmailServiceImpl emailService;

	@Test
	public void testSendSimpleMessage() {

		emailService.sendSimpleMessage("mymail546@abv.bg", "test", "text text");
	}

}