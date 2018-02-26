package database;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class responsible for database connection
 *
 * @author Daniel Zawadzki, s9482
 * @version 1.0
 */
public class databaseConnection {

    /**
     * Getting connection to database
     *
     * @return DriverManager connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        try {
            /** SQLlite-jdbc-3.21.0 */
            Class.forName("org.sqlite.JDBC");

            return DriverManager.getConnection("jdbc:sqlite:restaurant.sqlite");
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }
}
