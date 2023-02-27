package proyecto1.pkg4;

/**
 *
 * @author alecuna
 */
public class Route {
    
    private Warehouse origin;
    private Warehouse destination;
    private int distance;
    private String route;
    private Vertice vertice;

    public Route(Warehouse origin, Warehouse destination, int distance,  String route) {
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.vertice = vertice;
        this.route = route;
        
    }

    public Warehouse getOrigin() {
        return origin;
    }

    public void setOrigin(Warehouse origin) {
        this.origin = origin;
    }

    public Warehouse getDestination() {
        return destination;
    }

    public void setDestination(Warehouse destination) {
        this.destination = destination;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Vertice getVertice() {
        return vertice;
    }

    public void setVertice(Vertice vertice) {
        this.vertice = vertice;
    }

  
    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
    
}
