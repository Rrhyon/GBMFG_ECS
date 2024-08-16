package gbmfg_ecs;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
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

        scollPaneTools = new javax.swing.JScrollPane();
        tblTools = new javax.swing.JTable();
        lblMaterialTitle = new javax.swing.JLabel();
        scrollPaneMaterials = new javax.swing.JScrollPane();
        tblMaterials = new javax.swing.JTable();
        pnlToolActions = new javax.swing.JPanel();
        btnToolSearch = new javax.swing.JButton();
        btnAddTool = new javax.swing.JButton();
        btnGetAllTools = new javax.swing.JButton();
        btnRemoveTool = new javax.swing.JButton();
        txtToolSearch = new javax.swing.JTextField();
        btnUpdateToolInfo = new javax.swing.JButton();
        pnlMaterialActions = new javax.swing.JPanel();
        btnAddMaterial = new javax.swing.JButton();
        btnUpdateMaterialInfo = new javax.swing.JButton();
        btnGetAllMaterials = new javax.swing.JButton();
        btnRemoveMaterials = new javax.swing.JButton();
        btnSearchMaterial = new javax.swing.JButton();
        txtMaterialSearch = new javax.swing.JTextField();
        lblToolsTitle = new javax.swing.JLabel();
        mnbInventory = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusCycleRoot(false);
        setFocusable(false);
        setName("inventoryManagementMenu"); // NOI18N

        tblTools.getTableHeader().setReorderingAllowed(false);
        scollPaneTools.setViewportView(tblTools);

        lblMaterialTitle.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblMaterialTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMaterialTitle.setText("Material Inventory");
        lblMaterialTitle.setFocusable(false);
        lblMaterialTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblMaterialTitle.setMaximumSize(new java.awt.Dimension(229, 48));
        lblMaterialTitle.setMinimumSize(new java.awt.Dimension(229, 48));
        lblMaterialTitle.setPreferredSize(new java.awt.Dimension(229, 48));

        tblMaterials.getTableHeader().setReorderingAllowed(false);
        scrollPaneMaterials.setViewportView(tblMaterials);

        pnlToolActions.setPreferredSize(new java.awt.Dimension(1400, 44));

        btnToolSearch.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnToolSearch.setText("Search Tools");
        btnToolSearch.setMaximumSize(new java.awt.Dimension(165, 32));
        btnToolSearch.setMinimumSize(new java.awt.Dimension(165, 32));
        btnToolSearch.setPreferredSize(new java.awt.Dimension(165, 32));
        btnToolSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToolSearchActionPerformed(evt);
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

        btnGetAllTools.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnGetAllTools.setText("Refresh List");
        btnGetAllTools.setAlignmentY(0.0F);
        btnGetAllTools.setMaximumSize(new java.awt.Dimension(165, 32));
        btnGetAllTools.setMinimumSize(new java.awt.Dimension(165, 32));
        btnGetAllTools.setPreferredSize(new java.awt.Dimension(165, 32));
        btnGetAllTools.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetAllToolsActionPerformed(evt);
            }
        });

        btnRemoveTool.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnRemoveTool.setText("Remove Tool");
        btnRemoveTool.setMaximumSize(new java.awt.Dimension(165, 32));
        btnRemoveTool.setMinimumSize(new java.awt.Dimension(165, 32));
        btnRemoveTool.setPreferredSize(new java.awt.Dimension(165, 32));
        btnRemoveTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveToolActionPerformed(evt);
            }
        });

        txtToolSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtToolSearch.setToolTipText("Enter text here");
        txtToolSearch.setPreferredSize(new java.awt.Dimension(165, 32));
        txtToolSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtToolSearchActionPerformed(evt);
            }
        });

        btnUpdateToolInfo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnUpdateToolInfo.setText("Update Tool Info");
        btnUpdateToolInfo.setActionCommand("Update Tool Info");
        btnUpdateToolInfo.setMaximumSize(new java.awt.Dimension(165, 32));
        btnUpdateToolInfo.setMinimumSize(new java.awt.Dimension(165, 32));
        btnUpdateToolInfo.setPreferredSize(new java.awt.Dimension(165, 32));
        btnUpdateToolInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateToolInfoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlToolActionsLayout = new javax.swing.GroupLayout(pnlToolActions);
        pnlToolActions.setLayout(pnlToolActionsLayout);
        pnlToolActionsLayout.setHorizontalGroup(
            pnlToolActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlToolActionsLayout.createSequentialGroup()
                .addComponent(btnAddTool, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUpdateToolInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGetAllTools, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRemoveTool, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnToolSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtToolSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlToolActionsLayout.setVerticalGroup(
            pnlToolActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlToolActionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlToolActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlToolActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAddTool, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGetAllTools, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRemoveTool, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnToolSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnUpdateToolInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtToolSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAddMaterial.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAddMaterial.setText("Add Material");
        btnAddMaterial.setMaximumSize(new java.awt.Dimension(165, 32));
        btnAddMaterial.setMinimumSize(new java.awt.Dimension(165, 32));
        btnAddMaterial.setPreferredSize(new java.awt.Dimension(165, 32));

        btnUpdateMaterialInfo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnUpdateMaterialInfo.setText("Update Material Info");
        btnUpdateMaterialInfo.setMaximumSize(new java.awt.Dimension(165, 32));
        btnUpdateMaterialInfo.setMinimumSize(new java.awt.Dimension(165, 32));
        btnUpdateMaterialInfo.setPreferredSize(new java.awt.Dimension(165, 32));
        btnUpdateMaterialInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateMaterialInfoActionPerformed(evt);
            }
        });

        btnGetAllMaterials.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnGetAllMaterials.setText("Refresh List");
        btnGetAllMaterials.setMaximumSize(new java.awt.Dimension(165, 32));
        btnGetAllMaterials.setMinimumSize(new java.awt.Dimension(165, 32));
        btnGetAllMaterials.setPreferredSize(new java.awt.Dimension(165, 32));

        btnRemoveMaterials.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnRemoveMaterials.setText("Remove Material");
        btnRemoveMaterials.setMaximumSize(new java.awt.Dimension(165, 32));
        btnRemoveMaterials.setMinimumSize(new java.awt.Dimension(165, 32));
        btnRemoveMaterials.setPreferredSize(new java.awt.Dimension(165, 32));

        btnSearchMaterial.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnSearchMaterial.setText("Search Materials");
        btnSearchMaterial.setMaximumSize(new java.awt.Dimension(165, 32));
        btnSearchMaterial.setMinimumSize(new java.awt.Dimension(165, 32));
        btnSearchMaterial.setPreferredSize(new java.awt.Dimension(165, 32));

        txtMaterialSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMaterialSearch.setToolTipText("Enter text here");
        txtMaterialSearch.setPreferredSize(new java.awt.Dimension(165, 32));

        javax.swing.GroupLayout pnlMaterialActionsLayout = new javax.swing.GroupLayout(pnlMaterialActions);
        pnlMaterialActions.setLayout(pnlMaterialActionsLayout);
        pnlMaterialActionsLayout.setHorizontalGroup(
            pnlMaterialActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMaterialActionsLayout.createSequentialGroup()
                .addComponent(btnAddMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUpdateMaterialInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGetAllMaterials, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRemoveMaterials, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearchMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaterialSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMaterialActionsLayout.setVerticalGroup(
            pnlMaterialActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMaterialActionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMaterialActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtMaterialSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlMaterialActionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUpdateMaterialInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGetAllMaterials, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRemoveMaterials, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearchMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAddMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblToolsTitle.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lblToolsTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblToolsTitle.setText("Tool Inventory");
        lblToolsTitle.setFocusable(false);
        lblToolsTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblToolsTitle.setMaximumSize(new java.awt.Dimension(229, 48));
        lblToolsTitle.setMinimumSize(new java.awt.Dimension(229, 48));
        lblToolsTitle.setPreferredSize(new java.awt.Dimension(229, 48));

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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblMaterialTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6))
                    .addComponent(pnlToolActions, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1302, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblToolsTitle, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlMaterialActions, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(scrollPaneMaterials, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scollPaneTools))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblToolsTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scollPaneTools, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlToolActions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMaterialTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneMaterials, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlMaterialActions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(165, 165, 165))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Refreshes the tool table
    private void btnGetAllToolsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetAllToolsActionPerformed
        displayToolsWithCatAndLocNames();
    }//GEN-LAST:event_btnGetAllToolsActionPerformed

    // Generates a popup window to add new tools to the database
    private void btnAddToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToolActionPerformed
        enterToolData();
    }//GEN-LAST:event_btnAddToolActionPerformed

    private void btnToolSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToolSearchActionPerformed
                String inquiry = txtToolSearch.getText().trim();
        if(!txtToolSearch.getText().isEmpty()){
            // Execute the search
            JOptionPane.showMessageDialog(this, inquiry,
                    "Notice", JOptionPane.WARNING_MESSAGE);
            List<Tool> results = toolService.searchTools(inquiry);
            
                displayTools(results, 
                        catService.getAllCategories(),
                        locService.getAllLocations());
        }else{
        
        displayToolsWithCatAndLocNames();
        }
    }//GEN-LAST:event_btnToolSearchActionPerformed

    private void btnRemoveToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveToolActionPerformed
        deleteTool();
    }//GEN-LAST:event_btnRemoveToolActionPerformed

    private void txtToolSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtToolSearchActionPerformed

    }//GEN-LAST:event_txtToolSearchActionPerformed

    private void btnUpdateMaterialInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateMaterialInfoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateMaterialInfoActionPerformed

    private void btnUpdateToolInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateToolInfoActionPerformed
        toolUpdater();
    }//GEN-LAST:event_btnUpdateToolInfoActionPerformed

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
    private javax.swing.JButton btnRemoveMaterials;
    private javax.swing.JButton btnRemoveTool;
    private javax.swing.JButton btnSearchMaterial;
    private javax.swing.JButton btnToolSearch;
    private javax.swing.JButton btnUpdateMaterialInfo;
    private javax.swing.JButton btnUpdateToolInfo;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JLabel lblMaterialTitle;
    private javax.swing.JLabel lblToolsTitle;
    private javax.swing.JMenuBar mnbInventory;
    private javax.swing.JPanel pnlMaterialActions;
    private javax.swing.JPanel pnlToolActions;
    private javax.swing.JScrollPane scollPaneTools;
    private javax.swing.JScrollPane scrollPaneMaterials;
    private javax.swing.JTable tblMaterials;
    private javax.swing.JTable tblTools;
    private javax.swing.JTextField txtMaterialSearch;
    private javax.swing.JTextField txtToolSearch;
    // End of variables declaration//GEN-END:variables

    public void enterToolData(){
        // Creates vectors for category and location names
        List<String> catNames = new ArrayList<>();
        List<String> locNames = new ArrayList<>();
        
        // Iterates through categories and locations adding them to the lists
        for (Category category : catService.getAllCategories()){
            catNames.add(category.getName());
        }
        for (Location location : locService.getAllLocations()){
            locNames.add(location.getName());
        }

        // Creates the JComboBoxes
        JComboBox<String> catComboBox = 
                new JComboBox<>(catNames.toArray(new String[0]));
        JComboBox<String> locComboBox = 
                new JComboBox<>(locNames.toArray(new String[0]));

        // Show a dialog to collect tool details
        JTextField toolName = new JTextField();
        JTextField toolDesc = new JTextField();
        JTextField toolCondition = new JTextField();
        JCheckBox isAvailable = new JCheckBox("Available", true);
        JTextField serialNum = new JTextField();
        JComboBox categoryNames = catComboBox;
        JComboBox locationNames = locComboBox;
        
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

        // Handles the input data
        if (option == JOptionPane.OK_OPTION) {
            Tool newTool = new Tool(
                toolName.getText(),
                toolDesc.getText(),
                toolCondition.getText(),
                isAvailable.isSelected(),
                serialNum.getText(),
                catService.getCategoryByName(
                        catComboBox.getSelectedItem().toString()).getCategoryId(),
                locService.getLocationByName(
                        locComboBox.getSelectedItem().toString()).getLocationId()
            );

            // Save the new tool using the ToolService
            toolService.saveTool(newTool);
            
            // Reload the tool list to include the new tool
            displayToolsWithCatAndLocNames();
        }  
    }
    
    public void toolUpdater(){
        // Creates vectors for category and location names
        List<String> catNames = new ArrayList<>();
        List<String> locNames = new ArrayList<>();
        
        // Iterates through categories and locations adding them to the lists
        for (Category category : catService.getAllCategories()){
            catNames.add(category.getName());
        }
        for (Location location : locService.getAllLocations()){
            locNames.add(location.getName());
        }

        // Get the selected row index
        int selectedRow = tblTools.getSelectedRow();
        if (selectedRow != -1) {
            // Get the tool ID from the selected row
            int toolId = (int) tblTools.getValueAt(selectedRow, 0);
            Tool tool = toolService.getTool(toolId);
        

        // Creates the JComboBoxes
        JComboBox<String> catComboBox = 
                new JComboBox<>(catNames.toArray(new String[0]));
        JComboBox<String> locComboBox = 
                new JComboBox<>(locNames.toArray(new String[0]));

        // Show a dialog to collect tool details
        JTextField toolName = new JTextField(tool.getName());
        JTextField toolDesc = new JTextField(tool.getDescription());
        JTextField toolCondition = new JTextField(tool.getCondition());
        JCheckBox isAvailable = new JCheckBox("Available", tool.isAvailable());
        JTextField serialNum = new JTextField(tool.getSerialNum());
        catComboBox.setSelectedItem(catService.getCategoryById(tool.getCategoryId()).getName());
        locComboBox.setSelectedItem(locService.getLocationById(tool.getLocationId()).getName());
        
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

        // Handles the input data
        if (option == JOptionPane.OK_OPTION) {
                tool = new Tool(
                tool.getToolId(),
                toolName.getText(),
                toolDesc.getText(),
                toolCondition.getText(),
                isAvailable.isSelected(),
                serialNum.getText(),
                catService.getCategoryByName(
                        catComboBox.getSelectedItem().toString()).getCategoryId(),
                locService.getLocationByName(
                        locComboBox.getSelectedItem().toString()).getLocationId()
            );
            // Save the new tool using the ToolService
            toolService.saveToolUpdates(tool);
            }
        }
    }
    
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
    
    public void displayToolsWithCatAndLocNames(){
            displayTools(toolService.getAllTools(), 
                catService.getAllCategories(),
                locService.getAllLocations());
    }
    
    public void deleteTool(){
        // Gets the selected row's index
        int[] selectedRows = tblTools.getSelectedRows();
        
        // Validates that at least one row is selected
        if (selectedRows.length > 0){
                                    
            // Confirm tool deletion
            int confirm = JOptionPane.showConfirmDialog(this, 
                    "Are you sure you want to remove selected tool(s)?", 
                    "Confirm DELETE", JOptionPane.YES_NO_OPTION);
            
            // Removes the tool from the database and refreshes
            if(confirm == JOptionPane.YES_OPTION){
                // Loop through the selected row(s), deleting each tool
                // Looping backward to avoid potential index errors
                for(int i = selectedRows.length - 1; i >= 0; i--){
                    // Gets the tool ID for each tool
                    int toolId = (int) tblTools.getValueAt(selectedRows[i], 0);
                    toolService.removeTool(toolId);
                }
                // Refreshes the tool list table
                displayToolsWithCatAndLocNames();
            }
        }else{
            JOptionPane.showMessageDialog(this, "No tools are selected.",
                    "Notice", JOptionPane.WARNING_MESSAGE);
        }
    }
}
