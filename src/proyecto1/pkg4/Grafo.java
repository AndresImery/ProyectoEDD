package proyecto1.pkg4;

import javax.swing.JOptionPane;

/**
 *
 * @author alecuna
 */
public class Grafo {

    Warehouse[] warehouses;
    int[][] matrizA;
    int[][] matrizD;
    Vertice vertice;
    private Map map;
    Route[] route;
    private int numRoutes;
    String[] routes = new String[10];

    public Grafo(Map m) {

        this.warehouses = new Warehouse[10];
        this.route = new Route[10];
        this.matrizA = new int[warehouses.length][warehouses.length];
        this.matrizD = new int[warehouses.length][warehouses.length];

        this.map = m;

        for (int i = 0; i < matrizA.length; i++) {
            for (int j = 0; j < matrizA[i].length; j++) {
                matrizA[i][j] = 0;
            }
        }

        for (int i = 0; i < matrizD.length; i++) {
            for (int j = 0; j < matrizD[i].length; j++) {
                matrizD[i][j] = 0;
            }
        }

    }

    //verifica si la lista existen almacenes
    public boolean IsEmpty() {
        boolean vacio = true;
        for (int i = 0; i < warehouses.length; i++) {
            if (warehouses[i] != null) {
                vacio = false;
            }
        }

        return vacio;
    }

    //permite agregar un nuevo almacen (vertice) en el dibujo del grafo
    public void addWarehouse(String name) {
        boolean agregado = false;

        for (int i = 0; i < warehouses.length; i++) {
            if (warehouses[i] == null) {
                this.warehouses[i] = new Warehouse(name);
                agregado = true;
                break;
            }
        }
        if (!agregado) {
            JOptionPane.showMessageDialog(null, "No se pueden agregar más almacenes.");
        }

    }

    //agrega rutas (aristas) para conectar dos almacenes (vertices)
    public void addRoute(String origin, String destination, int distance, String nRoute) {
        boolean existeO = false;
        boolean existeD = false;
        boolean existeV = false;
        int posO = 0;
        int posD = 0;

        for (int i = 0; i < warehouses.length; i++) {

            if (warehouses[i].getCodigoC().equals(origin)) {
                existeO = true;
                posO = i;

                break;
            }
        }

        for (int j = 0; j < warehouses.length; j++) {
            if (warehouses[j].getCodigoC().equalsIgnoreCase(destination)) {
                existeD = true;
                posD = j;

                break;
            }
        }

        for (int m = 0; m < route.length; m++) {
            if (route[m] != null) {
                if (route[m].getOrigin().getName().equalsIgnoreCase(origin) && route[m].getDestination().getName().equalsIgnoreCase(destination)) {
                    existeV = true;
                    break;
                }
            }
        }

        if (existeO && existeD && matrizA[posO][posD] == 0 && !existeV) {
            for (int k = 0; k < route.length; k++) {
                if (route[k] == null) {
                    route[k] = new Route(this.warehouses[posO], this.warehouses[posD], distance, nRoute);

                    matrizA[posO][posD] = 1;
                    matrizD[posO][posD] = this.route[k].getDistance();
                    break;
                }
            }

            for (int j = 0; j < map.getVertice().size(); j++) {

                if (map.getVertice().elementAt(j).getName().equalsIgnoreCase(origin)) {
                    posO = j;
                }
            }
            for (int i = 0; i < map.getVertice().size(); i++) {

                if (map.getVertice().elementAt(i).getName().equalsIgnoreCase(destination)) {
                    posD = i;

                }
            }

            map.getLine().add(new Arista(map.getVertice().elementAt(posO).getX(), map.getVertice().elementAt(posO).getY(), map.getVertice().elementAt(posD).getX(), map.getVertice().elementAt(posD).getY(), origin + destination, Integer.toString(distance)));
            map.repaint();

        } else {
            JOptionPane.showMessageDialog(map, "Error. Vuela a intentarlo.");
        }

    }

    public Warehouse searchWarehouse(String nombre) {
        Warehouse aux = null;
        boolean found = false;

        for (int i = 0; i < warehouses.length; i++) {
            if (warehouses[i] != null) {
                if (warehouses[i].getName().equalsIgnoreCase(nombre)) {

                    aux = warehouses[i];
                    found = true;
                    break;

                }
            }
        }
        if (found) {
            return aux;
        } else {
            return null;
        }

    }

    //retorna el indice de un almacen en el array de almacenes
    public int getIndex(String nombre) {
        int index = -1;

        for (int i = 0; i < warehouses.length; i++) {
            if (warehouses[i] != null) {
                if (nombre.trim().equalsIgnoreCase(warehouses[i].getName().trim())) {

                    index = i;
                    break;
                }
            }

        }

        return index;
    }

    public boolean searchVertice(int i, int j) {
        if (matrizA[i][j] != 0) {
            return true;
        } else if (matrizA[i][j] == 0) {
            return false;
        }
        return false;
    }
    
    //crea un array de productos de todos los almacenes
    public String listAllProducts() {
        String listProducts = "";

        for (int i = 0; i < this.warehouses.length; i++) {

            if (this.warehouses[i] != null) {

                if (!this.warehouses[i].getProducts().IsEmpty()) {

                    Product aux = this.warehouses[i].getProducts().getpFirst();
                    for (int j = 0; j < this.warehouses[i].getProducts().getSize(); j++) {
                        if (aux != null) {
                            if (aux.getStock() > 0) {
                                listProducts += aux.getName() + "\n";
                            }

                            aux = aux.getPnext();
                        }

                    }

                }
            }
        }

        return listProducts;
    }
    
    //crea un array de todos los almacenes existentes
    public String[] listAllWarehouses(String product) {
        String[] listWarehouse1 = new String[this.warehouses.length];
        int size = 0;
        for (int i = 0; i < this.warehouses.length; i++) {

            if (this.warehouses[i] != null) {

                if (!this.warehouses[i].getProducts().IsEmpty()) {

                    Product aux = this.warehouses[i].getProducts().getpFirst();
                    for (int j = 0; j < this.warehouses[i].getProducts().getSize(); j++) {
                        if (aux != null) {

                            if (product.trim().equalsIgnoreCase(aux.getName()) && aux.getStock() > 0) {
                                listWarehouse1[size] = this.warehouses[i].getName();
                                size++;
                            }
                            aux = aux.getPnext();
                        }

                    }

                }
            }
        }

        String[] listWarehouses2 = new String[size];

        for (int j = 0; j < size; j++) {
            listWarehouses2[j] = listWarehouse1[j];
        }

        return listWarehouses2;

    }
    
    public int maxStockWarehouse(String product) {
        int stock = 0;
        for (int i = 0; i < this.warehouses.length; i++) {

            if (this.warehouses[i] != null) {

                if (!this.warehouses[i].getProducts().IsEmpty()) {

                    Product aux = this.warehouses[i].getProducts().getpFirst();
                    for (int j = 0; j < this.warehouses[i].getProducts().getSize(); j++) {
                        if (aux != null) {

                            if (product.trim().equalsIgnoreCase(aux.getName())) {

                                stock += aux.getStock();
                            }
                            aux = aux.getPnext();
                        }

                    }

                }
            }
        }

        return stock;

    }


    public Warehouse[] getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(Warehouse[] warehouses) {
        this.warehouses = warehouses;
    }

    public int[][] getMatrizA() {
        return matrizA;
    }

    public void setMatrizA(int[][] matrizA) {
        this.matrizA = matrizA;
    }

    public Vertice getVertice() {
        return vertice;
    }

    public void setVertice(Vertice vertice) {
        this.vertice = vertice;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Route[] getRoute() {
        return route;
    }

    public void setRoute(Route[] route) {
        this.route = route;
    }

    public int[][] getMatrizD() {
        return matrizD;
    }

    public void setMatrizD(int[][] matrizD) {
        this.matrizD = matrizD;
    }

}
