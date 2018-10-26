package ua.edu.ukma.gpd.storage.controller;
	
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.edu.ukma.gpd.storage.entity.User;
import ua.edu.ukma.gpd.storage.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User> getUsers() {
		List<User> users;
		try {
			users = userService.getAll();	
		} catch (Exception e) {
			e.printStackTrace();
			users = null;
		}
		return users;
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") Long id) {
		User user;
		try {
			user = userService.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
			user = null;
		}
		return user;
	}
	
	@PostMapping
	public Long addUser(@RequestBody User user) {
		Long id;
		try {
			id = userService.add(user);
		} catch (Exception e) {
			e.printStackTrace();
			id = (long) -1;
		}
		System.out.println(id);
		return id;
	}
	
}
