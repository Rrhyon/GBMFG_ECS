package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
import java.time.LocalDateTime;
import java.util.List;

public class CheckoutTransactionService {

    private CheckoutTransactionDAO checkoutTransactionDAO;

    public CheckoutTransactionService() {
        this.checkoutTransactionDAO = new CheckoutTransactionDAO();
    }

    public String addCheckoutTransaction(int empId, int toolId, LocalDateTime checkoutDate, LocalDateTime dueDate, LocalDateTime returnDate, String status) {
        CheckoutTransaction transaction = new CheckoutTransaction(empId, toolId, checkoutDate, dueDate, returnDate, status);
        return checkoutTransactionDAO.addCheckoutTransaction(transaction);
    }

    public String removeCheckoutTransaction(int transactionId) {
        return checkoutTransactionDAO.removeCheckoutTransaction(transactionId);
    }

    public CheckoutTransaction getCheckoutTransaction(int transactionId) {
        return checkoutTransactionDAO.getCheckoutTransaction(transactionId);
    }

    public String updateCheckoutTransaction(int transactionId, int empId, int toolId, LocalDateTime checkoutDate, LocalDateTime dueDate, LocalDateTime returnDate, String status) {
        CheckoutTransaction transaction = new CheckoutTransaction(empId, toolId, checkoutDate, dueDate, returnDate, status);
        transaction.setTransactionId(transactionId);
        return checkoutTransactionDAO.updateCheckoutTransaction(transaction);
    }

    public List<CheckoutTransaction> getAllCheckoutTransactions() {
        return checkoutTransactionDAO.getAllCheckoutTransactions();
    }
}
