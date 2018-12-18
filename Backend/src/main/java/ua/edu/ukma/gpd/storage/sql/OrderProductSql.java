package ua.edu.ukma.gpd.storage.sql;

public class OrderProductSql {

    public static final String INSERT =
            "INSERT INTO orders_products (id_order, id_product, amount, amount_returned) " +
            "VALUES (?, ?, ?, ?)";

    public static final String FIND_BY_ID =
            "SELECT * FROM orders_products WHERE id_order = ? AND id_product = ?";

    public static final String FIND_ALL =
            "SELECT * FROM orders_products";
    
    public static final String FIND_BY_ORDER =
            "SELECT * FROM orders_products WHERE id_order = ?";
    
    public static final String FIND_SHORTAGE =
    		"SELECT id_product id, (osps.amount - ps.amount) shortage, name " +
    		"FROM orders_products osps INNER JOIN products ps ON osps.id_product = ps.id " +
    		"WHERE osps.id_order = ? AND osps.amount > ps.amount";
    
    public static final String RESERVE_PRODUCTS_FOR_ORDER =
    		"UPDATE products " +
    		"SET reserved = reserved + oop.qty, amount = amount - oop.qty " +
    		"FROM (SELECT op.id_order, op.id_product, op.amount qty " +
    		 	  "FROM orders_products op INNER JOIN products p ON op.id_product = p.id " +
    			  "WHERE op.id_order = ?) oop " +
    		"WHERE id = oop.id_product";
    
    public static final String WRITE_OFF_RESERVED_PRODUCTS_FOR_ORDER =
    		"UPDATE products " +
    		"SET reserved = reserved - oop.qty " +
    		"FROM (SELECT op.id_order, op.id_product, op.amount qty " +
    		 	  "FROM orders_products op INNER JOIN products p ON op.id_product = p.id " +
    			  "WHERE op.id_order = ?) oop " +
    		"WHERE id = oop.id_product";
    
}
