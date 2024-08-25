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

    private CheckoutTransactionDAO checkoutTransactionDAO;

    public CheckoutTransactionService() {
        this.checkoutTransactionDAO = new CheckoutTransactionDAO();
    }

    // Creates the object and returns the object to the DAO method for DB Entry.
    public String addCheckoutTransaction(int empId, int toolId, 
            LocalDateTime checkoutDate, LocalDateTime dueDate, 
            LocalDateTime returnDate, String status) {
        CheckoutTransaction transaction = new CheckoutTransaction(empId, toolId,
                checkoutDate, dueDate, returnDate, status);
        return checkoutTransactionDAO.addCheckoutTransaction(transaction);
    }
    
    /* Creates the object, retrieves the existing ID and returns the updates to
     * the DAO method for DB Entry.
     */
    public String updateCheckoutTransaction(int transactionId, int empId, 
            int toolId, LocalDateTime checkoutDate, LocalDateTime dueDate, 
            LocalDateTime returnDate, String status) {
        CheckoutTransaction transaction = new CheckoutTransaction(empId, toolId,
                checkoutDate, dueDate, returnDate, status);
        transaction.setTransactionId(transactionId);
        return checkoutTransactionDAO.updateCheckoutTransaction(transaction);
    }

    // Retrieves the transaction record by ID.
    public CheckoutTransaction getCheckoutTransaction(int transactionId) {
        return checkoutTransactionDAO.getCheckoutTransaction(transactionId);
    }

    // Creates a list and retrieves all available transactions.
    public List<CheckoutTransaction> getAllCheckoutTransactions() {
        return checkoutTransactionDAO.getAllCheckoutTransactions();
    }
    
    // Removes selected transactions.
    public String removeCheckoutTransaction(int transactionId) {
        return checkoutTransactionDAO.removeCheckoutTransaction(transactionId);
    }
}
