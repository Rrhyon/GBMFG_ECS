package gbmfg_ecs;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * EmployeeManagement class for managing employee records via a Swing UI.
 * Interacts with EmployeeServiceImpl to perform operations.
 */
public class EmployeeManagement {

    private MainMenuUI mainMenu;
    private JFrame empFrame;
    private JTable employeeTable;
    private EmployeeService empService;

    public EmployeeManagement() {
        this.empService = new EmployeeServiceImpl();
    }

    public void showEmployeeServiceDialog(JFrame empFrame) {
        JDialog dialog = new JDialog(empFrame, "Manage Employees", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(600, 400);

        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("Add Employee");
        JButton btnUpdate = new JButton("Update Employee");
        JButton btnDelete = new JButton("Delete Employee");
        JButton btnRefresh = new JButton("Refresh");  // New Refresh button
        JButton btnReturn = new JButton("Return to Main Menu"); // Return button

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnRefresh);  // Add Refresh button to panel
        buttonPanel.add(btnReturn); // Add Return button to panel
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Table for displaying employees
        employeeTable = new JTable();
        refreshEmployeeTable();
        dialog.add(new JScrollPane(employeeTable), BorderLayout.CENTER);

        // Action listeners for buttons
        btnAdd.addActionListener((ActionEvent e) -> showEmployeeDialog("Add", null));

        btnUpdate.addActionListener((ActionEvent e) -> {
            int selectedRow = employeeTable.getSelectedRow();
            if (selectedRow != -1) {
                int employeeId;
                try {
                    employeeId = (int) employeeTable.getValueAt(selectedRow, 0);
                    Employee employee = empService.getEmployee(employeeId);
                    if (employee != null) {
                        showEmployeeDialog("Edit", employee);
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Employee not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (ClassCastException ex) {
                    JOptionPane.showMessageDialog(dialog, "Error retrieving employee ID. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(dialog, "Please select an employee to update.");
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = employeeTable.getSelectedRow();
                if (selectedRow != -1) {
                    int employeeId = (int) employeeTable.getValueAt(selectedRow, 0);
                    int confirmation = JOptionPane.showConfirmDialog(dialog,
                            "Are you sure you want to delete this employee?",
                            "Confirm Deletion", JOptionPane.YES_NO_OPTION);

                    if (confirmation == JOptionPane.YES_OPTION) {
                        String result = empService.removeEmployee(employeeId);
                        JOptionPane.showMessageDialog(dialog, result, "Delete Employee", JOptionPane.INFORMATION_MESSAGE);
                        refreshEmployeeTable();
                    }
                } else {
                    JOptionPane.showMessageDialog(dialog, "Please select an employee to delete.");
                }
            }
        });

        // Action listener for the Refresh button
        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshEmployeeTable();  // Refresh the employee table
            }
        });

        // Action listener for the Return to Main Menu button
        btnReturn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // Close the employee management dialog
                if (mainMenu != null) {
                    mainMenu.setVisible(true); // Show the main menu UI
                }
            }
        });

        dialog.setVisible(true);
    }

    private void refreshEmployeeTable() {
        List<Employee> employees = empService.getAllEmployees();
    System.out.println("Number of employees fetched: " + employees.size());  // Debugging line
    for (Employee emp : employees) {
        System.out.println(emp.getEmpId() + " " + emp.getFirstName() + " " + emp.getLastName());  // Print each employee's details
    }
        String[] columnNames = {"EmpID", "First Name", "Last Name", "Middle Initial", "Phone Number", "Email Address", "Emp Role", "Username"};

        // Update the Object array size to 8 (to match the number of columns)
        Object[][] data = new Object[employees.size()][8];

        for (int i = 0; i < employees.size(); i++) {
            Employee emp = employees.get(i);
            data[i][0] = emp.getEmpId();
            data[i][1] = emp.getFirstName();
            data[i][2] = emp.getLastName();
            data[i][3] = emp.getMiddleInitial();
            data[i][4] = emp.getPhoneNum();
            data[i][5] = emp.getEmailAddress();
            data[i][6] = emp.getEmpRole();
            data[i][7] = emp.getUsername();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        employeeTable.setModel(model);
    }

    private void showEmployeeDialog(String action, Employee employee) {
        JDialog employeeDialog = new JDialog(empFrame, action + " Employee", true);
        employeeDialog.setLayout(new GridLayout(0, 2, 5, 5));
        employeeDialog.setSize(400, 250);

        JTextField empFirstNameField = new JTextField(employee != null ? employee.getFirstName() : "");
        JTextField empLastNameField = new JTextField(employee != null ? employee.getLastName() : "");
        JTextField empMiddleInitialField = new JTextField(employee != null ? employee.getMiddleInitial() : "");
        JTextField empPhoneNumField = new JTextField(employee != null ? employee.getPhoneNum() : "");
        JTextField empEmailAddressField = new JTextField(employee != null ? employee.getEmailAddress() : "");
        JTextField empEmpRoleField = new JTextField(employee != null ? employee.getEmpRole() : "");
        JTextField empUsernameField = new JTextField(employee != null ? employee.getUsername() : "");
        JTextField empPasswordField = new JTextField(employee != null ? "" : "");  // Leave password field empty for updates

        JPanel empPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        empPanel.add(new JLabel("Employee First Name:"));
        empPanel.add(empFirstNameField);
        empPanel.add(new JLabel("Employee Last Name:"));
        empPanel.add(empLastNameField);
        empPanel.add(new JLabel("Employee Middle Initial:"));
        empPanel.add(empMiddleInitialField);
        empPanel.add(new JLabel("Employee Phone Number:"));
        empPanel.add(empPhoneNumField);
        empPanel.add(new JLabel("Employee Email Address:"));
        empPanel.add(empEmailAddressField);
        empPanel.add(new JLabel("Employee Role:"));
        empPanel.add(empEmpRoleField);
        empPanel.add(new JLabel("Employee Username:"));
        empPanel.add(empUsernameField);
        empPanel.add(new JLabel("Employee Password:"));
        empPanel.add(empPasswordField);

        employeeDialog.add(empPanel, BorderLayout.CENTER);

        JButton btnSave = new JButton(action);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!validateInput(empFirstNameField, "First Name") ||
                    !validateInput(empLastNameField, "Last Name") ||
                    !validateInput(empMiddleInitialField, "Middle Initial") ||
                    !validateInput(empPhoneNumField, "Phone Number") ||
                    !validateInput(empEmailAddressField, "Email Address") ||
                    !validateInput(empEmpRoleField, "Role") ||
                    !validateInput(empUsernameField, "Username") ||
                    !validateInput(empPasswordField, "Password")) {
                    return;
                }

                if (action.equals("Add")) {
                    if (empService.getEmployeeByUsername(empUsernameField.getText()) != null) {
                        JOptionPane.showMessageDialog(employeeDialog, "Username already exists. Please choose a different username.", "Duplicate Username", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    Employee newEmployee = new Employee(empLastNameField.getText(), empFirstNameField.getText(),
                                                        empMiddleInitialField.getText(), empPhoneNumField.getText(),
                                                        empEmailAddressField.getText(), empEmpRoleField.getText(),
                                                        empUsernameField.getText(), empPasswordField.getText());
                    String result = empService.addEmployee(newEmployee);
                    JOptionPane.showMessageDialog(employeeDialog, result, "Add Employee", JOptionPane.INFORMATION_MESSAGE);
                } else if (action.equals("Edit") && employee != null) {
                    employee.setLastName(empLastNameField.getText());
                    employee.setFirstName(empFirstNameField.getText());
                    employee.setMiddleInitial(empMiddleInitialField.getText());
                    employee.setPhoneNum(empPhoneNumField.getText());
                    employee.setEmailAddress(empEmailAddressField.getText());
                    employee.setEmpRole(empEmpRoleField.getText());
                    employee.setUsername(empUsernameField.getText());
                    employee.setPassword(empPasswordField.getText()); // Assume password change
                    String result = empService.updateEmployee(employee);
                    JOptionPane.showMessageDialog(employeeDialog, result, "Update Employee", JOptionPane.INFORMATION_MESSAGE);
                }

                employeeDialog.dispose();
                refreshEmployeeTable();
            }
        });

        employeeDialog.add(btnSave, BorderLayout.SOUTH);
        employeeDialog.setVisible(true);
    }

    private boolean validateInput(JTextField field, String fieldName) {
        if (field.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(empFrame, fieldName + " cannot be empty.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
