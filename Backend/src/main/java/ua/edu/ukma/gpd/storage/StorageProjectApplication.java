package ua.edu.ukma.gpd.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@EnableScheduling
@Controller
public class StorageProjectApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(StorageProjectApplication.class, args);
	}
	
}
