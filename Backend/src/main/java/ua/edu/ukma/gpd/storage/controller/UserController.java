package ua.edu.ukma.gpd.storage.controller;
	
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.edu.ukma.gpd.storage.dto.SignupFormDto;
import ua.edu.ukma.gpd.storage.entity.User;
import ua.edu.ukma.gpd.storage.service.EmailService;
import ua.edu.ukma.gpd.storage.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@SuppressWarnings("unused")
	@Autowired
	private EmailService emailService;
	
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
	public ResponseEntity<Long> addUser(@Valid @RequestBody SignupFormDto form) throws Exception {
		HttpStatus status;
		Long id;
		try {
			User user = buildUserFromDto(form);
			id = userService.add(user);
			status = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
			id = (long) -1;
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Long>((long) id, status);
	}
	
	private User buildUserFromDto(SignupFormDto form) {
		User user = new User();
		user.setEmail(form.getEmail());
		user.setPassword(form.getPassword());
		user.setName(form.getName());
		user.setSurname(form.getSurname());
		user.setPhone(form.getPhone());
		return user;
	}
	
}
