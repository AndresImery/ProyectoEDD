
package proyecto1.pkg4;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author alecuna
 */
public class Click extends MouseAdapter {

    private Map map;
    private int route;

    public Click(Map map) {
        this.map = map;
        this.route = 0;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getButton() == MouseEvent.BUTTON1) {

            if (map.createNode) {
                if (map.getGrafo().getWarehouses().length > map.pos) {

                    map.getVertice().add(new Vertice(me.getX(), me.getY(), map.getCodigoW()));
                    map.pos += 1;
                    
                    map.repaint(map.getX(), map.getY() - 23, map.getWidth(), map.getHeight());
                }

            }
        }

    }
}
