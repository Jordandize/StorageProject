package ua.edu.ukma.gpd.storage.service;

import java.util.List;


import ua.edu.ukma.gpd.storage.entity.User;
import ua.edu.ukma.gpd.storage.exception.EmailAlreadyInUseException;

public interface UserService {
	
	Long add(User user) throws EmailAlreadyInUseException, Exception;
	
	User getById(Long id) throws Exception;
	
	User getByEmail(String email) throws Exception;

	List<User> getAll() throws Exception;
	
	List<User> getAllByEmail(String email) throws Exception;

	List<User> getActiveKeepers() throws Exception;
	
}
