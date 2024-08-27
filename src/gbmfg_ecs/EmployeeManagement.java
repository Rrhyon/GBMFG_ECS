package gbmfg_ecs;

import java.awt.BorderLayout;
import java.awt.Frame;
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

/**
 *
 * @author phillip.tette
 */
public class EmployeeManagement {

    private MainMenuUI mainMenu;
    private JFrame empFrame;
    private JTable employeeTable;
    private EmployeeService employeeService;

    public EmployeeManagement() {
        employeeService = new EmployeeService();
    }

    public void showEmployeeServiceDialog(JFrame empFrame) {
        JDialog dialog = new JDialog(empFrame, "Manage Employees", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(500, 400);

        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("Add Employee");
        JButton btnUpdate = new JButton("Update Employee");
        JButton btnDelete = new JButton("Delete Employee");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Table for displaying categories
        employeeTable = new JTable();
        refreshEmployeeTable();
        dialog.add(new JScrollPane(employeeTable), BorderLayout.CENTER);

        // Action listeners for buttons
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showEmployeeDialog("Add", null);
            }
        });

        btnUpdate.addActionListener((ActionListener e) -> {
            int selectedRow = employeeTable.getSelectedRow();
            if (selectedRow != -1) {
                int employeeId;
                try {
                    employeeId = (int) employeeTable.getValueAt(selectedRow, 0);
                } catch (ClassCastException ex) {
                    JOptionPane.showMessageDialog(dialog,
                            "Error retrieving employee ID. Please try again.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
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
                        employeeService.removeEmployee(empId);
                        refreshEmployeeTable();

                    }
                } else {
                    JOptionPane.showMessageDialog(dialog, "Please select a employee to delete.");
                }
            }
        });
        dialog.setVisible(true);
    }

    private void refreshEmployeeTable() {
        List<Employee> employees = employeeService.getAllEmployees();
        String[] columnNames = {"EmpID", "Last Name", "First Name", "Middle Initial", "Phone Number", "Email Address", "Emp Role", "Username"};

        Object[][] data = new Object[employees.size()][7];

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
        employeeTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
    }

    private void showEmployeeDialog(String action, Employee employee) {
        JDialog employeeDialog = new JDialog(empFrame, action + " Employee", true);
        employeeDialog.setLayout(new GridLayout(0, 2, 5, 5));
        employeeDialog.setSize(400, 250);

        JTextField empIdField = new JTextField(employee != null ? String.valueOf(employee.getEmpId()) : "");
        JTextField empFirstNameField = new JTextField(employee != null ? employee.getFirstName() : "");
        JTextField empLastNameField = new JTextField(employee != null ? employee.getLastName() : "");
        JTextField empMiddleInitialField = new JTextField(employee != null ? employee.getMiddleInitial() : "");
        JTextField empPhoneNumField = new JTextField(employee != null ? employee.getPhoneNum() : "");
        JTextField empEmailAddressField = new JTextField(employee != null ? employee.getEmailAddress() : "");
        JTextField empEmpRoleField = new JTextField(employee != null ? employee.getEmpRole() : "");
        JTextField empUsernameField = new JTextField(employee != null ? employee.getUsername() : "");

    boolean validInput = false;
        
    while (!validInput) {
        JPanel empPanel = new JPanel(new GridLayout(0,2,5,5));
        empPanel.add(new JLabel("Employee First name:"));
        empPanel.add(empFirstNameField);
        empPanel.add(new JLabel("Employee Last name:"));
        empPanel.add(empLastNameField);
        empPanel.add(new JLabel("Employee Middle initial:"));
        empPanel.add(empMiddleInitialField);
        empPanel.add(new JLabel("Employee Phone Number:"));
        empPanel.add(empPhoneNumField);
        empPanel.add(new JLabel("Employee Email address:"));
        empPanel.add(empEmailAddressField);
        empPanel.add(new JLabel("Employee Emp Role:"));
        empPanel.add(empEmpRoleField);
        empPanel.add(new JLabel("Employee Username:"));
        empPanel.add(empUsernameField);

        JButton btnSave = new JButton(action);
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String empId = empIdField.getText().trim();
                String firstName = empFirstNameField.getText().trim();
                String lastName = empLastNameField.getText().trim();
                String middleInitial = empMiddleInitialField.getText().trim();
                String emailAddress = empEmailAddressField.getText().trim();
                String empRole = empEmpRoleField.getText().trim();
                String username = empUsernameField.getText().trim();

                if (empId.isEmpty()) {
                    JOptionPane.showMessageDialog(employeeDialog, 
                            "Employee name cannot be empty.", "Invalid Input", 
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (firstName.isEmpty()) {
                    JOptionPane.showMessageDialog(employeeDialog, 
                            "Employee description cannot be empty.", "Invalid Input", 
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (empId.isEmpty()) {
                    JOptionPane.showMessageDialog(employeeDialog, 
                            "Employee name cannot be empty.", "Invalid Input", 
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (empId.isEmpty()) {
                    JOptionPane.showMessageDialog(employeeDialog, 
                            "Employee name cannot be empty.", "Invalid Input", 
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (empId.isEmpty()) {
                    JOptionPane.showMessageDialog(employeeDialog, 
                            "Employee name cannot be empty.", "Invalid Input", 
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (empId.isEmpty()) {
                    JOptionPane.showMessageDialog(employeeDialog, 
                            "Employee name cannot be empty.", "Invalid Input", 
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    if ("Add".equals(action)) {
                        // Check if the employee ID already exists
                        if (EmployeeService.EmployeeById(employeeId) != null) {
                            JOptionPane.showMessageDialog(employeeDialog, "Employee ID already exists. Please choose a different ID.", "Duplicate ID", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        // Add new category
                        Employee newEmployee = new Employee(employeeId, Name, Desc);
                        employeeService.saveEmployee(new);
                    } else if ("Edit".equals(action) && employee != null) {
                        // Edit existing category
                        employee.setName(Name);
                        employee.setDescription(Desc);
                        EmployeeService.saveEmployeeUpdates(employee);
                    }

                    employeeDialog.dispose();
                    refreshEmployeeTable();

                } catch (HeadlessException ex) {
                    JOptionPane.showMessageDialog(employeeDialog, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        employeeDialog.add(new JLabel()); // Spacer
        employeeDialog.add(btnSave);
        employeeDialog.setVisible(true);
    }

    private List<Employee> getEmployeeList() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
