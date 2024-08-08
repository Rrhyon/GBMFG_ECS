/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gbmfg_ecs;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 *
 * @author phillip.tette
 */
@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sessionId;

    @ManyToOne
    @JoinColumn(name = "empId")
    private Employee employee;

    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

    // Constructors, getters, and setters
    public Session() {
    }

    public Session(Employee employee) {
        this.employee = employee;
        this.isActive = true;
        this.createdAt = LocalDateTime.now();
        this.expiresAt = this.createdAt.plusHours(2);
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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

    public void endSession() {
        this.isActive = false;
    }
}
