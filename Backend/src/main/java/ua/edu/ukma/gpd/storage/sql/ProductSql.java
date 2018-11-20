package ua.edu.ukma.gpd.storage.sql;

public class ProductSql {

    public static final String INSERT =
            "INSERT INTO products (name, amount, active, description, image, icon, id_category) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static final String UPDATE =
            "UPDATE products "
            + "SET name = ?, amount = ?, active = ?, description = ?, image = ?, icon = ?, id_category = ? "
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
