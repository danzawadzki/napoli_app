package reservation;

import database.databaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * Repository data object access
 *
 * @author Daniel Zawadzki, s9482
 * @version 1.0
 */
public class ReservationRespository {
    /**
     * Method to get reservations array and add to the reservations class extension
     */
    public static void getReservations() {
        try {
            //Creating connection and executing select all reservations
            Connection conn = databaseConnection.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM reservations");

            //Remove everything from class extension
            Reservation.getReservationArrayList().clear();

            //Creating reservations
            while (rs.next()) {
                //Checking if class extension contains all reservations
                if (!Reservation.checkReservationArrayList(rs.getInt(1))) {
                    new Reservation(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(4),
                            rs.getString(7),
                            rs.getInt("dispatcher")
                    );
                    Reservation.printReservationList();
                }
            }
            //Closing connection
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method inserting new reservation record to database
     *
     * @param name         - client name
     * @param surname      - client surname
     * @param date         - reservation date
     * @param hour         - reservation hour
     * @param guestsAmount - reservation guest amount
     * @param comment      - additional comment
     * @param userId       - dispatcher id
     */
    public static void addReservation(String name, String surname, TemporalAccessor date, String hour, String guestsAmount, String comment, int userId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String sql = "INSERT INTO reservations (name, surname, guestsAmount, date, hour, comment, dispatcher) VALUES (?, ?, ?, '" + formatter.format(date) + "', ?, ?, ?)";

        try {
            Connection conn = databaseConnection.getConnection();

            //Preparing SQL statement and executing
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, surname);
            stmt.setString(3, guestsAmount);
            stmt.setString(4, hour);
            stmt.setString(5, comment);
            stmt.setString(6, String.valueOf(userId));
            stmt.execute();

            //Updating reservations table and cleaning form
            new Reservation(
                    name,
                    surname,
                    formatter.format(date),
                    hour,
                    guestsAmount,
                    comment,
                    Integer.valueOf(userId));

            //Closing connection
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
