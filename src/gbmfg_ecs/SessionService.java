package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
import java.time.LocalDateTime;
import java.util.List;

public class SessionService {

    private SessionDAO sessionDAO;

    public SessionService() {
        this.sessionDAO = new SessionDAO();
    }

    public String addSession(int empId, boolean isActive, LocalDateTime createdAt, LocalDateTime expiresAt) {
        Session session = new Session(empId, isActive, createdAt, expiresAt);
        return sessionDAO.addSession(session);
    }

    public String removeSession(int sessionId) {
        return sessionDAO.removeSession(sessionId);
    }

    public Session getSession(int sessionId) {
        return sessionDAO.getSession(sessionId);
    }

    public String updateSession(int sessionId, int empId, boolean isActive, LocalDateTime createdAt, LocalDateTime expiresAt) {
        Session session = new Session(empId, isActive, createdAt, expiresAt);
        session.setSessionId(sessionId);
        return sessionDAO.updateSession(session);
    }

    public List<Session> getAllSessions() {
        return sessionDAO.getAllSessions();
    }

    String login(String johndoe, String password123) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    String logout(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
