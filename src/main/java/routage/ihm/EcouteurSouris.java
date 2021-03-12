package routage.ihm;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.util.InteractiveElement;
import routage.metier.Commutateur;
import routage.metier.Reseau;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EnumSet;
import java.util.Iterator;

/**
 * Ecouteur sur les action de la souris sur la vue du graphe
 *
 * @author Joshua Galien
 */
public class EcouteurSouris extends MouseAdapter {

    /**
     * Fenêtre parente
     */
    private final AffichageReseau parent;

    /**
     * Constructeur
     *
     * @param parent la fenêtre parente
     */
    public EcouteurSouris(AffichageReseau parent) {
        this.parent = parent;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        Graph graph = parent.getGraph();
        View view = parent.getView();
        Reseau reseau = parent.getReseau();
        Node node = (Node) view.findGraphicElementAt(EnumSet.of(InteractiveElement.NODE), me.getX(), me.getY());

        for (Iterator<Node> it = graph.nodes().iterator(); it.hasNext(); ) {
            Node n = it.next();
            if (node != null) {
                if (node.getId().equals(n.getId())) {
                    if (estCommutateur(node)) {
                        String att = node.getAttribute("ui.class").equals("selected") ? "not_selected" : "selected";
                        node.setAttribute("ui.class", att);
                        Commutateur com = reseau.getCommutateur(node.getId());
                        parent.setPanelRoutage(new PanelRoutage(com.getRoutes(), com));
                        parent.repaint();
                        parent.revalidate();
                    }
                    parent.setPanelSaisie(new PanelModif(parent, reseau.getLiable(node.getId())));
                } else {
                    String att = !estCommutateur(n) ? (String) n.getAttribute("ui.class") : "not_selected";
                    n.setAttribute("ui.class", att);
                }
            }
        }
    }

    /**
     * Retourne si le node en paramètre est un commutateur
     *
     * @param node le node selectionné
     * @return vrai si c'est un commutateur
     */
    private boolean estCommutateur(Node node) {
        return !node.getAttribute("ui.class").equals("machine") &&
                !node.getAttribute("ui.class").equals("depart") &&
                !node.getAttribute("ui.class").equals("arrivee");
    }
}
