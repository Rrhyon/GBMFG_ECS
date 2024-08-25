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

public class MaintenanceRecordService {

    private MaintenanceRecordDAO maintenanceRecordDAO;

    public MaintenanceRecordService() {
        this.maintenanceRecordDAO = new MaintenanceRecordDAO();
    }

    // Creates the object and returns the object to the DAO method for DB Entry.
    public String addMaintenanceRecord(int toolId, int empId, 
            LocalDateTime maintenanceDate, String description, String status) {
        MaintenanceRecord record = new MaintenanceRecord(toolId, empId, 
                maintenanceDate, description, status);
        return maintenanceRecordDAO.addMaintenanceRecord(record);
    }
    
    /* Creates the object, retrieves the existing ID and returns the updates to
     * the DAO method for DB Entry.
     */
    public String updateMaintenanceRecord(int recordId, int toolId, int empId, 
            LocalDateTime maintenanceDate, String description, String status) {
        MaintenanceRecord record = new MaintenanceRecord(toolId, empId, 
                maintenanceDate, description, status);
        record.setRecordId(recordId);
        return maintenanceRecordDAO.updateMaintenanceRecord(record);
    }

    // Retrieves the maintenance record record by ID.
    public MaintenanceRecord getMaintenanceRecord(int recordId) {
        return maintenanceRecordDAO.getMaintenanceRecord(recordId);
    }

    // Creates a list and retrieves all available maintenance records.
    public List<MaintenanceRecord> getAllMaintenanceRecords() {
        return maintenanceRecordDAO.getAllMaintenanceRecords();
    }
    
    // Removes selected maintenance records.
    public String removeMaintenanceRecord(int recordId) {
        return maintenanceRecordDAO.removeMaintenanceRecord(recordId);
    }
}
