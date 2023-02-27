package proyecto1.pkg4;

/**
 *
 * @author alecuna
 */
public class Product {

    private String name;
    private int stock;
    private Product pNext;

    public Product(String name, int stock) {
        this.name = name;
        this.stock = stock;
        this.pNext = null;
    }

    public Product(String[] products) {
        this.name = products[0];
        this.stock = Integer.parseInt(products[1]);
        this.pNext = null;

    }
    
    //Aparta los productos que selecciono el usuario al crear su orden, sin restarlos de los disponibles hasta que la orden no sea confirmada
    public void reserveProducts(int stock, Buy buy, int distance, Warehouse warehouse) {

        if (stock <= this.stock) {

            buy.quantity -= stock;
            buy.addToCart(warehouse, stock, this, distance);
            buy.OrderSummaryContents += "       - " + warehouse.getName() + " " + Integer.toString(stock) + " " + Integer.toString(distance) + "KM" + "\n";

        } else {
            buy.OrderSummaryContents += "       - " + warehouse.getName() + " " + Integer.toString(this.stock) + " " + Integer.toString(distance) + "KM" + "\n";
            buy.quantity -= this.stock;
            buy.addToCart(warehouse, this.stock, this, distance);

        }
    }
    
    //reduce el stock de los productos comprados
    public void buy(int stock) {

        if (stock <= this.stock) {
            this.stock -= stock;

        } else {

            this.stock = 0;

        }
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }


    public Product getPnext() {
        return pNext;
    }

    public void setPnext(Product pnext) {
        this.pNext = pnext;
    }

}
