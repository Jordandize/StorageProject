package ua.edu.ukma.gpd.storage.sql;

public class ProductSql {

    public static final String INSERT =
            "INSERT INTO products (categoryId, name, amount, description, isActive)"
            + " VALUES (?, ?, ?, ?, ?)";

    public static final String UPDATE =
            "UPDATE products"
            + " SET categoryId = ?, name = ?, amount = ?, description = ?, isActive "
            + "WHERE productId = ?";

    public static final String DELETE =
            "DELETE * FROM products WHERE productId = ?";

    public static final String FIND_BY_ID =
            "SELECT * FROM products WHERE productId = ?";

    public static final String FIND_BY_NAME =
            "SELECT * FROM products WHERE name = ?";

    public static final String FIND_ALL =
            "SELECT * FROM products";
}
