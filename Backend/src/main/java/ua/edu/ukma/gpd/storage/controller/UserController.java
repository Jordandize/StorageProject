package ua.edu.ukma.gpd.storage.controller;
	
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.edu.ukma.gpd.storage.dto.SignupFormDto;
import ua.edu.ukma.gpd.storage.entity.User;
import ua.edu.ukma.gpd.storage.service.EmailService;
import ua.edu.ukma.gpd.storage.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;
	

	@GetMapping("api/users")
	public List<User> getUsers() throws Exception {
		return userService.getAll();
	}
	
	@GetMapping("api/users/{id}")
	public User getUserById(@PathVariable("id") Long id) throws Exception {
		return userService.getById(id);
	}

	@GetMapping("/role={user}/active={activeness}")
	public List<User> getActiveKeepers(@PathVariable("user") String user, @PathVariable("activeness") boolean activeness) throws Exception{
		List<User> users;
		if (user.equals("keeper") && activeness){
			users = userService.getActiveKeepers();
			return users;
		} else {
			throw new Exception("No active keepers");
		}
	}

	@PostMapping("users")
	public ResponseEntity<Long> addUser(@Valid @RequestBody SignupFormDto form) throws Exception {
		HttpStatus status;
		Long id;
		try {
			User user = buildUserFromDto(form);
			id = userService.add(user);
			emailService.sendGreeting(user.getEmail());
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
