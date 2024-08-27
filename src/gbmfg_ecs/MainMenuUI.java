package gbmfg_ecs;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class MainMenuUI extends JFrame {

    private JButton checkoutToolButton;
    private JButton maintenanceButton;
    private JButton manageInventoryButton;
    private JButton logoutButton;
    private int sessionId;
    private LocalDateTime sessionExpiry;
    private Timer sessionTimer;
    private final LoginService loginService;
    private final InventoryManager invManager;

    public MainMenuUI(int sessionId) {
        this.sessionId = sessionId;
        this.loginService = new LoginService();
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
        setLayout(new GridLayout(4, 1));

        checkoutToolButton = createButton("Check Out Tool", e -> checkOutTool());
        maintenanceButton = createButton("Maintenance", e -> openMaintenance());
        manageInventoryButton = createButton("Manage Inventory", e -> openInventoryManager());
        logoutButton = createButton("Logout", e -> logout());

        add(checkoutToolButton);
        add(maintenanceButton);
        add(manageInventoryButton);
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
        JOptionPane.showMessageDialog(this, "Tool Checkout Functionality is not yet implemented.");
        // Call the relevant service or UI for tool checkout here
    }

    private void openMaintenance() {
        new MaintenanceGUI().setVisible(true);
    }

    private void openInventoryManager() {
        this.getContentPane().removeAll();  // Clear existing components (main menu buttons)
        this.repaint();  // Refresh the frame
        invManager.showInventoryManagerDialog(this);
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