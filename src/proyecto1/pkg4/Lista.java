package proyecto1.pkg4;

/**
 *
 * @author alecuna
 */
public class Lista {

    private Product pFirst;
    private Product pLast;
    private int size;

    public Product getpFirst() {
        return pFirst;
    }

    public void setpFirst(Product pFirst) {
        this.pFirst = pFirst;
    }

    public Product getPlast() {
        return pLast;
    }

    public void setPlast(Product pLast) {
        this.pLast = pLast;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Lista() {
        this.pFirst = null;
        this.pLast = null;
        this.size = 0;
    }

    public boolean IsEmpty() {
        return pFirst == null;
    }

    public boolean findProduct(String name) {

        boolean found = false;
        if (!IsEmpty()) {
            Product aux = pFirst;
            for (int i = 0; i < size; i++) {
                if (aux.getName().equalsIgnoreCase(name)) {
                    found = true;
                }
                aux = aux.getPnext();

            }
        }

        return found;

    }

    public void insertFinal(String name, int stock) {

        Product nuevo = new Product(name, stock);

        if (IsEmpty()) {
            pFirst = nuevo;
            pLast = nuevo;
        } else {
            Product aux = pLast;
            aux.setPnext(nuevo);
            pLast = nuevo;

        }

        size += 1;

    }

    public void printList() {

        if (!IsEmpty()) {
            Product aux = pFirst;
            for (int i = 0; i < size; i++) {
                System.out.print(aux.getName() + " " + aux.getStock());
                aux = aux.getPnext();
            }
        } else {
            System.out.println("La lista esta vacia");
        }
    }

    public void pickProducts(String name, int stock, Buy buy, int distance, Warehouse warehouse) {

        Product aux = pFirst;
        for (int i = 0; i < size; i++) {

            if (name.trim().equalsIgnoreCase(aux.getName().trim())) {
                aux.reserveProducts(stock, buy, distance, warehouse);
                break;
            }

            aux = aux.getPnext();

        }

    }

    public void buyProducts(String name, int stock) {

        Product aux = pFirst;
        for (int i = 0; i < size; i++) {

            if (name.trim().equalsIgnoreCase(aux.getName().trim())) {
                aux.buy(stock);
                break;
            }

            aux = aux.getPnext();

        }

    }

}
