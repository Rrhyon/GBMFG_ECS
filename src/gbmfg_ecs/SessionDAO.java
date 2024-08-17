package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Database Access Object for Session class.
 * Date: August 13, 2024
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SessionDAO {

    // Adds a session to the DB
    public String addSession(Session session) {
        String sql = "INSERT INTO session (empId, isActive, createdAt, expiresAt) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, session.getEmpId());
            stmt.setBoolean(2, session.isActive());
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(session.getCreatedAt()));
            stmt.setTimestamp(4, java.sql.Timestamp.valueOf(session.getExpiresAt()));
            stmt.executeUpdate();
            return "Session added successfully.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed to add session.";
        }
    }

    // Updates a session in the DB
    public String updateSession(Session session) {
        String sql = "UPDATE session SET isActive = ?, createdAt = ?, expiresAt = ? WHERE sessionId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, session.isActive());
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(session.getCreatedAt()));
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(session.getExpiresAt()));
            stmt.setInt(4, session.getSessionId());
            stmt.executeUpdate();
            return "Session updated successfully.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed to update session.";
        }
    }

    // Retrieves the session by ID
    public Session getSession(int sessionId) {
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
                return session;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Retrieves the active session by employee ID
    public Session getActiveSessionByEmployeeId(int empId) {
        String sql = "SELECT * FROM session WHERE empId = ? AND isActive = true";
        try (Connection conn = DatabaseUtil.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, empId);
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

    // Removes a session from the DB
    public String removeSession(int sessionId) {
        String sql = "DELETE FROM session WHERE sessionId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, sessionId);
            stmt.executeUpdate();
            return "Session removed successfully.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed to remove session.";
        }
    }

    List<Session> getAllSessions() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
