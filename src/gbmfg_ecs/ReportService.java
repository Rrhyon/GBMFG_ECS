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

public class ReportService {

    private ReportDAO reportDAO;

    public ReportService() {
        this.reportDAO = new ReportDAO();
    }

    // Creates a report and returns the report to the DAO method for DB Entry.
    public String addReport(String title, String content, 
            LocalDateTime createdDate, String reportType) {
        Report report = new Report(title, content, createdDate, reportType);
        return reportDAO.addReport(report);
    }

    /* Creates the object, retrieves the existing ID and returns the updates to
     * the DAO method for DB Entry.
     */
    public String updateReport(int reportId, String title, String content, 
            LocalDateTime createdDate, String reportType) {
        Report report = new Report(title, content, createdDate, reportType);
        report.setReportId(reportId);
    return reportDAO.updateReport(report);
    }

    // Retrieves the report by ID.
    public Report getReport(int reportId) {
        return reportDAO.getReport(reportId);
    }

    // Creates a list and retrieves all available reports.
    public List<Report> getAllReports() {
        return reportDAO.getAllReports();
    }
    
    // Removes selected reports.
    public String removeReport(int reportId) {
        return reportDAO.removeReport(reportId);
    }
}
