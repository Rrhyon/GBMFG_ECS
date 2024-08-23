package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Provides framework for Checkout Transaction object 
 * creation.
 * Date: August 13, 2024
 */
import java.time.LocalDateTime;

public class CheckoutTransaction {

    private int transactionId;
    private int empId;
    private int toolId;
    private LocalDateTime checkoutDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;
    private String status;

    // Constructors
    public CheckoutTransaction() {
        
    }

    public CheckoutTransaction(int empId, int toolId, 
            LocalDateTime checkoutDate, LocalDateTime dueDate, 
            LocalDateTime returnDate, String status) {
        this.empId = empId;
        this.toolId = toolId;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
    }


    // Getters
    public int getTransactionId() {
        return transactionId;
    }

    public int getEmpId() {
    return empId;
    }
    
    public int getToolId() {
    return toolId;
    }

    public LocalDateTime getCheckoutDate() {
    return checkoutDate;
    }
    
    public LocalDateTime getDueDate() {
    return dueDate;
    }
    
    public LocalDateTime getReturnDate() {
    return returnDate;
    }
    
    public String getStatus() {
    return status;
    }
    
    // Setters
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public void setToolId(int toolId) {
        this.toolId = toolId;
    }

    public void setCheckoutDate(LocalDateTime checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
