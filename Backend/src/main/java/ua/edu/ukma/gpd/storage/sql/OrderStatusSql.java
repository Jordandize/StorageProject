package ua.edu.ukma.gpd.storage.sql;

public class OrderStatusSql {

	// unused code
    public static final String INSERT =
            "INSERT INTO order_statuses (name) VALUES (?)";
    
    public static final String UPDATE =
			"UPDATE order_statuses "
			+ "SET name = ?"
			+ "WHERE id = ?";
    
	public static final String UPDATE_ORDER_STATUS =
			"UPDATE orders "
			+ "SET id_status = ?"
			+ "WHERE id = ?";

	public static final String DELETE =
		"DELETE FROM order_statuses WHERE id = ?";
	
	public static final String FIND_ALL =
			"SELECT * FROM order_statuses";
	
	public static final String FIND_BY_ID =
			"SELECT * FROM order_statuses WHERE id = ?";
	
	public static final String FIND_BY_NAME =
			"SELECT * FROM order_statuses WHERE name = ?";

	
}
