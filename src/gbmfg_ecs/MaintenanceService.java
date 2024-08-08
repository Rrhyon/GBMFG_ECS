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

public class MaintenanceService {

    private SessionFactory sessionFactory;

    public MaintenanceService() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public String submitToolToMaintenance(int toolId, int empId, String description) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();

            Tool tool = session.get(Tool.class, toolId);
            Employee employee = session.get(Employee.class, empId);

            if (tool == null || employee == null) {
                return "Tool or Employee not found.";
            }

            MaintenanceRecord record = new MaintenanceRecord(tool, employee, description);
            session.save(record);
            session.getTransaction().commit();
            return "Maintenance record submitted successfully.";
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return "Error submitting maintenance record.";
        } finally {
            session.close();
        }
    }

    public void close() {
        sessionFactory.close();
    }
}
