package gbmfg_ecs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette, Chandler Perry
 * Program Description: Handles connections to the DB.
 * Date: August 13, 2024
 */
public class DatabaseUtil {

    // constants
    private static final String propFileName = "db.properties";
    private static String dbUrl = null;
    private static String dbUsername = null;
    private static String dbPassword = null;
    private static String dbDriverClassName = null;

    static {
        try (InputStream input = DatabaseUtil.class.getClassLoader()
                .getResourceAsStream(propFileName)) {
            
            // debugging actions below
            if (input == null) {
                System.out.println("Sorry, unable to find db.properties");
            }

            Properties prop = new Properties();
            prop.load(input);

            dbUrl = prop.getProperty("db.url");
            dbUsername = prop.getProperty("db.username");
            dbPassword = prop.getProperty("db.password");
            dbDriverClassName = prop.getProperty("db.driverClassName");
            
            if (dbDriverClassName == null) {
                System.out.println("jdbcDriverClassName is null");
            } else {
                Class.forName(dbDriverClassName);
            }

            Class.forName(dbDriverClassName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Using the provided properties, attemps to connect to the DB.
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }
}
