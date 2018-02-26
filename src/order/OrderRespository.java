package order;

import client.ClientRepository;
import database.databaseConnection;
import dish.Dish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Order data access object
 *
 * @author Daniel Zawadzki, s9482
 * @version 1.0
 */
public class OrderRespository {
    /**
     * Method inserting new record to order database.
     *
     * @param client        - client id
     * @param placementDate - order placement date
     * @param deliveryHour  - delivery hour
     * @param dishArrayList - list of ordered dishes
     * @param annotation    - order annotation
     */
    public static Order addOrder(int client, String placementDate, String deliveryHour, ArrayList<Dish> dishArrayList, String annotation) {

        String sql = "INSERT INTO order (client, placementDate, deliveryHour, annotation) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = databaseConnection.getConnection();

            //Preparing SQL statement and executing
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, client);
            stmt.setString(2, placementDate);
            stmt.setString(3, deliveryHour);
            stmt.setString(4, annotation);
            stmt.execute();

            //Closing connection
            conn.close();

            //Returning instance of address class
            Order o = new Order(ClientRepository.getClient(client), dishArrayList, placementDate, deliveryHour, annotation);

            //Executing insert for each dish
            for (Dish d : dishArrayList) addOrderDish(o.getId(), d.getId());

            //Return m
            return o;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method inserting new record to database associating order with dishes
     *
     * @param orderId - order key
     * @param dishId  - dish key
     */
    public static void addOrderDish(int orderId, int dishId) {
        try {
            Connection conn = databaseConnection.getConnection();

            //Preparing SQL statement and executing
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO order_dish (order, dish) VALUES (?, ?)");
            stmt.setInt(1, orderId);
            stmt.setInt(2, dishId);
            stmt.execute();

            //Closing connection
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
