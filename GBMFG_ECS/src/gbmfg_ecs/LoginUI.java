/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gbmfg_ecs;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginUI extends JFrame {
    private JButton loginButton;
    private JButton logoutButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private LoginService loginService;
    private int currentSessionId = -1; // To store the session ID after login

    public LoginUI() {
        loginService = new LoginService();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Login Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2)); // Adjust layout to add logout button

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(e -> performLogin());
        add(loginButton);

        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> performLogout());
        add(logoutButton);

        setVisible(true);
    }

    private void performLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Perform the login and capture the session ID (assuming session ID is returned as int)
        int sessionId = loginService.login(username, password);

        if (sessionId != -1) { // Assuming -1 indicates a failed login
            currentSessionId = sessionId; // Store the session ID for later logout
            JOptionPane.showMessageDialog(this, "Login successful. Session ID: " + sessionId, "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Login failed. Please check your credentials.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void performLogout() {
        if (currentSessionId != -1) {
            String result = loginService.logout(currentSessionId);
            JOptionPane.showMessageDialog(this, result, "Logout", JOptionPane.INFORMATION_MESSAGE);
            currentSessionId = -1; // Reset session ID after logout
        } else {
            JOptionPane.showMessageDialog(this, "No active session to logout.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginUI();
        });
    }
}

