package ua.edu.ukma.gpd.storage.dao.relation.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ua.edu.ukma.gpd.storage.dao.RoleDao;
import ua.edu.ukma.gpd.storage.dao.UserDao;
import ua.edu.ukma.gpd.storage.dao.relation.UsersRolesDao;
import ua.edu.ukma.gpd.storage.entity.Role;
import ua.edu.ukma.gpd.storage.entity.User;
import ua.edu.ukma.gpd.storage.sql.relation.UsersRolesSql;

@Repository
public class UsersRolesDaoImpl implements UsersRolesDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	public UsersRolesDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private RowMapper<User> userMapper = (rs, i) ->
		userDao.findById(rs.getLong("id_user"));
		
	private RowMapper<Role> roleMapper = (rs, i) ->
		roleDao.findById(rs.getByte("id_role"));

	@Override
	public boolean create(User user, Role role) {
		return jdbcTemplate.update(UsersRolesSql.INSERT,
				user.getId(), role.getId()) > 0;
	}
	
	@Override
	public boolean delete(User user) {
		return jdbcTemplate.update(UsersRolesSql.DELETE_USER_ROLES, user.getId()) > 0;
	}

	@Override
	public List<Role> findByUser(User user) {
		return jdbcTemplate.query(UsersRolesSql.FIND_BY_ID_USER, 
				roleMapper, user.getId());
	}

	@Override
	public List<User> findByRole(Role role) {
		return jdbcTemplate.query(UsersRolesSql.FIND_BY_ID_ROLE, 
				userMapper, role.getId());
	}

}
