# MenuList
API for listing menu items available in a restaurant.

## Overview
The `MenuListAPI` is designed to list menu items available in a restaurant, with the added functionality of filtering based on specific dietary restrictions.

## Features
- **Dynamic Filtering**: Allows filtering menu items based on dietary restrictions such as gluten-free, nut-free, vegan, etc.
- **Database Utility Class**: Manages database connections efficiently.
- **Custom Exception Handling**: Provides structured error handling with custom exceptions for better error management.

## Usage
- **Method**: `getMenuItems(String restaurantId, List<String> filters)`
  - **Parameters**:
    - `restaurantId`: The ID of the restaurant whose menu items are to be listed.
    - `filters`: A list of dietary restrictions to filter the menu items.
  - **Returns**: A list of menu item names that match the specified filters.
  - **Throws**: `MenuListAPIException` if an error occurs during database access or query execution.

## Example
```java
MenuListAPI api = new MenuListAPI();
List<String> filters = Arrays.asList("gluten_free", "vegan");
try {
    List<String> menuItems = api.getMenuItems("restaurant123", filters);
    menuItems.forEach(System.out::println);
} catch (MenuListAPI.MenuListAPIException e) {
    System.err.println("Error: " + e.getMessage());
}
```

## Database Schema
Ensure your database schema includes boolean columns for each dietary restriction in the `menu` table (e.g., `gluten_free`, `nut_free`).

## Improvements
- **Security**: Database credentials are managed through environment variables for enhanced security.
- **Maintainability**: Centralized database connection management and dynamic query building for better code maintainability.
- **Error Handling**: Improved error handling with custom exceptions and logging.
