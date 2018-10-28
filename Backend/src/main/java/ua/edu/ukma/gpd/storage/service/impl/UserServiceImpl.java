package ua.edu.ukma.gpd.storage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ua.edu.ukma.gpd.storage.dao.UserDao;
import ua.edu.ukma.gpd.storage.entity.User;
import ua.edu.ukma.gpd.storage.exception.EmailAlreadyInUseException;
import ua.edu.ukma.gpd.storage.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public Long add(User user) throws Exception {
		try {
			User exists = getByEmail(user.getEmail());
			if(exists == null) {
				return userDao.create(user);
			} else {
				throw new EmailAlreadyInUseException("User with email [" + user.getEmail() + "] already exists");
			}
		} catch(EmailAlreadyInUseException em) {
			throw em;
		} catch (Exception e) {
			throw new Exception("UserServiceImpl: Add user operation failed", e);
		}
	}

	@Override
	public User getById(Long id) throws Exception {
		try {
			return userDao.findById(id);
		} catch (Exception e) {
			throw new Exception("UserServiceImpl: Get user by id [" + id + "] operation failed", e);
		}
	}

	@Override
	public User getByEmail(String email) throws Exception {
		try {
			return userDao.findByEmail(email);
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new Exception("UserServiceImpl: Get user by email [" + email + "] operation failed", e);
		}
	}

	@Override
	public List<User> getAll() throws Exception {
		try {
			return userDao.findAll();
		} catch (Exception e) {
			throw new Exception("UserServiceImpl: Get all users operation failed", e);
		}
	}

}
