package ua.edu.ukma.gpd.storage.sql;

public class OrderProductSql {

    public static final String INSERT =
            "INSERT INTO orders_products (productId, orderId, amount, amount_returned)"
            + " VALUES (?, ?, ?, ?)";

    public static final String FIND_BY_ID =
            "SELECT * FROM orders_products WHERE id_order = ? AND id_product = ?";

    public static final String FIND_ALL =
            "SELECT * FROM orders_products";
}
