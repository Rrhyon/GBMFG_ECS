package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
import java.util.List;

public class EmployeeService {

    private EmployeeDAO employeeDAO;

    public EmployeeService() {
        this.employeeDAO = new EmployeeDAO();
    }

    public String addEmployee(String lastName, String firstName,
            String middleInitial, String phoneNum, String emailAddress,
            String empRole, String username, String password) {
        String hashedPassword = PasswordHashUtil.hashPassword(password);
        Employee employee = new Employee(lastName, firstName,
                middleInitial, phoneNum, emailAddress, empRole, username,
                hashedPassword);
        return employeeDAO.addEmployee(employee);
    }
    
    // Remove references to password and create a separate updatePassword method
    public String updateEmployee(int empId, String lastName, String firstName, 
            String middleInitial, String phoneNum, String emailAddress, 
            String empRole, String username, String password) {
        Employee employee = new Employee(lastName, firstName, middleInitial, 
                phoneNum, emailAddress, empRole, username, password);
        employee.setEmpId(empId);
        return employeeDAO.updateEmployee(employee);
    }

    public Employee getEmployee(int empId) {
        return employeeDAO.getEmployee(empId);
    }

    public Employee getEmployeeByUsername(String username) {
        return employeeDAO.getEmployeeByUsername(username);
    }
    
    public String removeEmployee(int empId) {
        return employeeDAO.removeEmployee(empId);
    }



    List<Employee> getAllEmployees() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
