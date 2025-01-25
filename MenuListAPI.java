
// MenuListAPI.java

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuListAPI {
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/filtertofork";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    public static void main(String[] args) {
        // Placeholder for main method
    }

    // Method to retrieve a list of menu items
    public List<String> getMenuItems(String restaurantId) {
        List<String> menuItems = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT item_name FROM menu WHERE restaurant_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, restaurantId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                menuItems.add(resultSet.getString("item_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItems;
    }
}
