package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette, Chandler Perry
 * Program Description: Provides framework for session object creation.
 * Date: August 13, 2024
 */
import java.time.LocalDateTime;

public class Session {

    private int sessionId;
    private int empId;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

    // Constructors
    public Session() {
    }

    public Session(int empId, boolean isActive, LocalDateTime createdAt,
            LocalDateTime expiresAt) {
        this.empId = empId;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

    // Getters
    public int getSessionId() {
        return sessionId;
    }
    
    public int getEmpId() {
        return empId;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }
    
    // Setters
    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
}
