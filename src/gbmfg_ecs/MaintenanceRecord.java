package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Provides framework for Maintenance Record object creation.
 * Date: August 13, 2024
 */
import java.time.LocalDateTime;

public class MaintenanceRecord {

    private int recordId;
    private int toolId;
    private int empId;
    private LocalDateTime maintenanceDate;
    private String description;
    private String status;

    // Constructors
    public MaintenanceRecord() {
    }

    public MaintenanceRecord(int toolId, int empId, 
            LocalDateTime maintenanceDate, String description, String status) {
        this.toolId = toolId;
        this.empId = empId;
        this.maintenanceDate = maintenanceDate;
        this.description = description;
        this.status = status;
    }

    // Getters
    public int getRecordId() {
        return recordId;
    }
    
    public int getToolId() {
        return toolId;
    }
    
    public int getEmpId() {
        return empId;
    }
    
    public LocalDateTime getMaintenanceDate() {
        return maintenanceDate;
    }
    
    public String getDescription() {
        return description;
    }
    
    public String getStatus() {
        return status;
    }   
    
    // Setters
    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public void setToolId(int toolId) {
        this.toolId = toolId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public void setMaintenanceDate(LocalDateTime maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
