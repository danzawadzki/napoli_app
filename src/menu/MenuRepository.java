package menu;

import database.databaseConnection;
import dish.Dish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Menu class data access object
 *
 * @author Daniel Zawadzki, s9482
 * @version 1.0
 */
public class MenuRepository {
    /**
     * Method inserting new record to menu database.
     *
     * @param name          - menu name
     * @param description   - menu description
     * @param dishArrayList - list of dishes
     */
    public static Menu addMenu(String name, String description, ArrayList<Dish> dishArrayList) {

        String sql = "INSERT INTO menu (name, description) VALUES (?, ?)";

        try {
            Connection conn = databaseConnection.getConnection();

            //Preparing SQL statement and executing
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.execute();

            //Closing connection
            conn.close();

            //Returning instance of address class
            Menu m = new Menu(name, description, dishArrayList);

            //Executing insert for each dish
            for (Dish d : dishArrayList) addMenuDish(m.getId(), d.getId());

            //Return m
            return m;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method inserting new record to database associating menu with dishes
     *
     * @param menuId - menu key
     * @param dishId - dish key
     */
    public static void addMenuDish(int menuId, int dishId) {
        try {
            Connection conn = databaseConnection.getConnection();

            //Preparing SQL statement and executing
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO menu_dish (menu, dish) VALUES (?, ?)");
            stmt.setInt(1, menuId);
            stmt.setInt(2, dishId);
            stmt.execute();

            //Closing connection
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
