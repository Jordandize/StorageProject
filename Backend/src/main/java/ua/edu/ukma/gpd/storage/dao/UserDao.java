package ua.edu.ukma.gpd.storage.dao;

import java.util.List;

import ua.edu.ukma.gpd.storage.entity.User;

public interface UserDao {
	
	public List<User> findAll();
	
	public User findById(Long id);
	
	public User findByEmail(String email);
	
	public User add(User user);
	
	public User update(User user);
	
	public User delete(User user);
	
	public boolean createUsersTable();
	
	public boolean dropUsersTable();

}
