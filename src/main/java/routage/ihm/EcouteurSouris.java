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

public class EcouteurSouris extends MouseAdapter {

    private final AffichageReseau parent;

    public EcouteurSouris(AffichageReseau parent) {
        this.parent = parent;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        Graph graph = parent.getGraph();
        View view = parent.getView();
        Reseau reseau = parent.getReseau();

        for (Iterator<Node> it = graph.nodes().iterator(); it.hasNext(); ) {
            Node n = it.next();

            Node node = (Node) view.findGraphicElementAt(EnumSet.of(InteractiveElement.NODE), me.getX(), me.getY());
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
                } else {
                    String att = !estCommutateur(n) ? (String) n.getAttribute("ui.class") : "not_selected";
                    n.setAttribute("ui.class", att);
                }
            }
        }
    }

    private boolean estCommutateur(Node node) {
        return !node.getAttribute("ui.class").equals("machine") &&
                !node.getAttribute("ui.class").equals("depart") &&
                !node.getAttribute("ui.class").equals("arrivee");
    }
}
