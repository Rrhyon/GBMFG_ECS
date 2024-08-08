package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */

import java.time.LocalDateTime;
import java.util.List;

public class MaintenanceRecordService {

    private MaintenanceRecordDAO maintenanceRecordDAO;

    public MaintenanceRecordService() {
        this.maintenanceRecordDAO = new MaintenanceRecordDAO();
    }

    public String addMaintenanceRecord(int toolId, int empId, LocalDateTime maintenanceDate, String description, String status) {
        MaintenanceRecord record = new MaintenanceRecord(toolId, empId, maintenanceDate, description, status);
        return maintenanceRecordDAO.addMaintenanceRecord(record);
    }

    public String removeMaintenanceRecord(int recordId) {
        return maintenanceRecordDAO.removeMaintenanceRecord(recordId);
    }

    public MaintenanceRecord getMaintenanceRecord(int recordId) {
        return maintenanceRecordDAO.getMaintenanceRecord(recordId);
    }

    public String updateMaintenanceRecord(int recordId, int toolId, int empId, LocalDateTime maintenanceDate, String description, String status) {
        MaintenanceRecord record = new MaintenanceRecord(toolId, empId, maintenanceDate, description, status);
        record.setRecordId(recordId);
        return maintenanceRecordDAO.updateMaintenanceRecord(record);
    }

    public List<MaintenanceRecord> getAllMaintenanceRecords() {
        return maintenanceRecordDAO.getAllMaintenanceRecords();
    }
}
