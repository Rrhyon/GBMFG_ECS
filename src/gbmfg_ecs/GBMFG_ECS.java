package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: This program tracks tools using a checkout transaction
 * system that is associated with employee identification. Other features include
 * employee management, tool and material inventory management, maintenance
 * management, and generates reports for inspections and other purposes.
 * Date: August 13, 2024
 */

import java.time.LocalDateTime;
import java.util.List;

public class GBMFG_ECS {

    public static void main(String[] args) {
        
        // Initialize services
//        EmployeeService employeeService = new EmployeeService();
//        ToolServiceImpl toolService = new ToolServiceImpl();
//        MaterialService materialService = new MaterialService();
//        SessionService sessionService = new SessionService();
//        MaintenanceRecordService maintenanceRecordService = 
//                new MaintenanceRecordService();
//        CheckoutTransactionService checkoutTransactionService = 
//                new CheckoutTransactionService();
//        CategoryService categoryService = new CategoryService();
//        LocationService locationService = new LocationService();
//        ReportService reportService = new ReportService();
//        LoginService loginService = new LoginService();


// Example operations
        // Add an employee
        // !!Operation Successful!!
//        String addEmployeeResult = employeeService.addEmployee("Done", "John",
//                "A", "1234567890", "john.doe@example.com", "Technician", 
//                "johndone", "password123");
//        System.out.println(addEmployeeResult);

        // Add a tool
        // !!Operation Successful!!
//        String addToolResult = toolService.addTool("Heavy Drill", "Craftsman", 
//                "Good", true, "987455678", 1, 1);
//        System.out.println(addToolResult);

        // Add a material
        // !!Operation Successful!!
//        String addMaterialResult = materialService.addMaterial("Wood", 
//                "Pine wood", 100, "pieces");
//        System.out.println(addMaterialResult);
        // Add a session
        // Note: Had to move the hashing process from the Employee.java 
        // employee constructor to the EmployeeService.java addUser function
        // !!SuccessfulOperation!!
//        String loginResult = loginService.login("johndone", "password123");
//        System.out.println(loginResult);
        // Add a maintenance record
        // !!Operation Successful!!
//        String addMaintenanceRecordResult = maintenanceRecordService.
//                addMaintenanceRecord(9, 1, LocalDateTime.now(), "Routine check", 
//                        "Completed");
//        System.out.println(addMaintenanceRecordResult);
        // Add a checkout transaction
        // !!OperationSuccessful!!
//        String addCheckoutTransactionResult = checkoutTransactionService.
//                addCheckoutTransaction(1, 9, LocalDateTime.now(), LocalDateTime.
//                        now().plusDays(7), null, "Checked out");
//        System.out.println(addCheckoutTransactionResult);
        // Add a category
        // !!Operation Successful
//        String addCategoryResult = categoryService.addCategory(1, "Tools", 
//                "Various tools");
//        System.out.println(addCategoryResult);
        // Add a location
        // !!Operation Successful!!
//        String addLocationResult = locationService.addLocation(0, "Warehouse 1", 
//                "Main storage warehouse");
//        System.out.println(addLocationResult);
        // Add a report
//        String addReportResult = reportService.addReport("Monthly Report",
//                "This is the monthly report content.", LocalDateTime.now(), 
//                "Monthly");
//        System.out.println(addReportResult);
        // List all employees
//        List<Employee> employees = employeeService.getAllEmployees();
//        System.out.println("All employees:");
//        for (Employee employee : employees) {
//            System.out.println(employee.getFirstName() + " " + employee.getLastName());
//        }
        // List all tools
//        List<Tool> tools = toolService.getAllTools();
//        System.out.println("All tools:");
//        System.out.println("Tools fetched: " + tools.size());  // This should print the number of tools fetched
//        
        // Logout
        // !!OperationSuccessful!!
//        String logoutResult = loginService.logout(8);
//        System.out.println(logoutResult);
    }
}
