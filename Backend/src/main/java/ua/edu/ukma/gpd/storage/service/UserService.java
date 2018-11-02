package ua.edu.ukma.gpd.storage.service;

import java.util.List;

import ua.edu.ukma.gpd.storage.entity.User;
import ua.edu.ukma.gpd.storage.exception.EmailAlreadyInUseException;

public interface UserService {
	
	public Long add(User user) throws EmailAlreadyInUseException, Exception;
	
	public User getById(Long id) throws Exception;
	
	public User getByEmail(String email) throws Exception;

	public List<User> getAll() throws Exception;
	
}
