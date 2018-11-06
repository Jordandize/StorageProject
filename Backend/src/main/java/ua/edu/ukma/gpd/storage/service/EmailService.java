package ua.edu.ukma.gpd.storage.service;

public interface EmailService {
	
	void send(String to, String subject, String text);
	
	void sendGreeting(String to);

}
