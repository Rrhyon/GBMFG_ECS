package gbmfg_ecs;

/**
 *
 * @author phillip.tette
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EmployeeManager {

    private final JFrame empFrame;
    private JTable employeeTable;
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    public EmployeeManager(JFrame empFrame) {
        this.empFrame = empFrame;
         
    }

    public void showEmployeeServiceDialog() {
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
        btnAdd.addActionListener((ActionEvent e) -> {
            showCategoryDialog("Add", null);
        });

        btnUpdate.addActionListener((ActionEvent e) -> {
            int selectedRow = employeeTable.getSelectedRow();
            
            if (selectedRow != -1) {
                int employeeId;
                try {
                    employeeId = (int) employeeTable.getValueAt(selectedRow, 0);
                } catch (ClassCastException ex) {
                    JOptionPane.showMessageDialog(dialog, "Error retrieving employee ID. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        },

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = employeeTable.getSelectedRow();
                if (selectedRow != -1) {
                    int employeeId = (int) employeeTable.getValueAt(selectedRow, 0);
                    int confirmation = JOptionPane.showConfirmDialog(dialog,
                            "Are you sure you want to delete this employee?",
                            "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                    
                    if 
                            (confirmation == JOptionPane.YES_OPTION) {
                        EmployeeService.removeEmployee(int empId);
                        refreshEmployeeTable();
                        
                    }
                } else {
                    JOptionPane.showMessageDialog(dialog, "Please select a employee to delete.");
                }
            }
        };

        dialog.setVisible(true);
    }

        private void refreshEmployeeTable() {
        List<Employee> employees = EmployeeList();
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

    private void showCategoryDialog(String action, Category employee) {
        JDialog employeeDialog = new JDialog(Frame, action + " Employee", true);
        employeeDialog.setLayout(new GridLayout(0, 2, 5, 5));
        employeeDialog.setSize(400, 250);

        JTextField empIdField = new JTextField(employee != null ? String.valueOf(employee.employeeId()) : "");
        JTextField empFirstNameField = new JTextField(employee != null ? employee.firstname() : "");
        JTextField empLastNameField = new JTextField(employee != null ? employee.lastName() : "");
        JTextField empMiddleInitialField = new JTextField(employee != null ? employee.middleInitial() : "");
        JTextField empPhoneNumField = new JTextField(employee != null ? employee.phoneNum() : "");
        JTextField empEmailAddressField = new JTextField(employee != null ? employee.emailAddress() : "");
        JTextField empEmpRoleField = new JTextField(employee != null ? employee.empRole() : "");
        JTextField empUsernameField = new JTextField(employee != null ? employee.Username() : "");

        if (action.equals("Edit")) {
            setEditable(false);
        }

        employeeDialog.add(new JLabel("Employee ID:"));
        employeeDialog.add(empIdField);
        employeeDialog.add(new JLabel("Employee First name:"));
        employeeDialog.add(empFirstNameField);
        employeeDialog.add(new JLabel("Employee Last name:"));
        employeeDialog.add(empLastNameField);
        employeeDialog.add(new JLabel("Employee Middle initial:"));
        employeeDialog.add(empMiddleInitialField);
        employeeDialog.add(new JLabel("Employee Email address:"));
        employeeDialog.add(empEmailAddressField);
        employeeDialog.add(new JLabel("Employee Emp Role:"));
        employeeDialog.add(empEmpRoleField);
        employeeDialog.add(new JLabel("Employee Username:"));
        employeeDialog.add(empUsernameField);

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
            JOptionPane.showMessageDialog(employeeDialog, "Employee name cannot be empty.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (firstName.isEmpty()) {
            JOptionPane.showMessageDialog(employeeDialog, "Employee description cannot be empty.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (empId.isEmpty()) {
            JOptionPane.showMessageDialog(employeeDialog, "Employee name cannot be empty.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (empId.isEmpty()) {
            JOptionPane.showMessageDialog(employeeDialog, "Employee name cannot be empty.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (empId.isEmpty()) {
            JOptionPane.showMessageDialog(employeeDialog, "Employee name cannot be empty.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (empId.isEmpty()) {
            JOptionPane.showMessageDialog(employeeDialog, "Employee name cannot be empty.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int employeeId;
        try {
            employeeId = Integer.parseInt(empIdText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(employeeDialog, "Please enter a valid number for Employee ID.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if ("Add".equals(action)) {
                // Check if the employee ID already exists
                if (EmployeeService.CategoryById(employeeId) != null) {
                    JOptionPane.showMessageDialog(employeeDialog, "Employee ID already exists. Please choose a different ID.", "Duplicate ID", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Add new category
                Category newCategory = new Category(employeeId, Name, Desc);
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