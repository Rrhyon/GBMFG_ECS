package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
import java.sql.*;

public class EmployeeDAO {

    public String addEmployee(Employee employee) {
        String sql = "INSERT INTO employee (empLastName, empFirstName, "
                + "empMiddleInitial, empPhoneNum, empEmailAddress, empRole, "
                + "empUsername, empPassword) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, employee.getLastName());
            stmt.setString(2, employee.getFirstName());
            stmt.setString(3, employee.getMiddleInitial());
            stmt.setString(4, employee.getPhoneNum());
            stmt.setString(5, employee.getEmailAddress());
            stmt.setString(6, employee.getEmpRole());
            stmt.setString(7, employee.getUsername());
            stmt.setString(8, employee.getPassword()); // Hashed password
            stmt.executeUpdate();
            return "Employee added successfully.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error adding employee.";
        }
    }

    public String removeEmployee(int empId) {
        String sql = "DELETE FROM employee WHERE empId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, empId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return "Employee removed successfully.";
            } else {
                return "Employee not found.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error removing employee.";
        }
    }

    public Employee getEmployee(int empId) {
        String sql = "SELECT * FROM employee WHERE empId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, empId);
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

    // Remove references to password and create a separate updatePassword method
    public String updateEmployee(Employee employee) {
        String sql = "UPDATE employee SET empLastName = ?, empFirstName = ?, "
                + "empMiddleInitial = ?, empPhoneNum = ?, empEmailAddress = ?, "
                + "empRole = ?, empUsername = ?, empPassword = ? WHERE empId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); 
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, employee.getLastName());
            stmt.setString(2, employee.getFirstName());
            stmt.setString(3, employee.getMiddleInitial());
            stmt.setString(4, employee.getPhoneNum());
            stmt.setString(5, employee.getEmailAddress());
            stmt.setString(6, employee.getEmpRole());
            stmt.setString(7, employee.getUsername());
            stmt.setString(8, employee.getPassword()); // Hashed password
            stmt.setInt(9, employee.getEmpId());
            stmt.executeUpdate();
            return "Employee updated successfully.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error updating employee.";
        }
    }

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
}
