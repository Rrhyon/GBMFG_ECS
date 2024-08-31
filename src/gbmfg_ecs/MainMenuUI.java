package gbmfg_ecs;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Chandler Perry
 * Program Description: Provides framework for Main Menu UI.
 * Date: August 13, 2024
 */
public class MainMenuUI extends JFrame {

    private JButton checkoutToolButton;
    private JButton maintenanceButton;
    private JButton manageInventoryButton;
    private JButton employeeManagementButton;  // New button for Employee Management
    private JButton logoutButton;
    private int sessionId;
    private LocalDateTime sessionExpiry;
    private Timer sessionTimer;
    private final LoginService loginService;
    private final InventoryManager invManager;
    private final CheckoutTransactionService ctService; // Add CheckoutService instance

    public MainMenuUI(int sessionId) {
        this.sessionId = sessionId;
        this.loginService = new LoginService();
        this.ctService = new CheckoutTransactionService(); // Initialize CheckoutService
        this.invManager = new InventoryManager(this);
        this.sessionExpiry = LocalDateTime.now().plusMinutes(10);
        initializeUI();
        startSessionTimer();
    }

    public void initializeUI() {
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));  // Updated to 5 rows to include new button

        checkoutToolButton = createButton("Check Out Tool", e -> checkOutTool());
        maintenanceButton = createButton("Maintenance", e -> openMaintenance());
        manageInventoryButton = createButton("Manage Inventory", e -> openInventoryManager());
        employeeManagementButton = createButton("Employee Management", e -> openEmployeeManagement()); // New button
        logoutButton = createButton("Logout", e -> logout());

        add(checkoutToolButton);
        add(maintenanceButton);
        add(manageInventoryButton);
        add(employeeManagementButton);  // Add new button to the layout
        add(logoutButton);

        setVisible(true);
    }

    private JButton createButton(String text, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.addActionListener(action);
        return button;
    }

    private void startSessionTimer() {
        sessionTimer = new Timer(true);
        sessionTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                checkSessionTimeout();
            }
        }, 60 * 1000, 60 * 1000); // Check every minute
    }

    private void checkSessionTimeout() {
        if (LocalDateTime.now().isAfter(sessionExpiry)) {
            sessionTimer.cancel();
            JOptionPane.showMessageDialog(this, "Session timed out. Please log in again.", "Session Timeout", JOptionPane.WARNING_MESSAGE);
            logout();
        }
    }

    private void checkOutTool() {
        // Create and display the CheckoutUI window without arguments
        new CheckoutUI().setVisible(true);
    }

    private void openMaintenance() {
        new MaintenanceMenu().setVisible(true);
    }

    private void openInventoryManager() {
        this.getContentPane().removeAll();  // Clear existing components (main menu buttons)
        this.repaint();  // Refresh the frame
        invManager.showInventoryManagerDialog(this);
    }

    private void openEmployeeManagement() {
        new EmployeeManagement().showEmployeeServiceDialog(this);  // Opens Employee Management dialog
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            loginService.logout(sessionId);
            dispose();
            new LoginUI().setVisible(true);
        }
    }
}