/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package autozencarmanagmentsystem;
import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import java.awt.Color;








/**
 *
 * @author malsh
 */
public class profitcalculator extends javax.swing.JFrame {

    /**
     * Creates new form profitcalculator
     */
    public profitcalculator() {
        initComponents();
        ctload();
        otherReportLoad();
    }

    public void ctload(){
        try {
    DefaultTableModel dt = (DefaultTableModel) carreporttable.getModel(); // Replace with your JTable name
    dt.setRowCount(0);

    Statement s = db.myccon().createStatement();
    ResultSet rs = s.executeQuery("SELECT * FROM carreport");

    while (rs.next()) {
        Vector v = new Vector();

        v.add(rs.getString("carregnumber"));
        v.add(rs.getString("soldstatus"));
        v.add(rs.getDate("dos"));
        v.add(rs.getBigDecimal("profit"));

        dt.addRow(v);
    }
    
     // Renderer for soldstatus (column 1)
        DefaultTableCellRenderer soldStatusRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (value != null && value.toString().equalsIgnoreCase("unsold")) {
                    c.setForeground(Color.RED);
                } else {
                    c.setForeground(Color.BLACK);
                }

                if (isSelected) {
                    c.setBackground(table.getSelectionBackground());
                } else {
                    c.setBackground(Color.WHITE);
                }

                return c;
            }
        };
        carreporttable.getColumnModel().getColumn(1).setCellRenderer(soldStatusRenderer);

        // Renderer for profit (column 3)
        DefaultTableCellRenderer profitRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {

                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                try {
                    if (value != null && new java.math.BigDecimal(value.toString()).compareTo(java.math.BigDecimal.ZERO) < 0) {
                        c.setForeground(Color.RED);
                    } else {
                        c.setForeground(Color.BLACK);
                    }
                } catch (NumberFormatException ex) {
                    c.setForeground(Color.BLACK);
                }

                if (isSelected) {
                    c.setBackground(table.getSelectionBackground());
                } else {
                    c.setBackground(Color.WHITE);
                }

                return c;
            }
        };
        carreporttable.getColumnModel().getColumn(3).setCellRenderer(profitRenderer);

} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Error loading car report data into table: " + e.getMessage());
}

    }
    
    public void otherReportLoad() {
    try {
        DefaultTableModel dt = (DefaultTableModel) otherreporttable.getModel(); 
        dt.setRowCount(0); // Clear existing rows

        Statement s = db.myccon().createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM otherbillreport");

        while (rs.next()) {
            Vector v = new Vector();

            v.add(rs.getString("billid"));
            v.add(rs.getString("status"));
            v.add(rs.getBigDecimal("amount"));

            dt.addRow(v);
        }
        
        DefaultTableCellRenderer soldStatusRenderer = new DefaultTableCellRenderer() {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
if (value != null && value.toString().equalsIgnoreCase("Unpaid")) {  // Correct spelling
            c.setForeground(Color.RED);
        } else {
            c.setForeground(Color.BLACK);
        }

        if (isSelected) {
            c.setBackground(table.getSelectionBackground());
        } else {
            c.setBackground(Color.WHITE);
        }

        return c;
    }
};
otherreporttable.getColumnModel().getColumn(1).setCellRenderer(soldStatusRenderer);
        
        

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error loading other report data into table: " + e.getMessage());
    }
}

    private double evaluateExpression(String expression) throws Exception {
    ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
    Object result = engine.eval(expression);
    return Double.parseDouble(result.toString());
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        carreporttable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        otherreporttable = new javax.swing.JTable();
        btnCalculae1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtEnterEranings = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnCalculae2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtEnterSpents = new javax.swing.JTextField();
        txtTotalEarnings = new javax.swing.JTextField();
        txtTotalSpents = new javax.swing.JTextField();
        btnCalculae = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtProfit = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 51));
        jLabel1.setText("Profit Calculator");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 540, -1));

        carreporttable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Reg Number", "Status", "DOS", "Profit(¥)"
            }
        ));
        jScrollPane1.setViewportView(carreporttable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 560, 450));

        otherreporttable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Bill Id", "Amount(¥)", "Status"
            }
        ));
        jScrollPane2.setViewportView(otherreporttable);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 90, 620, 450));

        btnCalculae1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnCalculae1.setForeground(new java.awt.Color(0, 0, 204));
        btnCalculae1.setText("Exit");
        btnCalculae1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCalculae1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCalculae1MouseClicked(evt);
            }
        });
        btnCalculae1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculae1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCalculae1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 670, 150, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 255));
        jLabel2.setText("Enter Total Earnings(¥)");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, 200, -1));

        txtEnterEranings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnterEraningsActionPerformed(evt);
            }
        });
        getContentPane().add(txtEnterEranings, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 560, 187, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 255));
        jLabel3.setText("Total Spents(¥)");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 670, 188, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 255));
        jLabel4.setText("Total Earnings(¥)");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 630, 188, -1));

        btnCalculae2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnCalculae2.setForeground(new java.awt.Color(0, 0, 204));
        btnCalculae2.setText("Reload");
        btnCalculae2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCalculae2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCalculae2MouseClicked(evt);
            }
        });
        btnCalculae2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculae2ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCalculae2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 570, 150, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 255));
        jLabel5.setText("Enter Total Spents(¥)");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 600, 188, -1));
        getContentPane().add(txtEnterSpents, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 600, 187, -1));
        getContentPane().add(txtTotalEarnings, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 640, 187, -1));
        getContentPane().add(txtTotalSpents, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 680, 187, -1));

        btnCalculae.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnCalculae.setForeground(new java.awt.Color(0, 0, 204));
        btnCalculae.setText("Calculate");
        btnCalculae.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCalculae.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalculaeActionPerformed(evt);
            }
        });
        getContentPane().add(btnCalculae, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 620, 150, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 255));
        jLabel6.setText("Profit/Lost(¥)");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 570, 188, -1));

        txtProfit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProfitActionPerformed(evt);
            }
        });
        getContentPane().add(txtProfit, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 600, 187, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/popupicon/reports.png"))); // NOI18N
        jLabel7.setText("jLabel7");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -50, 1270, 900));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCalculaeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculaeActionPerformed
        // TODO add your handling code here:
        
        new Thread(() -> {
    try {
        // 1. Read inputs from GUI
        String earningsInput = txtEnterEranings.getText().trim();
        String spendsInput = txtEnterSpents.getText().trim();

        // 2. Use encapsulated data class
        FinanceReport data = new FinanceReport(earningsInput, spendsInput);

        BigDecimal totalEarnings = data.getTotalEarnings();
        BigDecimal totalSpents = data.getTotalSpends();

        // 3. Show totals
        txtTotalEarnings.setText(totalEarnings.toPlainString());
        txtTotalSpents.setText(totalSpents.toPlainString());

        // 4. Use polymorphic calculator
        Calculator calc = new profitcalculatorex();
        BigDecimal result = calc.calculate(totalEarnings, totalSpents);

        txtProfit.setText(result.toPlainString());

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Invalid input! Please enter values like 300+200+150");
    }
}).start();

    }//GEN-LAST:event_btnCalculaeActionPerformed

    private void txtProfitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProfitActionPerformed
        // TODO add your handling code here:
        


    }//GEN-LAST:event_txtProfitActionPerformed

    private void txtEnterEraningsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnterEraningsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnterEraningsActionPerformed

    private void btnCalculae1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculae1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCalculae1ActionPerformed

    private void btnCalculae1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCalculae1MouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here:
        Dashboard2 pi= new Dashboard2();
        pi.setVisible(true);
    }//GEN-LAST:event_btnCalculae1MouseClicked

    private void btnCalculae2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalculae2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCalculae2ActionPerformed

    private void btnCalculae2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCalculae2MouseClicked
        // TODO add your handling code here:
        otherReportLoad();
    }//GEN-LAST:event_btnCalculae2MouseClicked

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
            java.util.logging.Logger.getLogger(profitcalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(profitcalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(profitcalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(profitcalculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new profitcalculator().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalculae;
    private javax.swing.JButton btnCalculae1;
    private javax.swing.JButton btnCalculae2;
    private javax.swing.JTable carreporttable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable otherreporttable;
    private javax.swing.JTextField txtEnterEranings;
    private javax.swing.JTextField txtEnterSpents;
    private javax.swing.JTextField txtProfit;
    private javax.swing.JTextField txtTotalEarnings;
    private javax.swing.JTextField txtTotalSpents;
    // End of variables declaration//GEN-END:variables
}
