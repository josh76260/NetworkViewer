package routage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PanelRoutage extends JPanel {
    private final ArrayList<Route> tabRoutage;
    private final Commutateur selected;

    public PanelRoutage(ArrayList<Route> tabRoutage, Commutateur selected) {
        this.tabRoutage = tabRoutage;
        this.selected = selected;
        setSize(600, 50);
        setPreferredSize(new Dimension(250, 800));
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.drawString("Table de routage du commutateur " + selected.getNom() + " : ", 20, 20);
        String previousDest = "";
        Route courant;
        String toDraw = "";
        int nbLigne = 1;
        for (Route route : tabRoutage) {
            courant = route;
            if (courant.getDest().getNom().equals(previousDest)) {
                toDraw += courant.getRouteur() + ":" + courant.getPoids() + "  ";
            } else {
                graphics2D.drawString(toDraw, 30, 20 * (nbLigne++));
                toDraw = courant.getDest() + "    " + courant.getRouteur() + ":" + courant.getPoids() + "  ";
            }
            previousDest = courant.getDest().getNom();
        }
        graphics2D.drawString(toDraw, 30, 20 * (nbLigne));
    }
}
