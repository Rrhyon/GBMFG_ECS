package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
import java.time.LocalDateTime;
import java.util.List;

public class GBMFG_ECS {

    public static void main(String[] args) {
        // Initialize services
        EmployeeService employeeService = new EmployeeService();
        ToolService toolService = new ToolService();
        MaterialService materialService = new MaterialService();
        SessionService sessionService = new SessionService();
        MaintenanceRecordService maintenanceRecordService = new MaintenanceRecordService();
        CheckoutTransactionService checkoutTransactionService = new CheckoutTransactionService();
        CategoryService categoryService = new CategoryService();
        LocationService locationService = new LocationService();
        ReportService reportService = new ReportService();
        LoginService loginService = new LoginService();

        // Example operations
        // Add an employee
        // !!Operation Successful!!
//        String addEmployeeResult = employeeService.addEmployee("Done", "John", 
//                "A", "1234567890", "john.doe@example.com", "Technician", 
//                "johndone", "password123");
//        System.out.println(addEmployeeResult);

        // Add a tool
        // !!Operation Successful!!
//        String addToolResult = toolService.addTool("Hammer", "Heavy duty hammer", 
//                "New", true, "1234-5678", 1, 0);
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
//        for (Tool tool : tools) {
//            System.out.println(tool.getName() + " - " + tool.getDescription());
//        }

        // Logout
        // !!OperationSuccessful!!
        String logoutResult = loginService.logout(8);
        System.out.println(logoutResult);
    }
}
