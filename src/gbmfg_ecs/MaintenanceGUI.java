package gbmfg_ecs;



import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Ylene Pierre
 */
public class MaintenanceGUI extends javax.swing.JFrame {
    
    public static void main (String[] args){
       
     SwingUtilities.invokeLater(new Runnable() {
           public void run()
           {
               MaintenanceGUI gui = new MaintenanceGUI();
               gui.setVisible(true);
           }
       });
            
}
        
    /**
     * Creates new form MaintenaceUI
     */
    public MaintenanceGUI() {
        initComponents();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnAddRecords = new javax.swing.JButton();
        btnGetRecords = new javax.swing.JButton();
        btnRemoveRecords = new javax.swing.JButton();
        btnUpdateRecords = new javax.swing.JButton();
        btnMainMenu = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 204));
        jPanel1.setForeground(new java.awt.Color(255, 255, 204));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Maintenance Records Menu");

        btnAddRecords.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddRecords.setText("Add Records");
        btnAddRecords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRecordsActionPerformed(evt);
            }
        });

        btnGetRecords.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnGetRecords.setText("Get Records");
        btnGetRecords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetRecordsActionPerformed(evt);
            }
        });

        btnRemoveRecords.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRemoveRecords.setText("Remove Records");
        btnRemoveRecords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveRecordsActionPerformed(evt);
            }
        });

        btnUpdateRecords.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUpdateRecords.setText("Update Records");
        btnUpdateRecords.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateRecordsActionPerformed(evt);
            }
        });

        btnMainMenu.setText("Main Menu");
        btnMainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMainMenuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddRecords, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdateRecords, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGetRecords, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRemoveRecords, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btnMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(153, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddRecords, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUpdateRecords, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGetRecords, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRemoveRecords, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMainMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddRecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRecordsActionPerformed
               this.setVisible(false);
              
              // Maintenance_AddRecords gui = new Maintenance_AddRecords();
              //new M_AddRecords(this,true);
              M_AddRecords dialog = new M_AddRecords(new javax.swing.JFrame(), true);
              dialog.setVisible(true);
              
              
                   
                   
              
   
                //new Maintenance_AddRecords().setVisible(true);
    }//GEN-LAST:event_btnAddRecordsActionPerformed

    private void btnUpdateRecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateRecordsActionPerformed
            this.setVisible(false);
            M_UpdateRecords dialog = new M_UpdateRecords(new javax.swing.JFrame(), true);
            dialog.setVisible(true);
           
    }//GEN-LAST:event_btnUpdateRecordsActionPerformed

    private void btnGetRecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetRecordsActionPerformed
       this.setVisible(false);
        M_GetRecords dialog = new M_GetRecords(new javax.swing.JFrame(), true);
            dialog.setVisible(true);
    }//GEN-LAST:event_btnGetRecordsActionPerformed

    private void btnRemoveRecordsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveRecordsActionPerformed
        this.setVisible(false);
       M_RemoveRecords dialog = new M_RemoveRecords(new javax.swing.JFrame(), true);
            dialog.setVisible(true);
    }//GEN-LAST:event_btnRemoveRecordsActionPerformed

    private void btnMainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMainMenuActionPerformed
        this.setVisible(false);
        // new REPLACE WITH MAIN MENU CLASS().setVisible(true);
    }//GEN-LAST:event_btnMainMenuActionPerformed

   
    
       

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddRecords;
    private javax.swing.JButton btnGetRecords;
    private javax.swing.JButton btnMainMenu;
    private javax.swing.JButton btnRemoveRecords;
    private javax.swing.JButton btnUpdateRecords;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables


}
