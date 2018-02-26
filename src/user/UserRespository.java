package user;

import admin.Admin;
import database.databaseConnection;
import deliverer.Deliverer;
import dispatcher.Dispatcher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User data object access
 *
 * @author Daniel Zawadzki, s9482
 * @version 1.0
 */
public class UserRespository {

    /**
     * Method getting all users from database and creating local instances
     */
    public static void getUsers() {
        try {
            //Creating connection
            Connection conn = databaseConnection.getConnection();

            //Selecting all users
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM users");

            //Creating instances of objects basing on user type
            while (rs.next()) {
                switch (rs.getString("type")) {
                    case "Dispatcher":
                        new Dispatcher(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("surname"),
                                rs.getString("pesel"),
                                rs.getString("emailAddress"),
                                rs.getString("phoneNumber")
                        );
                        break;
                    case "Admin":
                        new Admin(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("surname"),
                                rs.getString("pesel"),
                                rs.getString("emailAddress"),
                                rs.getString("phoneNumber")
                        );
                        break;
                    case "Deliverer":
                        new Deliverer(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("surname"),
                                rs.getString("pesel"),
                                rs.getString("emailAddress"),
                                rs.getString("phoneNumber")
                        );
                        break;
                    default:
                        break;
                }
            }

            //Closing connection
            conn.close();

            //Helper method to check users list
            User.printUserHashMap();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

//    /**
//     * Method inserting new record to user database.
//     *
//     * @param name         - client name
//     * @param surname      - client surname
//     * @param phoneNumber  - client phoneNumber
//     * @param emailAddress - client email address
//     * @param address      - client address
//     * @return
//     */
//    public static Client addUser(String name, String surname, String emailAddress, String password, String pesel, String phoneNumber, String type) {
//
//        String sql = "INSERT INTO user (name, surname, emailAddress, password, pesel, phoneNumber, type) VALUES (?, ?, ?, ?, ?, ?, ?)";
//
//        try {
//            Connection conn = databaseConnection.getConnection();
//
//            //Preparing SQL statement and executing
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            stmt.setString(1, name);
//            stmt.setString(2, surname);
//            stmt.setString(3, emailAddress);
//            stmt.setString(4, password);
//            stmt.setString(5, pesel);
//            stmt.setString(6, phoneNumber);
//            stmt.setString(7, type);
//            stmt.execute();
//
//            //Closing connection
//            conn.close();
//
//            //Returning instance of user class
//            switch (type) {
//                case "Dispatcher":
//                    return new Dispatcher(name, surname, pesel, emailAddress, phoneNumber);
//                break;
//                case "Admin":
//                    return new Admin(
//                            rs.getInt("id"),
//                            rs.getString("name"),
//                            rs.getString("surname"),
//                            rs.getString("pesel"),
//                            rs.getString("emailAddress"),
//                            rs.getString("phoneNumber")
//                    );
//                break;
//                case "Deliverer":
//                    return new Deliverer(
//                            rs.getInt("id"),
//                            rs.getString("name"),
//                            rs.getString("surname"),
//                            rs.getString("pesel"),
//                            rs.getString("emailAddress"),
//                            rs.getString("phoneNumber")
//                    );
//                break;
//                default:
//                    return null;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
