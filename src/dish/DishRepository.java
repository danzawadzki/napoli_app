package dish;

import database.databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DishRepository {
    /**
     * Method inserting new record to dish database.
     *
     * @param name        - dish name
     * @param description - dish description
     * @param price       - dish price
     * @param components  - dish component products as comma separated string (example: "eggs, milk, flour, salt")
     */
    public static Dish addDish(String name, String description, Double price, String components) {

        String sql = "INSERT INTO dish (name, description, price, components) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = databaseConnection.getConnection();

            //Preparing SQL statement and executing
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setDouble(3, price);
            stmt.setString(4, components);
            stmt.execute();

            //Closing connection
            conn.close();

            //Returning instance of address class
            return new Dish(name, description, price, components);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method selecting dish from database and returning local instance.
     *
     * @param id
     */
    public static Dish getDish(int id) {
        String name = null, description = null, componentsComma = null;
        double price = 0;

        try {
            //Creating connection and executing select all reservations
            Connection conn = databaseConnection.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM dish WHERE id = " + id);

            //Initializing variables
            while (rs.next()) {
                id = rs.getInt("id");
                name = rs.getString("name");
                description = rs.getString("description");
                componentsComma = rs.getString("components");
                price = rs.getDouble("price");
            }

            //Closing connection
            conn.close();

            if (Dish.getDishHashMap().get(id) == null) {
                //Returning address instance
                return new Dish(id, name, description, price, componentsComma);
            } else {
                return Dish.getDishHashMap().get(id);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
