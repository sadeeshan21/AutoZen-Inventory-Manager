/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package autozencarmanagmentsystem;
import java.math.BigDecimal;
import java.sql.*;
import javax.swing.JOptionPane;
import com.toedter.calendar.JMonthChooser;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JTable;


/**
 *
 * @author malsh
 */
public class CarManagement extends javax.swing.JFrame {

    /**
     * Creates new form CarManagement
     */
    public CarManagement() {
        initComponents();
        
        txtstatus.addFocusListener(new FocusAdapter() {
        @Override
        public void focusLost(FocusEvent e) {
            handleStatusBehavior();
        }
    });
        
        
         cartable();
    }
    
    private void handleStatusBehavior() { //private method
String status = txtstatus.getText().trim().toLowerCase(); // Use toLowerCase for comparison

if (status.equals("unsold") ) {
    txtdos.setDate(txtdop.getDate());         // Set sold date = purchase date
    txtdos.setEnabled(false);                 // Disable the date picker
    txtsoldprice.setText("0.00");             // Set sold price to 0
    txtsoldprice.setEditable(false);          // Prevent editing
} else {
    txtdos.setEnabled(true);                  // Enable date picker
    txtsoldprice.setEditable(true);           // Allow editing
}


}
private void calculateProfit() {
    try {
        String status = txtstatus.getText().trim().toLowerCase(); //trim use to avoid spaces and etc.

        if (status.equals("unsold")) {
            txtprofit.setText("0");
            return;
        }

        BigDecimal buyingPrice = new BigDecimal(txtbuyingprice.getText().trim());
        BigDecimal otherCost = new BigDecimal(txtothercost.getText().trim());
        String soldPriceText = txtsoldprice.getText().trim();

        if (soldPriceText.isEmpty()) {
            txtprofit.setText("");
            return;
        }

        BigDecimal soldPrice = new BigDecimal(soldPriceText);

        BigDecimal profit = soldPrice.subtract(buyingPrice.add(otherCost));
        txtprofit.setText(profit.toPlainString());

    } catch (NumberFormatException e) {
        txtprofit.setText("");
        // error handling
    }
}

    public void cartable(){
    try {
    DefaultTableModel dt = (DefaultTableModel) cars_table.getModel(); //tablename=cars_table
    dt.setRowCount(0);  // Clear existing rows to avoid duplication

    Statement s = db.myccon().createStatement();
    ResultSet rs = s.executeQuery("SELECT * FROM cars");

    while (rs.next()) {
        Vector v = new Vector();

        // Add  values to the table from the 'cars' table
        v.add(rs.getString("regno"));
        v.add(rs.getString("brand"));
        v.add(rs.getString("model"));
        v.add(rs.getDate("dop"));
        v.add(rs.getBigDecimal("buyingprice"));
        v.add(rs.getBigDecimal("othercost"));
        v.add(rs.getString("status"));
        v.add(rs.getDate("dos"));
        v.add(rs.getBigDecimal("soldprice"));
        v.add(rs.getBigDecimal("profit"));
        

        dt.addRow(v);
    }
      DefaultTableCellRenderer statusRenderer = new DefaultTableCellRenderer() { //create table fomat
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

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
        cars_table.getColumnModel().getColumn(6).setCellRenderer(statusRenderer);

        //  Custom renderer for profit column (index 9)
        DefaultTableCellRenderer profitRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

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
        cars_table.getColumnModel().getColumn(9).setCellRenderer(profitRenderer);

    
    

} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Error loading car data into table: " + e.getMessage());
}

}
    
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        cars_table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtdop = new com.toedter.calendar.JDateChooser();
        txtdos = new com.toedter.calendar.JDateChooser();
        txtregnumber = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtbrand = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        txtmodel = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtbuyingprice = new javax.swing.JTextField();
        txtothercost = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtsoldprice = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtprofit = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtstatus = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtsearchcar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        txtupdate = new javax.swing.JButton();
        txtadd = new javax.swing.JButton();
        txtsearch = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cars_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Reg. No.", "Brand", "Model", "DOP", "Buying Price", "Other Costs", "Status", "DOS", "Sold Price", "Profit"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        cars_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cars_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(cars_table);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, 750, 760));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 51));
        jLabel1.setText("Car Registration");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 390, -1));
        getContentPane().add(txtdop, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 150, -1));
        getContentPane().add(txtdos, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 390, 150, -1));

        txtregnumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtregnumberActionPerformed(evt);
            }
        });
        getContentPane().add(txtregnumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 150, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Registration Number");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 204, -1));

        txtbrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbrandActionPerformed(evt);
            }
        });
        getContentPane().add(txtbrand, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 150, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Brand");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 93, -1));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 153));
        jButton2.setText("Exit");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton2MouseEntered(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 720, 150, 40));
        getContentPane().add(txtmodel, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, 150, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Model");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 68, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Date of Purchase");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Buying  Price(¥)");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 138, -1));
        getContentPane().add(txtbuyingprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 150, -1));

        txtothercost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtothercostActionPerformed(evt);
            }
        });
        getContentPane().add(txtothercost, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 150, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Other Cost(¥)");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Date of Sold");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 90, -1));
        getContentPane().add(txtsoldprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 430, 150, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Sold Price(¥)");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 100, -1));

        txtprofit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtprofitActionPerformed(evt);
            }
        });
        getContentPane().add(txtprofit, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 470, 150, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Profit(¥)");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 100, -1));

        txtstatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstatusActionPerformed(evt);
            }
        });
        getContentPane().add(txtstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 150, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Status(sold/unsold)");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 154, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 51, 255));
        jLabel13.setText("Customer Id");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 570, -1, -1));

        txtsearchcar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsearchcarActionPerformed(evt);
            }
        });
        txtsearchcar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtsearchcarKeyPressed(evt);
            }
        });
        getContentPane().add(txtsearchcar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 610, 150, 30));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 153));
        jButton1.setText("Reload");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 660, 150, 40));

        txtupdate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtupdate.setForeground(new java.awt.Color(0, 0, 204));
        txtupdate.setText("Update");
        txtupdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtupdateActionPerformed(evt);
            }
        });
        getContentPane().add(txtupdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 290, 100, 45));

        txtadd.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtadd.setForeground(new java.awt.Color(0, 0, 204));
        txtadd.setText("Add");
        txtadd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtadd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtaddMouseClicked(evt);
            }
        });
        txtadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtaddActionPerformed(evt);
            }
        });
        getContentPane().add(txtadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, 100, 45));

        txtsearch.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtsearch.setForeground(new java.awt.Color(0, 0, 204));
        txtsearch.setText("Search");
        txtsearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        txtsearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtsearchMouseClicked(evt);
            }
        });
        txtsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsearchActionPerformed(evt);
            }
        });
        getContentPane().add(txtsearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, 100, 45));

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 0, 0));
        jButton6.setText("Delete");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 360, 100, 45));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/popupicon/car.png"))); // NOI18N
        jLabel12.setText("jLabel12");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1260, 800));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtregnumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtregnumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtregnumberActionPerformed

    private void txtbrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbrandActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbrandActionPerformed

    private void txtaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtaddActionPerformed
// TODO add your handling code here:
try {
 
    String regNo = txtregnumber.getText().trim(); // Get values from textf.
    String brand = txtbrand.getText().trim();
    String model = txtmodel.getText().trim();

    java.util.Date utilDOP = txtdop.getDate(); // purchase date
    java.util.Date utilDOS = txtdos.getDate(); // sold date

    if (utilDOP == null || utilDOS == null) {
        JOptionPane.showMessageDialog(this, "Please select both dates.");
        return;
    }

    java.sql.Date sqlDOP = new java.sql.Date(utilDOP.getTime());
    java.sql.Date sqlDOS = new java.sql.Date(utilDOS.getTime());

    BigDecimal buyingPrice = new BigDecimal(txtbuyingprice.getText().trim());
    BigDecimal otherCost = new BigDecimal(txtothercost.getText().trim());
    BigDecimal soldPrice = new BigDecimal(txtsoldprice.getText().trim());

    BigDecimal profit = soldPrice.subtract(buyingPrice.add(otherCost));
    txtprofit.setText(profit.toPlainString());

    String status = txtstatus.getText().trim();

    //  Insert into 'cars' table
    String sql1 = "INSERT INTO cars (regno, brand, model, dop, buyingprice, othercost,status, dos, soldprice, profit) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    PreparedStatement pst = db.myccon().prepareStatement(sql1); //sql statemnt
    pst.setString(1, regNo);
    pst.setString(2, brand);
    pst.setString(3, model);
    pst.setDate(4, sqlDOP);
    pst.setBigDecimal(5, buyingPrice);
    pst.setBigDecimal(6, otherCost);
    pst.setString(7, status);
    pst.setDate(8, sqlDOS);
    pst.setBigDecimal(9, soldPrice);
    pst.setBigDecimal(10, profit);
    

    pst.executeUpdate(); // xecute the first insert and enter data to it

    // 3. Insert into 'carreport' table
    String sql2 = "INSERT INTO carreport (carregnumber, soldstatus, dos, profit) VALUES (?, ?, ?, ?)";
    PreparedStatement sst = db.myccon().prepareStatement(sql2);
    sst.setString(1, regNo);
    sst.setString(2, status);
    sst.setDate(3, sqlDOS);
    sst.setBigDecimal(4, profit);

    sst.executeUpdate(); // Execute the second insert

    JOptionPane.showMessageDialog(this, "Car registered successfully!");

    //  Clear form
    txtregnumber.setText("");
    txtbrand.setText("");
    txtmodel.setText("");
    txtdop.setDate(null);
    txtdos.setDate(null);
    txtbuyingprice.setText("");
    txtothercost.setText("");
    txtsoldprice.setText("");
    txtprofit.setText("");
    txtstatus.setText("");

} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "Invalid number entered. Please check input fields.");
} catch (SQLIntegrityConstraintViolationException e) {
    JOptionPane.showMessageDialog(this, "Duplicate entry for registration number.");
} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
}


        
        
        
    }//GEN-LAST:event_txtaddActionPerformed

    private void txtaddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtaddMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtaddMouseClicked

    private void txtothercostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtothercostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtothercostActionPerformed

    private void txtstatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstatusActionPerformed

    private void txtsearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtsearchMouseClicked
        // TODO add your handling code here:
        
        
        
    }//GEN-LAST:event_txtsearchMouseClicked

    private void txtsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsearchActionPerformed
try {
    Statement s = db.myccon().createStatement();

    // Search for the car by registration number
    ResultSet rs = s.executeQuery("SELECT * FROM cars WHERE regno = '" + txtregnumber.getText().trim() + "'");

    if (rs.next()) {
        txtbrand.setText(rs.getString("brand"));
        txtmodel.setText(rs.getString("model"));
        txtdop.setDate(rs.getDate("dop"));
        txtbuyingprice.setText(rs.getString("buyingprice"));
        txtothercost.setText(rs.getString("othercost"));
        txtdos.setDate(rs.getDate("dos"));
        txtsoldprice.setText(rs.getString("soldprice"));
        txtprofit.setText(rs.getString("profit"));
        txtstatus.setText(rs.getString("status"));
    } else {
        JOptionPane.showMessageDialog(this, "Car not found!");
    }

} catch (SQLException e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
}




        // TODO add your handling code here:
    }//GEN-LAST:event_txtsearchActionPerformed

    private void txtupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtupdateActionPerformed
        // TODO add your handling code here:
    try {
    // 1. Get form values
    String regNo = txtregnumber.getText().trim();
    String brand = txtbrand.getText().trim();
    String model = txtmodel.getText().trim();

    java.util.Date utilDOP = txtdop.getDate(); // Date of Purchase
    java.util.Date utilDOS = txtdos.getDate(); // Date of Sold

    if (utilDOP == null) {
        JOptionPane.showMessageDialog(this, "Please select a purchase date.");
        return;
    }

    String status = txtstatus.getText().trim().toLowerCase();

    java.sql.Date sqlDOP = new java.sql.Date(utilDOP.getTime());
    java.sql.Date sqlDOS;

    BigDecimal soldPrice;
    if (status.equals("unsold") || status.equals("unpaid")) {
        soldPrice = BigDecimal.ZERO;
        sqlDOS = null;
    } else {
        if (utilDOS == null) {
            JOptionPane.showMessageDialog(this, "Please select a sold date.");
            return;
        }
        sqlDOS = new java.sql.Date(utilDOS.getTime());
        soldPrice = new BigDecimal(txtsoldprice.getText().trim());
    }

    BigDecimal buyingPrice = new BigDecimal(txtbuyingprice.getText().trim());
    BigDecimal otherCost = new BigDecimal(txtothercost.getText().trim());

    BigDecimal profit = soldPrice.subtract(buyingPrice.add(otherCost));
    txtprofit.setText(profit.toPlainString());

    // 2. Update 'cars' table
    String sql1 = "UPDATE cars SET brand = ?, model = ?, dop = ?, buyingprice = ?, othercost = ?, status = ?, dos = ?, soldprice = ?, profit = ? WHERE regno = ?";
    PreparedStatement pst = db.myccon().prepareStatement(sql1);
    pst.setString(1, brand);
    pst.setString(2, model);
    pst.setDate(3, sqlDOP);
    pst.setBigDecimal(4, buyingPrice);
    pst.setBigDecimal(5, otherCost);
    pst.setString(6, status);
    pst.setDate(7, sqlDOS); // can be null
    pst.setBigDecimal(8, soldPrice);
    pst.setBigDecimal(9, profit);
    pst.setString(10, regNo);

    int updatedRows1 = pst.executeUpdate();

    // 3. Update 'carreport' table
    String sql2 = "UPDATE carreport SET soldstatus = ?, dos = ?, profit = ? WHERE carregnumber = ?";
    PreparedStatement sst = db.myccon().prepareStatement(sql2);
    sst.setString(1, status);
    sst.setDate(2, sqlDOS); // can be null
    sst.setBigDecimal(3, profit);
    sst.setString(4, regNo);

    int updatedRows2 = sst.executeUpdate();

    if (updatedRows1 > 0 && updatedRows2 > 0) {
        JOptionPane.showMessageDialog(this, "Car details updated successfully!");
    } else {
        JOptionPane.showMessageDialog(this, "No matching record found to update.");
    }

    // 4. Clear form
    txtregnumber.setText("");
    txtbrand.setText("");
    txtmodel.setText("");
    txtdop.setDate(null);
    txtdos.setDate(null);
    txtbuyingprice.setText("");
    txtothercost.setText("");
    txtsoldprice.setText("");
    txtprofit.setText("");
    txtstatus.setText("");

} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "Invalid number entered. Please check input fields.");
} catch (Exception e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
}


    }//GEN-LAST:event_txtupdateActionPerformed

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        // TODO add your handling code here:
        
        String regNo = txtregnumber.getText().trim();  // Get registration number from input field

if (regNo.isEmpty()) {
    JOptionPane.showMessageDialog(null, "Please enter or select a Registration Number to delete.");
    return;
}

int confirm = JOptionPane.showConfirmDialog(null,
    "Are you sure you want to delete this car record?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

if (confirm == JOptionPane.YES_OPTION) {
    try {
        // 1. Delete from 'cars' table
        String sql = "DELETE FROM cars WHERE regno = ?";
        PreparedStatement ps = db.myccon().prepareStatement(sql);
        ps.setString(1, regNo);

        int rows = ps.executeUpdate();

        // 2. Optional: Delete from 'carreport' table too
        String sql2 = "DELETE FROM carreport WHERE carregnumber = ?";
        PreparedStatement ps2 = db.myccon().prepareStatement(sql2);
        ps2.setString(1, regNo);
        ps2.executeUpdate();  // optional: does not affect rows variable

        if (rows > 0) {
            JOptionPane.showMessageDialog(null, "Car record deleted successfully.");

            // Clear fields
            txtregnumber.setText("");
            txtbrand.setText("");
            txtmodel.setText("");
            txtdop.setDate(null);
            txtdos.setDate(null);
            txtbuyingprice.setText("");
            txtothercost.setText("");
            txtsoldprice.setText("");
            txtprofit.setText("");
            txtstatus.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "No car found with this Registration Number.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error occurred: " + e.getMessage());
    }
}

    }//GEN-LAST:event_jButton6MouseClicked

    private void txtsearchcarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsearchcarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsearchcarActionPerformed

    private void txtsearchcarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtsearchcarKeyPressed
        //Serach from id
         String carreg=txtsearchcar.getText();
         
          try {
        DefaultTableModel dt = (DefaultTableModel) cars_table.getModel();
        dt.setRowCount(0);  // Clear existing rows before loading new ones

        Statement s = db.myccon().createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM cars WHERE regno = '"+carreg+"'");

        while (rs.next()) {
            Vector v = new Vector();

            // Add each column value from the result set to the vector
         v.add(rs.getString("regno"));
        v.add(rs.getString("brand"));
        v.add(rs.getString("model"));
        v.add(rs.getDate("dop"));
        v.add(rs.getBigDecimal("buyingprice"));
        v.add(rs.getBigDecimal("othercost"));
        v.add(rs.getString("status"));
        v.add(rs.getDate("dos"));
        v.add(rs.getBigDecimal("soldprice"));
        v.add(rs.getBigDecimal("profit"));
            // ✅ Add the vector as a row to the table model
            dt.addRow(v);
        }

    } catch (Exception e) {
        e.printStackTrace();  // Print error to the console
        JOptionPane.showMessageDialog(null, "Error loading data into table: " + e.getMessage());
    }
        
    }//GEN-LAST:event_txtsearchcarKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
  cartable();
         
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtprofitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtprofitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprofitActionPerformed

    private void cars_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cars_tableMouseClicked
int i = cars_table.getSelectedRow(); // Get selected row index

String regNo = cars_table.getValueAt(i, 0).toString();
String brand = cars_table.getValueAt(i, 1).toString();
String model = cars_table.getValueAt(i, 2).toString();
String dop = cars_table.getValueAt(i, 3).toString(); // format: yyyy-MM-dd
String dos = cars_table.getValueAt(i, 7).toString();
String buyingPrice = cars_table.getValueAt(i, 4).toString();
String otherCost = cars_table.getValueAt(i, 5).toString();
String soldPrice = cars_table.getValueAt(i, 8).toString();
String profit = cars_table.getValueAt(i, 9).toString();
String status = cars_table.getValueAt(i, 6).toString();

// Set values to input fields
txtregnumber.setText(regNo);
txtbrand.setText(brand);
txtmodel.setText(model);

// Parse and set dates
try {
    java.util.Date dopDate = new SimpleDateFormat("yyyy-MM-dd").parse(dop);
    java.util.Date dosDate = new SimpleDateFormat("yyyy-MM-dd").parse(dos);
    txtdop.setDate(dopDate);
    txtdos.setDate(dosDate);
} catch (ParseException e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(this, "Date parsing error: " + e.getMessage());
}

txtbuyingprice.setText(buyingPrice);
txtothercost.setText(otherCost);
txtsoldprice.setText(soldPrice);
txtprofit.setText(profit);
txtstatus.setText(status);







        // TODO add your handling code here:
    }//GEN-LAST:event_cars_tableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
          // TODO add your handling code here:
        Dashboard2 pi= new Dashboard2();
        pi.setVisible(true);  
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:

        Dashboard2 pi= new Dashboard2();
        pi.setVisible(true);
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2MouseEntered

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
            java.util.logging.Logger.getLogger(CarManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CarManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CarManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CarManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CarManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable cars_table;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton txtadd;
    private javax.swing.JTextField txtbrand;
    private javax.swing.JTextField txtbuyingprice;
    private com.toedter.calendar.JDateChooser txtdop;
    private com.toedter.calendar.JDateChooser txtdos;
    private javax.swing.JTextField txtmodel;
    private javax.swing.JTextField txtothercost;
    private javax.swing.JTextField txtprofit;
    private javax.swing.JTextField txtregnumber;
    private javax.swing.JButton txtsearch;
    private javax.swing.JTextField txtsearchcar;
    private javax.swing.JTextField txtsoldprice;
    private javax.swing.JTextField txtstatus;
    private javax.swing.JButton txtupdate;
    // End of variables declaration//GEN-END:variables
}
