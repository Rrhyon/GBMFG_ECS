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

    private final EmployeeDAOImpl employeeDAO;

    // Calls the DAO methods to add input information into the DB.
    public EmployeeServiceImpl() {
        this.employeeDAO = new EmployeeDAOImpl();
    }

    // Method adds new Employees to the DB.
    @Override
    public String AddEmployee(Employee employee) {
        String hashedPassword = 
                PasswordHashUtil.hashPassword(employee.getPassword());
        employee.setPassword(hashedPassword);
        return employeeDAO.saveEmployee(employee);
    }
    
    // To do: Remove password references, create separate updatePassword method
    @Override
    public String UpdateEmployee(Employee employee) {
        String hashedPassword = 
                PasswordHashUtil.hashPassword(employee.getPassword());
        employee.setPassword(hashedPassword);
        return employeeDAO.saveEmployeeUpdates(employee);
    }
    
    @Override
    public String saveEmployee(Employee employee) {
        String hashedPassword = 
                PasswordHashUtil.hashPassword(employee.getPassword());
        employee.setPassword(hashedPassword);
        return employeeDAO.saveEmployee(employee);
    }
    // Method to retrieve employee record using their unique ID.
    @Override
    public Employee getEmployee(int empId) {
        return employeeDAO.getEmployee(empId);
    }

    // Method to retrieve employee record using their username.
    @Override
    public Employee getEmployeeByUsername(String username) {
        return employeeDAO.getEmployeeByUsername(username);
    }
    
    // Method used to remove an employee record associated with their unique ID.
    @Override
    public String RemoveEmployee(int empId) {
        return employeeDAO.removeEmployee(empId);
    }

    // Method used to list all employee records in the DB.
    @Override
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    @Override
    public void saveEmployeeUpdates(Employee employee) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
