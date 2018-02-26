package address;

import database.databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Client address class
 *
 * @author Daniel Zawadzki, s9482
 * @version 1.0
 */
public class AddressRepository {
    /**
     * Method inserting new record to address database.
     *
     * @param streetName   - delivery street name
     * @param streetNumber - delivery street number
     * @param flat         - flat number (optional)
     * @param city         - city name
     * @param zipCode      - city code
     * @param annotation   - additional information for deliverer (entrance code, building color etc.)
     */
    public static Address addAddress(String streetName, String streetNumber, String flat, String city, String zipCode, String annotation) {

        String sql = "INSERT INTO address (streetName, streetNumber, flat, city, zipCode, annotation) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = databaseConnection.getConnection();

            //Preparing SQL statement and executing
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, streetName);
            stmt.setString(2, streetNumber);
            stmt.setString(3, flat);
            stmt.setString(4, city);
            stmt.setString(5, zipCode);
            stmt.setString(6, annotation);
            stmt.execute();

            //Closing connection
            conn.close();

            //Returning instance of address class
            return new Address(streetName, streetNumber, flat, city, zipCode, annotation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method selecting address from database and returning local instance.
     *
     * @param id
     */
    public static Address getAddress(int id) {
        String streetName = null, streetNumber = null, flat = null, city = null, zipCode = null, annotation = null;

        try {
            //Creating connection and executing select all reservations
            Connection conn = databaseConnection.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM address WHERE id = " + id);

            //Initializing variables
            while (rs.next()) {
                id = rs.getInt("id");
                streetName = rs.getString("streetName");
                streetNumber = rs.getString("streetNumber");
                flat = rs.getString("flat");
                city = rs.getString("city");
                zipCode = rs.getString("zipCode");
                annotation = rs.getString("annotation");
            }

            //Closing connection
            conn.close();

            if (Address.getAddressHashMap().get(id) == null) {
                //Returning address instance
                return new Address(id, streetName, streetNumber, flat, city, zipCode, annotation);
            } else {
                return Address.getAddressHashMap().get(id);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
