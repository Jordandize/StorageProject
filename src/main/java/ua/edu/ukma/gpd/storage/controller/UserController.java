package ua.edu.ukma.gpd.storage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.edu.ukma.gpd.storage.service.UserService;

@RestController("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String users() {
		return userService.getAll().toString();
	}

}
