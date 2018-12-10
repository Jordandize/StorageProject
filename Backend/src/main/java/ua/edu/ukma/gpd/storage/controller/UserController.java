package ua.edu.ukma.gpd.storage.controller;
	
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.edu.ukma.gpd.storage.dto.ProductDto;
import ua.edu.ukma.gpd.storage.dto.RolesDto;
import ua.edu.ukma.gpd.storage.dto.SignupFormDto;
import ua.edu.ukma.gpd.storage.entity.Role;
import ua.edu.ukma.gpd.storage.entity.User;
import ua.edu.ukma.gpd.storage.service.EmailService;
import ua.edu.ukma.gpd.storage.service.RoleService;
import ua.edu.ukma.gpd.storage.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
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
	@GetMapping("api/users/search/{email}")
	public List<User> getAllByEmail(@PathVariable("email") String email) throws Exception{
		List<User> emailLike;
	        try{
	        	emailLike = userService.getAllByEmail(email);
	        } catch (Exception e){
	            e.printStackTrace();
	            emailLike = null;
	        }
	        return emailLike;
	}
	@GetMapping("api/users/{id}/roles")
	public List<Role> getAllRolesByUser(@PathVariable("id") Long id) throws Exception{
		List <Role> roles;
		User user;
		
	        try{
	        	user=userService.getById(id);
	        	roles=roleService.getRolesForUser(user);
	        	
	        } catch (Exception e){
	            e.printStackTrace();
	            roles = null;
	        }
	        return roles;
	}
	@PostMapping("api/users/{id}/roles")
	public ResponseEntity<Boolean> changeRolesForUser(@Valid @RequestBody RolesDto form,@PathVariable("id") Long id) throws Exception{
		List <Role> roles;
		User user;
	    boolean b;
	    HttpStatus status;
	        try{
	        	roles = buildRolesFromDto(form);
	        	user = userService.getById(id);
	        	roleService.removeRolesForUser(user);
	        	for(int i=0;i<roles.size();i++ ) {
	        	roleService.addRoleToUser(user, roles.get(i));
	        	}
	        	b=true;
	            status = HttpStatus.OK;
	        } catch (Exception e){
	            e.printStackTrace();
	            b=false;
	            status = HttpStatus.BAD_REQUEST;
	        }
	        return new ResponseEntity<>(b, status);
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
	private List<Role> buildRolesFromDto(RolesDto form) throws Exception {
		List<Role> roles = new ArrayList<Role>();
		String[] names=form.getName();
		for(int i=0;i<names.length;i++) {
		Role role=new Role(); 
			if(names[i].equals(Role.USER)) {
			role = roleService.getRoleByName(Role.USER);
			roles.set(i, role);
			}
			else if(names[i].equals(Role.KEEPER)){
				role = roleService.getRoleByName(Role.KEEPER);
				roles.set(i, role);
			}
			else if(names[i].equals(Role.ADMIN)){
			role = roleService.getRoleByName(Role.ADMIN);
			roles.set(i, role);
			}
		}
		return roles;
	}
	
}
