
// MenuListaimport java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuListAPI {

    public List<String> getMenuItems(String restaurantId, List<String> filters) throws SQLException {
        List<String> menuItems = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT item_name FROM menu WHERE restaurant_id = ?");

        // Append filters for dietary restrictions to the SQL query
        if (!filters.isEmpty()) {
            for (String filter : filters) {
                queryBuilder.append(" AND ").append(filter).append(" = TRUE");
            }
        }

        String query = queryBuilder.toString();

        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, restaurantId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    menuItems.add(resultSet.getString("item_name"));
                }
            }
        } catch (SQLException e) {
            // Log the error and rethrow it as a custom exception for better error management
            throw new MenuListAPIException("Error fetching menu items with dietary filters", e);
        }
        return menuItems;
    }

    // Define a custom exception for handling MenuListAPI errors
    public static class MenuListAPIException extends RuntimeException {
        public MenuListAPIException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}

class DatabaseUtility {
    private static final String DB_URL = System.getenv("DB_URL");
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}

    }
}

class DatabaseUtility {
    private static final String DB_URL = System.getenv("DB_URL");
    private static final String DB_USER = System.getenv("DB_USER");
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
