package routage.ihm;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.util.InteractiveElement;
import routage.metier.Commutateur;
import routage.metier.Reseau;

import javax.swing.*;
import java.awt.*;
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
                    if (!node.getAttribute("ui.class").equals("machine")) {
                        String att = node.getAttribute("ui.class").equals("selected") ? "not_selected" : "selected";
                        node.setAttribute("ui.class", att);
                        Commutateur com = reseau.getCommutateur(node.getId());
                        parent.setPanelRoutage(new PanelRoutage(com.getRoutes(), com));
                        parent.repaint();
                        parent.revalidate();
                    }
                } else {
                    String att = n.getAttribute("ui.class").equals("machine") ? "machine" : "not_selected";
                    n.setAttribute("ui.class", att);
                }
            }
        }
    }
}
