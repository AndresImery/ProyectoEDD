package proyecto1.pkg4;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author alecuna
 */
public class Map extends JPanel {

    private ArrayList<Integer> aux_route = new ArrayList();
    private ArrayList<Integer> aux_route2 = new ArrayList();
    private ArrayList<Integer> aux_distance = new ArrayList();
    private ArrayList aux_warehouseC = new ArrayList();
    private ArrayList<Integer> aux_posX = new ArrayList();
    private ArrayList<Integer> aux_posY = new ArrayList();
    private ArrayList aux_originC = new ArrayList();
    private ArrayList aux_destinationC = new ArrayList();
 
    Warehouse auxOrigin;
    Warehouse auxDestination;

    private Vector<Vertice> vertice;
    private Vector<Arista> Line;
    private Point p1;
    private Point p2;
    private Route route;
    private String codigoW;
    private Grafo grafo;
    private String nRoute;

    int pos = 0;
    int num = 0;

    public boolean createNode = false;

    public Map() {

        this.vertice = new Vector<>();
        this.Line = new Vector<>();
        this.grafo = new Grafo(this);
        this.setLayout(new FlowLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setSize(800, 550);
        this.p1 = null;
        this.p2 = null;
        this.setBackground(Color.WHITE);
        this.setLocation(125, 50);

    }
    public void reset(){
        this.vertice = new Vector<>();
        this.Line = new Vector<>();
        this.grafo = new Grafo(this);
        this.p1 = null;
        this.p2 = null;
    }
   
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Vertice objCirculo : vertice) {
            objCirculo.warehouseDibujo(g);
        }
        for (Arista linea : Line) {
            linea.paintLine(g);
        }
    }

    public Vector<Vertice> getVertice() {
        return vertice;
    }

    public void setVertice(Vector<Vertice> vertice) {
        this.vertice = vertice;
    }

    public Vector<Arista> getLine() {
        return Line;
    }

    public void setLine(Vector<Arista> Line) {
        this.Line = Line;
    }

    public Point getP1() {
        return p1;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getCodigoW() {
        return codigoW;
    }

    public void setCodigoW(String codigoW) {
        this.codigoW = codigoW;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isCreateNode() {
        return createNode;
    }

    public void setCreateNode(boolean createNode) {
        this.createNode = createNode;
    }

    public Grafo getGrafo() {
        return grafo;
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
    }

    public String getnRoute() {
        return nRoute;
    }

    public void setnRoute(String nRoute) {
        this.nRoute = nRoute;
    }

    public ArrayList<Integer> getAux_route() {
        return aux_route;
    }

    public void setAux_route(ArrayList<Integer> aux_route) {
        this.aux_route = aux_route;
    }

    public ArrayList<Integer> getAux_route2() {
        return aux_route2;
    }

    public void setAux_route2(ArrayList<Integer> aux_route2) {
        this.aux_route2 = aux_route2;
    }
    
    public ArrayList<Integer> getAux_distance() {
        return aux_distance;
    }

    public void setAux_distance(ArrayList<Integer> aux_distance) {
        this.aux_distance = aux_distance;
    }

    public Warehouse getAuxOrigin() {
        return auxOrigin;
    }

    public void setAuxOrigin(Warehouse auxOrigin) {
        this.auxOrigin = auxOrigin;
    }

    public Warehouse getAuxDestination() {
        return auxDestination;
    }

    public void setAuxDestination(Warehouse auxDestination) {
        this.auxDestination = auxDestination;
    }

    public ArrayList getAux_warehouseC() {
        return aux_warehouseC;
    }

    public void setAux_warehouseC(ArrayList aux_warehouseC) {
        this.aux_warehouseC = aux_warehouseC;
    }

    public ArrayList<Integer> getAux_posX() {
        return aux_posX;
    }

    public void setAux_posX(ArrayList<Integer> aux_posX) {
        this.aux_posX = aux_posX;
    }

    public ArrayList<Integer> getAux_posY() {
        return aux_posY;
    }

    public void setAux_posY(ArrayList<Integer> aux_posY) {
        this.aux_posY = aux_posY;
    }

    public ArrayList getAux_originC() {
        return aux_originC;
    }

    public void setAux_originC(ArrayList aux_originC) {
        this.aux_originC = aux_originC;
    }

    public ArrayList getAux_destinationC() {
        return aux_destinationC;
    }

    public void setAux_destinationC(ArrayList aux_destinationC) {
        this.aux_destinationC = aux_destinationC;
    }
    
}
