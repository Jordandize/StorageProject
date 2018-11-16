package ua.edu.ukma.gpd.storage.sql;

public class ProductSql {

    public static final String INSERT =
            "INSERT INTO products (name, amount, id_category, description, active)"
            + " VALUES (?, ?, ?, ?, ?)";

    public static final String UPDATE =
            "UPDATE products"
            + " SET id_category = ?, name = ?, amount = ?, description = ?, active "
            + "WHERE id = ?";

    public static final String DELETE =
            "DELETE * FROM products WHERE id = ?";

    public static final String FIND_BY_ID =
            "SELECT * FROM products WHERE id = ?";

    public static final String FIND_BY_NAME =
            "SELECT * FROM products WHERE name = ?";
    
    public static final String FIND_BY_CATEGORY =
    		"SELECT * FROM products WHERE id_category = ?";

    public static final String FIND_ALL =
            "SELECT * FROM products";
}
