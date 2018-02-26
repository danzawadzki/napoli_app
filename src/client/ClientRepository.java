package client;

import address.Address;
import address.AddressRepository;
import database.databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Guest client class (for clients making online orders with out registration)
 *
 * @author Daniel Zawadzki, s9482
 * @version 1.0
 */
public class ClientRepository {
    /**
     * Method inserting new record to client database.
     *
     * @param name         - client name
     * @param surname      - client surname
     * @param phoneNumber  - client phoneNumber
     * @param emailAddress - client email address
     * @param address      - client address
     * @return
     */
    public static Client addClient(String name, String surname, String phoneNumber, String emailAddress, Address address) {

        String sql = "INSERT INTO client (name, surname, phoneNumber, emailAddress, address) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = databaseConnection.getConnection();

            //Preparing SQL statement and executing
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, surname);
            stmt.setString(3, phoneNumber);
            stmt.setString(4, emailAddress);
            stmt.setString(5, String.valueOf(address.getId()));
            stmt.execute();

            //Closing connection
            conn.close();

            //Returning instance of address class
            return new Client(name, surname, phoneNumber, emailAddress, address);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method selecting client from database and returning local instance.
     *
     * @param id
     */
    public static Client getClient(int id) {
        String name = null;
        String surname = null;
        String phoneNumber = null;
        String emailAddress = null;
        Address address = null;

        try {
            //Creating connection and executing select client
            Connection conn = databaseConnection.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM client WHERE id = " + id);

            //Initializing variables
            while (rs.next()) {
                name = rs.getString("name");
                surname = rs.getString("surname");
                phoneNumber = rs.getString("phoneNumber");
                emailAddress = rs.getString("emailAddress");
                address = AddressRepository.getAddress(rs.getInt("address"));
            }

            //Closing connection
            conn.close();

            if (Client.getClientHashMap().get(id) == null) {
                //Returning client instance
                return new Client(id, name, surname, phoneNumber, emailAddress, address);
            } else {
                Client.getClientHashMap().get(id);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
