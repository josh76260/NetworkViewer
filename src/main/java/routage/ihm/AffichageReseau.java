package routage.ihm;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import routage.metier.Commutateur;
import routage.metier.Liaison;
import routage.metier.Reseau;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class AffichageReseau extends JFrame {

    private static String css;

    private Reseau reseau;
    private ViewPanel view;
    private SingleGraph graph;
    private final PanelRoutage panelRoutage;
    private final JPanel panelEst;

    public AffichageReseau() {
        css = "url(" + this.getClass().getClassLoader().getResource("css.css").toString() + ")";

        setTitle("Réseau 1");
        charger(getClass().getClassLoader().getResourceAsStream("reseau.data"));
        initReseau();
        initTabRoute();
        initView();

        add(new Menu(this), BorderLayout.NORTH);

        panelRoutage = new PanelRoutage(reseau.getCommutateur("s").getRoutes(), reseau.getCommutateur("s"));
        panelEst = new JPanel(new GridLayout(2, 1));
        panelEst.add(panelRoutage);
        panelEst.add(new JPanel());
        add(panelEst, BorderLayout.EAST);

        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initTabRoute() {
        LinkedList<Commutateur> lComm = new LinkedList<>(reseau.getCommutateurs());
        lComm.forEach(Commutateur::delRoutes);
        for (int i = 0; i < lComm.size(); i++) {
            Commutateur depart = lComm.removeFirst();
            for (Iterator<Node> it = graph.getNode(depart.getNom()).neighborNodes().iterator(); it.hasNext(); ) {
                Node nodeDepart = it.next();
                Commutateur voisin = lComm.get(lComm.indexOf(reseau.getCommutateur(nodeDepart.getId())));
                Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "weight");
                dijkstra.init(graph);
                dijkstra.setSource(nodeDepart);
                dijkstra.compute();

                for (Commutateur c : lComm) {
                    Node nodeArrivee = graph.getNode(c.getNom());
                    if (nodeArrivee != nodeDepart) {
                        depart.addRoute(c, voisin, (int) (dijkstra.getPathLength(nodeArrivee) +
                                ((int) graph.getNode(depart.getNom()).getEdgeBetween(nodeDepart).
                                        getAttribute("weight"))));
                    }
                }

                depart.addRoute(voisin, voisin, (Integer) graph.getNode(depart.getNom()).getEdgeBetween(nodeDepart).
                        getAttribute("weight"));
            }
            lComm.add(depart);
        }
    }

    public void charger(InputStream inputStream) {
        String[] tabData;
        Commutateur c1 = null, c2 = null;
        reseau = new Reseau("Réseau 1");
        try {
            Scanner sc = new Scanner(inputStream);
            while (sc.hasNext()) {
                String ligne = sc.nextLine();
                tabData = ligne.split(":");

                if (tabData[0].equals("C")) {
                    reseau.ajouterCommutateur(new Commutateur(tabData[1]));
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
                    Liaison.creerLiaison(Integer.parseInt(tabData[3]), c1, c2);
                }
            }
            sc.close();
        } catch (Exception exc) {
            System.out.println("Erreur fichier " + exc);
            exc.printStackTrace();
        }
    }

    public void sauvegarder(File selectedFile) {
        try {
            FileWriter fw = new FileWriter(selectedFile);

            for (Commutateur c : reseau.getCommutateurs()) {
                fw.write("C:" + c.getNom() + "\n");
            }

            for (Liaison l : Liaison.getLiaisons())
                fw.write("+:" + l.getCommutateurA().getNom() + ":" + l.getCommutateurB().getNom() + ":" + l.getPoids() + "\n");

            fw.close();

        } catch (
                IOException e) {
            e.printStackTrace();
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

        for (Liaison l : Liaison.getLiaisons()) {
            Commutateur a = l.getCommutateurA(), b = l.getCommutateurB();
            Node dep = graph.getNode(a.getNom()), dest = graph.getNode(b.getNom());
            graph.addEdge("l" + a.getNom() + b.getNom(), dep, dest);
            graph.getEdge("l" + a.getNom() + b.getNom()).setAttribute("weight", l.getPoids());
            graph.getEdge("l" + a.getNom() + b.getNom()).setAttribute("ui.label", l.getPoids());
        }
    }

    private void initView() {
        if (view != null) remove(view);

        Viewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
        view = (ViewPanel) viewer.addDefaultView(false);
        view.addMouseListener(new EcouteurSouris(this));
        add(view);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AffichageReseau::new);
    }

    public Reseau getReseau() {
        return reseau;
    }

    public void majIHM() {
        initReseau();
        initTabRoute();
        initView();
        setPanelSaisie(new JPanel());
        repaint();
        revalidate();
    }

    public void setPanelSaisie(JPanel panel) {
        panelEst.remove(1);
        panelEst.add(panel, 1);

        repaint();
        revalidate();
    }

    public Graph getGraph() {
        return graph;
    }

    public View getView() {
        return view;
    }

    public JPanel getPanelEst() {
        return panelEst;
    }

    public JPanel getPanelRoutage() {
        return panelRoutage;
    }
}


