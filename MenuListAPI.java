
// MenuListaimport java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuListAPI {

    public List<String> getMenuItems(String restaurantId) throws SQLException {
        List<String> menuItems = new ArrayList<>();
        String query = "SELECT item_name FROM menu WHERE restaurant_id = ?";

        try (Connection connection = DatabaseUtility.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, restaurantId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    menuItems.add(resultSet.getString("item_name"));
                }
            }
        } catch (SQLException e) {
            // Log the error and rethrow as a custom exception
            // Logger.error("Error fetching menu items", e);
            throw new MenuListAPIException("Error fetching menu items", e);
        }
        return menuItems;
    }

    // Custom exception for the MenuListAPI
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
