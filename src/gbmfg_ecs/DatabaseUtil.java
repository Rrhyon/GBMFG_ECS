package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class DatabaseUtil {

    // constants
    private static String propFileName = "db.properties";
    private static String dbUrl;
    private static String dbUsername;
    private static String dbPassword;
    private static String dbDriverClassName;

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

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }
}
