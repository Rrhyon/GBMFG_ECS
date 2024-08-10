package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
import java.sql.*;

public class AuthenticationDAO {

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

    public void createSession(Session session) {
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
            e.printStackTrace();
        }
    }

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

    public void deactivateSession(int sessionId) {
        String sql = "UPDATE session SET isActive = false WHERE sessionId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, sessionId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
