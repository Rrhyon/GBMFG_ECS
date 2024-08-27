package gbmfg_ecs;

import java.util.List;

/**
 *
 * @author phillip.tette
 */
public interface EmployeeDAO {
    String saveEmployee(Employee employee);
    String saveEmployeeUpdates(Employee employee);
    Employee getEmployee(int empId);
    Employee getEmployeeByUsername(String username);
    List<Employee> getAllEmployees();
    String removeEmployee(int empId);
}
