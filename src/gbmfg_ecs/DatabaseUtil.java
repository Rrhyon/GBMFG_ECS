package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Handles connections to the DB.
 * Date: August 13, 2024
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class DatabaseUtil {

    private static String dbUrl;
    private static String dbUsername;
    private static String dbPassword;
    private static String dbDriverClassName;

    static {
        try (InputStream input = DatabaseUtil.class.getClassLoader().getResourceAsStream("db.properties")) {

            if (input == null) {
                System.err.println("Sorry, unable to find db.properties");
                // Option 1: Terminate the program
                System.exit(1);

                // Option 2: Set the connection parameters to null and handle later
                dbUrl = null;
                dbUsername = null;
                dbPassword = null;
                dbDriverClassName = null;
            } else {
                Properties prop = new Properties();
                prop.load(input);

                dbUrl = prop.getProperty("db.url");
                dbUsername = prop.getProperty("db.username");
                dbPassword = prop.getProperty("db.password");
                dbDriverClassName = prop.getProperty("db.driverClassName");

                if (dbDriverClassName != null) {
                    Class.forName(dbDriverClassName);
                } else {
                    System.err.println("jdbcDriverClassName is null");
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if (dbUrl == null || dbUsername == null || dbPassword == null) {
            throw new SQLException("Database connection details are not properly set.");
        }
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }
}
