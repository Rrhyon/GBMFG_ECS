package gbmfg_ecs;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainMenuUI extends JFrame {

    private InventoryManager invManager;
    private JButton checkoutToolButton;
    private JButton returnToolButton;
    private JButton manageInventoryButton;
    private JButton logoutButton;

    public MainMenuUI() {
        initializeUI();
        
        // Initialize the InventoryManager with this frame at the parent
        invManager = new InventoryManager(this);
    }

    void initializeUI() {
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1792, 898);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1)); // Adjust layout to add more buttons if needed

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

    private void checkOutTool() {
        // Placeholder for tool checkout logic
        JOptionPane.showMessageDialog(this, "Tool Checkout Functionality");
    }

    private void maintenance() {
        // Placeholder for tool maintenance
        new MaintenanceGUI().setVisible(true);
    }

    private void inventory() {
        this.getContentPane().removeAll();  // Clear existing components (main menu buttons)
        this.repaint();  // Refresh the frame
        invManager.showInventoryManagerDialog(this);
    }

    private void logout() {
        // Placeholder for logout logic
        int confirm = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to logout?", "Confirm Logout", 
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Logic to logout the user and close the application
            dispose();
            new LoginUI().setVisible(true); // Reopen the login screen after logout
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new MainMenuUI();
        });
    }
}
