package routage;

import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.util.Scanner;

public class AffichageReseau extends JFrame{

    private static String css;

    private Reseau reseau;
    private SingleGraph graph;

    public AffichageReseau() {
        css = "url(" + this.getClass().getClassLoader().getResource("css.css").toString() + ")";
        reseau = new Reseau("Réseau 1");
        setTitle("Réseau 1");
        charger(getClass().getClassLoader().getResourceAsStream("reseau.data"));
        initReseau();

        Viewer viewer = graph.display(true);
        View view = viewer.addDefaultView(false);
        add((Component) view);

        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
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
        }

        for (Commutateur c : reseau.getCommutateurs()) {
            for (Liaison l : c.getLiaisons()) {
                Node dep = graph.getNode(c.getNom()), dest = graph.getNode(l.getDestination().getNom());
                graph.addEdge("l" + c.getNom() + l.getDestination().getNom(), dep, dest);
                graph.getEdge("l" + c.getNom() + l.getDestination().getNom()).setAttribute("ui.label", l.getPoids());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AffichageReseau::new);
    }
}
