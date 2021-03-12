package routage.ihm;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;
import routage.metier.Machine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel permettant de calculer le chemin entre deux machine
 *
 * @author Joshua Galien
 */
public class PanelChemin extends PanelSaisie {

    /**
     * Liste des machines au départ
     */
    private final JComboBox<Machine> lDep;

    /**
     * Liste de machines à l'arrivée
     */
    private final JComboBox<Machine> lArr;

    /**
     * Label permettant d'afficher le poids du chemin
     */
    private final JLabel poids;

    /**
     * Constructeur
     *
     * @param ihm la fenêtre parente
     */
    public PanelChemin(AffichageReseau ihm) {
        super("Calcul du chemin", ihm);

        GridBagConstraints c = new GridBagConstraints();

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

    /**
     * Méthode permettant de calculer le chemin le plus court entre deux machines
     */
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

    /**
     * Méthode permettant de modifier la liste des commutateur à l'arrivée en fonction du départ sélectionné
     */
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
