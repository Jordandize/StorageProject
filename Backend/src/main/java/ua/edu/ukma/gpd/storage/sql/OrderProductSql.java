package ua.edu.ukma.gpd.storage.sql;

public class OrderProductSql {

    public static final String INSERT =
            "INSERT INTO orderProduct (productId, orderId, amount, amount_returned)"
            + " VALUES (?, ?, ?, ?)";
}
