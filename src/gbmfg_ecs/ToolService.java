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
public class ToolService {

    private SessionFactory sessionFactory;

    public ToolService() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public String addTool(String name, String description, String condition, boolean isAvailable, String serialNum, int categoryId, int locationId) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();

            Category category = session.get(Category.class, categoryId);
            Location location = session.get(Location.class, locationId);

            Tool tool = new Tool(name, description, condition, isAvailable, serialNum, category, location);
            session.save(tool);
            session.getTransaction().commit();
            return "Tool added successfully.";
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return "Error adding tool.";
        } finally {
            session.close();
        }
    }

    public Tool getTool(int toolId) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Tool.class, toolId);
        } finally {
            session.close();
        }
    }

    public String removeTool(int toolId) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();

            Tool tool = session.get(Tool.class, toolId);
            if (tool != null) {
                session.delete(tool);
                session.getTransaction().commit();
                return "Tool removed successfully.";
            } else {
                return "Tool not found.";
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return "Error removing tool.";
        } finally {
            session.close();
        }
    }

    public String updateTool(Tool tool) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(tool);
            session.getTransaction().commit();
            return "Tool updated successfully.";
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return "Error updating tool.";
        } finally {
            session.close();
        }
    }

    public void close() {
        sessionFactory.close();
    }
}
