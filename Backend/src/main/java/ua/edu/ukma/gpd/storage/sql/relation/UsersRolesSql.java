package ua.edu.ukma.gpd.storage.sql.relation;

public class UsersRolesSql {
	
	public static final String INSERT =
			"INSERT INTO users_roles (id_user, id_role) values (?,?)";
	
	public static final String FIND_BY_ID_USER =
			"SELECT * FROM users_roles WHERE id_user = ?";
	
	public static final String FIND_BY_ID_ROLE =
			"SELECT * FROM users_roles WHERE id_role = ?";
	
	public static final String CREATE_TABLE =
			"CREATE TABLE IF NOT EXISTS users_roles ("
			+ "id_user BIGSERIAL NOT NULL,"
			+ "id_role SMALLINT NOT NULL,"
			+ "PRIMARY KEY (id_user, id_role)"
			+ ")";
	
	public static final String DROP_TABLE =
			"DROP TABLE IF EXISTS users_roles";
	
	private UsersRolesSql() { }

}
