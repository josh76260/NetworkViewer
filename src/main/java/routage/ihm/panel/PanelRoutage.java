package routage.ihm.panel;

import routage.metier.Commutateur;
import routage.metier.Route;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel permettant de visualiser les tables de routage
 *
 * @author Joshua Galien
 */
public class PanelRoutage extends JPanel {

    /**
     * Liste des routes du commutateur sélectionné
     */
    private final ArrayList<Route> tabRoutage;

    /**
     * Commutateur sélectionné
     */
    private final Commutateur selected;

    /**
     * Construction du panel
     *
     * @param tabRoutage la liste des routes
     * @param selected le commutateur sélectionné
     */
    public PanelRoutage(ArrayList<Route> tabRoutage, Commutateur selected) {
        this.tabRoutage = tabRoutage;
        this.selected = selected;
        setPreferredSize(new Dimension(300, 800));
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
