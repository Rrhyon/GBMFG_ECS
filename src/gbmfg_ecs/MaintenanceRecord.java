package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
import java.time.LocalDateTime;

public class MaintenanceRecord {

    private int recordId;
    private int toolId;
    private int empId;
    private LocalDateTime maintenanceDate;
    private String description;
    private String status;

    // Constructors, getters, and setters
    public MaintenanceRecord() {
    }

    public MaintenanceRecord(int toolId, int empId, LocalDateTime maintenanceDate, String description, String status) {
        this.toolId = toolId;
        this.empId = empId;
        this.maintenanceDate = maintenanceDate;
        this.description = description;
        this.status = status;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getToolId() {
        return toolId;
    }

    public void setToolId(int toolId) {
        this.toolId = toolId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public LocalDateTime getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(LocalDateTime maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
