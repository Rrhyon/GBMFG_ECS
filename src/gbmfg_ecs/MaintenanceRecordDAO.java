package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceRecordDAO {

    public String addMaintenanceRecord(MaintenanceRecord record) {
        String sql = "INSERT INTO maintenance_record (toolId, empId, maintDate, "
                + "maintDesc, maintStatus) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, record.getToolId());
            stmt.setInt(2, record.getEmpId());
            stmt.setTimestamp(3, Timestamp.valueOf(record.getMaintenanceDate()));
            stmt.setString(4, record.getDescription());
            stmt.setString(5, record.getStatus());
            stmt.executeUpdate();
            return "Maintenance record added successfully.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error adding maintenance record.";
        }
    }

    public String removeMaintenanceRecord(int maintRecordId) {
        String sql = "DELETE FROM maintenance_record WHERE maintRecordId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maintRecordId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return "Maintenance record removed successfully.";
            } else {
                return "Maintenance record not found.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error removing maintenance record.";
        }
    }

    public MaintenanceRecord getMaintenanceRecord(int maintRecordId) {
        String sql = "SELECT * FROM maintenance_record WHERE maintRecordId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, maintRecordId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                MaintenanceRecord record = new MaintenanceRecord(
                        rs.getInt("toolId"),
                        rs.getInt("empId"),
                        rs.getTimestamp("maintDate").toLocalDateTime(),
                        rs.getString("maintDesc"),
                        rs.getString("maintStatus")
                );
                record.setRecordId(rs.getInt("maintRecordId"));
                return record;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String updateMaintenanceRecord(MaintenanceRecord record) {
        String sql = "UPDATE maintenance_record SET toolId = ?, empId = ?, "
                + "maintDate = ?, maintDesc = ?, maintStatus = ? "
                + "WHERE maintRecordId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, record.getToolId());
            stmt.setInt(2, record.getEmpId());
            stmt.setTimestamp(3, Timestamp.valueOf(record.getMaintenanceDate()));
            stmt.setString(4, record.getDescription());
            stmt.setString(5, record.getStatus());
            stmt.setInt(6, record.getRecordId());
            stmt.executeUpdate();
            return "Maintenance record updated successfully.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error updating maintenance record.";
        }
    }

    public List<MaintenanceRecord> getAllMaintenanceRecords() {
        String sql = "SELECT * FROM maintenance_record";
        List<MaintenanceRecord> records = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MaintenanceRecord record = new MaintenanceRecord(
                        rs.getInt("toolId"),
                        rs.getInt("empId"),
                        rs.getTimestamp("maintDate").toLocalDateTime(),
                        rs.getString("maintDesc"),
                        rs.getString("maintStatus")
                );
                record.setRecordId(rs.getInt("maintRecordId"));
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }
}
