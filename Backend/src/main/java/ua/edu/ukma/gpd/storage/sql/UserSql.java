package ua.edu.ukma.gpd.storage.sql;

public class UserSql {
	
	public static final String INSERT = 
		"INSERT INTO users (email, password, name, surname, phone) "
		+ "values (?, ?, ?, ?, ?)";
	
	public static final String UPDATE =
		"UPDATE users"
		+ "SET email = ?, password = ?, name = ?, surname = ?, phone = ?"
		+ "WHERE id = ?";
	
	public static final String DELETE =
		"DELETE FROM users WHERE id = ?";
	
	public static final String FIND_ALL =
		"SELECT * FROM users";

	public static final String FIND_BY_ID =
		"SELECT * FROM users WHERE id = ?";
	
	public static final String FIND_BY_EMAIL =
		"SELECT * FROM users WHERE email = ?";
	
	public static final String CREATE_TABLE = 
		"CREATE TABLE IF NOT EXISTS users ("
		+ "id BIGSERIAL NOT NULL,"
		+ "email VARCHAR(255) NOT NULL,"
		+ "password VARCHAR(60) NOT NULL,"
		+ "name VARCHAR(255) NOT NULL,"
		+ "surname VARCHAR(255) NOT NULL,"
		+ "phone VARCHAR(15) NOT NULL,"
		+ "UNIQUE (email),"
		+ "PRIMARY KEY (id)"
		+ ")";
	
	public static final String DROP_TABLE =
		"DROP TABLE IF EXISTS users CASCADE";
	
	private UserSql() { }

}
