package routage;

import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Node;

import javax.swing.*;

public class AffichageReseau{

    private static String css = "url(./resources/css.css";

    private Reseau reseau;

    public AffichageReseau(){
        reseau = new Reseau("Réseau 1");

        System.setProperty("org.graphstream.ui", "swing");
        SingleGraph graph = new SingleGraph("reseau");
        graph.setAttribute("ui.stylesheet", css);
        graph.display();
        for (Commutateur c:
             reseau.getCommutateurs() ) {
            graph.addNode(c.getNom());
            graph.getNode(c.getNom()).setAttribute("ui.label", c.getNom());
        }

        for (Commutateur c:
             reseau.getCommutateurs()) {
            for (Liaison l:
                 c.getLiaisons()) {
                Node dep = graph.getNode(c.getNom()), dest = graph.getNode(l.getDestination().getNom());
                graph.addEdge("l" + c.getNom() + l.getDestination().getNom(), dep, dest);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AffichageReseau::new);
    }
}
