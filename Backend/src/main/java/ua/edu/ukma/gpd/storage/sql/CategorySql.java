package ua.edu.ukma.gpd.storage.sql;

public class CategorySql {

    public static final String INSERT =
            "INSERT INTO categories (name) VALUES (?)";

    public static final String FIND_BY_NAME =
            "SELECT * FROM categories WHERE name = (?)";

}
