package proyecto1.pkg4;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author alecuna
 */


public class AddRoute extends javax.swing.JFrame {

    Map m;
    String origin;
    int numRoutes;

    public AddRoute(Map m) {
        initComponents();
        this.setTitle("Agregar Ruta");
        this.setResizable(false);
        this.m = m;
        this.origin = null;
        this.originName.setEditable(true);
        this.numRoutes = -1;
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    
    public AddRoute(Map m, String nombre, int numeroRutas) {
        initComponents();
        this.setTitle("Agregar Ruta");
        this.setResizable(false);
        this.m = m;
        this.origin = nombre;
        this.originName.setText(nombre);
        this.originName.setEditable(false);
        this.numRoutes = 0;
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        originName = new javax.swing.JTextField();
        destinationName = new javax.swing.JTextField();
        distanceInput = new javax.swing.JTextField();
        ButtonAddRoutes = new javax.swing.JButton();
        ButtonBack = new javax.swing.JButton();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Almacen Origen");

        jLabel2.setText("Almaceb Destino");

        jLabel3.setText("Distancia");

        ButtonAddRoutes.setText("Agregar Rutas");
        ButtonAddRoutes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAddRoutesActionPerformed(evt);
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
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(destinationName, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(originName, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(distanceInput, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ButtonBack, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ButtonAddRoutes)
                .addGap(54, 54, 54))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(originName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(destinationName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(distanceInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ButtonAddRoutes)
                    .addComponent(ButtonBack))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Boton para agregar nuevas rutas
    private void ButtonAddRoutesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAddRoutesActionPerformed

        try {

            String origin = originName.getText();
            String destination = destinationName.getText();
            int distance = Integer.parseInt(distanceInput.getText());

            m.getGrafo().addRoute(origin, destination, distance, origin + destination);
            if (numRoutes != -1) {
                numRoutes += 1;
            } else {
                this.setVisible(false);
            }

            destinationName.setText("");
            distanceInput.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Algo salió mal. Por favor vuelva a intentarlo.");
        }


    }//GEN-LAST:event_ButtonAddRoutesActionPerformed

    
    private void ButtonBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonBackActionPerformed
        // TODO add your handling code here:
        if (numRoutes != -1 && numRoutes < 2) {
            JOptionPane.showMessageDialog(this, "Debe ingresar al menos dos rutas.");
        } else {
            this.setVisible(false);
        }

    }//GEN-LAST:event_ButtonBackActionPerformed

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
            java.util.logging.Logger.getLogger(AddRoute.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddRoute.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddRoute.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddRoute.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
       
        /* Create and display the form */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAddRoutes;
    private javax.swing.JButton ButtonBack;
    private javax.swing.JTextField destinationName;
    private javax.swing.JTextField distanceInput;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField originName;
    // End of variables declaration//GEN-END:variables
}
