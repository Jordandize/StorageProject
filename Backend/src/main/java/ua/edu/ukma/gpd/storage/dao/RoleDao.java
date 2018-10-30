package ua.edu.ukma.gpd.storage.dao;

import java.util.List;

import ua.edu.ukma.gpd.storage.entity.Role;

public interface RoleDao {
	
	public Byte create(Role role);
	
	public Role findById(Byte id);
	
	public Role findByName(String name);
	
	public List<Role> findAll();
	
	public void createRolesTable();
	
	public void dropRolesTable();

}
