package ua.edu.ukma.gpd.storage.query;

public class UserQuery {
	
	public static final String FIND_ALL =
		"SELECT * FROM users";

	public static final String FIND_BY_ID =
		"SELECT * FROM users WHERE id = ?";
	
	public static final String FIND_BY_EMAIL =
		"SELECT * FROM users WHERE email = ?";
	
	public static final String ADD_USER = 
		"INSERT INTO users (email, password, name, surname) "
		+ "values (?, ?, ?, ?, ?)";
	
	public static final String UPDATE_USER =
		"UPDATE users"
		+ "SET email = ?, password = ?, name = ?, surname = ?"
		+ "WHERE id = ?";
	
	public static final String CREATE_USERS_TABLE = 
		"CREATE TABLE IF NOT EXISTS users ("
		+ "id BIGINT NOT NULL AUTO_INCREMENT,"
		+ "email VARCHAR(255) NOT NULL,"
		+ "password VARCHAR(60) NOT NULL,"
		+ "name VARCHAR(255) NOT NULL,"
		+ "surname VARCHAR(255) NOT NULL,"
		+ "UNIQUE (email),"
		+ "PRIMARY KEY (id)"
		+ ")";
	
	public static final String DELETE_USERS_TABLE =
		"DROP TABLE IF EXISTS users CASCADE";
	
	private UserQuery() { }

}
