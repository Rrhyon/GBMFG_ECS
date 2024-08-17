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
import java.io.FileNotFoundException;

public class DatabaseUtil {

    // Constants for property file and database connection details
    private static String propFileName = "resources/db.properties";
    private static String dbUrl;
    private static String dbUsername;
    private static String dbPassword;
    private static String dbDriverClassName;

    static {
        try (InputStream input = DatabaseUtil.class.getClassLoader().getResourceAsStream(propFileName)) {
            
            if (input == null) {
                throw new FileNotFoundException("Property file '" + propFileName + "' not found in the classpath");
            }

            Properties prop = new Properties();
            prop.load(input);

            dbUrl = prop.getProperty("db.url");
            dbUsername = prop.getProperty("db.username");
            dbPassword = prop.getProperty("db.password");
            dbDriverClassName = prop.getProperty("db.driverClassName");

            if (dbDriverClassName == null) {
                throw new ClassNotFoundException("Database driver class name is missing from the properties file.");
            }

            // Load the database driver class
            Class.forName(dbDriverClassName);

        } catch (FileNotFoundException ex) {
            System.err.println("Error: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println("Error: Database Driver not found - " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Method to get a connection to the database using the loaded properties
public static Connection getConnection() throws SQLException {
    // Debugging statement to indicate the start of the connection attempt
    System.out.println("Attempting to connect to the database...");

    // Check if the connection details are properly set
    if (dbUrl == null || dbUsername == null || dbPassword == null) {
        throw new SQLException("Database connection details are not set.");
    }

    // Debugging statements to print the connection details
    System.out.println("Database URL: " + dbUrl);
    System.out.println("Database Username: " + dbUsername);
    // Note: You may want to avoid printing the password for security reasons

    // Attempt to establish a connection to the database
    Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

    // Debugging statement to confirm a successful connection
    System.out.println("Connected to the database successfully.");

    // Return the established connection
    return conn;
}

}
