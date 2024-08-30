package gbmfg_ecs;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ylene Pierre
 */
public class MaintenanceMenu extends JFrame  {
      private JTextField textToolId;
    private JTextField textEmpId;
    private JTextField textDesc;
    private JTextField textStat;
    private JTextField textRecID;
    private int nextId = 1; // Starting ID
    private JTable table;
    private DefaultTableModel tableModel;
    private List<MaintenanceRecord> records; // In-memory list of records

    public MaintenanceMenu() {
        this.setTitle("Maintenance Records Menu");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // Initialize records list
        records = new ArrayList<>();

        // Create UI components
        textToolId = new JTextField(10);
        textEmpId = new JTextField(10);
        textDesc = new JTextField(20);
        textStat = new JTextField(10);
        textRecID = new JTextField(10);

        JButton btnAddRecord = new JButton("Add Record");
        JButton btnUpdateRecord = new JButton("Update Record");
        JButton btnRemoveRecord = new JButton("Remove Record");
        JButton btnGetRecord = new JButton("Get Record");
        JButton btnGetAllRecord = new JButton("Get All Records");

        // Table setup
        tableModel = new DefaultTableModel(new Object[]{"Record ID", "Tool ID", "Employee ID", "Maintenance Date", "Description", "Status"}, 0);
        table = new JTable(tableModel);

        // Layout setup
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        inputPanel.add(new JLabel("Record ID:"));
        inputPanel.add(textRecID);
        inputPanel.add(new JLabel("Tool ID:"));
        inputPanel.add(textToolId);
        inputPanel.add(new JLabel("Employee ID:"));
        inputPanel.add(textEmpId);
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(textDesc);
        inputPanel.add(new JLabel("Status:"));
        inputPanel.add(textStat);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAddRecord);
        buttonPanel.add(btnUpdateRecord);
        buttonPanel.add(btnRemoveRecord);
        buttonPanel.add(btnGetRecord);
        buttonPanel.add(btnGetAllRecord);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        btnAddRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRecord();
            }
        });

        btnUpdateRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRecord();
            }
        });

        btnRemoveRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeRecord();
            }
        });

        btnGetRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayRecord();
            }
        });

        btnGetAllRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAllRecords();
            }
        });
    }

    private void addRecord() {
        try {
            // Gather input data
            int toolId = Integer.parseInt(textToolId.getText());
            int empId = Integer.parseInt(textEmpId.getText());
            LocalDateTime maintenanceDate = LocalDateTime.now(); // Use current date and time
            String desc = textDesc.getText();
            String stat = textStat.getText();

            // Create a MaintenanceRecord instance
            MaintenanceRecord record = new MaintenanceRecord(toolId, empId, maintenanceDate, desc, stat);
            record.setRecordId(nextId); // Assign the next available ID
            
            // Add the record to the in-memory list
            records.add(record);
            
            // Add the record to the table model
            tableModel.addRow(new Object[]{
                record.getRecordId(),
                record.getToolId(),
                record.getEmpId(),
                record.getMaintenanceDate(),
                record.getDescription(),
                record.getStatus()
            });
            
            // Increment the ID for the next record
            nextId++;
            JOptionPane.showMessageDialog(this, "Record added successfully!");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid data.");
        }
        
        // Clear input fields
        textToolId.setText("");
        textEmpId.setText("");
        textDesc.setText("");
        textStat.setText("");
    }

    private void updateRecord() {
        try {
            int recordId = Integer.parseInt(textRecID.getText());
            int toolId = Integer.parseInt(textToolId.getText());
            int empId = Integer.parseInt(textEmpId.getText());
            LocalDateTime maintenanceDate = LocalDateTime.now(); // Use current date and time
            String desc = textDesc.getText();
            String stat = textStat.getText();
            
            for (int i = 0; i < records.size(); i++) {
                MaintenanceRecord record = records.get(i);
                if (record.getRecordId() == recordId) {
                    // Update the record
                    record.setToolId(toolId);
                    record.setEmpId(empId);
                    record.setMaintenanceDate(maintenanceDate);
                    record.setDescription(desc);
                    record.setStatus(stat);

                    // Update the table model
                    tableModel.setValueAt(toolId, i, 1);
                    tableModel.setValueAt(empId, i, 2);
                    tableModel.setValueAt(maintenanceDate, i, 3);
                    tableModel.setValueAt(desc, i, 4);
                    tableModel.setValueAt(stat, i, 5);

                    JOptionPane.showMessageDialog(this, "Record updated successfully!");
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Record not found.");
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid data.");
        }
        // Clear input fields
        textToolId.setText("");
        textEmpId.setText("");
        textDesc.setText("");
        textStat.setText("");
    }
    
    private void removeRecord() {
        try {
            int recordId = Integer.parseInt(textRecID.getText());

            for (int i = 0; i < records.size(); i++) {
                MaintenanceRecord rec = records.get(i);
                if (rec.getRecordId() == recordId) {
                    records.remove(i);
                    tableModel.removeRow(i);

                    JOptionPane.showMessageDialog(this, "Record removed successfully!");
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Record not found.");
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid record ID.");
        }
        // Clear input fields
        textToolId.setText("");
        textEmpId.setText("");
        textDesc.setText("");
        textStat.setText("");
    }

    private void displayRecord() {
        try {
            int recordId = Integer.parseInt(textRecID.getText());

            // Clear previous table data
            tableModel.setRowCount(0);

            for (MaintenanceRecord record : records) {
                if (record.getRecordId() == recordId) {
                    // Display record details in text fields
                    textToolId.setText(String.valueOf(record.getToolId()));
                    textEmpId.setText(String.valueOf(record.getEmpId()));
                    textDesc.setText(record.getDescription());
                    textStat.setText(record.getStatus());

                    // Add the record to the table
                    tableModel.addRow(new Object[]{
                        record.getRecordId(),
                        record.getToolId(),
                        record.getEmpId(),
                        record.getMaintenanceDate(),
                        record.getDescription(),
                        record.getStatus()
                    });

                    JOptionPane.showMessageDialog(this, "Record displayed successfully!");
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Record not found.");
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid record ID.");
        }
        // Clear input fields
        textToolId.setText("");
        textEmpId.setText("");
        textDesc.setText("");
        textStat.setText("");
    }

    private void showAllRecords() {
        // Clear previous table data
        tableModel.setRowCount(0);

        for (MaintenanceRecord record : records) {
            // Add each record to the table
            tableModel.addRow(new Object[]{
                record.getRecordId(),
                record.getToolId(),
                record.getEmpId(),
                record.getMaintenanceDate(),
                record.getDescription(),
                record.getStatus()
            });
        }
        // Clear input fields
        textToolId.setText("");
        textEmpId.setText("");
        textDesc.setText("");
        textStat.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MaintenanceMenu().setVisible(true));
    }
}