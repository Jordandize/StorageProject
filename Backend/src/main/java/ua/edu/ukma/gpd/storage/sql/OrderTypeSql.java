package ua.edu.ukma.gpd.storage.sql;

public class OrderTypeSql {

    public static final String INSERT = 
    		"INSERT INTO order_types (name) VALUES (?)";
    
	public static final String UPDATE =
			"UPDATE order_types "
			+ "SET name = ?"
			+ "WHERE id = ?";
	
	public static final String UPDATE_ORDER_TYPE =
			"UPDATE orders "
			+ "SET id_type = ?"
			+ "WHERE id = ?";

	public static final String DELETE =
		"DELETE FROM order_types WHERE id = ?";
	
	public static final String FIND_ALL =
			"SELECT * FROM order_types";
	
	public static final String FIND_BY_ID =
			"SELECT * FROM order_types WHERE id = ?";
	
	public static final String FIND_BY_NAME =
			"SELECT * FROM order_types WHERE name = ?";
	
}
