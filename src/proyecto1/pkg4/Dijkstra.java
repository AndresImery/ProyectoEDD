package proyecto1.pkg4;

import javax.swing.JOptionPane;

/**
 *
 * @author alecuna
 */
public class Dijkstra {

    private int[][] peso;
    private int[] ultimo;
    private int[] D;
    private int[] route;
    private boolean[] F;
    private int s, n; // vértice origen y número de vértices
    private Grafo g;
    private Map m;
    private int destination;
    private int result;

    public Dijkstra(Grafo g, int origin, int destination, Map m) {
        n = g.getWarehouses().length;
        s = origin;
        this.peso = g.getMatrizD();
        this.ultimo = new int[n];
        this.D = new int[n];
        this.F = new boolean[n];
        this.destination = destination;
        this.g = g;
        this.m = m;
        this.route = new int[n];

        for (int i = 0; i < route.length; i++) {
            route[i] = 999;
        }

        for (int i = 0; i < peso.length; i++) {
            for (int j = 0; j < peso[i].length; j++) {
                if (peso[i][j] == 0) {
                    peso[i][j] = 999999999;
                }

            }
        }

    }

    public Dijkstra(Grafo g, int origin, boolean d, int destination, Map m) {
        n = g.getWarehouses().length;
        s = origin;
        peso = g.getMatrizD();
        ultimo = new int[n];
        D = new int[n];
        F = new boolean[n];
        this.g = g;
        this.destination = destination;
        this.m = m;

        for (int i = 0; i < route.length; i++) {
            route[i] = 999;
        }

        for (int i = 0; i < peso.length; i++) {
            for (int j = 0; j < peso[i].length; j++) {
                if (peso[i][j] == 0) {
                    peso[i][j] = 999999999;
                }

            }
        }
    }

    //calcula la ruta mas corta entre dos vertices
    public void shortestRoute() {

        for (int i = 0; i < n; i++) {
            F[i] = false;
            D[i] = peso[s][i];
            ultimo[i] = s;
        }

        F[s] = true;
        D[s] = 0;
        for (int i = 0; i < n; i++) {

            int v = minimum();

            F[v] = true;
          
            for (int w = 0; w < n; w++) {
                if (!F[w]) {

                    if ((D[v] + peso[v][w]) < D[w]) {

                        D[w] = D[v] + peso[v][w];

                        ultimo[w] = v;

                    }
                }
            }

        }

        for (int i = 0; i < n; i++) {
            if (i == destination) {
                if (D[i] != 999999999 && D[i] != 0) {
                    this.result = D[i];
                } else {
                    JOptionPane.showMessageDialog(m, "No existen rutas a ese destino");
                }

            }

        }

    }

    private int minimum() {
        int mx = 999999999;
        int v = 1;
        for (int j = 0; j < n; j++) {

            if (!F[j] && (D[j] <= mx) && D[j] != 0) {

                mx = D[j];
                v = j;
            }
        }

        return v;
    }

    public void recuperaCamino(int v) {
        int anterior = ultimo[v];
        if (v != s) {
            recuperaCamino(anterior); // vuelve al último del último
            for (int k = 1; k < n; k++) {
                if (route[k] == 999) {
                    route[k] = v;
                    break;
                }
            }

        } else {

            route[0] = s;
        }

    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int[] getRoute() {
        return route;
    }

    public void setRoute(int[] route) {
        this.route = route;
    }

}
