package gbmfg_ecs;

import java.util.List;

/**
 *
 * @author phillip.tette
 */
public interface EmployeeService {
    String addEmployee(Employee employee);
    String updateEmployee(Employee employee);
    Employee getEmployee(int empId);
    Employee getEmployeeByUsername(String username);
    List<Employee> getAllEmployees();
    String removeEmployee(int empId);
}
