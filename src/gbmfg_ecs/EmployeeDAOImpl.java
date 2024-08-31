package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service Course: CEIS 400
 * - Software Engineering II Author: Phillip Tette Program Description: Database
 * Access Object for Employee class. Date: August 13, 2024
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    /* Method to create SQL prepared statement to create an employee record
     * to add to the DB after entering employee information.
     */
    @Override
    public String saveEmployee(Employee employee) {
        String sql = "INSERT INTO employee (empLastName, empFirstName, "
                + "empMiddleInitial, empPhoneNum, empEmailAddress, empRole, "
                + "empUsername, empPassword) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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
            return "Error adding employee.";
        }
    }

    // To do: Remove password references, create separate updatePassword method
    /* Method to create SQL prepared statement to update employee records
     * after entering employee information.
     */
    @Override
    public String saveEmployeeUpdates(Employee employee) {
        String sql = "UPDATE employee SET empLastName = ?, empFirstName = ?, "
                + "empMiddleInitial = ?, empPhoneNum = ?, empEmailAddress = ?, "
                + "empRole = ?, empUsername = ?, empPassword = ? WHERE empId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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
            return "Error updating employee.";
        }
    }

    /* Method to create SQL prepared statement to retrieve employee record
     * after entering employee ID.
     */
    @Override
    public Employee getEmployee(int empId) {
        String sql = "SELECT * FROM employee WHERE empId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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
            return null;
        }
    }

    /* Method to create SQL prepared statement to retrieve employee record
     * after entering employee username.
     */
    @Override
    public Employee getEmployeeByUsername(String username) {
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
                return employee;
            }
            return null;
        } catch (SQLException e) {
            return null;
        }
    }

    /* Method to create SQL prepared statement to create a new ArrayList called
     * 'employees' and add all employees to the array.
     */
    @Override
    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM employee";
        List<Employee> employees = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Employee employee = new Employee(
                        rs.getInt("empId"),
                        rs.getString("empLastName"),
                        rs.getString("empFirstName"),
                        rs.getString("empMiddleInitial"),
                        rs.getString("empPhoneNum"),
                        rs.getString("empEmailAddress"),
                        rs.getString("empRole"),
                        rs.getString("empUsername"),
                        rs.getString("empPassword")
                );
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log or handle the exception as needed
        }
        return employees;
    }

    /* Method to create SQL prepared statement to remove employee record
     * after entering employee ID.
     */
    @Override
    public String removeEmployee(int empId) {
        String sql = "DELETE FROM employee WHERE empId = ?";
        try (Connection conn = DatabaseUtil.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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
}
