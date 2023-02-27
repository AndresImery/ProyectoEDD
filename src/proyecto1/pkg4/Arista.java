package proyecto1.pkg4;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author alecuna
 */
public class Arista {

    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private String route;
    private String distance;
    public Color color;
    
    public Arista(int x1, int y1, int x2, int y2, String route, String distance) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.route = route;
        this.color = Color.BLUE;
        this.distance = distance;

    }

    //Pinta la linea que bordea el area donde se muestra el grafo
    public void paintLine(Graphics g) {
        g.setColor(color);

        g.drawLine(x1, y1, x2, y2);

        int xMenor = smallest(x1, x2);
        int xMayor = largest(x1, x2);
        int yMenor = smallest(y1, y2);
        int yMayor = largest(y1, y2);

        int distanciaVertical = yMayor + yMenor;
        int distanciaHorizontal = xMayor + xMenor;
        
        g.setColor(Color.BLACK);
        
        g.drawString(route+" - "+distance, distanciaHorizontal / 2, distanciaVertical / 2);
      

    }

    private int smallest(int n1, int n2) {
        if (n1 < n2) {
            return n1;

        } else {
            return n2;
        }
    }

    private int largest(int n1, int n2) {
        if (n1 > n2) {
            return n1;
        } else {
            return n2;
        }
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

}
