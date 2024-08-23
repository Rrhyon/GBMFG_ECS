//package gbmfg_ecs;
//
///**
// *
// * @author phillip.tette
// */
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.util.List;
//
//public class EmployeeManager {
//
//    private JFrame frame;
//    private JTable employeeTable;
//    private EmployeeService employeeService; // Assume this is initialized
//
//    public EmployeeManager(JFrame frame) {
//        this.frame = frame;
//        employeeService = new EmployeeService(); // Or use dependency injection
//    }
//
//    public void showCategoryManagerDialog() {
//        JDialog dialog = new JDialog(frame, "Manage Categories", true);
//        dialog.setLayout(new BorderLayout());
//        dialog.setSize(400, 300);
//
//        JPanel buttonPanel = new JPanel();
//        JButton btnAdd = new JButton("Add Category");
//        JButton btnEdit = new JButton("Edit Category");
//        JButton btnDelete = new JButton("Delete Category");
//
//        buttonPanel.add(btnAdd);
//        buttonPanel.add(btnEdit);
//        buttonPanel.add(btnDelete);
//        dialog.add(buttonPanel, BorderLayout.SOUTH);
//
//        // Table for displaying categories
//        employeeTable = new JTable();
//        refreshCategoryTable();
//        dialog.add(new JScrollPane(employeeTable), BorderLayout.CENTER);
//
//        // Action listeners for buttons
//        btnAdd.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                showCategoryDialog("Add", null);
//            }
//        });
//
//        btnEdit.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                int selectedRow = employeeTable.getSelectedRow();
//                if (selectedRow != -1) {
//                    int employeeId = (int) employeeTable.getValueAt(selectedRow, 0);
//                    Category employee = employeeService.getCategoryById(employeeId);
//                    showCategoryDialog("Edit", employee);
//                } else {
//                    JOptionPane.showMessageDialog(dialog, "Please select a employee to edit.");
//                }
//            }
//        });
//
//        btnDelete.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                int selectedRow = employeeTable.getSelectedRow();
//                if (selectedRow != -1) {
//                    int employeeId = (int) employeeTable.getValueAt(selectedRow, 0);
//                    int confirmation = JOptionPane.showConfirmDialog(dialog,
//                            "Are you sure you want to delete this employee?",
//                            "Confirm Deletion", JOptionPane.YES_NO_OPTION);
//
//                    if (confirmation == JOptionPane.YES_OPTION) {
//                        employeeService.removeCategory(employeeId);
//                        refreshCategoryTable();
//                    }
//                } else {
//                    JOptionPane.showMessageDialog(dialog, "Please select a employee to delete.");
//                }
//            }
//        });
//
//        dialog.setVisible(true);
//    }
//
//        private void refreshCategoryTable() {
//        List<Category> categories = employeeService.getAllCategories();
//        String[] columnNames = {"Category ID", "Name", "Description"};
//        Object[][] data = new Object[categories.size()][3];
//
//        for (int i = 0; i < categories.size(); i++) {
//            Category cat = categories.get(i);
//            data[i][0] = cat.getCategoryId();
//            data[i][1] = cat.getName();
//            data[i][2] = cat.getDescription();
//        }
//
//        employeeTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
//    }
//
//    private void showCategoryDialog(String action, Category employee) {
//        JDialog employeeDialog = new JDialog(frame, action + " Category", true);
//        employeeDialog.setLayout(new GridLayout(0, 2, 5, 5));
//        employeeDialog.setSize(400, 250);
//
//        JTextField catIdField = new JTextField(employee != null ? String.valueOf(employee.getCategoryId()) : "");
//        JTextField catNameField = new JTextField(employee != null ? employee.getName() : "");
//        JTextField catDescField = new JTextField(employee != null ? employee.getDescription() : "");
//
//        if (action.equals("Edit")) {
//            catIdField.setEditable(false);  // Prevent ID from being edited during update
//        }
//
//        employeeDialog.add(new JLabel("Category ID:"));
//        employeeDialog.add(catIdField);
//        employeeDialog.add(new JLabel("Category Name:"));
//        employeeDialog.add(catNameField);
//        employeeDialog.add(new JLabel("Category Description:"));
//        employeeDialog.add(catDescField);
//
//        JButton btnSave = new JButton(action);
//        btnSave.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                if (catNameField.getText().trim().isEmpty()) {
//                    JOptionPane.showMessageDialog(employeeDialog, "Category name cannot be empty.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//                if (catDescField.getText().trim().isEmpty()) {
//                    JOptionPane.showMessageDialog(employeeDialog, "Category description cannot be empty.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//
//                try {
//                    int employeeId = Integer.parseInt(catIdField.getText());
//
//                    // Check if the employee ID already exists
//                    if (action.equals("Add") && employeeService.getCategoryById(employeeId) != null) {
//                        JOptionPane.showMessageDialog(employeeDialog, "Category ID already exists. Please choose a different ID.", "Duplicate ID", JOptionPane.WARNING_MESSAGE);
//                        return;
//                    }
//
//                    if (action.equals("Add")) {
//                        Category newCategory = new Category(employeeId, catNameField.getText(), catDescField.getText());
//                        employeeService.saveCategory(newCategory);
//                    } else if (action.equals("Edit") && employee != null) {
//                        employee.setName(catNameField.getText());
//                        employee.setDescription(catDescField.getText());
//                        employeeService.saveCategoryUpdates(employee);
//                    }
//
//                    employeeDialog.dispose();
//                    refreshCategoryTable();
//
//                } catch (NumberFormatException ex) {
//                    JOptionPane.showMessageDialog(employeeDialog, "Please enter a valid number for Category ID.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
//                }
//            }
//        });
//
//        employeeDialog.add(new JLabel()); // Spacer
//        employeeDialog.add(btnSave);
//        employeeDialog.setVisible(true);
//    }
//}