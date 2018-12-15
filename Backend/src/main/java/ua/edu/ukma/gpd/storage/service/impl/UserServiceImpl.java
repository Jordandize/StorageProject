package ua.edu.ukma.gpd.storage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ua.edu.ukma.gpd.storage.dao.UserDao;
import ua.edu.ukma.gpd.storage.entity.Role;
import ua.edu.ukma.gpd.storage.entity.User;
import ua.edu.ukma.gpd.storage.exception.EmailAlreadyInUseException;
import ua.edu.ukma.gpd.storage.service.RoleService;
import ua.edu.ukma.gpd.storage.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleService roleService;
	
	@Override
	public Long add(User user) throws Exception {
		Long id = null;
		try {
			User exists = getByEmail(user.getEmail());
			if(exists == null) {
				user.setActive(true);
				id = userDao.create(user);
				Role role = roleService.getRoleByName(Role.USER);
				roleService.addRoleToUser(user, role);
				return id;
			} else {
				throw new EmailAlreadyInUseException("User with email [" + user.getEmail() + "] already exists");
			}
		} catch(EmailAlreadyInUseException em) {
			throw em;
		} catch (Exception e) {
			user.setActive(false);
			try {
				if(id != null) {
					userDao.delete(user);
					roleService.removeRolesForUser(user);
				}
			} catch (Exception exc) {
				throw new Exception("UserServiceImpl: Clean results of failed operation add user [" + user + "] operation failed", e);
			}
			throw new Exception("UserServiceImpl: Add user [" + user + "] operation failed", e);
		}
	}

	@Override
	public User getById(Long id) throws EmptyResultDataAccessException, Exception {
		try {
			return userDao.findById(id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		} catch (Exception e) {
			throw new Exception("UserServiceImpl: Get user by id [" + id + "] operation failed", e);
		}
	}

	@Override
	public User getByEmail(String email) throws EmptyResultDataAccessException, Exception {
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
	@Override
	public List<User> getAllByEmail(String email) throws Exception {
		try {
			return userDao.findAllByEmail(email);
		} catch (Exception e) {
			throw new Exception("UserServiceImpl: Get all users by email operation failed", e);
		}
	}
	

	@Override
	public List<User> getActiveKeepers() throws Exception {
		try{
			return userDao.findActiveKeepers();
		} catch (Exception e){
			throw new Exception("UserServiceImpl: getActiveKeepers operation failed", e);
		}
	}

}
