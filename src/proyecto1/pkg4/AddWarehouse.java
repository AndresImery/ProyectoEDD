package proyecto1.pkg4;

import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author alecuna
 */
public class AddWarehouse extends javax.swing.JFrame {

    Map m;
    int numRoutes;

    public AddWarehouse(Map m) {
        initComponents();
        this.setTitle("Agregar Almacen");
        this.setResizable(false);
        this.m = m;
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        warehouseName = new javax.swing.JTextField();
        ButtonAccept = new javax.swing.JButton();
        ButtonBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Ingrese Nombre");

        warehouseName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                warehouseNameActionPerformed(evt);
            }
        });

        ButtonAccept.setText("Aceptar");
        ButtonAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAcceptActionPerformed(evt);
            }
        });

        ButtonBack.setText("Volver");
        ButtonBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1)
                        .addGap(40, 40, 40)
                        .addComponent(warehouseName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(ButtonBack)
                        .addGap(41, 41, 41)
                        .addComponent(ButtonAccept)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(warehouseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonBack)
                    .addComponent(ButtonAccept))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void warehouseNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_warehouseNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_warehouseNameActionPerformed

    //Boton de aceptar para agregar el almacen como vertice en el grafo
    private void ButtonAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAcceptActionPerformed
        // TODO add your handling code here:
        String name = warehouseName.getText().trim().toUpperCase();
        Random random = new Random();
        if (m.getGrafo().searchWarehouse(name) == null) {
            m.getGrafo().addWarehouse(name);
             if (m.getGrafo().searchWarehouse(name) != null) {
            m.getVertice().add(new Vertice(random.nextInt(700), random.nextInt(500), name.trim()));
            m.repaint();
            new AddRoute(m,name,numRoutes).setVisible(true);
                this.setVisible(false);
             }
        }else{
             JOptionPane.showMessageDialog(this, "Ya existe ese almacen.");
        }
    
    }//GEN-LAST:event_ButtonAcceptActionPerformed

    
    private void ButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonBackActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_ButtonBackActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAccept;
    private javax.swing.JButton ButtonBack;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField warehouseName;
    // End of variables declaration//GEN-END:variables
}
