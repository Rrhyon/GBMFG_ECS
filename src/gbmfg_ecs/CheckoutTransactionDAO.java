package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CheckoutTransactionDAO {

    public String addCheckoutTransaction(CheckoutTransaction transaction) {
        String sql = "INSERT INTO checkout_transactions (empId, toolId, checkoutDate, dueDate, returnDate, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, transaction.getEmpId());
            stmt.setInt(2, transaction.getToolId());
            stmt.setTimestamp(3, Timestamp.valueOf(transaction.getCheckoutDate()));
            stmt.setTimestamp(4, Timestamp.valueOf(transaction.getDueDate()));
            stmt.setTimestamp(5, transaction.getReturnDate() != null ? Timestamp.valueOf(transaction.getReturnDate()) : null);
            stmt.setString(6, transaction.getStatus());
            stmt.executeUpdate();
            return "Checkout transaction added successfully.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error adding checkout transaction.";
        }
    }

    public String removeCheckoutTransaction(int transactionId) {
        String sql = "DELETE FROM checkout_transactions WHERE transactionId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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

    public CheckoutTransaction getCheckoutTransaction(int transactionId) {
        String sql = "SELECT * FROM checkout_transactions WHERE transactionId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, transactionId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                CheckoutTransaction transaction = new CheckoutTransaction(
                        rs.getInt("empId"),
                        rs.getInt("toolId"),
                        rs.getTimestamp("checkoutDate").toLocalDateTime(),
                        rs.getTimestamp("dueDate").toLocalDateTime(),
                        rs.getTimestamp("returnDate") != null ? rs.getTimestamp("returnDate").toLocalDateTime() : null,
                        rs.getString("status")
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

    public String updateCheckoutTransaction(CheckoutTransaction transaction) {
        String sql = "UPDATE checkout_transactions SET empId = ?, toolId = ?, checkoutDate = ?, dueDate = ?, returnDate = ?, status = ? WHERE transactionId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, transaction.getEmpId());
            stmt.setInt(2, transaction.getToolId());
            stmt.setTimestamp(3, Timestamp.valueOf(transaction.getCheckoutDate()));
            stmt.setTimestamp(4, Timestamp.valueOf(transaction.getDueDate()));
            stmt.setTimestamp(5, transaction.getReturnDate() != null ? Timestamp.valueOf(transaction.getReturnDate()) : null);
            stmt.setString(6, transaction.getStatus());
            stmt.setInt(7, transaction.getTransactionId());
            stmt.executeUpdate();
            return "Checkout transaction updated successfully.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error updating checkout transaction.";
        }
    }

    public List<CheckoutTransaction> getAllCheckoutTransactions() {
        String sql = "SELECT * FROM checkout_transactions";
        List<CheckoutTransaction> transactions = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CheckoutTransaction transaction = new CheckoutTransaction(
                        rs.getInt("empId"),
                        rs.getInt("toolId"),
                        rs.getTimestamp("checkoutDate").toLocalDateTime(),
                        rs.getTimestamp("dueDate").toLocalDateTime(),
                        rs.getTimestamp("returnDate") != null ? rs.getTimestamp("returnDate").toLocalDateTime() : null,
                        rs.getString("status")
                );
                transaction.setTransactionId(rs.getInt("transactionId"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
