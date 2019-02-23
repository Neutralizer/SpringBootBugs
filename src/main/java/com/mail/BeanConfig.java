package com.mail;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class BeanConfig {

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.abv.bg");
		mailSender.setPort(465);

		mailSender.setUsername("mymail546@abv.bg");
		mailSender.setPassword("enterpassword1");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.smtp.host", "smtp.abv.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		return mailSender;
	}

	@Bean
	public EmailServiceImpl emailService() {
		return new EmailServiceImpl();
	}

}
