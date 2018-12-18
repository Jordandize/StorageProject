package ua.edu.ukma.gpd.storage.sql;

public class OrderSql {

    public static final String INSERT =
            "INSERT INTO orders (id_parent, order_statuses, id_order_type, created, changed, annotation, archived, id_user) " +
            		"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    
    public static final String UPDATE =
    		"UPDATE orders "
    		+ "SET id_parent = ?, order_statuses = ?, id_order_type = ?, created = ?, changed = ?, annotation = ?, archived = ?, id_user = ? "
    		+ "WHERE id = ?";

    public static final String DELETE =
            "DELETE FROM orders\n" +
                    "WHERE orders.id = ?";

    public static final String FIND_ALL =
            "SELECT * FROM orders";

    public static final String FIND_BY_ID =
            "SELECT * FROM orders WHERE id = ?";

    public static final String FIND_ORDERS_FOR_USER =
            "SELECT * FROM orders WHERE id_user = ? ORDER BY created DESC";

    public static final String ASSIGN_KEEPER_TO_ORDER =
            "UPDATE orders " +
                    "SET id_keeper = ?, order_statuses = ? " +
                    "WHERE id = ?";

    public static final String FIND_UNASIGNED_ORDERS =
            "SELECT * \n" +
                    "FROM orders\n" +
                    "WHERE id_keeper is null\n";

    public static final String FIND_ORDERS_FOR_KEEPER_BY_STATUS =
    		"SELECT * " +
    			"FROM orders " +
    			"WHERE id_keeper = ? AND order_statuses = ?";
    
}
