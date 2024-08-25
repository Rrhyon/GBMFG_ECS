package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
public interface EmployeeService {
    String addEmployee(Employee employee);
    String updateEmployee(Employee employee);
    Employee getEmployee(int empId);
    Employee getEmployeeByUsername(String username);
    String removeEmployee(int empId);
}
