package ua.edu.ukma.gpd.storage.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ua.edu.ukma.gpd.storage.dao.RoleDao;
import ua.edu.ukma.gpd.storage.entity.Role;
import ua.edu.ukma.gpd.storage.sql.RoleSql;

@Repository
public class RoleDaoImpl implements RoleDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public RoleDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private RowMapper<Role> mapper = (rs, i) -> {
		Role role = new Role();
		role.setId(rs.getByte("id"));
		role.setName(rs.getString("name"));
		return role;
	};

	@Override
	public Byte create(Role role) {
		jdbcTemplate.update(RoleSql.INSERT,
				role.getId(), role.getName());
		return role.getId();
	}

	@Override
	public Role findById(Byte id) {
		return jdbcTemplate.queryForObject(RoleSql.FIND_BY_ID, 
				new Object[] { id }, mapper);
	}

	@Override
	public Role findByName(String name) {
		return jdbcTemplate.queryForObject(RoleSql.FIND_BY_NAME, 
				new Object[] { name }, mapper);
	}

	@Override
	public List<Role> findAll() {
		return jdbcTemplate.query(RoleSql.FIND_ALL, mapper);
	}

	@Override
	public void createRolesTable() {
		jdbcTemplate.execute(RoleSql.CREATE_TABLE);
	}

	@Override
	public void dropRolesTable() {
		jdbcTemplate.execute(RoleSql.DROP_TABLE);
	}
	
	

}
