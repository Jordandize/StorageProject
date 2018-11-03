package ua.edu.ukma.gpd.storage.sql.relation;

public class UsersRolesSql {
	
	public static final String INSERT =
			"INSERT INTO users_roles (id_user, id_role) values (?,?)";

	public static final String DELETE_USER_ROLES =
			"DELETE FROM users_roles WHERE id_user = ?";
	
	public static final String FIND_BY_ID_USER =
			"SELECT * FROM users_roles WHERE id_user = ?";
	
	public static final String FIND_BY_ID_ROLE =
			"SELECT * FROM users_roles WHERE id_role = ?";
	
	private UsersRolesSql() { }

}
