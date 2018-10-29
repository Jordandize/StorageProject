package ua.edu.ukma.gpd.storage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import ua.edu.ukma.gpd.storage.configuration.auth.UserPrincipal;
import ua.edu.ukma.gpd.storage.entity.User;
import ua.edu.ukma.gpd.storage.service.UserService;

@Component
public class PostgresUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		try {
			User user = userService.getByEmail(email);
			if(user == null)
				throw new UsernameNotFoundException("User with email [" + email + "] not founded");	
			return new UserPrincipal(user);
		} catch(Exception e) {
			throw new UsernameNotFoundException("User with email [" + email + "] not founded");	
		}
	}

}
