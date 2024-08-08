package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */

import java.time.LocalDateTime;
import java.util.List;

public class ReportService {

    private ReportDAO reportDAO;

    public ReportService() {
        this.reportDAO = new ReportDAO();
    }

    public String addReport(String title, String content, LocalDateTime createdDate, String reportType) {
        Report report = new Report(title, content, createdDate, reportType);
        return reportDAO.addReport(report);
    }

    public String removeReport(int reportId) {
        return reportDAO.removeReport(reportId);
    }

    public Report getReport(int reportId) {
        return reportDAO.getReport(reportId);
    }

    public String updateReport(int reportId, String title, String content, LocalDateTime createdDate, String reportType) {
        Report report = new Report(title, content, createdDate, reportType);
        report.setReportId(reportId);
        return reportDAO.updateReport(report);
    }

    public List<Report> getAllReports() {
        return reportDAO.getAllReports();
    }
}
