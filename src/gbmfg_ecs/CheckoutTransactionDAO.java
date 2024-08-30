package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Database Access Object for CheckoutTransaction class.
 * Date: August 13, 2024
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CheckoutTransactionDAO {

    /* Method to create SQL prepared statement to create a transaction record
     * to add to the DB after entering appropriate information.
     */
    public boolean addCheckoutTransaction(CheckoutTransaction transaction) {
        String sql = "INSERT INTO checkout_transaction (empId, toolId, "
                + "checkoutDate, dueDate, returnDate, transactionStatus) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, transaction.getEmpId());
            stmt.setInt(2, transaction.getToolId());
            stmt.setTimestamp(3, Timestamp.valueOf(transaction.getCheckoutDate()));
            stmt.setTimestamp(4, Timestamp.valueOf(transaction.getDueDate()));
            stmt.setTimestamp(5, transaction.getReturnDate() != 
                    null ? Timestamp.valueOf(transaction.getReturnDate()) : null);
            stmt.setString(6, transaction.getStatus());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* Method to create SQL prepared statement to update a transaction record
     * after entering appropriate information.
     */
    public boolean updateCheckoutTransaction(CheckoutTransaction transaction) {
        String sql = "UPDATE checkout_transaction SET empId = ?, toolId = ?, "
                + "checkoutDate = ?, dueDate = ?, returnDate = ?, "
                + "transactionStatus = ? WHERE transactionId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, transaction.getEmpId());
            stmt.setInt(2, transaction.getToolId());
            stmt.setTimestamp(3, Timestamp.valueOf(transaction.getCheckoutDate()));
            stmt.setTimestamp(4, Timestamp.valueOf(transaction.getDueDate()));
            stmt.setTimestamp(5, transaction.getReturnDate() != 
                    null ? Timestamp.valueOf(transaction.getReturnDate()) : null);
            stmt.setString(6, transaction.getStatus());
            stmt.setInt(7, transaction.getTransactionId());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* Method to create SQL prepared statement to retrieve a transaction record
     * based on transaction ID.
     */
    public CheckoutTransaction getCheckoutTransaction(int transactionId) {
        String sql = "SELECT * FROM checkout_transaction WHERE transactionId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, transactionId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                CheckoutTransaction transaction = new CheckoutTransaction(
                        rs.getInt("empId"),
                        rs.getInt("toolId"),
                        rs.getTimestamp("checkoutDate").toLocalDateTime(),
                        rs.getTimestamp("dueDate").toLocalDateTime(),
                        rs.getTimestamp("returnDate") != 
                                null ? rs.getTimestamp("returnDate").toLocalDateTime() : null,
                        rs.getString("transactionStatus")
                );
                transaction.setTransactionId(rs.getInt("transactionId"));
                return transaction;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Retrieves all checkout transactions from the database.
    public List<CheckoutTransaction> getAllCheckoutTransactions() {
        String sql = "SELECT * FROM checkout_transaction";
        List<CheckoutTransaction> transactions = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CheckoutTransaction transaction = new CheckoutTransaction(
                        rs.getInt("empId"),
                        rs.getInt("toolId"),
                        rs.getTimestamp("checkoutDate").toLocalDateTime(),
                        rs.getTimestamp("dueDate").toLocalDateTime(),
                        rs.getTimestamp("returnDate") != 
                                null ? rs.getTimestamp("returnDate").toLocalDateTime() : null,
                        rs.getString("transactionStatus")
                );
                transaction.setTransactionId(rs.getInt("transactionId"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    // Method to create SQL prepared statement to remove transactions record.
    public boolean removeCheckoutTransaction(int transactionId) {
        String sql = "DELETE FROM checkout_transaction WHERE transactionId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, transactionId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}