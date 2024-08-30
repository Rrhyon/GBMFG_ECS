package gbmfg_ecs;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service 
 * Course: CEIS 400 - Software Engineering II 
 * Authors: Rodney Mills, Cody Wilcox, and Phillip Tette 
 * Program Description: Provides a checkout transaction GUI for customers. 
 * Date: August 24, 2024
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CheckoutUI extends JFrame {

    private MainMenuUI mainMenu;
    private final CheckoutTransactionService ctService;
    private ToolService toolService;
    private LocationService locService;
    private CategoryService catService;
    private JTable tblTool;
    private JTable tblCart;
    private JTable tblCheckedOutTools;  // Table for checked out tools
    private DefaultTableModel cartModel;
    private DefaultTableModel checkedOutModel;  // Model for checked out tools

    public CheckoutUI(MainMenuUI mainMenu) {
        this.mainMenu = mainMenu;
        this.toolService = new ToolServiceImpl();
        this.catService = new CategoryService();
        this.locService = new LocationService();
        this.ctService = new CheckoutTransactionService();
        initializeUI();
    }

    public CheckoutUI() {
        this(null);
    }

    private void initializeUI() {
        setTitle("Checkout System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 900);  // Increased size to accommodate the third table
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1));  // Adjusted grid layout to 4 rows

        mainPanel.add(createToolPanel());
        mainPanel.add(createCartPanel());
        mainPanel.add(createCheckedOutPanel());  // New panel for checked out tools

        add(mainPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton btnCheckout = new JButton("Checkout");
        btnCheckout.addActionListener(this::handleCheckout);
        JButton btnReset = new JButton("Reset Cart");
        btnReset.addActionListener(this::handleReset);
        JButton btnMainMenu = new JButton("Main Menu");
        btnMainMenu.addActionListener(this::handleMainMenu);
        JButton btnCheckIn = new JButton("Check In Tool");  // New check-in button
        btnCheckIn.addActionListener(this::handleCheckInTool);  // Action listener for check-in

        buttonPanel.add(btnCheckout);
        buttonPanel.add(btnReset);
        buttonPanel.add(btnMainMenu);
        buttonPanel.add(btnCheckIn);  // Add the check-in button to the button panel

        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createToolPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Tool List"));

        tblTool = new JTable();
        JScrollPane scrollPane = new JScrollPane(tblTool);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton btnAddToolCart = new JButton("Add Tool to Cart");
        btnAddToolCart.addActionListener(this::handleAddToolToCart);
        panel.add(btnAddToolCart, BorderLayout.SOUTH);

        loadToolData();

        return panel;
    }

    private JPanel createCartPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Cart"));

        tblCart = new JTable();
        cartModel = new DefaultTableModel(new String[]{"Tool ID", "Name", "Description", "Condition", "Available", "Serial", "Category", "Location"}, 0);
        tblCart.setModel(cartModel);
        JScrollPane scrollPane = new JScrollPane(tblCart);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createCheckedOutPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Checked Out Tools"));

        tblCheckedOutTools = new JTable();
        checkedOutModel = new DefaultTableModel(new String[]{"Transaction ID", "Employee ID", "Tool ID", "Checkout Date", "Due Date", "Return Date", "Status"}, 0);
        tblCheckedOutTools.setModel(checkedOutModel);
        JScrollPane scrollPane = new JScrollPane(tblCheckedOutTools);
        panel.add(scrollPane, BorderLayout.CENTER);

        loadCheckedOutToolsData();  // Load data into the checked out tools table

        return panel;
    }

    private void loadToolData() {
        List<Tool> tools = toolService.getAllTools();
        if (tools == null) {
            JOptionPane.showMessageDialog(this, "No tools available", "Error", JOptionPane.ERROR_MESSAGE);
            tools = new ArrayList<>(); // Initialize an empty list to avoid further null issues
        }

        Object[][] data = convertToolListToData(tools);

        // Create the table model with String as the data type for all columns
        DefaultTableModel model = new DefaultTableModel(data, new String[]{"Tool ID", "Name", "Description", "Condition", "Available", "Serial", "Category", "Location"}) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 4) { // Assuming "Available" is the 5th column (index 4)
                    return String.class; // Treat "Available" column as String
                }
                return super.getColumnClass(columnIndex);
            }
        };

        tblTool.setModel(model);
    }

    private void loadCheckedOutToolsData() {
        List<CheckoutTransaction> transactions = ctService.getAllCheckoutTransactions();  // Assuming this method exists
        checkedOutModel.setRowCount(0);  // Clear previous data
        if (transactions != null) {
            for (CheckoutTransaction transaction : transactions) {
                // Filter out transactions with status "Returned"
                if (!"Returned".equals(transaction.getStatus())) {
                    Object[] row = {
                        transaction.getTransactionId(),
                        transaction.getEmpId(),
                        transaction.getToolId(),
                        transaction.getCheckoutDate().toString(),
                        transaction.getDueDate().toString(),
                        transaction.getReturnDate() != null ? transaction.getReturnDate().toString() : "",
                        transaction.getStatus()
                    };
                    checkedOutModel.addRow(row);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No checked out tools found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleAddToolToCart(ActionEvent e) {
        int selectedRow = tblTool.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a tool to add to the cart.");
            return;
        }

        int toolId = (int) tblTool.getValueAt(selectedRow, 0);
        String name = (String) tblTool.getValueAt(selectedRow, 1);
        String description = (String) tblTool.getValueAt(selectedRow, 2);
        String condition = (String) tblTool.getValueAt(selectedRow, 3);
        String available = (String) tblTool.getValueAt(selectedRow, 4);  // Handle as String
        String serial = (String) tblTool.getValueAt(selectedRow, 5);
        String category = (String) tblTool.getValueAt(selectedRow, 6);
        String location = (String) tblTool.getValueAt(selectedRow, 7);

        // Add the selected tool to the cart table
        Object[] newRow = {toolId, name, description, condition, available, serial, category, location};
        cartModel.addRow(newRow);
    }

    private void handleCheckout(ActionEvent e) {
        int empId = getCurrentEmployeeId();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dueDate = now.plusDays(30);

        for (int i = 0; i < cartModel.getRowCount(); i++) {
            int toolId = (int) cartModel.getValueAt(i, 0);

            // Update the tool's availability status to false (checked out)
            Tool tool = toolService.getTool(toolId);
            if (tool != null && tool.isAvailable()) {
                tool.setAvailable(false);  // Mark as unavailable
                toolService.saveToolUpdates(tool);  // Save the updated tool back to the database

                boolean success = ctService.addCheckoutTransaction(empId, toolId, now, dueDate);
                if (!success) {
                    JOptionPane.showMessageDialog(this, "Error processing checkout for tool ID: " + toolId);
                }
            }
        }

        JOptionPane.showMessageDialog(this, "Checkout successful!");
        cartModel.setRowCount(0); // Clear the cart after checkout
        loadCheckedOutToolsData();  // Refresh the checked out tools table
        loadToolData();  // Refresh the tool list to reflect the updated availability
    }

    private void handleReset(ActionEvent e) {
        cartModel.setRowCount(0); // Clear the cart
    }

    private void handleCheckInTool(ActionEvent e) {
        int selectedRow = tblCheckedOutTools.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a tool to check in.");
            return;
        }

        int toolId = (int) tblCheckedOutTools.getValueAt(selectedRow, 2);  // Assuming the tool ID is in column 2
        Tool tool = toolService.getTool(toolId);
        if (tool != null && !tool.isAvailable()) {
            tool.setAvailable(true);  // Mark as available
            toolService.saveToolUpdates(tool);  // Save the updated tool back to the database

            // Update the checkout transaction status
            int transactionId = (int) tblCheckedOutTools.getValueAt(selectedRow, 0);  // Assuming transaction ID is in column 0
            CheckoutTransaction transaction = ctService.getCheckoutTransaction(transactionId);
            if (transaction != null) {
                transaction.setReturnDate(LocalDateTime.now());  // Set the return date to now
                transaction.setStatus("Returned");
                ctService.updateCheckoutTransaction(transaction);

                JOptionPane.showMessageDialog(this, "Tool checked in successfully.");
                loadCheckedOutToolsData();  // Refresh the checked out tools table
                loadToolData();  // Refresh the tool list to reflect the updated availability
            } else {
                JOptionPane.showMessageDialog(this, "Error updating transaction.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tool is already available or not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleMainMenu(ActionEvent e) {
        this.dispose();
        if (mainMenu != null) {
            mainMenu.initializeUI();
        }
    }

    private int getCurrentEmployeeId() {
        // This should be implemented to return the current logged-in employee's ID
        return 1; // Placeholder value
    }

    private Object[][] convertToolListToData(List<Tool> tools) {
        List<Category> categories = catService.getAllCategories();
        List<Location> locations = locService.getAllLocations();
        Object[][] data = new Object[tools.size()][8];
        for (int i = 0; i < tools.size(); i++) {
            Tool tool = tools.get(i);
            String categoryName = "Uncategorized"; // initialize variable
            String locationName = "Unknown"; // initialize variable
            for (Category category : categories) {
                if (tool.getCategoryId() == category.getCategoryId()) {
                    categoryName = category.getName();
                }
            }
            for (Location location : locations) {
                if (tool.getLocationId() == location.getLocationId()) {
                    locationName = location.getName();
                }
            }
            data[i][0] = tool.getToolId();
            data[i][1] = tool.getName();
            data[i][2] = tool.getDescription();
            data[i][3] = tool.getCondition();
            data[i][4] = tool.isAvailable() ? "Yes" : "No";  // Convert boolean to "Yes"/"No"
            data[i][5] = tool.getSerialNum();
            data[i][6] = categoryName;
            data[i][7] = locationName;
        }
        return data;
    }
}
