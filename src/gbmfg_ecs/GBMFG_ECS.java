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
        String addEmployeeResult = employeeService.addEmployee("Doe", "John", 
                "A", "1234567890", "john.doe@example.com", "Technician", 
                "johndoe", "password123");
        System.out.println(addEmployeeResult);

        // Add a tool
        String addToolResult = toolService.addTool("Hammer", "Heavy duty hammer", 
                "New", true, "1234-5678", 1, 1);
        System.out.println(addToolResult);

        // Add a material
        String addMaterialResult = materialService.addMaterial("Wood", 
                "Pine wood", 100, "pieces");
        System.out.println(addMaterialResult);

        // Add a session
        String loginResult = loginService.login("johndoe", "password123");
        System.out.println(loginResult);

        // Add a maintenance record
        String addMaintenanceRecordResult = maintenanceRecordService.
                addMaintenanceRecord(1, 1, LocalDateTime.now(), "Routine check", 
                        "Completed");
        System.out.println(addMaintenanceRecordResult);

        // Add a checkout transaction
        String addCheckoutTransactionResult = checkoutTransactionService.
                addCheckoutTransaction(1, 1, LocalDateTime.now(), LocalDateTime.
                        now().plusDays(7), null, "Checked out");
        System.out.println(addCheckoutTransactionResult);

        // Add a category
        String addCategoryResult = categoryService.addCategory("Tools", 
                "Various tools");
        System.out.println(addCategoryResult);

        // Add a location
        String addLocationResult = locationService.addLocation("Warehouse 1", 
                "Main storage warehouse");
        System.out.println(addLocationResult);

        // Add a report
        String addReportResult = reportService.addReport("Monthly Report", 
                "This is the monthly report content.", LocalDateTime.now(), 
                "Monthly");
        System.out.println(addReportResult);

        // List all employees
        List<Employee> employees = employeeService.getAllEmployees();
        System.out.println("All employees:");
        for (Employee employee : employees) {
            System.out.println(employee.getFirstName() + " " + employee.getLastName());
        }

        // List all tools
        List<Tool> tools = toolService.getAllTools();
        System.out.println("All tools:");
        for (Tool tool : tools) {
            System.out.println(tool.getName() + " - " + tool.getDescription());
        }

        // Logout
        String logoutResult = loginService.logout(1);
        System.out.println(logoutResult);
    }
}
