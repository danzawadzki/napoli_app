package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class databaseHelpers {

    /**
     * Method getting max id value from selected table
     *
     * @param tableName from sqlite, to execute select query
     */
    public static int getIdCounter(String tableName) {
        int idCounter = 0;
        try {
            Connection conn = databaseConnection.getConnection();

            ResultSet rs = conn.createStatement().executeQuery("SELECT MAX(id) FROM " + tableName);

            while (rs.next()) {
                idCounter = rs.getInt(1);
            }

            conn.close();


        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return idCounter - 1;
    }

}
