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
public class EmployeeService {

    private SessionFactory sessionFactory;

    public EmployeeService() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public String addEmployee(String lastName, String firstName, String middleInitial, String phoneNum, String emailAddress, String empRole, String username, String password) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Employee employee = new Employee(lastName, firstName, middleInitial, phoneNum, emailAddress, empRole, username, password);
            session.save(employee);
            session.getTransaction().commit();
            return "Employee added successfully.";
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return "Error adding employee.";
        } finally {
            session.close();
        }
    }

    public String removeEmployee(int empId) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Employee employee = session.get(Employee.class, empId);
            if (employee != null) {
                session.delete(employee);
                session.getTransaction().commit();
                return "Employee removed successfully.";
            } else {
                return "Employee not found.";
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return "Error removing employee.";
        } finally {
            session.close();
        }
    }

    public Employee getEmployee(int empId) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Employee.class, empId);
        } finally {
            session.close();
        }
    }

    public String updateEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(employee);
            session.getTransaction().commit();
            return "Employee updated successfully.";
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return "Error updating employee.";
        } finally {
            session.close();
        }
    }

    public void close() {
        sessionFactory.close();
    }
}
