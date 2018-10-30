package ua.edu.ukma.gpd.storage.sql;

public class RoleSql {
	
	public static final String INSERT =
			"INSERT INTO roles (id, name) values (?,?)";
	
	public static final String FIND_ALL =
			"SELECT * FROM roles";
	
	public static final String FIND_BY_ID =
			"SELECT * FROM roles WHERE id = ?";
	
	public static final String FIND_BY_NAME =
			"SELECT * FROM roles WHERE name = ?";
	
	public static final String CREATE_TABLE =
			"CREATE TABLE IF NOT EXISTS roles ("
			+ "id SMALLINT NOT NULL UNIQUE,"
			+ "name VARCHAR(16) NOT NULL UNIQUE,"
			+ "PRIMARY KEY (id)"
			+ ")";
	
	public static final String DROP_TABLE =
			"DROP TABLE IF EXISTS roles";
	
	private RoleSql() { }

}
