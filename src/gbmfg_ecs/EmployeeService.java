package gbmfg_ecs;

import java.util.List;

/**
 *
 * @author phillip.tette
 */
public interface EmployeeService {
    String AddEmployee(Employee employee);
    String UpdateEmployee(Employee employee);
    String saveEmployee(Employee employee);
    Employee getEmployee(int empId);
    Employee getEmployeeByUsername(String username);
    List<Employee> getAllEmployees();
    String RemoveEmployee(int empId);

    public void saveEmployeeUpdates(Employee employee);
}
