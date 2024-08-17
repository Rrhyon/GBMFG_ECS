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
        EmployeeService employeeService = new EmployeeService();
        ToolService toolService = new ToolService();
        MaterialService materialService = new MaterialService();
        SessionService sessionService = new SessionService();
        MaintenanceRecordService maintenanceRecordService = 
                new MaintenanceRecordService();
        CheckoutTransactionService checkoutTransactionService = 
                new CheckoutTransactionService();
        CategoryService categoryService = new CategoryService();
        LocationService locationService = new LocationService();
        ReportService reportService = new ReportService();
        LoginService loginService = new LoginService();

        // Example operations

        // Logging in with johndoe
        int loginResult = loginService.login("johndoe", "password123");
        System.out.println(loginResult);

        // Logout
        String logoutResult = loginService.logout(2); // Use the correct session ID
System.out.println(logoutResult);


    }
}
