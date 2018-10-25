package ua.edu.ukma.gpd.storage.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.edu.ukma.gpd.storage.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@SuppressWarnings("unused")
	@Autowired
	private UserService userService;
	
	@Autowired
	private DataSource dataSource;

	@GetMapping
	public String users() {
		//Test If DB is available
		JdbcTemplate template = new JdbcTemplate(dataSource);
		Integer integer = template.query("SELECT 1", new ResultSetExtractor<Integer>() {

			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				rs.next();
				return rs.getInt(1);
			}
			
		});
		return integer.toString();
	}

}
