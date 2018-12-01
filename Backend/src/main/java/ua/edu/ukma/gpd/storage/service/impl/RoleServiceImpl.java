package ua.edu.ukma.gpd.storage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ua.edu.ukma.gpd.storage.dao.RoleDao;
import ua.edu.ukma.gpd.storage.dao.relation.UsersRolesDao;
import ua.edu.ukma.gpd.storage.entity.Role;
import ua.edu.ukma.gpd.storage.entity.User;
import ua.edu.ukma.gpd.storage.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UsersRolesDao usersRolesDao;

	@Override
	public Role getRoleByName(String name) throws Exception {
		Role role;
		try {
			role = roleDao.findByName(name);
		} catch(EmptyResultDataAccessException ex) {
			role = null;
		} catch (Exception ex) {
			throw new Exception("RoleServiceImpl: Get role by name [" + name + "] operation failed", ex);
		}
		return role;
	}

	@Override
	public List<Role> getRolesForUser(User user) throws Exception {
		List<Role> roles;
		try {
			roles = usersRolesDao.findByUser(user);
		} catch(EmptyResultDataAccessException ex) {
			roles = new ArrayList<>();
		} catch (Exception ex) {
			throw new Exception("RoleServiceImpl: Get roles for user [" + user + "] operation failed", ex);
		}
		return roles;
	}

	@Override
	public boolean addRoleToUser(User user, Role role) throws Exception {
		boolean added;
		try {
			added = usersRolesDao.create(user, role);
		} catch(EmptyResultDataAccessException ex) {
			added = false;
		} catch (Exception ex) {
			throw new Exception("RoleServiceImpl: Add role [" + role + "] to user [" + user + "] operation failed", ex);
		}
		return added;
	}

	@Override
	public boolean removeRolesForUser(User user) throws Exception {
		boolean removed = false;
		try {
			removed = usersRolesDao.delete(user);
		} catch (Exception ex) {
			throw new Exception("RoleServiceImpl: Remove roles for user [" + user + "] operation failed", ex);
		}
		return removed;
	}

}
