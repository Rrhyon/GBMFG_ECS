package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
public interface EmployeeDAO {
    String saveEmployee(Employee employee);
    String saveEmployeeUpdates(Employee employee);
    Employee getEmployee(int empId);
    Employee getEmployeeByUsername(String username);
    String removeEmployee(int empId);
}
