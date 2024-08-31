package gbmfg_ecs;

import java.util.List;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Public Interface class (possibly unnecessary).
 * Date: August 13, 2024
 */
public interface EmployeeService {
    String addEmployee(Employee employee);
    String updateEmployee(Employee employee);
    Employee getEmployee(int empId);
    Employee getEmployeeByUsername(String username);
    String removeEmployee(int empId);
    List<Employee> getAllEmployees();
}
