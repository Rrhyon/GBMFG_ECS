package gbmfg_ecs;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SessionService {

    private static final Logger logger = Logger.getLogger(SessionService.class.getName());
    private final SessionDAO sessionDAO;

    public SessionService() {
        this.sessionDAO = new SessionDAO();
    }

    public boolean addSession(int empId, LocalDateTime createdAt, LocalDateTime expiresAt) {
        Session session = new Session(empId, true, createdAt, expiresAt);
        try {
            sessionDAO.addSession(session);
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to add session: {0}", e.getMessage());
            return false;
        }
    }

    public boolean updateSession(int sessionId, int empId, boolean isActive, LocalDateTime createdAt, LocalDateTime expiresAt) {
        Session session = new Session(empId, isActive, createdAt, expiresAt);
        session.setSessionId(sessionId);
        try {
            sessionDAO.updateSession(session);
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to update session: {0}", e.getMessage());
            return false;
        }
    }

    public Optional<Session> getSession(int sessionId) {
        return sessionDAO.getSession(sessionId);
    }

    public List<Session> getAllSessions() {
        return sessionDAO.getAllSessions();
    }

    public boolean removeSession(int sessionId) {
        try {
            sessionDAO.removeSession(sessionId);
            return true;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to remove session: {0}", e.getMessage());
            return false;
        }
    }
}
