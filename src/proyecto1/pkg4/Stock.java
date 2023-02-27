package proyecto1.pkg4;

import javax.swing.*;

/**
 *
 * @author alecuna
 */
public class Stock extends javax.swing.JFrame {

    String name;
    Map m;
    String contenido = "";
    String nombreAux;
    int cantidad;

    public Stock(Map m) {
        initComponents();
        this.setResizable(false);
        this.setTitle("Gestionar Stock");
        this.m = m;
        this.showInfoArea.setEditable(false);
        this.buttonModify.setVisible(false);
        this.buttonAddProduct.setVisible(false);
        this.buttonBack.setVisible(false);
        this.productName.setVisible(false);
        this.productQuantity.setVisible(false);
        this.labelNameProduct.setVisible(false);
        this.labelQuantity.setVisible(false);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    //muestra el stock disponiple de productos en cada almacen para que el usuario lo pueda ver en pantalla
    private void print(int warehouse) {
        contenido = "";
        if (!m.getGrafo().getWarehouses()[warehouse].getProducts().IsEmpty()) {
            Product aux = m.getGrafo().getWarehouses()[warehouse].getProducts().getpFirst();
            for (int j = 0; j < m.getGrafo().getWarehouses()[warehouse].getProducts().getSize(); j++) {

                contenido += aux.getName() + "    Cantidad: " + Integer.toString(aux.getStock()) + "\n";

                aux = aux.getPnext();

            }

        }
        showInfoArea.setText(contenido);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        showInfoArea = new javax.swing.JTextArea();
        searchWarehouse = new javax.swing.JButton();
        nameWarehouse = new javax.swing.JTextField();
        labelNameProduct = new javax.swing.JLabel();
        labelNameWarehouse = new javax.swing.JLabel();
        productName = new javax.swing.JTextField();
        labelQuantity = new javax.swing.JLabel();
        productQuantity = new javax.swing.JTextField();
        buttonAddProduct = new javax.swing.JButton();
        buttonModify = new javax.swing.JButton();
        buttonBack = new javax.swing.JButton();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        showInfoArea.setColumns(20);
        showInfoArea.setRows(5);
        jScrollPane1.setViewportView(showInfoArea);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 233, 295));

        searchWarehouse.setText("Buscar Almacen");
        searchWarehouse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchWarehouseActionPerformed(evt);
            }
        });
        getContentPane().add(searchWarehouse, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 138, -1));

        nameWarehouse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameWarehouseActionPerformed(evt);
            }
        });
        getContentPane().add(nameWarehouse, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 138, -1));

        labelNameProduct.setText("Nombre del Producto");
        getContentPane().add(labelNameProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        labelNameWarehouse.setText("Nombre del Almacen");
        getContentPane().add(labelNameWarehouse, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        productName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productNameActionPerformed(evt);
            }
        });
        getContentPane().add(productName, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 138, -1));

        labelQuantity.setText("Cantidad Del Producto");
        getContentPane().add(labelQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        productQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productQuantityActionPerformed(evt);
            }
        });
        getContentPane().add(productQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 138, -1));

        buttonAddProduct.setText("Agregar Nuevo");
        buttonAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddProductActionPerformed(evt);
            }
        });
        getContentPane().add(buttonAddProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 138, -1));

        buttonModify.setText("Modificar");
        buttonModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModifyActionPerformed(evt);
            }
        });
        getContentPane().add(buttonModify, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 138, -1));

        buttonBack.setText("Volver");
        buttonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackActionPerformed(evt);
            }
        });
        getContentPane().add(buttonBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 138, -1));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo2.jpeg"))); // NOI18N
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 380));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //busca el almacen ingresado por el usuario para luego mostrar sus productos
    private void searchWarehouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchWarehouseActionPerformed
        try {

            name = this.nameWarehouse.getText();

            if (m.getGrafo().searchWarehouse(name) != null) {
                this.print(m.getGrafo().getIndex(name));

                this.nameWarehouse.setVisible(false);
                this.labelNameWarehouse.setVisible(false);
                this.searchWarehouse.setVisible(false);
                this.productQuantity.setVisible(true);
                this.productName.setVisible(true);
                this.labelNameProduct.setVisible(true);
                this.labelQuantity.setVisible(true);
                this.buttonAddProduct.setVisible(true);
                this.buttonModify.setVisible(true);
                this.buttonBack.setVisible(true);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Algo salio mal.");
        }

    }//GEN-LAST:event_searchWarehouseActionPerformed
 
    //agrega un producto y la cantidad del mismo, a la lista de productos del almacen seleccionado
    private void buttonAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddProductActionPerformed
        boolean found = false;
        try {
            nombreAux = this.productName.getText();
            cantidad = Integer.parseInt(this.productQuantity.getText());

            if (this.productName.getText() == null && this.productQuantity.getText() != null) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese el nombre del producto.");
            }
            if (!m.getGrafo().getWarehouses()[m.getGrafo().getIndex(name)].getProducts().IsEmpty()) {
                Product aux = m.getGrafo().getWarehouses()[m.getGrafo().getIndex(name)].getProducts().getpFirst();
                for (int j = 0; j < m.getGrafo().getWarehouses()[m.getGrafo().getIndex(name)].getProducts().getSize(); j++) {
                    if (nombreAux.equalsIgnoreCase(aux.getName())) {
                        found = true;
                        break;
                    }

                    aux = aux.getPnext();

                }

            }
            if (!found) {
                m.getGrafo().searchWarehouse(name).getProducts().insertFinal(nombreAux, cantidad);
            } else {
                JOptionPane.showMessageDialog(this, "No se puede agregar este producto debido a que ya existe.");
            }

            productName.setText("");
            productQuantity.setText("");

            this.print(m.getGrafo().getIndex(name));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese una cantidad valida.");
        }
    }//GEN-LAST:event_buttonAddProductActionPerformed

    //modifica la cantidad de un producto disponible en el almacen seleccionado
    private void buttonModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModifyActionPerformed
        try {
            boolean found = false;
            nombreAux = this.productName.getText();
            cantidad = Integer.parseInt(this.productQuantity.getText());
            if (!m.getGrafo().getWarehouses()[m.getGrafo().getIndex(name)].getProducts().IsEmpty()) {
                Product aux = m.getGrafo().getWarehouses()[m.getGrafo().getIndex(name)].getProducts().getpFirst();
                for (int j = 0; j < m.getGrafo().getWarehouses()[m.getGrafo().getIndex(name)].getProducts().getSize(); j++) {
                    if (nombreAux.equalsIgnoreCase(aux.getName())) {
                        found = true;
                        break;
                    }

                    aux = aux.getPnext();

                }
                if (found) {
                    aux.setStock(cantidad);
                    this.print(m.getGrafo().getIndex(name));
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor ingrese un producto que exista.");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Algo salió mal.");
        }

    }//GEN-LAST:event_buttonModifyActionPerformed

    private void buttonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBackActionPerformed
        this.buttonModify.setVisible(false);
        this.buttonAddProduct.setVisible(false);
        this.buttonBack.setVisible(false);
        this.productName.setVisible(false);
        this.productQuantity.setVisible(false);
        this.labelNameProduct.setVisible(false);
        this.labelQuantity.setVisible(false);
        this.nameWarehouse.setVisible(true);
        this.labelNameWarehouse.setVisible(true);
        this.searchWarehouse.setVisible(true);

    }//GEN-LAST:event_buttonBackActionPerformed

    private void nameWarehouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameWarehouseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameWarehouseActionPerformed

    private void productNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productNameActionPerformed

    private void productQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productQuantityActionPerformed

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
            java.util.logging.Logger.getLogger(Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Stock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddProduct;
    private javax.swing.JButton buttonBack;
    private javax.swing.JButton buttonModify;
    private javax.swing.JLabel fondo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelNameProduct;
    private javax.swing.JLabel labelNameWarehouse;
    private javax.swing.JLabel labelQuantity;
    private javax.swing.JTextField nameWarehouse;
    private javax.swing.JTextField productName;
    private javax.swing.JTextField productQuantity;
    private javax.swing.JButton searchWarehouse;
    private javax.swing.JTextArea showInfoArea;
    // End of variables declaration//GEN-END:variables
}
