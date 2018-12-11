package ua.edu.ukma.gpd.storage.dao;

import java.util.List;

import ua.edu.ukma.gpd.storage.entity.User;

public interface UserDao {

	Long create(User user);
	
	boolean update(User user);
	
	boolean delete(User user);
	
	User findById(Long id);
	
	User findByEmail(String email);
	
	List<User> findAllByEmail(String email);
	
	List<User> findAll();

	List<User> findActiveKeepers();

}
