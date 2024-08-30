package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Intermediary class to pass object information to the DAO.
 * Date: August 13, 2024
 */
import java.time.LocalDateTime;
import java.util.List;

public class CheckoutTransactionService {

    private final CheckoutTransactionDAO transactionDAO;

    public CheckoutTransactionService() {
        this.transactionDAO = new CheckoutTransactionDAO();
    }

    public boolean addCheckoutTransaction(int empId, int toolId, LocalDateTime checkoutDate, LocalDateTime dueDate) {
        CheckoutTransaction transaction = new CheckoutTransaction(empId, toolId, checkoutDate, dueDate, null, "Checked Out");
        return transactionDAO.addCheckoutTransaction(transaction);
    }

    public boolean updateCheckoutTransaction(CheckoutTransaction transaction) {
        return transactionDAO.updateCheckoutTransaction(transaction);
    }

    public CheckoutTransaction getCheckoutTransaction(int transactionId) {
        return transactionDAO.getCheckoutTransaction(transactionId);
    }

    public List<CheckoutTransaction> getAllCheckoutTransactions() {
        return transactionDAO.getAllCheckoutTransactions();
    }

    public boolean removeCheckoutTransaction(int transactionId) {
        return transactionDAO.removeCheckoutTransaction(transactionId);
    }
}