package proyecto1.pkg4;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author alecuna
 */
public class Buy extends javax.swing.JFrame {

    Map m;
    String productList = "";
    int size;
    String[] aux_productList;
    Dijkstra d;
    int quantity = 0;
    Warehouse[] warehouseRoute;
    ItemCompra[] items;
    String OrderSummaryContents = "";
    int x;
    String[] warehouseList;

    public Buy(Map m) {

        initComponents();
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.m = m;
        this.warehouseRoute = new Warehouse[m.getGrafo().warehouses.length];
        this.aux_productList = m.getGrafo().listAllProducts().split("\n");
        this.size = m.getGrafo().listAllProducts().split("\n").length;
        this.quantity = 0;
        this.removeDuplicates();
        for (int i = 0; i < size; i++) {
            this.productList += aux_productList[i] + "\n";
        }
        this.CompleteProductList.setEditable(false);
        this.OrderSummary.setEditable(false);
        this.ButtonPlaceOrder.setVisible(false);
        this.ButtonAddProduct.setVisible(false);
        CompleteProductList.setText(productList);
        this.items = new ItemCompra[100];
        this.warehouseList = this.getAlmacenList();
        this.chooseWarehouse();

    }
    
    //Le da la opcion al usuario de a que almacen quiere que lleguen sus productos
    public void chooseWarehouse() {
        this.x = JOptionPane.showOptionDialog(null, "Seleccione el almacen al que desee que lleguen los productos.",
                "Selección de Almacen",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, warehouseList, warehouseList[0]);
        this.OrderSummaryContents += "Almacen de destino: " + warehouseList[x] + "\n\n";
        OrderSummary.setText(OrderSummaryContents);
        this.ButtonAddProduct.setVisible(true);
    }

    //No permite duplicados en la lista de productos disponibles que se muestra a la hora de hacer un pedido 
    public void removeDuplicates() {
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (aux_productList[i].equals(aux_productList[j])) {
                    aux_productList[j] = aux_productList[size - 1];
                    size--;
                    j--;
                }
            }
        }
    }

    //Para revisar que el producto ingresado por el usuario exista en la lista de productos disponibles
    public boolean findProduct(String name) {
        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (name.trim().equalsIgnoreCase(aux_productList[i])) {
                found = true;
                break;
            }
        }

        return found;
    }

    //funcion para extra check a la hora de solicitar un pedido
    public boolean check(String name) {
        boolean found = false;
        for (int i = 0; i < this.warehouseRoute.length; i++) {
            if (this.warehouseRoute[i] != null) {
                if (this.warehouseRoute[i].getName().equalsIgnoreCase(name)) {
                    found = true;
                }
            }
        }
        return found;
    }
    
    //Utilizado para crear la orden y mostrar en resumen de orden con el almacen de origen, la ruta mas cerca y los kilometros a recorrer
    public void preOrder(int x, String[] warehouses, String product) {

        int posD = m.getGrafo().getIndex(warehouses[x]);
        int pos = 0;
        int shortestRoute = 999999999; //mientras se cambia su valor
        Warehouse closestWarehouse = m.getGrafo().searchWarehouse(warehouses[x]);
        String finalRoute = "";
        String[] routesArray;

        for (int i = 0; i < warehouses.length; i++) {
            String route = "";

            if (i != x && m.getGrafo().warehouses[i].findProduct(product) && !this.check(warehouses[i])) {
                pos = m.getGrafo().getIndex(warehouses[i]);

                d = new Dijkstra(m.getGrafo(), pos, posD, m);
                d.shortestRoute();
                d.recuperaCamino(posD);
                for (int k = 0; k < d.getRoute().length; k++) {
                    if (d.getRoute()[k] != 999) {
                        route = route + " " + m.getGrafo().getWarehouses()[d.getRoute()[k]].getCodigoC();
                    } else {
                        break;
                    }

                }
                if (d.getResult() < shortestRoute) {
                    shortestRoute = d.getResult();
                    finalRoute = route;
                    routesArray = route.split(" ");
                    closestWarehouse = m.getGrafo().searchWarehouse(routesArray[1]);

                }
            }
        }
        m.getGrafo().searchWarehouse(closestWarehouse.getName()).getProducts().pickProducts(product, this.quantity, this, shortestRoute, closestWarehouse);
        this.OrderSummaryContents += "        La ruta mínima es " + finalRoute + "\n";
    }
    
    public boolean checkPreOrder(Warehouse warehouse, Product product) {
        boolean found = false;

        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i] != null) {
                if (this.items[i].getWarehouse().getName().equalsIgnoreCase(warehouse.getName()) && this.items[i].getProduct().getName().equalsIgnoreCase(product.getName())) {
                    found = true;
                    break;
                }
            }

        }

        return found;
    }

    //si el checkPreOrder es verdadero va a agregar los productos all array de items qie se desean comprar
    public void addToCart(Warehouse warehouse, int quantity, Product product, int distance) {

        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i] == null) {
                if (!this.checkPreOrder(warehouse, product)) {
                    this.items[i] = new ItemCompra(warehouse, product, quantity, distance);
                    break;
                }

            }
        }

    }

    //obtiene un array con los nombres de los almacenes existentes
    public String[] getAlmacenList() {
        String[] lista = new String[m.getGrafo().warehouses.length];
        int cont = 0;
        for (int i = 0; i < lista.length; i++) {
            if (m.getGrafo().warehouses[i] != null) {
                lista[i] = m.getGrafo().warehouses[i].getName();
                cont++;
            }

        }
        String[] lista2 = new String[cont];

        for (int j = 0; j < lista.length; j++) {
            if (m.getGrafo().warehouses[j] != null) {
                lista2[j] = lista[j];

            }

        }

        return lista2;
    }

    //resta las cantidades de productos solicitados una vez que se confirma la compra
    public void confirmBuy() {
        for (int i = 0; i < this.items.length; i++) {
            if (this.items[i] != null) {
                this.items[i].getWarehouse().getProducts().buyProducts(this.items[i].getProduct().getName(), this.items[i].getQuantity());
            }
        }

        JOptionPane.showMessageDialog(this, "Su compra ha sido exitosa.");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        CompleteProductList = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        ProductQuantity = new javax.swing.JTextField();
        ProductName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ButtonAddProduct = new javax.swing.JButton();
        ButtonPlaceOrder = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        OrderSummary = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, -1, -1));

        CompleteProductList.setColumns(20);
        CompleteProductList.setRows(5);
        jScrollPane1.setViewportView(CompleteProductList);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 220, 140));
        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, -1, -1));

        jLabel1.setText("Productos disponibles");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));
        getContentPane().add(ProductQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 200, -1));
        getContentPane().add(ProductName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 200, -1));

        jLabel2.setText("Cantidad");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jLabel3.setText("Producto");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        ButtonAddProduct.setText("Añadir");
        ButtonAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAddProductActionPerformed(evt);
            }
        });
        getContentPane().add(ButtonAddProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));

        ButtonPlaceOrder.setText("Confirmar pedido");
        ButtonPlaceOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonPlaceOrderActionPerformed(evt);
            }
        });
        getContentPane().add(ButtonPlaceOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        OrderSummary.setColumns(20);
        OrderSummary.setRows(5);
        jScrollPane3.setViewportView(OrderSummary);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, -1, 280));

        jLabel4.setText("Resumen de compra");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, -1, -1));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo2.jpeg"))); // NOI18N
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 530, 370));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAddProductActionPerformed

        try {
            String product = ProductName.getText();
            int quant = Integer.parseInt(ProductQuantity.getText());
            this.quantity = quant;

            if (this.findProduct(product)) {
                if (m.getGrafo().maxStockWarehouse(product) >= quant) {
                    this.OrderSummaryContents += "- " + Integer.toString(quant) + " " + product + "\n";
                    this.ButtonPlaceOrder.setVisible(true);
                    m.getGrafo().searchWarehouse(warehouseList[x]).getProducts().pickProducts(product, quant, this, 0, m.getGrafo().searchWarehouse(warehouseList[x]));
                    if (this.quantity > 0) {

                        do {
                            this.preOrder(x, warehouseList, product);
                        } while (this.quantity > 0);

                    }

                    ProductQuantity.setText("");
                    ProductName.setText("");
                    OrderSummary.setText(OrderSummaryContents);
                } else {
                    JOptionPane.showMessageDialog(this, "No hay suficientes " + product + " disponibles para completar el despacho.");
                }

            } else {
                JOptionPane.showMessageDialog(this, "No se encontro ningun producto con ese nombre.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Algo salió mal.");
        }


    }//GEN-LAST:event_ButtonAddProductActionPerformed

    private void ButtonPlaceOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonPlaceOrderActionPerformed
        // TODO add your handling code here:
        try {
            this.confirmBuy();
            this.setVisible(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Algo salió mal.");
        }

    }//GEN-LAST:event_ButtonPlaceOrderActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAddProduct;
    private javax.swing.JButton ButtonPlaceOrder;
    private javax.swing.JTextArea CompleteProductList;
    private javax.swing.JTextArea OrderSummary;
    private javax.swing.JTextField ProductName;
    private javax.swing.JTextField ProductQuantity;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
