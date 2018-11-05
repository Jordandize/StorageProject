package ua.edu.ukma.gpd.storage.service;

import java.util.List;

import ua.edu.ukma.gpd.storage.entity.Role;
import ua.edu.ukma.gpd.storage.entity.User;

public interface RoleService {
	
	public Role getRoleByName(String name) throws Exception;
	
	public List<Role> getRolesForUser(User user) throws Exception;
	
	public boolean addRoleToUser(User user, Role role) throws Exception;
	
	public boolean removeRolesForUser(User user) throws Exception;

}
