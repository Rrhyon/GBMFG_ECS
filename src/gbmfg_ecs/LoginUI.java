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

    private JButton loginButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox rememberMeCheckBox;
    private Preferences preferences;
    private LoginService loginService;

    public LoginUI() {
        loginService = new LoginService();  // Inject or initialize the LoginService
        preferences = Preferences.userRoot().node(getClass().getName());
        initializeUI();
        loadSavedCredentials();
    }

    private void initializeUI() {
        setTitle("Login Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        rememberMeCheckBox = new JCheckBox("Remember Me");
        add(rememberMeCheckBox);

        loginButton = new JButton("Login");
        loginButton.setEnabled(false);
        loginButton.addActionListener(e -> performLogin());
        add(loginButton);

        // Document listeners to enable/disable login button
        DocumentListener loginButtonEnabler = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                toggleLoginButton();
            }

            public void removeUpdate(DocumentEvent e) {
                toggleLoginButton();
            }

            public void changedUpdate(DocumentEvent e) {
                toggleLoginButton();
            }
        };

        usernameField.getDocument().addDocumentListener(loginButtonEnabler);
        passwordField.getDocument().addDocumentListener(loginButtonEnabler);

        setVisible(true);
    }

    private void toggleLoginButton() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        loginButton.setEnabled(!username.isEmpty() && !password.isEmpty());
    }

    private void performLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Check if the username is at least three characters long
        if (username.length() < 3) {
            JOptionPane.showMessageDialog(this, "Username must be at least 3 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
            usernameField.requestFocus();  // Focus back on the username field
            return;  // Exit the method to prevent further processing
        }

        // Delegating the authentication logic to the service
        int sessionId = loginService.login(username, password, rememberMeCheckBox.isSelected());

        if (sessionId != -1) {
            JOptionPane.showMessageDialog(this, "Login successful. Welcome!", "Success", JOptionPane.INFORMATION_MESSAGE);

            // Instantiate MainMenuUI with the session ID
            MainMenuUI mainMenuUI = new MainMenuUI(sessionId);
            mainMenuUI.setVisible(true);  // Show the main menu

            this.dispose();  // Close the login window
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
        toggleLoginButton();
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new LoginUI());
    }
}
