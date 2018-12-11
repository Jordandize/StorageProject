package ua.edu.ukma.gpd.storage.sql;

public class OrderSql {

    //deleted id_keeper insertion
    public static final String INSERT =
            "INSERT INTO orders (id_parent, order_statuses, id_order_type, created, changed, annotation, archived, id_user) " +
            		"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String FIND_ALL =
            "SELECT * FROM orders";

    public static final String FIND_BY_ID =
            "SELECT * FROM orders WHERE id = ?";

    public static final String FIND_ORDERS_FOR_USER =
            "SELECT * FROM orders WHERE id_user = ?";

    public static final String ASSIGN_KEEPER_TO_ORDER =
            "UPDATE orders " +
                    "SET id_keeper = ? " +
                    "WHERE id = ?";

    public static final String FIND_UNASIGNED_ORDERS =
            "SELECT * \n" +
                    "FROM orders\n" +
                    "WHERE id_keeper is null\n";

    public static final String DECLINE_ORDER =
            "UPDATE orders " +
                    "SET id_order_status = ? " +
                    "WHERE id = ?";

    
    public static final String FIND_ORDERS_FOR_KEEPER_BY_STATUS =
    		"SELECT * " +
    			"FROM orders " +
    			"WHERE id_keeper = ? " +
    			"AND (order_statuses = ? OR order_statuses = ?)";
}
