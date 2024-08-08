package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
import java.time.LocalDateTime;

public class Session {

    private int sessionId;
    private int empId;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

    // Constructors, getters, and setters
    public Session() {
    }

    public Session(int empId, boolean isActive, LocalDateTime createdAt, LocalDateTime expiresAt) {
        this.empId = empId;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
}
