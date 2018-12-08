package ua.edu.ukma.gpd.storage.sql;

public class UserSql {

    public static final String INSERT =
            "INSERT INTO users (email, password, name, surname, phone, active) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

    public static final String UPDATE =
            "UPDATE users "
                    + "SET email = ?, password = ?, name = ?, surname = ?, phone = ?, active = ? "
                    + "WHERE id = ?";

    public static final String DELETE =
            "DELETE FROM users WHERE id = ?";

    public static final String FIND_ALL =
            "SELECT * FROM users";

    public static final String FIND_BY_ID =
            "SELECT * FROM users WHERE id = ?";

    public static final String FIND_BY_EMAIL =
            "SELECT * FROM users WHERE email = ?";

    public static final String FIND_ACTIVE_KEEPERS =
            "SELECT * \n" +
                    "FROM (users INNER JOIN users_roles ON users.id = users_roles.id_user) X INNER JOIN roles ON X.id_role = roles.id\n" +
                    "WHERE roles.name = 'KEEPER'\n" +
                    "AND\n" +
                    "active='true'";

    private UserSql() {
    }

}
