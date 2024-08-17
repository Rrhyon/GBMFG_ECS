package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Provides framework for report object creation.
 * Date: August 13, 2024
 */
import java.time.LocalDateTime;

public class Report {

    private int reportId;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private String reportType;

    // Constructors
    public Report() {
    }

    public Report(String title, String content, LocalDateTime createdDate, 
            String reportType) {
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.reportType = reportType;
    }

    // Getters
    public int getReportId() {
        return reportId;
    }

    public String getTitle() {
        return title;
    }
    
    public String getContent() {
        return content;
    }
    
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    
    public String getReportType() {
        return reportType;
    }
    
    // Setters
    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
}
