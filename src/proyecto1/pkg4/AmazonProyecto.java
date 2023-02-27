package proyecto1.pkg4;

/**
 *
 * @author alecuna
 */
public class AmazonProyecto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Map m = new Map();

        Principal a = new Principal(m);

        a.getContentPane().add(m);

        m.addMouseListener(new Click(m));
        
        a.setVisible(true);
    }

}
