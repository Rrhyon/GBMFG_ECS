package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Database Access Object for Report class.
 * Date: August 13, 2024
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {

    /* Method to create SQL prepared statement to create a reports
     * after entering report information.
     */
    public String addReport(Report report) {
        String sql = "INSERT INTO reports (title, content, createdDate, "
                + "reportType) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, report.getTitle());
            stmt.setString(2, report.getContent());
            stmt.setTimestamp(3, Timestamp.valueOf(report.getCreatedDate()));
            stmt.setString(4, report.getReportType());
            stmt.executeUpdate();
            return "Report added successfully.";
        } catch (SQLException e) {
            return "Error adding report.";
        }
    }
    
    /* Method to create SQL prepared statement to update reports
     * after entering session information.
     */
    public String updateReport(Report report) {
        String sql = "UPDATE reports SET title = ?, content = ?, "
                + "createdDate = ?, reportType = ? WHERE reportId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, report.getTitle());
            stmt.setString(2, report.getContent());
            stmt.setTimestamp(3, Timestamp.valueOf(report.getCreatedDate()));
            stmt.setString(4, report.getReportType());
            stmt.setInt(5, report.getReportId());
            stmt.executeUpdate();
            return "Report updated successfully.";
        } catch (SQLException e) {
            return "Error updating report.";
        }
    }

    /* Method to create SQL prepared statement to retrieve reports
     * after entering ID.
     */
    public Report getReport(int reportId) {
        String sql = "SELECT * FROM reports WHERE reportId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reportId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Report report = new Report(
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getTimestamp("createdDate").toLocalDateTime(),
                        rs.getString("reportType")
                );
                report.setReportId(rs.getInt("reportId"));
                return report;
            }
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

    /* Method to create SQL prepared statement to create a new ArrayList called
     * 'reports' and add all reports to the array.
     */
    public List<Report> getAllReports() {
        String sql = "SELECT * FROM reports";
        List<Report> reports = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Report report = new Report(
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getTimestamp("createdDate").toLocalDateTime(),
                        rs.getString("reportType")
                );
                report.setReportId(rs.getInt("reportId"));
                reports.add(report);
            }
        } catch (SQLException e) {
        }
        return reports;
    }
    
    /* Method to create SQL prepared statement to remove a report
     * after entering report ID.
     */
     public String removeReport(int reportId) {
        String sql = "DELETE FROM reports WHERE reportId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reportId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return "Report removed successfully.";
            } else {
                return "Report not found.";
            }
        } catch (SQLException e) {
            return "Error removing report.";
        }
    }
}
