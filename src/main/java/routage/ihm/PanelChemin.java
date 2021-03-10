package routage.ihm;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;
import routage.metier.Commutateur;
import routage.metier.Machine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelChemin extends JPanel {
    private final AffichageReseau ihm;
    private final JComboBox<Machine> lArr;
    private final JComboBox<Machine> lDep;
    private final JLabel poids;

    public PanelChemin(AffichageReseau ihm) {
        super(new GridBagLayout());
        this.ihm = ihm;
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.CENTER;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 35, 0);


        add(new JLabel("Calcul du chemin"), c);

        c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(0, 0, 25, 0);

        add(new JLabel("Départ : "), c);

        lDep = new JComboBox<>(ihm.getReseau().getMachines().toArray(new Machine[0]));
        lDep.setPreferredSize(new Dimension(50, 20));
        lDep.addActionListener(ie -> modifArrivee());
        c.gridx = 1;
        add(lDep, c);

        ArrayList<Machine> lTemp = new ArrayList<>(ihm.getReseau().getMachines());
        c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 6;
        c.insets = new Insets(0, 0, 25, 0);

        lTemp.remove(lDep.getSelectedItem());
        add(new JLabel("Arrivée : "), c);
        lArr = new JComboBox<>(lTemp.toArray(new Machine[0]));
        lArr.setPreferredSize(new Dimension(50, 20));
        c.gridx = 1;

        add(lArr, c);

        c = new GridBagConstraints();

        c.insets = new Insets(0, 0, 25, 0);

        c.gridx = 0;
        c.gridy = 9;
        add(new JLabel("poids : "), c);

        poids = new JLabel("");
        c.gridx = 1;

        add(poids, c);

        c = new GridBagConstraints();

        c.gridy = 12;
        c.gridx = 1;
        JButton supprimer = new JButton("Calculer le chemin");
        supprimer.addActionListener(ae -> calculerChemin());
        add(supprimer, c);

        setVisible(true);
    }

    private void calculerChemin() {
        Path chemin;
        Graph graph = ihm.getGraph();
        if (lDep.getSelectedItem() != null && lArr.getSelectedItem() != null) {
            Node depart = graph.getNode(((Machine) lDep.getSelectedItem()).getNom()),
                    arrivee = graph.getNode(((Machine) lArr.getSelectedItem()).getNom());
            Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "weight");
            dijkstra.init(graph);
            dijkstra.setSource(depart);
            dijkstra.compute();

            chemin = dijkstra.getPath(arrivee);

            poids.setText(dijkstra.getPathLength(arrivee) + "");

            ihm.resetUI();
            depart.setAttribute("ui.class", "depart");
            arrivee.setAttribute("ui.class", "arrivee");

            for (Edge e : chemin.getEdgePath()) {
                e.setAttribute("ui.class", "chemin");
            }
        }
    }

    private void modifArrivee() {
        lArr.removeAllItems();
        ArrayList<Machine> lTemp = new ArrayList<>(ihm.getReseau().getMachines());
        lTemp.remove(lDep.getSelectedItem());
        for (Machine m : lTemp) {
            lArr.addItem(m);
        }
        repaint();
        revalidate();
    }
}
