package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SessionDAO {

    public String addSession(Session session) {
        String sql = "INSERT INTO sessions (empId, isActive, createdAt, expiresAt) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, session.getEmpId());
            stmt.setBoolean(2, session.isActive());
            stmt.setTimestamp(3, Timestamp.valueOf(session.getCreatedAt()));
            stmt.setTimestamp(4, Timestamp.valueOf(session.getExpiresAt()));
            stmt.executeUpdate();
            return "Session added successfully.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error adding session.";
        }
    }

    public String removeSession(int sessionId) {
        String sql = "DELETE FROM sessions WHERE sessionId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, sessionId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return "Session removed successfully.";
            } else {
                return "Session not found.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error removing session.";
        }
    }

    public Session getSession(int sessionId) {
        String sql = "SELECT * FROM sessions WHERE sessionId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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
                return session;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String updateSession(Session session) {
        String sql = "UPDATE sessions SET empId = ?, isActive = ?, createdAt = ?, expiresAt = ? WHERE sessionId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, session.getEmpId());
            stmt.setBoolean(2, session.isActive());
            stmt.setTimestamp(3, Timestamp.valueOf(session.getCreatedAt()));
            stmt.setTimestamp(4, Timestamp.valueOf(session.getExpiresAt()));
            stmt.setInt(5, session.getSessionId());
            stmt.executeUpdate();
            return "Session updated successfully.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error updating session.";
        }
    }

    public List<Session> getAllSessions() {
        String sql = "SELECT * FROM sessions";
        List<Session> sessions = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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
            e.printStackTrace();
        }
        return sessions;
    }
}
