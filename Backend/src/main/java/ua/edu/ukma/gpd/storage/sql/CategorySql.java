package ua.edu.ukma.gpd.storage.sql;

public class CategorySql {

    public static final String INSERT =
            "INSERT INTO categories (name) VALUES (?)";

    public static final String FIND_BY_ID =
            "SELECT * FROM categories WHERE id = ?";

    public static final String UPDATE =
            "UPDATE categories SET name = ? WHERE id = ?";

    public static final String DELETE =
            "DELETE FROM categories WHERE id = ?";

    public static final String FIND_BY_NAME =
            "SELECT * FROM categories WHERE name = ?";

    public static final String FIND_ALL =
            "SELECT * FROM categories";

}
