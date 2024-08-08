package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
import java.time.LocalDateTime;

public class Report {

    private int reportId;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private String reportType;

    // Constructors, getters, and setters
    public Report() {
    }

    public Report(String title, String content, LocalDateTime createdDate, String reportType) {
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.reportType = reportType;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
}
