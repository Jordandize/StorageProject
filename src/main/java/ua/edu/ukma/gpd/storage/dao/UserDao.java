package ua.edu.ukma.gpd.storage.dao;

import java.util.List;

import ua.edu.ukma.gpd.storage.entity.User;

public interface UserDao {

	public Long create(User user);
	
	public boolean update(User user);
	
	public boolean delete(User user);
	
	public User findById(Long id);
	
	public User findByEmail(String email);
	
	public List<User> findAll();
	
	public void createUsersTable();
	
	public void dropUsersTable();

}
