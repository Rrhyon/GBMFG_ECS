/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Chandler Perry
 */

package gbmfg_ecs;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainMenuUI extends JFrame {

    private JButton checkoutToolButton;
    private JButton returnToolButton;
    private JButton manageInventoryButton;
    private JButton logoutButton;

    public MainMenuUI() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1)); // Adjust layout to add more buttons if needed

        checkoutToolButton = new JButton("Check Out Tool");
        checkoutToolButton.addActionListener(e -> checkOutTool());
        add(checkoutToolButton);

        returnToolButton = new JButton("Return Tool");
        returnToolButton.addActionListener(e -> returnTool());
        add(returnToolButton);

        manageInventoryButton = new JButton("Manage Inventory");
        manageInventoryButton.addActionListener(e -> manageInventory());
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

    private void returnTool() {
        // Placeholder for tool return logic
        JOptionPane.showMessageDialog(this, "Tool Return Functionality");
    }

    private void manageInventory() {
        // Placeholder for managing inventory logic
        JOptionPane.showMessageDialog(this, "Manage Inventory Functionality");
    }

    private void logout() {
        // Placeholder for logout logic
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
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
