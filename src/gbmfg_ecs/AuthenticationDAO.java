package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Database Access Object for Authentication class.
 * Date: August 13, 2024
 */
import java.sql.*;

public class AuthenticationDAO {

    // Retrieves the usersname from the database.
    public Employee getEmployeeByUsername(String username) {
        String sql = "SELECT * FROM employee WHERE empUsername = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Employee employee = new Employee(
                        rs.getString("empLastName"),
                        rs.getString("empFirstName"),
                        rs.getString("empMiddleInitial"),
                        rs.getString("empPhoneNum"),
                        rs.getString("empEmailAddress"),
                        rs.getString("empRole"),
                        rs.getString("empUsername"),
                        rs.getString("empPassword")
                );
                employee.setEmpId(rs.getInt("empId"));
                return employee;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Adds a temporary session to the DB to track user connections.
    public void createSession(Session session) {
        String sql = "INSERT INTO session (empId, isActive, createdAt, "
                + "expiresAt)" + "VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, session.getEmpId());
            stmt.setBoolean(2, session.isActive());
            stmt.setTimestamp(3, Timestamp.valueOf(session.getCreatedAt()));
            stmt.setTimestamp(4, Timestamp.valueOf(session.getExpiresAt()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieves active session from the DB based on the employee ID.
    public Session getActiveSessionByEmployeeId(int empId) {
        String sql = "SELECT * FROM session "
                + "WHERE empId = ? AND isActive = true";
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
    
    // Implements the method to retrieve a session by session ID
    public Session getSessionById(int sessionId) {
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

    // Terminates the selected session.
public void deactivateSession(int sessionId) {
        String sql = "UPDATE session SET isActive = false WHERE sessionId = ?";
    System.out.println("Deactivating session ID: " + sessionId);
    try (Connection conn = DatabaseUtil.getConnection(); 
            PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, sessionId);
        int rowsUpdated = stmt.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Session ID " + sessionId + " deactivated.");
        } else {
            System.out.println("No session found with ID " + sessionId);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Session getSession(int sessionId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
