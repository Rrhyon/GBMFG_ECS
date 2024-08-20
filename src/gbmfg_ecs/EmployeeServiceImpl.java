package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Intermediary class to pass object information to the DAO.
 * Date: August 13, 2024
 */
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDAOImpl employeeDAO;

    // Calls the DAO methods to add input information into the DB.
    public EmployeeServiceImpl() {
        this.employeeDAO = new EmployeeDAOImpl();
    }

    // Method adds new Employees to the DB.
    public String addEmployee(Employee employee) {
        String hashedPassword = 
                PasswordHashUtil.hashPassword(employee.getPassword());
        employee.setPassword(hashedPassword);
        return employeeDAO.saveEmployee(employee);
    }
    
    // To do: Remove password references, create separate updatePassword method
    public String updateEmployee(Employee employee) {
        String hashedPassword = 
                PasswordHashUtil.hashPassword(employee.getPassword());
        employee.setPassword(hashedPassword);
        return employeeDAO.saveEmployeeUpdates(employee);
    }

    // Method to retrieve employee record using their unique ID.
    public Employee getEmployee(int empId) {
        return employeeDAO.getEmployee(empId);
    }

    // Method to retrieve employee record using their username.
    public Employee getEmployeeByUsername(String username) {
        return employeeDAO.getEmployeeByUsername(username);
    }
    
    // Method used to remove an employee record associated with their unique ID.
    public String removeEmployee(int empId) {
        return employeeDAO.removeEmployee(empId);
    }

    // Method used to list all employee records in the DB.
    List<Employee> getAllEmployees() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
