/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gbmfg_ecs;

import hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author phillip.tette
 */
public class Authentication {

    private SessionFactory sessionFactory;

    public Authentication() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public boolean login(String username, String password) {
        Session session = sessionFactory.openSession();
        try {
            Employee employee = (Employee) session.createQuery("FROM Employee WHERE username = :username")
                    .setParameter("username", username)
                    .uniqueResult();
            if (employee != null && employee.checkPassword(password)) {
                return true;
            } else {
                return false;
            }
        } finally {
            session.close();
            test();
        }
    }
}
