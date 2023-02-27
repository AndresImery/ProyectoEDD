package proyecto1.pkg4;

/**
 *
 * @author alecuna
 */
import java.awt.MenuItem;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Principal extends javax.swing.JFrame {

    private Map m;

    private String aux_origin;
    private String aux_destination;
    private int aux_distance;
    private String aux_product;
    private int aux_quantity;
    private String nameWarehouse;

    JFileChooser seleccionado = new JFileChooser();
    File archivo;
    Archivo file = new Archivo();

    public Principal(Map m) {

        initComponents();

        this.setTitle("Amazon");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.m = m;

    }

    //Actualiza los datos en el txt cargado anteriormente
    public void UpdateFiles() {
        int input = 1;
        do {
            if (seleccionado.showDialog(this, "Actualizar") == JFileChooser.APPROVE_OPTION) {
                File archivo = new File(seleccionado.getSelectedFile().toString());

                System.out.println(seleccionado.getSelectedFile());
                if (archivo.exists()) {
                    input = JOptionPane.showConfirmDialog(null, "El Archivo ya existe. ¿Desea Sobreescribirlo?");
                }
                if (input == 0 || !archivo.exists()) {
                    try {
                        input = 0;
                        BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
                        bw.write("Almacenes;");
                        bw.newLine();
                        for (int i = 0; i < m.getGrafo().getWarehouses().length; i++) {
                            if (m.getGrafo().getWarehouses()[i] != null) {
                                bw.write("Almacen " + m.getGrafo().getWarehouses()[i].getName() + ":");
                                bw.newLine();
                                if (!m.getGrafo().getWarehouses()[i].getProducts().IsEmpty()) {
                                    Product aux = m.getGrafo().getWarehouses()[i].getProducts().getpFirst();
                                    for (int j = 0; j < m.getGrafo().getWarehouses()[i].getProducts().getSize(); j++) {
                                        System.out.print(aux.getName() + " ");
                                        if (aux.getPnext() != null) {
                                            bw.write(aux.getName() + "," + Integer.toString(aux.getStock()));

                                        } else {
                                            bw.write(aux.getName() + "," + Integer.toString(aux.getStock()) + ";");
                                        }
                                        aux = aux.getPnext();
                                        bw.newLine();
                                    }

                                }

                            }
                        }
                        bw.write("Rutas;");
                        bw.newLine();
                        for (int i = 0; i < m.getGrafo().getRoute().length; i++) {
                            //escribe los datos en el archivo
                            if (m.getGrafo().getRoute()[i] != null) {
                                bw.write(m.getGrafo().getRoute()[i].getOrigin().getName() + "," + m.getGrafo().getRoute()[i].getDestination().getName() + "," + m.getGrafo().getRoute()[i].getDistance());
                                bw.newLine();
                            }
                        }

                        bw.close();
                    } catch (IOException ex) {
                        Logger.getLogger(JFileChooser.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } while (input == 1);

    }

    //Abre el archivo seleccionado a traves de un JFileChooser
    public void OpenFile() {

        Random random = new Random();
        if (seleccionado.showDialog(this, "Abrir Archivo") == JFileChooser.APPROVE_OPTION) {

            archivo = seleccionado.getSelectedFile();
            if (archivo.canRead()) {
                if (archivo.getName().endsWith("txt")) {

                    String contenido = file.openFile(archivo);
                    try {

                        BufferedReader bf = new BufferedReader(new FileReader(seleccionado.getSelectedFile().toString()));
                        String aux;
                        String bfRead = bf.readLine();
                        aux = bfRead;

                        // arreglo = aux.split(",", 3);
                        while (aux != null) {
                            if (aux.equalsIgnoreCase("Almacenes;")) {
                                do {
                                    aux = bf.readLine();

                                    if (!aux.contains(";") && aux.contains(":")) {
                                        aux = aux.replace(":", "");
                                        aux = aux.replace("Almacen", "");
                                        nameWarehouse = aux.trim();

                                        m.getGrafo().addWarehouse(nameWarehouse);
                                        m.getVertice().add(new Vertice(random.nextInt(700), random.nextInt(500), nameWarehouse.trim()));

                                        // aqui tienes el nombre del almacen o sea la letra
                                        //almacen lo agregariamos file listaAlmacen
                                    }

                                    m.repaint();
                                    if (aux.contains(",")) {
                                        aux = aux.replace(";", "");
                                        String[] arreglo = aux.split(",");
                                        aux_product = arreglo[0];
                                        aux_quantity = Integer.parseInt(arreglo[1]);

                                        m.getGrafo().searchWarehouse(nameWarehouse).getProducts().insertFinal(aux_product, aux_quantity);
                                    }
                                } while (!aux.equalsIgnoreCase("Rutas;"));
                            }
                            if (aux.contains("Rutas;")) {
                                do {
                                    aux = bf.readLine();
                                    if (aux != null) {
                                        String[] arreglo2 = aux.split(",");

                                        if (arreglo2.length == 3) {

                                            aux_origin = arreglo2[0];
                                            aux_destination = arreglo2[1];
                                            aux_distance = Integer.parseInt(arreglo2[2]);

                                            m.getGrafo().addRoute(aux_origin, aux_destination, aux_distance, aux_origin + aux_destination);
                                        }
                                    }
                                } while (aux != null);
                            }

                            aux = bf.readLine();
                        }

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Por favor seleccione un archivo de texto");
                }

            }

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        fondo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        itemOpen = new javax.swing.JMenuItem();
        itemUpdate = new javax.swing.JMenuItem();
        addWarehouse = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        StockInfo = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        BuyItems = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo2.jpeg"))); // NOI18N

        jMenu1.setText("Cargar Archivo");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        itemOpen.setText("Abrir");
        itemOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemOpenActionPerformed(evt);
            }
        });
        jMenu1.add(itemOpen);

        itemUpdate.setText("Actualizar");
        itemUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemUpdateActionPerformed(evt);
            }
        });
        jMenu1.add(itemUpdate);

        jMenuBar1.add(jMenu1);

        addWarehouse.setText("Agregar");

        jMenuItem1.setText("Almacen");
        jMenuItem1.addMenuKeyListener(new javax.swing.event.MenuKeyListener() {
            public void menuKeyPressed(javax.swing.event.MenuKeyEvent evt) {
            }
            public void menuKeyReleased(javax.swing.event.MenuKeyEvent evt) {
                jMenuItem1MenuKeyReleased(evt);
            }
            public void menuKeyTyped(javax.swing.event.MenuKeyEvent evt) {
            }
        });
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseClicked(evt);
            }
        });
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        addWarehouse.add(jMenuItem1);

        jMenuItem2.setText("Ruta");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        addWarehouse.add(jMenuItem2);

        jMenuBar1.add(addWarehouse);

        StockInfo.setText("Productos");
        StockInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StockInfoActionPerformed(evt);
            }
        });

        jMenuItem5.setText("Ver Stock");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        StockInfo.add(jMenuItem5);

        BuyItems.setText("Comprar");
        BuyItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuyItemsActionPerformed(evt);
            }
        });
        StockInfo.add(BuyItems);

        jMenuBar1.add(StockInfo);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        new AddWarehouse(m).setVisible(true);


    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        new AddRoute(m).setVisible(true);


    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked

    }//GEN-LAST:event_jMenuItem1MouseClicked

    private void jMenuItem1MenuKeyReleased(javax.swing.event.MenuKeyEvent evt) {//GEN-FIRST:event_jMenuItem1MenuKeyReleased

    }//GEN-LAST:event_jMenuItem1MenuKeyReleased

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        new Stock(m).setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void StockInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StockInfoActionPerformed
        // TODO add your handling code here:
        MenuItem menuItem11 = new MenuItem("Item 1.1.1");

    }//GEN-LAST:event_StockInfoActionPerformed

    private void BuyItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuyItemsActionPerformed

        new Buy(m).setVisible(true);


    }//GEN-LAST:event_BuyItemsActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed


    }//GEN-LAST:event_jMenu1ActionPerformed

    private void itemOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemOpenActionPerformed

        if (m.getGrafo().IsEmpty()) {
            this.OpenFile();
        } else {
            String[] options = {"Guardar", "Subir Archivo", "Cancelar"};
            int x = JOptionPane.showOptionDialog(null, "Desea guardar los cambios realizados, antes de cargar un nuevo archivo?",
                    "ALERTA",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
            System.out.println(x);

            if (x == 0) {
                this.UpdateFiles();
                m.reset();
                this.OpenFile();
            } else if (x == 1) {
                m.reset();
                this.OpenFile();
            }

        }    }//GEN-LAST:event_itemOpenActionPerformed

    private void itemUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemUpdateActionPerformed
        this.UpdateFiles();
     }//GEN-LAST:event_itemUpdateActionPerformed

    public static void main(String args[]) {

        Map m = new Map();
        Principal a = new Principal(m);
        a.setContentPane(m);
        m.addMouseListener(new Click(m));
        a.setSize(1000, 1000);
        a.setLocationRelativeTo(null);
        a.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem BuyItems;
    private javax.swing.JMenu StockInfo;
    private javax.swing.JMenu addWarehouse;
    private javax.swing.JLabel fondo;
    private javax.swing.JMenuItem itemOpen;
    private javax.swing.JMenuItem itemUpdate;
    private javax.swing.JButton jButton3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem5;
    // End of variables declaration//GEN-END:variables

}
