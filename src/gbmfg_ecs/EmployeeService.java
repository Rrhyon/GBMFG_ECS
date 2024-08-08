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

    public String addEmployee(String lastName, String firstName, String middleInitial, String phoneNum, String emailAddress, String empRole, String username, String password) {
        Employee employee = new Employee(lastName, firstName, middleInitial, phoneNum, emailAddress, empRole, username, password);
        return employeeDAO.addEmployee(employee);
    }

    public String removeEmployee(int empId) {
        return employeeDAO.removeEmployee(empId);
    }

    public Employee getEmployee(int empId) {
        return employeeDAO.getEmployee(empId);
    }

    public String updateEmployee(int empId, String lastName, String firstName, String middleInitial, String phoneNum, String emailAddress, String empRole, String username, String password) {
        Employee employee = new Employee(lastName, firstName, middleInitial, phoneNum, emailAddress, empRole, username, password);
        employee.setEmpId(empId);
        return employeeDAO.updateEmployee(employee);
    }

    public Employee getEmployeeByUsername(String username) {
        return employeeDAO.getEmployeeByUsername(username);
    }

    List<Employee> getAllEmployees() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
