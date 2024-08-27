package gbmfg_ecs;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Provides an Inventory GUI for customers.
 * Date: August 13, 2024
 */
public class InventoryManager {

    private MainMenuUI mainMenu;
    private JFrame invFrame;
    private JTable tblTools;
    private JTable tblMaterials;
    private ToolService toolService = new ToolServiceImpl();
    private MaterialService matService = new MaterialServiceImpl();
    private CategoryService catService = new CategoryService();
    private LocationService locService = new LocationService();
    private CategoryManager catManager = new CategoryManager();
    private LocationManager locManager = new LocationManager();

    public InventoryManager(MainMenuUI mainMenu) {
        this.mainMenu = mainMenu;
        this.invFrame = new JFrame("Inventory Manager");
        this.tblTools = new JTable();
        this.tblMaterials = new JTable();

    }

    public void showInventoryManagerDialog(JFrame invFrame) {
        // Creating the JFrame
        // JFrame invFrame = new JFrame("Inventory Manager");
        invFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Sets the default size of the window to the desired pixel density
        invFrame.setSize(1728, 972);

        // Centers the frame on the screen
        invFrame.setLocationRelativeTo(null);

        // Enables the resizing of the frame
        invFrame.setResizable(true);

        // Setting the BoxLayout for the main frame, aligned vertically
        invFrame.setLayout(new BoxLayout(invFrame.getContentPane(), BoxLayout.Y_AXIS));

        // Tool Inventory Section
        JLabel lblToolInv = new JLabel("Tool Inventory");
        lblToolInv.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        lblToolInv.setFont(new Font("Arial", Font.BOLD, 24));
        invFrame.add(lblToolInv);

        tblTools = new JTable();
        JScrollPane scpTools = new JScrollPane(tblTools);
        scpTools.setAlignmentX(JScrollPane.CENTER_ALIGNMENT);
        scpTools.setPreferredSize(new Dimension(1000, 200));

        Dimension buttonSize = new Dimension(180, 32);

        // Adding padding to the table
        JPanel pnlToolTable = new JPanel();
        pnlToolTable.setBorder(BorderFactory.createEmptyBorder(10, 18, 0, 18));
        pnlToolTable.setLayout(new BoxLayout(pnlToolTable, BoxLayout.Y_AXIS));
        pnlToolTable.add(scpTools);
        invFrame.add(pnlToolTable);

        JPanel pnlToolActions = new JPanel();
        pnlToolActions.setLayout(new BoxLayout(pnlToolActions, BoxLayout.X_AXIS));

        JButton btnAddTool = new JButton("Add Tool");
        btnAddTool.setFont(new Font("Arial", Font.PLAIN, 18));
        btnAddTool.setPreferredSize(buttonSize);
        btnAddTool.setMaximumSize(buttonSize);
        pnlToolActions.add(btnAddTool);

        pnlToolActions.add(Box.createRigidArea(new Dimension(5, 0))); // padding

        JButton btnUpdateTool = new JButton("Update Tool");
        btnUpdateTool.setFont(new Font("Arial", Font.PLAIN, 18));
        btnUpdateTool.setPreferredSize(buttonSize);
        btnUpdateTool.setMaximumSize(buttonSize);
        pnlToolActions.add(btnUpdateTool);

        pnlToolActions.add(Box.createRigidArea(new Dimension(5, 0)));

        JButton btnRefreshTool = new JButton("Refresh Table");
        btnRefreshTool.setFont(new Font("Arial", Font.PLAIN, 18));
        btnRefreshTool.setPreferredSize(buttonSize);
        btnRefreshTool.setMaximumSize(buttonSize);
        pnlToolActions.add(btnRefreshTool);

        pnlToolActions.add(Box.createRigidArea(new Dimension(5, 0)));

        JButton btnRemoveTool = new JButton("Remove Tool");
        btnRemoveTool.setFont(new Font("Arial", Font.PLAIN, 18));
        btnRemoveTool.setPreferredSize(buttonSize);
        btnRemoveTool.setMaximumSize(buttonSize);
        pnlToolActions.add(btnRemoveTool);

        pnlToolActions.add(Box.createRigidArea(new Dimension(5, 0)));

        JButton btnSearchTool = new JButton("Search");
        btnSearchTool.setFont(new Font("Arial", Font.PLAIN, 18));
        btnSearchTool.setPreferredSize(buttonSize);
        btnSearchTool.setMaximumSize(buttonSize);
        pnlToolActions.add(btnSearchTool);

        pnlToolActions.add(Box.createRigidArea(new Dimension(5, 0)));

        JTextField txtSearchTool = new JTextField(45);
        txtSearchTool.setMaximumSize(new Dimension(Integer.MAX_VALUE, 33));
        txtSearchTool.setFont(new Font("Arial", Font.PLAIN, 16));
        txtSearchTool.setToolTipText("Enter a part of the tool name, description, or serial number");
        pnlToolActions.add(txtSearchTool);

        // Adding the padding around the button panel
        JPanel pnlToolActionsWrapper = new JPanel();
        pnlToolActionsWrapper.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
        pnlToolActionsWrapper.setLayout(new BoxLayout(pnlToolActionsWrapper, BoxLayout.Y_AXIS));
        pnlToolActionsWrapper.add(pnlToolActions);
        invFrame.add(pnlToolActionsWrapper);

        // Material Inventory Section
        JLabel lblMaterialInv = new JLabel("Material Inventory");
        lblMaterialInv.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        lblMaterialInv.setFont(new Font("Arial", Font.BOLD, 24));
        invFrame.add(lblMaterialInv);

        tblMaterials = new JTable();
        JScrollPane scpMaterials = new JScrollPane(tblMaterials);
        scpMaterials.setAlignmentX(JScrollPane.CENTER_ALIGNMENT);
        scpMaterials.setPreferredSize(new Dimension(1000, 200));

        // Adding padding to the table
        JPanel pnlMaterialTable = new JPanel();
        pnlMaterialTable.setBorder(BorderFactory.createEmptyBorder(10, 18, 0, 18));
        pnlMaterialTable.setLayout(new BoxLayout(pnlMaterialTable, BoxLayout.Y_AXIS));
        pnlMaterialTable.add(scpMaterials);
        invFrame.add(pnlMaterialTable);

        JPanel pnlMaterialActions = new JPanel();
        pnlMaterialActions.setLayout(new BoxLayout(pnlMaterialActions, BoxLayout.X_AXIS));

        JButton btnAddMaterial = new JButton("Add Material");
        btnAddMaterial.setFont(new Font("Arial", Font.PLAIN, 18));
        btnAddMaterial.setPreferredSize(buttonSize);
        btnAddMaterial.setMaximumSize(buttonSize);
        pnlMaterialActions.add(btnAddMaterial);

        pnlMaterialActions.add(Box.createRigidArea(new Dimension(5, 0))); // padding

        JButton btnUpdateMaterial = new JButton("Update Material");
        btnUpdateMaterial.setFont(new Font("Arial", Font.PLAIN, 18));
        btnUpdateMaterial.setPreferredSize(buttonSize);
        btnUpdateMaterial.setMaximumSize(buttonSize);
        pnlMaterialActions.add(btnUpdateMaterial);

        pnlMaterialActions.add(Box.createRigidArea(new Dimension(5, 0)));

        JButton btnRefreshMaterial = new JButton("Refresh Table");
        btnRefreshMaterial.setFont(new Font("Arial", Font.PLAIN, 18));
        btnRefreshMaterial.setPreferredSize(buttonSize);
        btnRefreshMaterial.setMaximumSize(buttonSize);
        pnlMaterialActions.add(btnRefreshMaterial);

        pnlMaterialActions.add(Box.createRigidArea(new Dimension(5, 0)));

        JButton btnRemoveMaterial = new JButton("Remove Material");
        btnRemoveMaterial.setFont(new Font("Arial", Font.PLAIN, 18));
        btnRemoveMaterial.setPreferredSize(buttonSize);
        btnRemoveMaterial.setMaximumSize(buttonSize);
        pnlMaterialActions.add(btnRemoveMaterial);

        pnlMaterialActions.add(Box.createRigidArea(new Dimension(5, 0)));

        JButton btnSearchMaterial = new JButton("Search");
        btnSearchMaterial.setFont(new Font("Arial", Font.PLAIN, 18));
        btnSearchMaterial.setPreferredSize(buttonSize);
        btnSearchMaterial.setMaximumSize(buttonSize);
        pnlMaterialActions.add(btnSearchMaterial);

        pnlMaterialActions.add(Box.createRigidArea(new Dimension(5, 0)));

        JTextField txtSearchMaterial = new JTextField(45);
        txtSearchMaterial.setMaximumSize(new Dimension(Integer.MAX_VALUE, 33));
        txtSearchMaterial.setFont(new Font("Arial", Font.PLAIN, 16));
        txtSearchMaterial.setToolTipText("Enter a part of the material name, description, or serial number");
        pnlMaterialActions.add(txtSearchMaterial);

        // Adding the padding around the button panel
        JPanel pnlMaterialActionsWrapper = new JPanel();
        pnlMaterialActionsWrapper.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
        pnlMaterialActionsWrapper.setLayout(new BoxLayout(pnlMaterialActionsWrapper, BoxLayout.Y_AXIS));
        pnlMaterialActionsWrapper.add(pnlMaterialActions);
        invFrame.add(pnlMaterialActionsWrapper);

        // Category and Location Manager Section
        JPanel pnlCatAndLoc = new JPanel();
        pnlCatAndLoc.setLayout(new BoxLayout(pnlCatAndLoc, BoxLayout.X_AXIS));

        JButton btnCatManager = new JButton("Manage Categories");
        pnlCatAndLoc.add(btnCatManager);

        pnlCatAndLoc.add(Box.createRigidArea(new Dimension(5, 0))); // 5px padding

        JButton btnLocManager = new JButton("Manage Locations");
        pnlCatAndLoc.add(btnLocManager);

        pnlCatAndLoc.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        // Adding the padding around the button panel
        JPanel pnlCatAndLocWrapper = new JPanel();
        pnlCatAndLocWrapper.setBorder(BorderFactory.createEmptyBorder(0, 18, 10, 18));
        pnlCatAndLocWrapper.setLayout(new BoxLayout(pnlCatAndLocWrapper, BoxLayout.Y_AXIS));
        pnlCatAndLocWrapper.add(pnlCatAndLoc);
        invFrame.add(pnlCatAndLocWrapper);

        // Main Menu Button
        JButton mainMenu = new JButton("Return to Main Menu");
        mainMenu.setAlignmentX(JButton.CENTER_ALIGNMENT);
        invFrame.add(mainMenu);

        displayToolsWithCatAndLocNames();
        displayMaterialsWithCatAndLocNames();

        // ActionListeners for buttons
        btnAddTool.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterToolData();
            }
        });

        btnUpdateTool.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolUpdater();
            }
        });

        btnRefreshTool.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fetch latest data from services
                List<Tool> tools = toolService.getAllTools();
                List<Category> categories = catService.getAllCategories();
                List<Location> locations = locService.getAllLocations();

                // Handle potential nulls
                if (tools == null) {
                    tools = new ArrayList<>();
                }
                if (categories == null) {
                    categories = new ArrayList<>();
                }
                if (locations == null) {
                    locations = new ArrayList<>();
                }

                // Update the table with the latest data
                displayTools(tools, categories, locations);
            }
        });

        btnRemoveTool.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTool();
            }
        });

        btnSearchTool.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inquiry = txtSearchTool.getText().trim();
                if (!txtSearchTool.getText().isEmpty()) {
                    // Execute the search
                    JOptionPane.showMessageDialog(invFrame, inquiry,
                            "Notice", JOptionPane.WARNING_MESSAGE);
                    List<Tool> results = toolService.searchTools(inquiry);

                    displayTools(results,
                            catService.getAllCategories(),
                            locService.getAllLocations());
                } else {

                    displayToolsWithCatAndLocNames();
                    displayMaterialsWithCatAndLocNames();
                }
            }
        });

        btnAddMaterial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterMaterialData();
            }
        });

        btnUpdateMaterial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                materialUpdater();
            }
        });

        btnRefreshMaterial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fetch latest data from services
                List<Material> materials = matService.getAllMaterials();
                List<Category> categories = catService.getAllCategories();
                List<Location> locations = locService.getAllLocations();

                // Handle potential nulls
                if (materials == null) {
                    materials = new ArrayList<>();
                }
                if (categories == null) {
                    categories = new ArrayList<>();
                }
                if (locations == null) {
                    locations = new ArrayList<>();
                }
                displayMaterials(materials, categories, locations);
            }
        });

        btnRemoveMaterial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteMaterial();
            }
        });

        btnSearchMaterial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inquiry = txtSearchMaterial.getText().trim();
                if (!txtSearchMaterial.getText().isEmpty()) {
                    // Execute the search
                    List<Material> results = matService.searchMaterials(inquiry);
                    displayMaterials(results,
                            catService.getAllCategories(),
                            locService.getAllLocations());
                } else {
                    displayToolsWithCatAndLocNames();
                }
            }
        });

        btnCatManager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the method to show the Location Manager dialog
                catManager.showCategoryManagerDialog(invFrame);
            }
        });

        btnLocManager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the method to show the Location Manager dialog
                locManager.showLocationManagerDialog(invFrame);
            }
        });

        mainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to return to main menu
                returnToMainMenu();
            }
        });
        invFrame.revalidate();
        invFrame.repaint();
        invFrame.setVisible(true);
    }

    public void displayTools(List<Tool> tools, List<Category> categories,
            List<Location> locations) {

        String[] columnNames = {"Tool ID", "Name", "Description", "Condition",
            "Available", "Serial", "Category", "Location"};

        Object[][] data = new Object[tools.size()][8];

        for (int i = 0; i < tools.size(); i++) {
            Tool tool = tools.get(i);
            String categoryName = "Warboy"; // initialize variable
            String locationName = "Thunderdome"; // initialize variable
            for (int j = 0; j < categories.size(); j++) {
                if (tool.getCategoryId() == categories.get(j).getCategoryId()) {
                    categoryName = categories.get(j).getName();
                }
            }
            for (int j = 0; j < locations.size(); j++) {
                if (tool.getLocationId() == locations.get(j).getLocationId()) {
                    locationName = locations.get(j).getName();
                }
            }
            data[i][0] = tool.getToolId();
            data[i][1] = tool.getName();
            data[i][2] = tool.getDescription();
            data[i][3] = tool.getCondition();
            data[i][4] = tool.isAvailable();
            data[i][5] = tool.getSerialNum();
            data[i][6] = categoryName;
            data[i][7] = locationName;
        }
        tblTools.setModel(new DefaultTableModel(data, columnNames));
    }

    public void enterToolData() {
        // Creates vectors for category and location names
        List<String> catNames = new ArrayList<>();
        List<String> locNames = new ArrayList<>();

        // Iterates through categories and locations adding them to the lists
        for (Category category : catService.getAllCategories()) {
            catNames.add(category.getName());
        }

        for (Location location : locService.getAllLocations()) {
            locNames.add(location.getName());
        }

        // Creates the JComboBoxes
        JComboBox<String> catComboBox
                = new JComboBox<>(catNames.toArray(new String[0]));
        JComboBox<String> locComboBox
                = new JComboBox<>(locNames.toArray(new String[0]));

        // Show a dialog to collect tool details
        JTextField toolName = new JTextField();
        JTextField toolDesc = new JTextField();
        JTextField toolCondition = new JTextField();
        JCheckBox isAvailable = new JCheckBox("Available", true);
        JTextField serialNum = new JTextField();
        JComboBox<String> categoryNames = catComboBox;
        JComboBox<String> locationNames = locComboBox;

        // Input validation loop
        boolean validInput = false;

        while (!validInput) {
            // Create a JPanel to hold the input fields
            JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
            panel.add(new JLabel("Tool Name:"));
            panel.add(toolName);
            panel.add(new JLabel("Tool Description:"));
            panel.add(toolDesc);
            panel.add(new JLabel("Tool Condition:"));
            panel.add(toolCondition);
            panel.add(new JLabel("Available:"));
            panel.add(isAvailable);
            panel.add(new JLabel("Tool Serial:"));
            panel.add(serialNum);
            panel.add(new JLabel("Category:"));
            panel.add(categoryNames);
            panel.add(new JLabel("Location:"));
            panel.add(locationNames);

            // Sets the preferred size of the panel
            panel.setPreferredSize(new Dimension(400, 300));

            // Create the JOptionPane using the custom panel
            int option = JOptionPane.showConfirmDialog(null, panel, "Add New Tool",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (option == JOptionPane.OK_OPTION) {
                // Validate input fields
                if (toolName.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Tool Name is required.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    toolName.requestFocus();
                } else if (toolDesc.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Tool Description is required.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    toolDesc.requestFocus();
                } else if (toolCondition.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Tool Condition is required.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    toolCondition.requestFocus();
                } else if (serialNum.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Tool Serial Number is required.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    serialNum.requestFocus();
                } else {
                    // Passes validation test
                    validInput = true;

                    Tool newTool = new Tool(
                            toolName.getText(),
                            toolDesc.getText(),
                            toolCondition.getText(),
                            isAvailable.isSelected(),
                            serialNum.getText(),
                            catService.getCategoryByName(
                                    catComboBox.getSelectedItem().toString())
                                    .getCategoryId(),
                            locService.getLocationByName(
                                    locComboBox.getSelectedItem().toString())
                                    .getLocationId()
                    );

                    // Save the new tool using the ToolService
                    toolService.saveTool(newTool);

                    // Reload the tool list to include the new tool
                    displayToolsWithCatAndLocNames();
                }
            } else {
                break;  // Exit loop if Cancel is clicked
            }
        }
    }

    public void toolUpdater() {
        // Creates vectors for category and location names
        List<String> catNames = new ArrayList<>();
        List<String> locNames = new ArrayList<>();

        // Iterates through categories and locations adding them to the lists
        for (Category category : catService.getAllCategories()) {
            catNames.add(category.getName());
        }
        for (Location location : locService.getAllLocations()) {
            locNames.add(location.getName());
        }

        // Get the selected row index
        int selectedRow = tblTools.getSelectedRow();
        if (selectedRow != -1) {
            // Get the tool ID from the selected row
            int toolId = (int) tblTools.getValueAt(selectedRow, 0);
            Tool tool = toolService.getTool(toolId);

            // Creates the JComboBoxes
            JComboBox<String> catComboBox = new JComboBox<>(catNames.
                    toArray(new String[0]));
            JComboBox<String> locComboBox = new JComboBox<>(locNames.
                    toArray(new String[0]));

            // Show a dialog to collect tool details
            JTextField toolName = new JTextField(tool.getName());
            JTextField toolDesc = new JTextField(tool.getDescription());
            JTextField toolCondition = new JTextField(tool.getCondition());
            JCheckBox isAvailable = new JCheckBox("Available", tool.isAvailable());
            JTextField serialNum = new JTextField(tool.getSerialNum());
            catComboBox.setSelectedItem(catService.getCategoryById(tool.
                    getCategoryId()).getName());
            locComboBox.setSelectedItem(locService.getLocationById(tool.
                    getLocationId()).getName());

            // Input validation loop
            boolean validInput = false;

            while (!validInput) {
                // Create a JPanel to hold the input fields
                JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
                panel.add(new JLabel("Tool Name:"));
                panel.add(toolName);
                panel.add(new JLabel("Tool Description:"));
                panel.add(toolDesc);
                panel.add(new JLabel("Tool Condition:"));
                panel.add(toolCondition);
                panel.add(new JLabel("Available:"));
                panel.add(isAvailable);
                panel.add(new JLabel("Tool Serial:"));
                panel.add(serialNum);
                panel.add(new JLabel("Category:"));
                panel.add(catComboBox);
                panel.add(new JLabel("Location:"));
                panel.add(locComboBox);

                // Sets the preferred size of the panel
                panel.setPreferredSize(new Dimension(400, 300));

                // Create the JOptionPane using the custom panel
                int option = JOptionPane.showConfirmDialog(null, panel, "Update Tool",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (option == JOptionPane.OK_OPTION) {
                    // Validate input fields
                    if (toolName.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Tool Name is required.",
                                "Input Error",
                                JOptionPane.ERROR_MESSAGE);
                        toolName.requestFocus();
                    } else if (toolDesc.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Tool Description is required.",
                                "Input Error",
                                JOptionPane.ERROR_MESSAGE);
                        toolDesc.requestFocus();
                    } else if (toolCondition.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Tool Condition is required.",
                                "Input Error",
                                JOptionPane.ERROR_MESSAGE);
                        toolCondition.requestFocus();
                    } else if (serialNum.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Tool Serial Number is required.",
                                "Input Error",
                                JOptionPane.ERROR_MESSAGE);
                        serialNum.requestFocus();
                    } else {
                        // Passes validation test
                        validInput = true;

                        Tool toolUpdates = new Tool(
                                toolName.getText(),
                                toolDesc.getText(),
                                toolCondition.getText(),
                                isAvailable.isSelected(),
                                serialNum.getText(),
                                catService.getCategoryByName(
                                        catComboBox.getSelectedItem().toString())
                                        .getCategoryId(),
                                locService.getLocationByName(
                                        locComboBox.getSelectedItem().toString())
                                        .getLocationId()
                        );
                        toolUpdates.setToolId(tool.getToolId());

                        // Save the new tool data to the DB
                        toolService.saveToolUpdates(toolUpdates);

                        // Reload the tool list to include the updated tool
                        displayToolsWithCatAndLocNames();
                    }
                } else {
                    break;  // Exit loop if Cancel is clicked
                }
            }
        }
    }

    public void deleteTool() {
        // Gets the selected row's index
        int[] selectedRows = tblTools.getSelectedRows();

        // Validates that at least one row is selected
        if (selectedRows.length > 0) {

            // Confirm tool deletion
            int confirm = JOptionPane.showConfirmDialog(invFrame,
                    "Are you sure you want to remove selected tool(s)?",
                    "Confirm DELETE", JOptionPane.YES_NO_OPTION);

            // Removes the tool from the database and refreshes
            if (confirm == JOptionPane.YES_OPTION) {
                // Loop through the selected row(s), deleting each tool
                // Looping backward to avoid potential index errors
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    // Gets the tool ID for each tool
                    int toolId = (int) tblTools.getValueAt(selectedRows[i], 0);
                    toolService.removeTool(toolId);
                }
                // Refreshes the tool list table
                displayToolsWithCatAndLocNames();
            }
        } else {
            JOptionPane.showMessageDialog(invFrame, "No tools are selected.",
                    "Notice", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void enterMaterialData() {
        // Creates lists for category and location names
        List<String> catNames = new ArrayList<>();
        List<String> locNames = new ArrayList<>();

        // Iterates through categories and locations adding them to the lists
        for (Category category : catService.getAllCategories()) {
            catNames.add(category.getName());
        }
        for (Location location : locService.getAllLocations()) {
            locNames.add(location.getName());
        }

        // Creates the JComboBoxes
        JComboBox<String> catComboBox
                = new JComboBox<>(catNames.toArray(new String[0]));
        JComboBox<String> locComboBox
                = new JComboBox<>(locNames.toArray(new String[0]));

        // Show a dialog to collect material details
        JTextField matName = new JTextField();
        JTextField matDesc = new JTextField();
        JTextField matQuantity = new JTextField();
        JTextField matUnit = new JTextField();

        // Input validation loop
        boolean validInput = false;

        while (!validInput) {
            // Create a JPanel to hold the input fields
            JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
            panel.add(new JLabel("Material Name:"));
            panel.add(matName);
            panel.add(new JLabel("Material Description:"));
            panel.add(matDesc);
            panel.add(new JLabel("Material Quantity:"));
            panel.add(matQuantity);
            panel.add(new JLabel("Material Unit:"));
            panel.add(matUnit);
            panel.add(new JLabel("Category:"));
            panel.add(catComboBox);
            panel.add(new JLabel("Location:"));
            panel.add(locComboBox);

            // Sets the preferred size of the panel
            panel.setPreferredSize(new Dimension(400, 300));

            // Create the JOptionPane using the custom panel
            int option = JOptionPane.showConfirmDialog(null, panel, "Add New Material",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (option == JOptionPane.OK_OPTION) {
                // Validate input fields
                if (matName.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Material Name is required.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    matName.requestFocus();
                } else if (matDesc.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Material Description is required.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    matDesc.requestFocus();
                } else if (matQuantity.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Material Quantity is required.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    matQuantity.requestFocus();
                } else if (matUnit.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Material Unit is required.",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                    matUnit.requestFocus();
                } else {
                    try {
                        double quantity = Double.parseDouble(matQuantity.getText());
                        validInput = true;

                        // Handles the input data
                        Material newMaterial = new Material(
                                matName.getText(),
                                matDesc.getText(),
                                quantity,
                                matUnit.getText(),
                                catService.getCategoryByName(catComboBox.
                                        getSelectedItem().toString())
                                        .getCategoryId(),
                                locService.getLocationByName(locComboBox.
                                        getSelectedItem().toString())
                                        .getLocationId()
                        );

                        // Save the new material using the MaterialService
                        matService.saveMaterial(newMaterial);

                        // Reload the material list to include the new material
                        displayMaterialsWithCatAndLocNames();

                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null,
                                "Please enter a valid number for quantity",
                                "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        matQuantity.requestFocus();  // Keep the focus on the quantity field
                    }
                }
            } else {
                break;  // Exit loop if Cancel is clicked
            }
        }
    }

    public void materialUpdater() {
        // Creates lists for category and location names
        List<String> catNames = new ArrayList<>();
        List<String> locNames = new ArrayList<>();

        // Iterates through categories and locations adding them to the lists
        for (Category category : catService.getAllCategories()) {
            catNames.add(category.getName());
        }
        for (Location location : locService.getAllLocations()) {
            locNames.add(location.getName());
        }

        // Get the selected row index
        int selectedRow = tblMaterials.getSelectedRow();
        if (selectedRow != -1) {
            // Get the material ID from the selected row
            int materialId = (int) tblMaterials.getValueAt(selectedRow, 0);
            Material material = matService.getMaterial(materialId);

            // Creates the JComboBoxes and pre-select the current values
            JComboBox<String> catComboBox
                    = new JComboBox<>(catNames.toArray(new String[0]));
            JComboBox<String> locComboBox
                    = new JComboBox<>(locNames.toArray(new String[0]));

            // Set the current category and location as selected items
            catComboBox.setSelectedItem(catService.getCategoryById(material.
                    getCategoryId()).getName());
            locComboBox.setSelectedItem(locService.getLocationById(material.
                    getLocationId()).getName());

            // Pre-populate the text fields with existing material data
            JTextField matName = new JTextField(material.getName());
            JTextField matDesc = new JTextField(material.getDescription());
            JTextField matQuantity = new JTextField(String.valueOf(material.
                    getQuantity()));
            JTextField matUnit = new JTextField(material.getUnit());
            catComboBox.setSelectedItem(catService.getCategoryById(material.
                    getCategoryId()).getName());
            locComboBox.setSelectedItem(locService.getLocationById(material.
                    getLocationId()).getName());

            // Input validation loop
            boolean validInput = false;

            while (!validInput) {
                // Create a JPanel to hold the input fields
                JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
                panel.add(new JLabel("Material Name:"));
                panel.add(matName);
                panel.add(new JLabel("Material Description:"));
                panel.add(matDesc);
                panel.add(new JLabel("Material Quantity:"));
                panel.add(matQuantity);
                panel.add(new JLabel("Material Unit:"));
                panel.add(matUnit);
                panel.add(new JLabel("Category:"));
                panel.add(catComboBox);
                panel.add(new JLabel("Location:"));
                panel.add(locComboBox);

                // Sets the preferred size of the panel
                panel.setPreferredSize(new Dimension(400, 300));

                // Create the JOptionPane using the custom panel
                int option = JOptionPane.showConfirmDialog(null, panel, "Update Material",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (option == JOptionPane.OK_OPTION) {
                    // Validate input fields
                    if (matName.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Material Name is required.",
                                "Input Error",
                                JOptionPane.ERROR_MESSAGE);
                        matName.requestFocus();
                    } else if (matDesc.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Material Description is required.",
                                "Input Error",
                                JOptionPane.ERROR_MESSAGE);
                        matDesc.requestFocus();
                    } else if (matQuantity.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Material Quantity is required.",
                                "Input Error",
                                JOptionPane.ERROR_MESSAGE);
                        matQuantity.requestFocus();
                    } else {
                        try {
                            Double.parseDouble(matQuantity.getText());
                            validInput = true;  // Set validInput to true only if the input is valid
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null,
                                    "Please enter a valid number for quantity",
                                    "Invalid Input", JOptionPane.ERROR_MESSAGE);
                            matQuantity.requestFocus();
                        }
                    }

                    if (validInput && matUnit.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null,
                                "Material Unit is required.",
                                "Input Error",
                                JOptionPane.ERROR_MESSAGE);
                        matUnit.requestFocus();
                        validInput = false; // Revert to false since this field is invalid
                    }

                    if (validInput) {
                        // Create an updated Material object
                        Material materialUpdates = new Material(
                                material.getMaterialId(),
                                matName.getText(),
                                matDesc.getText(),
                                Double.parseDouble(matQuantity.getText()),
                                matUnit.getText(),
                                catService.getCategoryByName(catComboBox.
                                        getSelectedItem().toString()).getCategoryId(),
                                locService.getLocationByName(locComboBox.
                                        getSelectedItem().toString()).getLocationId()
                        );
                        materialUpdates.setMaterialId(material.getMaterialId());

                        // Save the updated material using the MaterialService
                        matService.saveMaterialUpdates(materialUpdates);

                        // Reload the material list to reflect the changes
                        displayToolsWithCatAndLocNames();
                    }
                } else {
                    break;  // Exit loop if Cancel is clicked
                }
            }
        }
    }

    public void displayMaterials(List<Material> materials,
            List<Category> categories,
            List<Location> locations) {

        String[] columnNames = {"Material ID", "Name", "Description", "Quantity",
            "Unit", "Category", "Location"};

        Object[][] data = new Object[materials.size()][7];

        for (int i = 0; i < materials.size(); i++) {
            Material material = materials.get(i);
            String categoryName = "Uncategorized";
            String locationName = "Nowhere, where you goin?";
            for (int j = 0; j < categories.size(); j++) {
                if (material.getCategoryId() == categories.get(j).getCategoryId()) {
                    categoryName = categories.get(j).getName();
                }
            }
            for (int j = 0; j < locations.size(); j++) {
                if (material.getLocationId() == locations.get(j).getLocationId()) {
                    locationName = locations.get(j).getName();
                }
            }
            data[i][0] = material.getMaterialId();
            data[i][1] = material.getName();
            data[i][2] = material.getDescription();
            data[i][3] = material.getQuantity();
            data[i][4] = material.getUnit();
            data[i][5] = categoryName;
            data[i][6] = locationName;
        }
        tblMaterials.setModel(new DefaultTableModel(data, columnNames));
    }

    public void deleteMaterial() {
        // Gets the selected row's index
        int[] selectedRows = tblMaterials.getSelectedRows();

        // Validates that at least one row is selected
        if (selectedRows.length > 0) {

            // Confirm material deletion
            int confirm = JOptionPane.showConfirmDialog(invFrame,
                    "Are you sure you want to remove selected material(s)?",
                    "Confirm DELETE", JOptionPane.YES_NO_OPTION);

            // Removes the material from the database and refreshes
            if (confirm == JOptionPane.YES_OPTION) {
                // Loop through the selected row(s), deleting each material
                // Looping backward to avoid potential index errors
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    // Gets the material ID for each material
                    int materialId = (int) tblMaterials.getValueAt(selectedRows[i], 0);
                    matService.removeMaterial(materialId);
                }
                // Refreshes the material list table
                displayToolsWithCatAndLocNames();
            }
        } else {
            JOptionPane.showMessageDialog(invFrame, "No materials are selected.",
                    "Notice", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void displayMaterialsWithCatAndLocNames() {
        displayMaterials(matService.getAllMaterials(),
                catService.getAllCategories(),
                locService.getAllLocations());
    }

    public void displayToolsWithCatAndLocNames() {
        displayTools(toolService.getAllTools(),
                catService.getAllCategories(),
                locService.getAllLocations());
    }

    // This will close the inventory interface and open the main menu
    private void returnToMainMenu() {
        if (invFrame != null) {
            mainMenu.getContentPane().removeAll();  // Clear the InventoryManager components
            mainMenu.revalidate();  // Revalidate to apply the new components
            mainMenu.repaint();     // Repaint to make sure the UI is refreshed
            mainMenu.initializeUI();
        }
    }
}
