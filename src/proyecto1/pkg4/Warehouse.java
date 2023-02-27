package proyecto1.pkg4;

/**
 *
 * @author alecuna
 */
public class Warehouse {

    private String name;
    private String codigoC;
    private Lista products;

    public Warehouse(String nombre) {
        this.name = nombre;
        this.codigoC = nombre;
        this.products = new Lista();
    }

    //encuentra un producto dentro del almacen seleccionado
    public boolean findProduct(String producto) {
        boolean found = false;
        if (!this.products.IsEmpty()) {
            Product aux = this.products.getpFirst();
            for (int i = 0; i < this.products.getSize(); i++) {
                if (aux.getName().equalsIgnoreCase(producto)) {
                    found = true;
                }
                aux = aux.getPnext();

            }
        }

        return found;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodigoC() {
        return codigoC;
    }

    public void setCodigoC(String codigoC) {
        this.codigoC = codigoC;
    }

    public Lista getProducts() {
        return products;
    }

    public void setProducts(Lista products) {
        this.products = products;
    }

}
