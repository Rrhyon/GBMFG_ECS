package gbmfg_ecs;

import gbmfg_ecs.CheckoutTransaction;
import gbmfg_ecs.CheckoutTransactionService;
import gbmfg_ecs.MainMenuUI;
import gbmfg_ecs.MaterialService;
import gbmfg_ecs.MaterialServiceImpl;
import gbmfg_ecs.SessionService;
import gbmfg_ecs.ToolService;
import gbmfg_ecs.ToolServiceImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author mills
 */
public class CheckoutGUI extends javax.swing.JFrame {

    private ToolService toolService = new ToolServiceImpl();

    private CheckoutTransactionService cTService = new CheckoutTransactionService();
    private MaterialService matService = new MaterialServiceImpl();
    private SessionService sesService = new SessionService();

    private CategoryService catService = new CategoryService();
    private LocationService locService = new LocationService();

//    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/gbmfg_ecs";
//    private static final String JDBC_USER = "root";
//    private static final String JDBC_PASSWORD = "FunkoPop09!!";
    /*
     * Creates new form CheckoutGUI
     */
    public CheckoutGUI() {
        initComponents();
        populateToolTable();
        populateMaterialTable();
    }

    private void populateToolTable() {
        actualToolPopulate(toolService.getAllTools(),
                catService.getAllCategories(),
                locService.getAllLocations());
    }

    private void actualToolPopulate(List<Tool> tools, List<Category> categories,
            List<Location> locations) {
        String[] columnNames = {"Tool ID", "Name", "Description", "Condition",
            "Available", "Serial", "Category", "Location"};

        Object[][] data = new Object[tools.size()][8];

        for (int i = 0; i < tools.size(); i++) {
            Tool tool = tools.get(i);
            String categoryName = "Uncategorized";
            String locationName = "Nowhere, where you goin?";
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
        tblTool.setModel(new DefaultTableModel(data, columnNames));
    }

    private void addToolToCart() {
        int empId = sesService.getSession(HEIGHT).getEmpId();
        System.out.println("Employer ID: " + empId); // Debugging Statement DELETE

        int selectedRow = tblTool.getSelectedRow();
        int toolId = (int) tblTool.getValueAt(selectedRow, 0);
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a tool to add to the cart.");
        }

        LocalDateTime dueDate = LocalDateTime.now().plusDays(14);
        String status = "Checked Out";
        System.out.println("Selected row: " + selectedRow);
        System.out.println("Now: " + LocalDateTime.now());
        System.out.println("Due: " + dueDate);

        List<CheckoutTransaction> toolToCheckout = new ArrayList<>();

        for (CheckoutTransaction toCart : toolToCheckout) {
            CheckoutTransaction transaction = new CheckoutTransaction(
                    empId,
                    toolId,
                    LocalDateTime.now(),
                    dueDate,
                    null,
                    status
            );

            cTService.addCheckoutTransaction(transaction);
        }
    }

    private void populateMaterialTable() {
        actualMaterialPopulate(matService.getAllMaterials(),
                catService.getAllCategories(),
                locService.getAllLocations());
    }

    private void actualMaterialPopulate(List<Material> materials, List<Category> categories, List<Location> locations) {
//        List<Location> locations        
        
        String[] columnNames = {"Material ID", "Name", "Description", "Quantity",
                "Unit", "Category", "Location"};

            Object[][] data = new Object[materials.size()][8];

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
            tblMaterial.setModel(new DefaultTableModel(data, columnNames));
        }

//    private void populateMaterialTable() {
//        // Define your SQL query
//        String query = "SELECT materialId, matName, matDesc, matQuantity, matUnit, categoryId, locationId FROM material";
//
//        // Create lists to hold the data
//        List<Object[]> data = new ArrayList<>();
//        String[] columnNames = {"Material ID", "Name", "Description", "Quantity", "Unit", "Category", "Location"};
//
//        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD); PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
//
//            // Process the result set
//            while (rs.next()) {
//                // Create an array to hold the row data
//                Object[] row = new Object[7];
//                row[0] = rs.getInt("materialId");
//                row[1] = rs.getString("matName");
//                row[2] = rs.getString("matDesc");
//                row[3] = rs.getInt("matQuantity");
//                row[4] = rs.getString("matUnit");
//                row[5] = getCategoryName(rs.getInt("categoryId"));
//                row[6] = getLocationName(rs.getInt("locationId"));
//
//                // Add the row data to the list
//                data.add(row);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace(); // Handle the exception appropriately in production code
//        }
//
//        // Convert list to array
//        Object[][] dataArray = data.toArray(new Object[0][]);
//
//        // Update the table model
//        tblMaterial.setModel(new DefaultTableModel(dataArray, columnNames));
//    }
//
//    private String getCategoryName(int categoryId) {
//        // Implement logic to retrieve category name by ID
//        // For simplicity, use a placeholder
//        return "Category" + categoryId;
//    }
//
//    private String getLocationName(int locationId) {
//        // Implement logic to retrieve location name by ID
//        // For simplicity, use a placeholder
//        return "Location" + locationId;
//    }
//        String toolId = (String) tblTool.getValueAt(selectedRow, 0);
//        String empId = "EMP123"; 
//        Date checkoutDate = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String checkoutDateStr = sdf.format(checkoutDate);
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(checkoutDate);
//        calendar.add(Calendar.DAY_OF_MONTH, 30);
//        Date dueDate = calendar.getTime();
//        String dueDateStr = sdf.format(dueDate);
//
//        String returnDateStr = ""; // To be filled later when the tool is returned
//        String status = "Checked Out";
//
//        String insertQuery = "INSERT INTO cart (transactionId, empId, toolId, checkoutDate, dueDate, returnDate, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
//
//        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
//             PreparedStatement stmt = conn.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
//
//            // Generate a new transaction ID
//            stmt.executeUpdate("SET @last_id = (SELECT IFNULL(MAX(transactionId), 0) FROM cart)");
//            stmt.executeUpdate("SET @new_id = @last_id + 1");
//            stmt.setInt(1, getGeneratedTransactionId(conn)); // Implement this method to get a new transaction ID
//            stmt.setString(2, empId);
//            stmt.setString(3, toolId);
//            stmt.setString(4, checkoutDateStr);
//            stmt.setString(5, dueDateStr);
//            stmt.setString(6, returnDateStr);
//            stmt.setString(7, status);
//
//            stmt.executeUpdate();
//
//            // Refresh the cart table
//            populateCartTable();
//
//        } catch (SQLException e) {
//            e.printStackTrace(); // Handle the exception appropriately in production code
//        }
//    private int getGeneratedTransactionId(Connection conn) throws SQLException {
//        String query = "SELECT IFNULL(MAX(transactionId), 0) FROM cart";
//        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
//            if (rs.next()) {
//                return rs.getInt(1) + 1;
//            }
//        }
//        return 1; // Start with 1 if no entries are present
//    }
//
//    private void populateCartTable() {
//        String query = "SELECT transactionId, empId, toolId, checkoutDate, dueDate, returnDate, status FROM cart";
//
//        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
//             PreparedStatement stmt = conn.prepareStatement(query);
//             ResultSet rs = stmt.executeQuery()) {
//
//            DefaultTableModel model = new DefaultTableModel(
//                new Object[][] {},
//                new String[] {
//                    "Transaction ID", "Employee ID", "Tool ID", "Checkout Date", "Due Date", "Return Date", "Status"
//                }
//            );
//
//            while (rs.next()) {
//                Object[] row = new Object[7];
//                row[0] = rs.getString("transactionId");
//                row[1] = rs.getString("empId");
//                row[2] = rs.getString("toolId");
//                row[3] = rs.getString("checkoutDate");
//                row[4] = rs.getString("dueDate");
//                row[5] = rs.getString("returnDate");
//                row[6] = rs.getString("status");
//                model.addRow(row);
//            }
//
//            tblCart.setModel(model);
//
//        } catch (SQLException e) {
//            e.printStackTrace(); // Handle the exception appropriately in production code
//        }
//    }
        /**
         * This method is called from within the constructor to initialize the
         * form. WARNING: Do NOT modify this code. The content of this method is
         * always regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTool = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblMaterial = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblCart = new javax.swing.JTable();
        btnCheckout = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnMainMenu = new javax.swing.JButton();
        btnAddToolCart = new javax.swing.JButton();
        btnMaterial = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 255, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Checkout System");
        jLabel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jLabel1ComponentShown(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Tool List");

        tblTool.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tool ID", "Name", "Description", "Condition", "Available", "Serial", "Category", "Location"
            }
        ));
        jScrollPane2.setViewportView(tblTool);

        tblMaterial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Material ID", "Name", "Description", "Quantity", "Unit", "Category", "Location"
            }
        ));
        jScrollPane3.setViewportView(tblMaterial);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Material List");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Cart");

        tblCart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Transaction ID", "Employee ID", "Tool ID", "Checkout Date", "Due Date", "Return Date", "Status"
            }
        ));
        jScrollPane4.setViewportView(tblCart);

        btnCheckout.setText("Checkout");
        btnCheckout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckoutActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnMainMenu.setText("Main menu");
        btnMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenuActionPerformed(evt);
            }
        });

        btnAddToolCart.setText("Add tool to cart");
        btnAddToolCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToolCartActionPerformed(evt);
            }
        });

        btnMaterial.setText("Add material to cart");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addComponent(btnAddToolCart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnMaterial)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(188, 188, 188))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(472, 472, 472)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(503, 503, 503)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCheckout, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(547, 547, 547)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(137, 137, 137)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddToolCart)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnMaterial)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReset)
                    .addComponent(btnMainMenu)
                    .addComponent(btnCheckout))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jLabel1ComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1ComponentShown

    private void btnCheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckoutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCheckoutActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainMenuActionPerformed
        this.dispose(); // Close the current window

        // Create and display the MainMenuUI
        MainMenuUI mainMenu = new MainMenuUI();
        mainMenu.setVisible(true);
    }//GEN-LAST:event_btnMainMenuActionPerformed

    private void btnAddToolCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToolCartActionPerformed
        addToolToCart();
    }//GEN-LAST:event_btnAddToolCartActionPerformed

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
            java.util.logging.Logger.getLogger(CheckoutGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckoutGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckoutGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckoutGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CheckoutGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddToolCart;
    private javax.swing.JButton btnCheckout;
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JButton btnMaterial;
    private javax.swing.JButton btnReset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable tblCart;
    private javax.swing.JTable tblMaterial;
    private javax.swing.JTable tblTool;
    // End of variables declaration//GEN-END:variables

}
