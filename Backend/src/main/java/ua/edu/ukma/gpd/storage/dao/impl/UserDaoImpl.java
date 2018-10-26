package ua.edu.ukma.gpd.storage.dao.impl;

import java.sql.PreparedStatement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ua.edu.ukma.gpd.storage.dao.UserDao;
import ua.edu.ukma.gpd.storage.entity.User;
import ua.edu.ukma.gpd.storage.sql.UserSql;

@Repository
public class UserDaoImpl implements UserDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public UserDaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private RowMapper<User> mapper = (rs, i) -> {
		User user = new User();
		user.setId(rs.getLong("id"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setName(rs.getString("name"));
		user.setSurname(rs.getString("surname"));
		return user;
	};

	@Override
	public Long create(User user) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(UserSql.INSERT,
					new String[] { "id" });
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getSurname());
			return ps;
		}, keyHolder);
		return (Long) keyHolder.getKey();
	}

	@Override
	public boolean update(User user) {
		return jdbcTemplate.update(UserSql.UPDATE,
				user.getEmail(), user.getPassword(), user.getName(), user.getSurname()) > 0;
	}

	@Override
	public boolean delete(User user) {
		return jdbcTemplate.update(UserSql.DELETE, user.getId()) > 0;
	}

	@Override
	public User findById(Long id) {
		return jdbcTemplate.queryForObject(UserSql.FIND_BY_ID,
				new Object[] { id }, mapper);
	}

	@Override
	public User findByEmail(String email) {
		return jdbcTemplate.queryForObject(UserSql.FIND_BY_EMAIL,
				new Object[] { email }, mapper);
	}

	@Override
	public List<User> findAll() {
		return jdbcTemplate.query(UserSql.FIND_ALL, mapper);
	}

	@Override
	public void createUsersTable() {
		jdbcTemplate.execute(UserSql.CREATE_TABLE);
	}

	@Override
	public void dropUsersTable() {
		jdbcTemplate.execute(UserSql.DROP_TABLE);
	}

}
