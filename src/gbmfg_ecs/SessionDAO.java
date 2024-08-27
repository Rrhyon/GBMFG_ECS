package gbmfg_ecs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SessionDAO {

    private static final Logger logger = Logger.getLogger(SessionDAO.class.getName());

    public void addSession(Session session) throws SQLException {
        String sql = "INSERT INTO session (empId, isActive, createdAt, expiresAt) "
                + "VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, session.getEmpId());
            stmt.setBoolean(2, session.isActive());
            stmt.setTimestamp(3, Timestamp.valueOf(session.getCreatedAt()));
            stmt.setTimestamp(4, Timestamp.valueOf(session.getExpiresAt()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error adding session: {0}", e.getMessage());
            throw e;
        }
    }

    public void updateSession(Session session) throws SQLException {
        String sql = "UPDATE session SET empId = ?, isActive = ?, createdAt = ?, "
                + "expiresAt = ? WHERE sessionId = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, session.getEmpId());
            stmt.setBoolean(2, session.isActive());
            stmt.setTimestamp(3, Timestamp.valueOf(session.getCreatedAt()));
            stmt.setTimestamp(4, Timestamp.valueOf(session.getExpiresAt()));
            stmt.setInt(5, session.getSessionId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating session: {0}", e.getMessage());
            throw e;
        }
    }

    public Optional<Session> getSession(int sessionId) {
        String sql = "SELECT * FROM session WHERE sessionId = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, sessionId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Session session = new Session(
                        rs.getInt("empId"),
                        rs.getBoolean("isActive"),
                        rs.getTimestamp("createdAt").toLocalDateTime(),
                        rs.getTimestamp("expiresAt").toLocalDateTime()
                );
                session.setSessionId(rs.getInt("sessionId"));
                return Optional.of(session);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving session by ID: {0}", 
                    e.getMessage());
        }
        return Optional.empty();
    }

    public List<Session> getAllSessions() {
        String sql = "SELECT * FROM session";
        List<Session> sessions = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Session session = new Session(
                        rs.getInt("empId"),
                        rs.getBoolean("isActive"),
                        rs.getTimestamp("createdAt").toLocalDateTime(),
                        rs.getTimestamp("expiresAt").toLocalDateTime()
                );
                session.setSessionId(rs.getInt("sessionId"));
                sessions.add(session);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving all sessions: {0}", 
                    e.getMessage());
        }
        return sessions;
    }

    public void removeSession(int sessionId) throws SQLException {
        String sql = "DELETE FROM session WHERE sessionId = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, sessionId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                logger.log(Level.WARNING, "No session found with ID {0}", sessionId);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error removing session: {0}", e.getMessage());
            throw e;
        }
    }
}