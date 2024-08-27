/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Chandler Perry
 * Program Description: Provides framework for Checkout Transaction object 
 * creation.
 * Date: August 13, 2024
 */







package gbmfg_ecs;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.prefs.Preferences;

public class LoginUI extends JFrame {

    private MainMenuUI mainMenu;
    private JButton loginButton;
    private JButton logoutButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox rememberMeCheckBox;
    private LoginService loginService;
    private Preferences preferences;
    private int currentSessionId = -1; // Track the session ID

    public LoginUI() {
        this.mainMenu = new MainMenuUI();  // Initialize main menu here once
        loginService = new LoginService();
        preferences = Preferences.userRoot().node(getClass().getName());
        initializeUI();
        loadSavedCredentials();
    }

    private void initializeUI() {
        setTitle("Login Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2)); // Adjust layout to add 'Remember Me'

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        rememberMeCheckBox = new JCheckBox("Remember Me");
        add(rememberMeCheckBox);

        loginButton = new JButton("Login");
        loginButton.setEnabled(false); // Disable the login button initially
        loginButton.addActionListener(e -> performLogin());
        add(loginButton);

        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> performLogout());
        add(logoutButton);

        // Add document listeners to enable the login button when either field is filled
        usernameField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                toggleLoginButton();
            }

            public void removeUpdate(DocumentEvent e) {
                toggleLoginButton();
            }

            public void changedUpdate(DocumentEvent e) {
                toggleLoginButton();
            }
        });

        passwordField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                toggleLoginButton();
            }

            public void removeUpdate(DocumentEvent e) {
                toggleLoginButton();
            }

            public void changedUpdate(DocumentEvent e) {
                toggleLoginButton();
            }
        });

        setVisible(true);
    }

    private void toggleLoginButton() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        // Enable login button if both fields are filled
        loginButton.setEnabled(!username.isEmpty() && !password.isEmpty());
    }

    private void performLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Validate username and password input
        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Password is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (username.length() < 3) {
            JOptionPane.showMessageDialog(this, "Username must be at least 3 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int sessionId = loginService.login(username, password);

        if (sessionId != -1) {  // Check if login was successful
            currentSessionId = sessionId;  // Store session ID
            JOptionPane.showMessageDialog(this, "Login successful. Welcome!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Save credentials if 'Remember Me' is checked
            if (rememberMeCheckBox.isSelected()) {
                preferences.put("username", username);
                preferences.put("password", password);
            } else {
                preferences.remove("username");
                preferences.remove("password");
            }

            // Show main menu and hide login screen
            mainMenu.getContentPane().removeAll();  // Clear the InventoryManager components
            mainMenu.initializeUI();  // Reinitialize the MainMenuUI components
            mainMenu.revalidate();    // Revalidate to apply the new components
            mainMenu.repaint();       // Repaint to make sure the UI is refreshed
            mainMenu.setVisible(true);
            this.setVisible(false);  // Hide the login screen
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");  // Clear password field
            passwordField.requestFocus();  // Focus on the password field
        }
    }

    private void loadSavedCredentials() {
        String savedUsername = preferences.get("username", "");
        String savedPassword = preferences.get("password", "");
        usernameField.setText(savedUsername);
        passwordField.setText(savedPassword);
        rememberMeCheckBox.setSelected(!savedUsername.isEmpty() && !savedPassword.isEmpty());
        toggleLoginButton(); // Enable/disable the login button based on loaded credentials
    }

    private void performLogout() {
        if (currentSessionId != -1) {
            // Perform logout through the login service
            String result = loginService.logout(currentSessionId);

            // Display logout result to the user
            JOptionPane.showMessageDialog(this, result, "Logout", JOptionPane.INFORMATION_MESSAGE);

            // Reset session ID to indicate no active session
            currentSessionId = -1;

            // Optionally, clear the user interface or navigate to a login screen
            mainMenu.dispose();  // Close the main menu window

            // Show the login screen again
            new LoginUI().setVisible(true);  // Show the login screen
        } else {
            // Inform the user that there's no active session
            JOptionPane.showMessageDialog(this, "No active session to logout.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginUI();
        });
    }
}
