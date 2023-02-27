package proyecto1.pkg4;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author alecuna
 */
public class Vertice {
    
    private int x;
    private int y;
    String name;
    public static final int d = 35;
    public Color color;

    public Vertice(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name; 
        this.color = Color.BLUE; 
    }
   
    public void warehouseDibujo(Graphics g){
        g.setColor(color);
        g.fillOval(x-d/2, y-d/2, d, d);
        g.setColor(Color.WHITE);
        g.drawString(name, x-d/3, y+d/6); //muestra el nombre del circulo
    }
    

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
            
    
    
    
}
