package ua.edu.ukma.gpd.storage.sql;

public class RoleSql {
	
	public static final String INSERT =
			"INSERT INTO roles (id, name) VALUES (?,?)";
	
	public static final String FIND_ALL =
			"SELECT * FROM roles";
	
	public static final String FIND_BY_ID =
			"SELECT * FROM roles WHERE id = ?";
	
	public static final String FIND_BY_NAME =
			"SELECT * FROM roles WHERE name = ?";

}
