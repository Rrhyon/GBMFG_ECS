package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Intermediary class to pass object information to the DAO.
 * Date: August 13, 2024
 */
import java.time.LocalDateTime;
import java.util.List;

public class SessionService {

    private SessionDAO sessionDAO;

    public SessionService() {
        this.sessionDAO = new SessionDAO();
    }

    // Creates a session and returns the session to the DAO method for DB Entry.
    public String addSession(int empId, boolean isActive, 
            LocalDateTime createdAt, LocalDateTime expiresAt) {
        Session session = new Session(empId, isActive, createdAt, expiresAt);
        return sessionDAO.addSession(session);
    }

    /* Creates the object, retrieves the existing ID and returns the updates to
     * the DAO method for DB Entry.
     */
    public String updateSession(int sessionId, int empId, boolean isActive, 
            LocalDateTime createdAt, LocalDateTime expiresAt) {
        Session session = new Session(empId, isActive, createdAt, expiresAt);
        session.setSessionId(sessionId);
        return sessionDAO.updateSession(session);
    }
    
    // Retrieves the session by ID.
    public Session getSession(int sessionId) {
        return sessionDAO.getSession(sessionId);
    }

    // Creates a list and retrieves all available sessions.
    public List<Session> getAllSessions() {
        return sessionDAO.getAllSessions();
    }

    // Removes selected sessions.
    public String removeSession(int sessionId) {
        return sessionDAO.removeSession(sessionId);
    }

    // Retrieves the active session by employee ID.
    public Session getActiveSessionByEmployeeId(int empId) {
        return sessionDAO.getActiveSessionByEmployeeId(empId);
    }
}