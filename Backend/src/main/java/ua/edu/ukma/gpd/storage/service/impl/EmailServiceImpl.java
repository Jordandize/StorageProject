package ua.edu.ukma.gpd.storage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import ua.edu.ukma.gpd.storage.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	public JavaMailSender emailSender;
	
	@Autowired
	private SimpleMailMessage greeting;
	
	@Override
	public void send(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(to); 
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
	}
	
	public void sendGreeting(String to) {
		String text = String.format(greeting.getText(), to);
		send(to, "Welcome!", text);
	}
	
}
