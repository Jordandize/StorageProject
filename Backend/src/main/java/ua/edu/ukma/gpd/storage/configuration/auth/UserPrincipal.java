package ua.edu.ukma.gpd.storage.configuration.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.edu.ukma.gpd.storage.entity.Role;
import ua.edu.ukma.gpd.storage.entity.User;
import ua.edu.ukma.gpd.storage.service.RoleService;

public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private RoleService roleService;
	
	private User user;
	
	public UserPrincipal(User user, RoleService roleService) {
		this.user = user;
		this.roleService = roleService;
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		try {
			for(Role role: roleService.getRolesForUser(user))
				authorities.add(new SimpleGrantedAuthority(role.getName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public User getUser() {
		return user;
	}
	
}
