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

    /* Method to create SQL prepared statement to create a checkout transaction
     * after entering transaction information.
     */
    public String addCheckoutTransaction(CheckoutTransaction transaction) {
        String sql = "INSERT INTO checkout_transaction (empId, toolId, "
                + "checkoutDate, dueDate, returnDate, transactionStatus) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, transaction.getEmpId());
            stmt.setInt(2, transaction.getToolId());
            stmt.setTimestamp(3, Timestamp.valueOf(transaction.
                    getCheckoutDate()));
            stmt.setTimestamp(4, Timestamp.valueOf(transaction.getDueDate()));
            stmt.setTimestamp(5, transaction.getReturnDate() != null ? 
                    Timestamp.valueOf(transaction.getReturnDate()) : null);
            stmt.setString(6, transaction.getStatus());
            stmt.executeUpdate();
            return "Checkout transaction added successfully.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error adding checkout transaction.";
        }
    }

    /* Method to create SQL prepared statement to update checkout transactions
     * after entering transaction information.
     */
    public String updateCheckoutTransaction(CheckoutTransaction transaction) {
        String sql = "UPDATE checkout_transaction SET empId = ?, toolId = ?, "
                + "checkoutDate = ?, dueDate = ?, returnDate = ?, "
                + "transactionStatus = ? WHERE transactionId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, transaction.getEmpId());
            stmt.setInt(2, transaction.getToolId());
            stmt.setTimestamp(3, Timestamp.valueOf(transaction.
                    getCheckoutDate()));
            stmt.setTimestamp(4, Timestamp.valueOf(transaction.getDueDate()));
            stmt.setTimestamp(5, transaction.getReturnDate() != null ? 
                    Timestamp.valueOf(transaction.getReturnDate()) : null);
            stmt.setString(6, transaction.getStatus());
            stmt.setInt(7, transaction.getTransactionId());
            stmt.executeUpdate();
            return "Checkout transaction updated successfully.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error updating checkout transaction.";
        }
    }

    /* Method to create SQL prepared statement to retrieve checkout transaction
     * after entering transaction ID.
     */
    public CheckoutTransaction getCheckoutTransaction(int transactionId) {
        String sql = "SELECT * FROM checkout_transaction "
                + "WHERE transactionId = ?";
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
                        rs.getTimestamp("returnDate") != null ? 
                        rs.getTimestamp("returnDate").toLocalDateTime() : null,
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

    /* Method to create SQL prepared statement to create a new ArrayList called
     * 'transactions' and add all transactions to the array.
     */
    public List<CheckoutTransaction> getAllCheckoutTransactions() {
        String sql = "SELECT * FROM checkout_transaction";
        List<CheckoutTransaction> transactions = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CheckoutTransaction transaction = new CheckoutTransaction(
                        rs.getInt("empId"),
                        rs.getInt("toolId"),
                        rs.getTimestamp("checkoutDate").toLocalDateTime(),
                        rs.getTimestamp("dueDate").toLocalDateTime(),
                        rs.getTimestamp("returnDate") != null ? 
                                rs.getTimestamp("returnDate").toLocalDateTime() 
                                : null,
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
    
    /* Method to create SQL prepared statement to remove a transaction record
     * after entering transaction ID.
     */
    public String removeCheckoutTransaction(int transactionId) {
        String sql = "DELETE FROM checkout_transaction WHERE transactionId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, transactionId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return "Checkout transaction removed successfully.";
            } else {
                return "Checkout transaction not found.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error removing checkout transaction.";
        }
    }
}
