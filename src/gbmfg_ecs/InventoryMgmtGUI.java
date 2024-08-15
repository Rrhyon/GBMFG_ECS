package gbmfg_ecs;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.table.DefaultTableModel;

/**
 * Program: Gigabyte Manufacturing - Equipment Checkout Service
 * Course: CEIS 400 - Software Engineering II
 * Author: Phillip Tette
 * Program Description: Provides an Inventory GUI for customers.
 * Date: August 13, 2024
 */
public class InventoryMgmtGUI extends javax.swing.JFrame {

    // Service to interact with the DB.
    private ToolService toolService = new ToolServiceImpl();
    private CategoryService catService = new CategoryService();
    private LocationService locService = new LocationService();
    private JSpinner catNamesSpinner;
    private JSpinner locNamesSpinner;
    
    // GUI Properties
    public InventoryMgmtGUI() {
        initComponents();
        
        displayTools(toolService.getAllTools(), 
                catService.getAllCategories(),
                locService.getAllLocations());
                
        // Centers the application form
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblToolTitle = new javax.swing.JLabel();
        scollPaneTools = new javax.swing.JScrollPane();
        tblTools = new javax.swing.JTable();
        btnGetAllTools = new javax.swing.JButton();
        btnAddTool = new javax.swing.JButton();
        btnUpdateToolInfo = new javax.swing.JButton();
        btnRemoveTool = new javax.swing.JButton();
        btnToolSearch = new javax.swing.JButton();
        lblMaterialTitle = new javax.swing.JLabel();
        scrollPaneMaterials = new javax.swing.JScrollPane();
        tblMaterials = new javax.swing.JTable();
        btnAddMaterial = new javax.swing.JButton();
        btnUpdateMaterial = new javax.swing.JButton();
        btnGetAllMaterials = new javax.swing.JButton();
        btnSearchMaterials = new javax.swing.JButton();
        btnRemoveMaterial = new javax.swing.JButton();
        mnbInventory = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("inventoryManagementMenu"); // NOI18N

        lblToolTitle.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblToolTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblToolTitle.setText("Tool Inventory");
        lblToolTitle.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblToolTitle.setFocusable(false);
        lblToolTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblToolTitle.setInheritsPopupMenu(false);

        tblTools.getTableHeader().setReorderingAllowed(false);
        scollPaneTools.setViewportView(tblTools);

        btnGetAllTools.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnGetAllTools.setText("Refresh List");
        btnGetAllTools.setAlignmentY(0.0F);
        btnGetAllTools.setMaximumSize(new java.awt.Dimension(165, 32));
        btnGetAllTools.setMinimumSize(new java.awt.Dimension(165, 32));
        btnGetAllTools.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetAllToolsActionPerformed(evt);
            }
        });

        btnAddTool.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAddTool.setText("Add Tool");
        btnAddTool.setMaximumSize(new java.awt.Dimension(165, 32));
        btnAddTool.setMinimumSize(new java.awt.Dimension(165, 32));
        btnAddTool.setPreferredSize(new java.awt.Dimension(165, 32));
        btnAddTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToolActionPerformed(evt);
            }
        });

        btnUpdateToolInfo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnUpdateToolInfo.setText("Update Tool Info");
        btnUpdateToolInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateToolInfoActionPerformed(evt);
            }
        });

        btnRemoveTool.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnRemoveTool.setText("Remove Tool");
        btnRemoveTool.setMaximumSize(new java.awt.Dimension(165, 32));
        btnRemoveTool.setMinimumSize(new java.awt.Dimension(165, 32));
        btnRemoveTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveToolActionPerformed(evt);
            }
        });

        btnToolSearch.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnToolSearch.setText("Search Tools");
        btnToolSearch.setMaximumSize(new java.awt.Dimension(165, 32));
        btnToolSearch.setMinimumSize(new java.awt.Dimension(165, 32));
        btnToolSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToolSearchActionPerformed(evt);
            }
        });

        lblMaterialTitle.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblMaterialTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMaterialTitle.setText("Material Inventory");

        tblMaterials.getTableHeader().setReorderingAllowed(false);
        scrollPaneMaterials.setViewportView(tblMaterials);

        btnAddMaterial.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAddMaterial.setText("Add Material");
        btnAddMaterial.setMaximumSize(new java.awt.Dimension(165, 32));
        btnAddMaterial.setMinimumSize(new java.awt.Dimension(165, 32));

        btnUpdateMaterial.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnUpdateMaterial.setText("Update Material Info");
        btnUpdateMaterial.setMaximumSize(new java.awt.Dimension(165, 32));
        btnUpdateMaterial.setMinimumSize(new java.awt.Dimension(165, 32));

        btnGetAllMaterials.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnGetAllMaterials.setText("Refresh List");
        btnGetAllMaterials.setMaximumSize(new java.awt.Dimension(165, 32));
        btnGetAllMaterials.setMinimumSize(new java.awt.Dimension(165, 32));

        btnSearchMaterials.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSearchMaterials.setText("Search Materials");
        btnSearchMaterials.setMaximumSize(new java.awt.Dimension(165, 32));
        btnSearchMaterials.setMinimumSize(new java.awt.Dimension(165, 32));

        btnRemoveMaterial.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnRemoveMaterial.setText("Remove Material");
        btnRemoveMaterial.setMaximumSize(new java.awt.Dimension(165, 32));
        btnRemoveMaterial.setMinimumSize(new java.awt.Dimension(165, 32));

        mnbInventory.setName(""); // NOI18N

        jMenu1.setText("File");
        mnbInventory.add(jMenu1);

        jMenu2.setText("Edit");
        mnbInventory.add(jMenu2);

        setJMenuBar(mnbInventory);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblToolTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scollPaneTools)
                    .addComponent(lblMaterialTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollPaneMaterials)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAddTool, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(40, 40, 40)
                        .addComponent(btnUpdateToolInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(40, 40, 40)
                        .addComponent(btnGetAllTools, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(40, 40, 40)
                        .addComponent(btnToolSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(40, 40, 40)
                        .addComponent(btnRemoveTool, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAddMaterial, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                        .addGap(40, 40, 40)
                        .addComponent(btnUpdateMaterial, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                        .addGap(40, 40, 40)
                        .addComponent(btnGetAllMaterials, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                        .addGap(40, 40, 40)
                        .addComponent(btnSearchMaterials, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                        .addGap(40, 40, 40)
                        .addComponent(btnRemoveMaterial, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblToolTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scollPaneTools, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddTool, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdateToolInfo)
                    .addComponent(btnGetAllTools, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemoveTool, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnToolSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblMaterialTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneMaterials, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdateMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGetAllMaterials, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchMaterials, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemoveMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(176, 176, 176))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Refreshes the tool table
    private void btnGetAllToolsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetAllToolsActionPerformed
        displayTools(toolService.getAllTools(), 
                catService.getAllCategories(),
                locService.getAllLocations());
    }//GEN-LAST:event_btnGetAllToolsActionPerformed

    // Generates a popup window to add new tools to the database
    private void btnAddToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToolActionPerformed
        enterToolData();
    }//GEN-LAST:event_btnAddToolActionPerformed

    private void btnUpdateToolInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateToolInfoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateToolInfoActionPerformed

    private void btnToolSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToolSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnToolSearchActionPerformed

    private void btnRemoveToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveToolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRemoveToolActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InventoryMgmtGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InventoryMgmtGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InventoryMgmtGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InventoryMgmtGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InventoryMgmtGUI().setVisible(true);
                
            }
            
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddMaterial;
    private javax.swing.JButton btnAddTool;
    private javax.swing.JButton btnGetAllMaterials;
    private javax.swing.JButton btnGetAllTools;
    private javax.swing.JButton btnRemoveMaterial;
    private javax.swing.JButton btnRemoveTool;
    private javax.swing.JButton btnSearchMaterials;
    private javax.swing.JButton btnToolSearch;
    private javax.swing.JButton btnUpdateMaterial;
    private javax.swing.JButton btnUpdateToolInfo;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JLabel lblMaterialTitle;
    private javax.swing.JLabel lblToolTitle;
    private javax.swing.JMenuBar mnbInventory;
    private javax.swing.JScrollPane scollPaneTools;
    private javax.swing.JScrollPane scrollPaneMaterials;
    private javax.swing.JTable tblMaterials;
    private javax.swing.JTable tblTools;
    // End of variables declaration//GEN-END:variables

    public void displayTools(List<Tool> tools, List<Category> categories,
            List<Location> locations){
               
        String[] columnNames = {"Tool ID", "Name", "Description", "Condition", 
            "Available", "Serial", "Category", "Location"};
        
        Object[][] data = new Object[tools.size()][8];
        
        for (int i = 0; i < tools.size(); i++) {
            Tool tool = tools.get(i);
            String categoryName = "Uncategorized";
            String locationName = "Nowhere, where you goin?";
            for (int j = 0; j < categories.size(); j++) {
                if (tool.getCategoryId() == categories.get(j).getCategoryId()){
                    categoryName = categories.get(j).getName();
                }
            }
            for (int j = 0; j < locations.size(); j++){
                if (tool.getLocationId() == locations.get(j).getLocationId()){
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
    
    public void enterToolData(){
        // Creates vectors for category and location names
        List<String> catNames = new ArrayList<>();
        List<String> locNames = new ArrayList<>();
        
        for (Category category : catService.getAllCategories()){
            catNames.add(category.getName());
        }
        for (Location location : locService.getAllLocations()){
            locNames.add(location.getName());
        }
        
        // Create JSpinner Models and Spinners
        catNamesSpinner = new JSpinner(new SpinnerListModel(catNames));
        locNamesSpinner = new JSpinner(new SpinnerListModel(locNames));

        // Show a dialog to collect tool details
        JTextField toolName = new JTextField();
        JTextField toolDesc = new JTextField();
        JTextField toolCondition = new JTextField();
        JCheckBox isAvailable = new JCheckBox("Available", true);
        JTextField serialNum = new JTextField();
        JSpinner categoryName = new JSpinner();
        JSpinner locationName = new JSpinner();

        
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
        panel.add(catNamesSpinner);
        panel.add(new JLabel("Location:"));
        panel.add(locNamesSpinner);
        

        // Sets the preferred size of the panel
        panel.setPreferredSize(new Dimension(400, 300));

        // Create the JOptionPane using the custom panel
        int option = JOptionPane.showConfirmDialog(null, panel, "Add New Tool", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        // Handle the input data
        if (option == JOptionPane.OK_OPTION) {
            Tool newTool = new Tool(
                toolName.getText(),
                toolDesc.getText(),
                toolCondition.getText(),
                isAvailable.isSelected(),
                serialNum.getText(),
                catService.getCategoryByName(
                        catNamesSpinner.getValue().toString()).getCategoryId(),
                locService.getLocationByName(
                        locNamesSpinner.getValue().toString()).getLocationId()
            );

            System.out.println("Tool Name: " + toolName);
            System.out.println("Tool Description: " + toolDesc);
            System.out.println("Tool Condition: " + toolCondition);
            System.out.println("Available: " + isAvailable);
            System.out.println("Serial: " + serialNum);
            System.out.println("Category Name: " + catNamesSpinner);
            System.out.println("Location Name: " + locNamesSpinner);



            // Save the new tool using the ToolService
            toolService.saveTool(newTool);
            
            // Reload the tool list to include the new tool
            displayTools(toolService.getAllTools(), 
                catService.getAllCategories(),
                locService.getAllLocations());
        }  
    }
}
