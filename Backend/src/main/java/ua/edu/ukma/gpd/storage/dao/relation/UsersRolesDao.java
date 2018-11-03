package ua.edu.ukma.gpd.storage.dao.relation;

import java.util.List;

import ua.edu.ukma.gpd.storage.entity.Role;
import ua.edu.ukma.gpd.storage.entity.User;

public interface UsersRolesDao {
	
	public boolean create(User user, Role role);
	
	public boolean delete(User user);
	
	public List<Role> findByUser(User user);
	
	public List<User> findByRole(Role role);
	
}
