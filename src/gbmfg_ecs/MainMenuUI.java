package gbmfg_ecs;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class MainMenuUI extends JFrame {

    private JFrame mainMenu;
    private JButton checkoutToolButton;
    private JButton returnToolButton;
    private JButton manageInventoryButton;
    private JButton logoutButton;
    private int sessionId;
    private LocalDateTime sessionExpiry;
    private Timer sessionTimer;
    private InventoryManager invManager;

    public void MainMenuUI() {
        //this.sessionId = sessionId;
        //this.sessionExpiry = LocalDateTime.now().plusMinutes(10); // Set session timeout to 10 minutes
        // Initialize the InventoryManager with this frame at the parent
        initializeUI();
        invManager = new InventoryManager(this);
        //startSessionTimer();
    }

    public void initializeUI() {
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1792, 898);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));

        checkoutToolButton = new JButton("Check Out Tool");
        checkoutToolButton.addActionListener(e -> checkOutTool());
        add(checkoutToolButton);

        returnToolButton = new JButton("Maintenance");
        returnToolButton.addActionListener(e -> maintenance());
        add(returnToolButton);

        manageInventoryButton = new JButton("Manage Inventory");
        manageInventoryButton.addActionListener(e -> inventory());
        add(manageInventoryButton);

        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> logout());
        add(logoutButton);

        setVisible(true);
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
            JOptionPane.showMessageDialog(this, "Session timed out. Please log in again.", "Session Timeout", JOptionPane.WARNING_MESSAGE);
            dispose();
            new LoginUI().setVisible(true);
        }
    }

    private void checkOutTool() {
        JOptionPane.showMessageDialog(this, "Tool Checkout Functionality");
    }

    private void maintenance() {
        // Placeholder for tool return logic
        new MaintenanceGUI().setVisible(true);
        JOptionPane.showMessageDialog(this, "Tool Return Functionality");
    }

    private void inventory() {
        if (mainMenu != null) {
            mainMenu.getContentPane().removeAll();  // Clear the InventoryManager components
            mainMenu.revalidate();  // Revalidate to apply the new components
            mainMenu.repaint();     // Repaint to make sure the UI is refreshed
            invManager.setVisible(true);

//        this.getContentPane().removeAll();  // Clear existing components (main menu buttons)
//        this.repaint();  // Refresh the frame
//        invManager.showInventoryManagerDialog(this);
        }
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            new LoginUI().setVisible(true);
        }
    }

//    public static void main(String[] args) {
//        javax.swing.SwingUtilities.invokeLater(() -> {
//            // new MainMenuUI(-1); // Replace with actual sessionId if needed
//        });
//    }
}

//package gbmfg_ecs;
//
//import java.awt.GridLayout;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JOptionPane;
//
//public class MainMenuUI extends JFrame {
//
//    private InventoryManager invManager;
//    //private EmployeeManager empManager;
//    private JButton checkoutToolButton;
//    private JButton returnToolButton;
//    private JButton manageInventoryButton;
//    private JButton logoutButton;
//
//    public MainMenuUI() {
//        initializeUI();
//        
//        // Initialize the InventoryManager with this frame at the parent
//        invManager = new InventoryManager(this);
//        //empManager = new EmployeeManager(this);
//    }
//
//    void initializeUI() {
//        setTitle("Main Menu");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(1792, 898);
//        setLocationRelativeTo(null);
//        setLayout(new GridLayout(5, 1)); // Adjust layout to add more buttons if needed
//
////        employeeMgmtButton = new JButton("Manage Employees");
////        employeeMgmtButton.addActionListener(e -> manageEmployee());
////        add(employeeMgmtButton);
//        
//        checkoutToolButton = new JButton("Check Out Tool");
//        checkoutToolButton.addActionListener(e -> checkOutTool());
//        add(checkoutToolButton);
//
//        returnToolButton = new JButton("Maintenance");
//        returnToolButton.addActionListener(e -> maintenance());
//        add(returnToolButton);
//
//        manageInventoryButton = new JButton("Manage Inventory");
//        manageInventoryButton.addActionListener(e -> inventory());
//        add(manageInventoryButton);
//
//        logoutButton = new JButton("Logout");
//        logoutButton.addActionListener(e -> logout());
//        add(logoutButton);
//
//        setVisible(true);
//    }
//
////    private void manageEmployee(){
////        this.getContentPane().removeAll();  // Clear existing components (main menu buttons)
////        this.repaint();  // Refresh the frame
////        empManager.showEmployeeServiceDialog(this);
////    }
//    
//    private void checkOutTool() {
//        // Placeholder for tool checkout logic
//        JOptionPane.showMessageDialog(this, "Tool Checkout Functionality");
//    }
//
//    private void maintenance() {
//        // Placeholder for tool maintenance
//        new MaintenanceGUI().setVisible(true);
//    }
//
//    private void inventory() {
//        this.getContentPane().removeAll();  // Clear existing components (main menu buttons)
//        this.repaint();  // Refresh the frame
//        invManager.showInventoryManagerDialog(this);
//    }
//
//    private void logout() {
//        // Placeholder for logout logic
//        int confirm = JOptionPane.showConfirmDialog(this, 
//                "Are you sure you want to logout?", "Confirm Logout", 
//                JOptionPane.YES_NO_OPTION);
//        if (confirm == JOptionPane.YES_OPTION) {
//            // Logic to logout the user and close the application
//            dispose();
//            new LoginUI().setVisible(true); // Reopen the login screen after logout
//        }
//    }
//
//    public static void main(String[] args) {
//        javax.swing.SwingUtilities.invokeLater(() -> {
//            new MainMenuUI();
//        });
//    }
//}
