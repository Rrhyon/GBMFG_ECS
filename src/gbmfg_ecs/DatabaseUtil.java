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

    private static String dbUrl;
    private static String dbUsername;
    private static String dbPassword;
    private static String dbDriverClassName;

    static {
        try (InputStream input = DatabaseUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find db.properties");
            }
            prop.load(input);
            dbUrl = prop.getProperty("jdbc:mysql://localhost:3306/gbmfg_ecs");
            dbUsername = prop.getProperty("root");
            dbPassword = prop.getProperty("devry123");
            dbDriverClassName = prop.getProperty("com.mysql.cj.jdbc.Driver");
            Class.forName(dbDriverClassName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
    }
}
