package gbmfg_ecs;

import java.sql.*;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthenticationDAO {

    private static final Logger logger = Logger.getLogger(AuthenticationDAO.class.getName());

    public Optional<Employee> getEmployeeByUsername(String username) {
        String sql = "SELECT * FROM employee WHERE empUsername = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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
                return Optional.of(employee);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving employee by username: {0}", e.getMessage());
        }
        return Optional.empty();
    }

    public void createSession(Session session) throws SQLException {
        String sql = "INSERT INTO session (empId, isActive, createdAt, expiresAt) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, session.getEmpId());
            stmt.setBoolean(2, session.isActive());
            stmt.setTimestamp(3, Timestamp.valueOf(session.getCreatedAt()));
            stmt.setTimestamp(4, Timestamp.valueOf(session.getExpiresAt()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error creating session: {0}", e.getMessage());
            throw e;
        }
    }

    public Optional<Session> getActiveSessionByEmployeeId(int empId) {
        String sql = "SELECT * FROM session WHERE empId = ? AND isActive = true";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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
                return Optional.of(session);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving active session by employee ID: {0}", e.getMessage());
        }
        return Optional.empty();
    }

    public void deactivateSession(int sessionId) throws SQLException {
        String sql = "UPDATE session SET isActive = false WHERE sessionId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, sessionId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                logger.log(Level.WARNING, "No session found with ID {0}", sessionId);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deactivating session: {0}", e.getMessage());
            throw e;
        }
    }
}
