package proyecto1.pkg4;

/**
 *
 * @author alecuna
 */
public class ItemCompra {
    
   private Warehouse warehouse;
   private Product product;
   private int quantity;
   private int distance;

    public ItemCompra(Warehouse warehouse, Product product, int quantity, int distance) {
        this.warehouse = warehouse;
        this.product = product;
        this.quantity = quantity;
        this.distance = distance;
    }
        public ItemCompra(Warehouse warehouse, Product product, int quantity) {
        this.warehouse = warehouse;
        this.product = product;
        this.quantity = quantity;
        this.distance = 0;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getDistance() {
        return distance;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
    
    
}
