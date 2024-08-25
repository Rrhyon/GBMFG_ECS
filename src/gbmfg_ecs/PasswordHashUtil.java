package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Password management class.
 * Date: August 13, 2024
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashUtil {

     // Ideally, use a random salt for each password
    private static final String SALT = "your-salt-value";

    // Very basic password protection providing extremely weak encryption.
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(SALT.getBytes()); // Add salt
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // During a login attempt or password update, compares an entered password's
    // hash value against what is stored in the DB.
    public static boolean checkPassword(String enteredPassword, 
            String storedPasswordHash) {
        String enteredPasswordHash = hashPassword(enteredPassword);
        return storedPasswordHash.equals(enteredPasswordHash);
    }
}
