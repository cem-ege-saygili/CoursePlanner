package DB_Utilities;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author sqlitetutorial.net
 */
public class CreateDB {

    public static void createNewDatabase(String dbName) {

        String url = "JDBC:sqlite:outputs/" + dbName + ".db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        createNewDatabase("JDBC:sqlite:CoursePlannerDB2.db");
//    }
}