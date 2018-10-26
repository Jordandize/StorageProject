package ua.edu.ukma.gpd.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class StorageProjectApplication {
	
	@GetMapping(value = "/api/foos")
	@ResponseBody
	public String userRolesAvail() {
		return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(StorageProjectApplication.class, args);
	}
	
}
