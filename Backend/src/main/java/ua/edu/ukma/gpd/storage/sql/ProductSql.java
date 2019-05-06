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
            "DELETE FROM products WHERE id = ?";

    public static final String FIND_BY_ID =
            "SELECT * FROM products WHERE id = ?";

    public static final String FIND_BY_NAME =
            "SELECT * FROM products WHERE name = ?";
    
    public static final String FIND_BY_CATEGORY =
    		"SELECT * FROM products WHERE id_category = ? ORDER BY id";

    public static final String FIND_ALL =
            "SELECT * FROM products ORDER BY id"";
    
    public static final String FIND_ALL_PRESENT =
            "SELECT * FROM products WHERE amount > 0";
    
    public static final String FIND_ALL_NOT_PRESENT =
            "SELECT * FROM products WHERE amount IN (0)";
    
    public static final String FIND_ALL_ENDS =
            "SELECT * FROM products WHERE amount < ?";


}
