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
public class MaterialService {

    private SessionFactory sessionFactory;

    public MaterialService() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public String addMaterial(String name, String description, double quantity, String unit) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();

            Material material = new Material(name, description, quantity, unit);
            session.save(material);
            session.getTransaction().commit();
            return "Material added successfully.";
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return "Error adding material.";
        } finally {
            session.close();
        }
    }

    public Material getMaterial(int materialId) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Material.class, materialId);
        } finally {
            session.close();
        }
    }

    public String removeMaterial(int materialId) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();

            Material material = session.get(Material.class, materialId);
            if (material != null) {
                session.delete(material);
                session.getTransaction().commit();
                return "Material removed successfully.";
            } else {
                return "Material not found.";
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return "Error removing material.";
        } finally {
            session.close();
        }
    }

    public String updateMaterial(Material material) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(material);
            session.getTransaction().commit();
            return "Material updated successfully.";
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return "Error updating material.";
        } finally {
            session.close();
        }
    }

    public void close() {
        sessionFactory.close();
    }
}
