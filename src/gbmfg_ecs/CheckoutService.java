/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gbmfg_ecs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.time.LocalDateTime;

/**
 *
 * @author phillip.tette
 */

public class CheckoutService {

    private SessionFactory sessionFactory;

    public CheckoutService() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public String checkoutItem(int empId, int inventoryId, LocalDateTime dueDate) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();

            Employee employee = session.get(Employee.class, empId);
            InventoryItem inventoryItem = session.get(InventoryItem.class, inventoryId);

            if (employee == null || inventoryItem == null) {
                return "Employee or Inventory Item not found.";
            }

            CheckoutTransaction transaction = new CheckoutTransaction(employee, inventoryItem, dueDate);
            session.save(transaction);
            session.getTransaction().commit();
            return "Item checked out successfully.";
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return "Error checking out item.";
        } finally {
            session.close();
        }
    }

    public String returnItem(int transactionId) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();

            CheckoutTransaction transaction = session.get(CheckoutTransaction.class, transactionId);
            if (transaction == null) {
                return "Transaction not found.";
            }

            transaction.returnItem();
            session.update(transaction);
            session.getTransaction().commit();
            return "Item returned successfully.";
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return "Error returning item.";
        } finally {
            session.close();
        }
    }

    public void close() {
        sessionFactory.close();
    }
}
