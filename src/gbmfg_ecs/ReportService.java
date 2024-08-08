/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gbmfg_ecs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author phillip.tette
 */

public class ReportService {

    private SessionFactory sessionFactory;

    public ReportService() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public String generateReport(String title, String content, String reportType) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Report report = new Report(title, content, reportType);
            session.save(report);
            session.getTransaction().commit();
            return "Report generated successfully.";
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return "Error generating report.";
        } finally {
            session.close();
        }
    }

    public void close() {
        sessionFactory.close();
    }
}
