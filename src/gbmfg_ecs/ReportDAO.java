package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {

    public String addReport(Report report) {
        String sql = "INSERT INTO reports (title, content, createdDate, reportType) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, report.getTitle());
            stmt.setString(2, report.getContent());
            stmt.setTimestamp(3, Timestamp.valueOf(report.getCreatedDate()));
            stmt.setString(4, report.getReportType());
            stmt.executeUpdate();
            return "Report added successfully.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error adding report.";
        }
    }

    public String removeReport(int reportId) {
        String sql = "DELETE FROM reports WHERE reportId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reportId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return "Report removed successfully.";
            } else {
                return "Report not found.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error removing report.";
        }
    }

    public Report getReport(int reportId) {
        String sql = "SELECT * FROM reports WHERE reportId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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
            e.printStackTrace();
            return null;
        }
    }

    public String updateReport(Report report) {
        String sql = "UPDATE reports SET title = ?, content = ?, createdDate = ?, reportType = ? WHERE reportId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, report.getTitle());
            stmt.setString(2, report.getContent());
            stmt.setTimestamp(3, Timestamp.valueOf(report.getCreatedDate()));
            stmt.setString(4, report.getReportType());
            stmt.setInt(5, report.getReportId());
            stmt.executeUpdate();
            return "Report updated successfully.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error updating report.";
        }
    }

    public List<Report> getAllReports() {
        String sql = "SELECT * FROM reports";
        List<Report> reports = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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
            e.printStackTrace();
        }
        return reports;
    }
}
