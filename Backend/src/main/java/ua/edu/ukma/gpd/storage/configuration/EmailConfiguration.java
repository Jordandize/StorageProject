package ua.edu.ukma.gpd.storage.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class EmailConfiguration {
	
	@Bean
	public SimpleMailMessage templateGreetingMessage() {
	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setText("Welcome to Storage!\n%s\n");
	    return message;
	}

}
