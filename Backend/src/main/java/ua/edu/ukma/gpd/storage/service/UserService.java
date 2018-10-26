package ua.edu.ukma.gpd.storage.service;

import java.util.List;

import ua.edu.ukma.gpd.storage.entity.User;

public interface UserService {
	
	public List<User> getAll();
	
	public User add(User user);
	
	public User edit(User user);
	
	public User delete(User user);
	
	public User getById(Long id);
	
	public User getByEmail(String email);
	
}
