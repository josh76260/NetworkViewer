package routage;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class AffichageReseau extends JFrame implements MouseListener {

    private static String css;

    private final Reseau reseau;
    private final ViewPanel view;
    private SingleGraph graph;
    private PanelRoutage panelRoutage;

    public AffichageReseau() {
        css = "url(" + this.getClass().getClassLoader().getResource("css.css").toString() + ")";
        reseau = new Reseau("Réseau 1");

        setTitle("Réseau 1");
        charger(getClass().getClassLoader().getResourceAsStream("reseau.data"));
        initReseau();
        initTabRoute();

        Viewer viewer = graph.display(true);
        view = viewer.addDefaultView(false);
        view.addMouseListener(this);
        add(view);

        panelRoutage = new PanelRoutage(reseau.getCommutateur("s").getRoutes(), reseau.getCommutateur("s"));
        add(panelRoutage, BorderLayout.EAST);

        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initTabRoute() {
        ArrayList<Commutateur> lComm = reseau.getCommutateurs();
        for (int i = 0; i < lComm.size(); i++) { // pour tout les éléments de la liste
            Commutateur depart = lComm.remove(0);
            for (Iterator<Node> it = graph.getNode(depart.getNom()).getNeighborNodeIterator(); it.hasNext(); ) { // pour tous les voisins
                Node nodeDepart = it.next();
                System.out.println(depart.getNom() + " nodeDepart = " + nodeDepart.getId());
                Commutateur voisin = lComm.remove(lComm.indexOf(reseau.getCommutateur(nodeDepart.getId())));

                System.out.println("lComm = " + lComm);

                Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "weight");
                dijkstra.init(graph);
                dijkstra.setSource(nodeDepart);
                dijkstra.compute();

                for (Commutateur c : lComm) { // pour toutes les destinations

                    System.out.println("c = " + c);
                    System.out.println("voisin = " + voisin);

                    Node nodeArrivee = graph.getNode(c.getNom());

                    depart.addRoute(c, voisin, (int) (dijkstra.getPathLength(nodeArrivee) +
                            ((int) graph.getNode(depart.getNom()).getEdgeBetween(nodeDepart).
                                    getAttribute("weight"))));


                }

                depart.addRoute(voisin, voisin, graph.getNode(depart.getNom()).getEdgeBetween(nodeDepart).
                        getAttribute("weight"));

                System.out.println("depart.getRoutes() = " + depart.getRoutes());

                lComm.add(lComm.size(), voisin);
            }

            lComm.add(lComm.size(), depart);
        }

        for (Commutateur c : lComm) {
            System.out.println(lComm);
            System.out.println(c.getNom() + " : " + c.getRoutes());
        }

    }


    public void charger(InputStream inputStream) {
        String[] tabData;
        Commutateur c1 = null, c2 = null;
        try {
            Scanner sc = new Scanner(inputStream);
            while (sc.hasNext()) {
                String ligne = sc.nextLine();
                tabData = ligne.split(":");

                if (tabData[0].equals("C")) {
                    reseau.getCommutateurs().add(new Commutateur(tabData[1]));
                }

                if (tabData[0].equals("+")) {
                    for (Commutateur c : reseau.getCommutateurs()) {
                        if (c.getNom().equals(tabData[1])) {
                            c1 = c;
                        }
                        if (c.getNom().equals(tabData[2])) {
                            c2 = c;
                        }
                    }
                    assert c1 != null;
                    c1.addLiaison(new Liaison(Integer.parseInt(tabData[3]), c2));
                }
            }
            sc.close();
        } catch (Exception exc) {
            System.out.println("Erreur fichier " + exc);
            exc.printStackTrace();
        }
    }

    private void initReseau() {
        System.setProperty("org.graphstream.ui", "swing");
        graph = new SingleGraph("reseau");
        graph.setAttribute("ui.stylesheet", css);
        graph.setAttribute("ui.antialias");
        for (Commutateur c : reseau.getCommutateurs()) {
            graph.addNode(c.getNom());
            graph.getNode(c.getNom()).setAttribute("ui.label", c.getNom());
            graph.getNode(c.getNom()).setAttribute("ui.class", "not_selected");
        }

        for (Commutateur c : reseau.getCommutateurs()) {
            for (Liaison l : c.getLiaisons()) {
                Node dep = graph.getNode(c.getNom()), dest = graph.getNode(l.getDestination().getNom());
                graph.addEdge("l" + c.getNom() + l.getDestination().getNom(), dep, dest);
                graph.getEdge("l" + c.getNom() + l.getDestination().getNom()).setAttribute("weight", l.getPoids());
                graph.getEdge("l" + c.getNom() + l.getDestination().getNom()).setAttribute("ui.label", l.getPoids());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AffichageReseau::new);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        for (Iterator<Node> it = graph.getNodeIterator(); it.hasNext(); ) {
            Node n = it.next();

            Node node = (Node) view.findNodeOrSpriteAt(me.getX(), me.getY());
            if (node != null && node.getId().equals(n.getId())) {
                String att = node.getAttribute("ui.class").equals("selected") ? "not_selected" : "selected";
                node.setAttribute("ui.class", att);
            } else {
                n.setAttribute("ui.class", "not_selected");
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
