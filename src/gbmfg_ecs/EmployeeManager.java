/*// package gbmfg_ecs;
// 
// /**
//  *
//  * @author phillip.tette
//  */
// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.util.List;
// 
// public class EmployeeManager {
// 
//     private JFrame frame;
//     private JTable employeeTable;
//     private EmployeeService employeeService; // Assume this is initialized
// 
//     public EmployeeManager(JFrame frame) {
//         this.frame = frame;
//         employeeService = new EmployeeService(); // Or use dependency injection
//     }
// 
//     public void showCategoryManagerDialog() {
//         JDialog dialog = new JDialog(frame, "Manage Categories", true);
//         dialog.setLayout(new BorderLayout());
//         dialog.setSize(400, 300);
// 
//         JPanel buttonPanel = new JPanel();
//         JButton btnAdd = new JButton("Add Category");
//         JButton btnEdit = new JButton("Edit Category");
//         JButton btnDelete = new JButton("Delete Category");
// 
//         buttonPanel.add(btnAdd);
//         buttonPanel.add(btnEdit);
//         buttonPanel.add(btnDelete);
//         dialog.add(buttonPanel, BorderLayout.SOUTH);
// 
//         // Table for displaying categories
//         employeeTable = new JTable();
//         refreshCategoryTable();
//         dialog.add(new JScrollPane(employeeTable), BorderLayout.CENTER);
// 
//         // Action listeners for buttons
//         btnAdd.addActionListener(new ActionListener() {
//             public void actionPerformed(ActionEvent e) {
//                 showCategoryDialog("Add", null);
//             }
//         });
// 
//         btnEdit.addActionListener(new ActionListener() {
//             public void actionPerformed(ActionEvent e) {
//                 int selectedRow = employeeTable.getSelectedRow();
//                 if (selectedRow != -1) {
//                     int employeeId = (int) employeeTable.getValueAt(selectedRow, 0);
//                     Category employee = employeeService.getCategoryById(employeeId);
//                     showCategoryDialog("Edit", employee);
//                 } else {
//                     JOptionPane.showMessageDialog(dialog, "Please select a employee to edit.");
//                 }
//             }
//         });
// 
//         btnDelete.addActionListener(new ActionListener() {
//             public void actionPerformed(ActionEvent e) {
//                 int selectedRow = employeeTable.getSelectedRow();
//                 if (selectedRow != -1) {
//                     int employeeId = (int) employeeTable.getValueAt(selectedRow, 0);
//                     int confirmation = JOptionPane.showConfirmDialog(dialog,
//                             "Are you sure you want to delete this employee?",
//                             "Confirm Deletion", JOptionPane.YES_NO_OPTION);
// 
//                     if (confirmation == JOptionPane.YES_OPTION) {
//                         employeeService.removeCategory(employeeId);
//                         refreshCategoryTable();
//   
