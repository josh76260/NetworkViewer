package routage.ihm;

import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swing_viewer.SwingViewer;
import org.graphstream.ui.swing_viewer.ViewPanel;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import routage.ihm.action.*;
import routage.metier.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Fenêtre principale du logiciel
 *
 * @author Joshua Galien
 */
public class AffichageReseau extends JFrame {

    /**
     * Chemin du fichier css
     */
    private static String css;

    /**
     * Réseau du logiciel
     */
    private Reseau reseau;

    /**
     * Vue du graphe
     */
    private ViewPanel view;

    /**
     * Graphe représentant le réseau
     */
    private SingleGraph graph;

    /**
     * Panel ou est affiché la table de routage d'un commutateur
     */
    private PanelRoutage panelRoutage;

    /**
     * Panel à l'Est de la fenêtre principale
     */
    private final JPanel panelEst;

    /**
     * Constructeur de la fenêtre
     */
    public AffichageReseau() {
        css = "url(" + this.getClass().getClassLoader().getResource("css.css").toString() + ")";

        setTitle("Affichage du réseau");
        charger(getClass().getClassLoader().getResourceAsStream("reseau.data"));
        initGraphe();
        initTabRoute();
        initView();

        initJMenuBar();

        add(new Menu(this), BorderLayout.NORTH);

        panelRoutage = new PanelRoutage(reseau.getCommutateur("s").getRoutes(), reseau.getCommutateur("s"));
        panelEst = new JPanel(new GridLayout(2, 1));
        panelEst.add(panelRoutage);
        panelEst.add(new PanelSaisie("Cliquez sur une action ou sur un élément du graphe", this));
        add(panelEst, BorderLayout.EAST);

        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Initialise le menu de la fenêtre principale
     */
    private void initJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu fichier = new JMenu("Fichier");
        JMenuItem jMenuItem = new JMenuItem(new ActionLoad(this));
        jMenuItem.setText("Charger");
        fichier.add(jMenuItem);

        jMenuItem = new JMenuItem(new ActionSave(this));
        jMenuItem.setText("Sauvegarder");
        fichier.add(jMenuItem);

        fichier.addSeparator();
        fichier.add(new ActionQuitter());

        JMenu action = new JMenu("Action");
        jMenuItem = new JMenuItem(new ActionAdd(this));
        jMenuItem.setText("Ajouter un élément");
        action.add(jMenuItem);

        jMenuItem = new JMenuItem(new ActionAddArc(this));
        jMenuItem.setText("Ajouter une route");
        action.add(jMenuItem);

        action.addSeparator();

        jMenuItem = new JMenuItem(new ActionDel(this));
        jMenuItem.setText("Supprimer un élément");
        action.add(jMenuItem);

        jMenuItem = new JMenuItem(new ActionDelArc(this));
        jMenuItem.setText("Supprimer une route");
        action.add(jMenuItem);

        JMenu reset = new JMenu("Affichage");
        jMenuItem = new JMenuItem(new ActionReset(this));
        jMenuItem.setText("Réinitialiser l'affichage");
        reset.add(jMenuItem);

        jMenuBar.add(fichier);
        jMenuBar.add(action);
        jMenuBar.add(reset);

        setJMenuBar(jMenuBar);
    }

    /**
     * Initialise les tables de routage de chaque commutateur du réseau
     */
    private void initTabRoute() {
        LinkedList<Commutateur> lComm = new LinkedList<>(reseau.getCommutateurs());
        lComm.forEach(Commutateur::delRoutes);
        for (int i = 0; i < lComm.size(); i++) {
            Commutateur depart = lComm.removeFirst();
            for (Iterator<Node> it = graph.getNode(depart.getNom()).neighborNodes().iterator(); it.hasNext(); ) {
                Node nodeDepart = it.next();
                if (!nodeDepart.getAttribute("ui.class").equals("machine")) {
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
            }
            lComm.add(depart);
        }
    }

    /**
     * Charge le réseau à partir d'un flux de données
     *
     * @param inputStream le flux de données
     */
    public void charger(InputStream inputStream) {
        String[] tabData;
        Liable l1 = null, l2 = null;
        reseau = new Reseau();
        try {
            Scanner sc = new Scanner(inputStream);
            while (sc.hasNext()) {
                String ligne = sc.nextLine();
                tabData = ligne.split(":");

                if (tabData[0].equals("C")) {
                    reseau.ajouterCommutateur(new Commutateur(tabData[1]));
                }

                if (tabData[0].equals("M")) {
                    reseau.ajouterMachine(new Machine(tabData[1]));
                }

                if (tabData[0].equals("+")) {
                    for (Liable l : reseau.getLiables()) {
                        if (l.getNom().equals(tabData[1])) {
                            l1 = l;
                        }
                        if (l.getNom().equals(tabData[2])) {
                            l2 = l;
                        }
                    }
                    Liaison.creerLiaison(Integer.parseInt(tabData[3]), l1, l2);
                }
            }
            sc.close();
        } catch (Exception exc) {
            System.out.println("Erreur fichier " + exc);
            exc.printStackTrace();
        }
    }

    /**
     * Sauvegarde le réseau courant dans un fichier
     *
     * @param selectedFile le fichier dans lequel on va écrire
     */
    public void sauvegarder(File selectedFile) {
        try {
            FileWriter fw = new FileWriter(selectedFile);
            String type;
            for (Liable l : reseau.getLiables()) {
                if (l.getClass() == Commutateur.class) type = "C";
                else type = "M";

                fw.write(type + ":" + l.getNom() + "\n");
            }

            for (Liaison l : Liaison.getLiaisons())
                fw.write("+:" + l.getLiableA().getNom() + ":" + l.getLiableB().getNom() + ":" + l.getPoids() + "\n");

            fw.close();

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialisation du graphe représentant le réseau
     */
    private void initGraphe() {
        System.setProperty("org.graphstream.ui", "swing");
        graph = new SingleGraph("reseau");
        graph.setAttribute("ui.stylesheet", css);
        graph.setAttribute("ui.antialias");
        for (Commutateur c : reseau.getCommutateurs()) {
            graph.addNode(c.getNom());
            graph.getNode(c.getNom()).setAttribute("ui.label", c.getNom());
            graph.getNode(c.getNom()).setAttribute("ui.class", "not_selected");
        }

        for (Machine m : reseau.getMachines()) {
            graph.addNode(m.getNom());
            graph.getNode(m.getNom()).setAttribute("ui.label", m.getNom());
            graph.getNode(m.getNom()).setAttribute("ui.class", "machine");
        }

        for (Liaison l : Liaison.getLiaisons()) {
            Liable a = l.getLiableA(), b = l.getLiableB();
            Node dep = graph.getNode(a.getNom()), dest = graph.getNode(b.getNom());
            graph.addEdge("l" + a.getNom() + b.getNom(), dep, dest);
            graph.getEdge("l" + a.getNom() + b.getNom()).setAttribute("weight", l.getPoids());
            graph.getEdge("l" + a.getNom() + b.getNom()).setAttribute("ui.label", l.getPoids());
        }
    }

    /**
     * Initialisation de la vue du graphe
     */
    private void initView() {
        if (view != null) remove(view);

        Viewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
        view = (ViewPanel) viewer.addDefaultView(false);
        view.addMouseListener(new EcouteurSouris(this));
        add(view);
    }

    /**
     * Main lancant notre logiciel
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(AffichageReseau::new);
    }

    /**
     * Accesseur sur l'attribut réseau
     *
     * @return le réseau courant
     */
    public Reseau getReseau() {
        return reseau;
    }

    /**
     * Méthode permettant de mettre à jour la fenêtre principale
     */
    public void majIHM() {
        initGraphe();
        initTabRoute();
        initView();
        setPanelSaisie(new JPanel());
        repaint();
        revalidate();
    }

    /**
     * Modifie le panel de saisie d'information
     *
     * @param panel le nouveau panel
     */
    public void setPanelSaisie(JPanel panel) {
        panelEst.remove(1);
        panelEst.add(panel, 1);

        repaint();
        revalidate();
    }

    /**
     * Retourne le graph courant
     *
     * @return le graph courant
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * Retourne la vue courante
     *
     * @return la vue courante
     */
    public View getView() {
        return view;
    }

    /**
     * Modifie le panel de visualisation des tables de routage
     *
     * @param newPanelRoutage le nouveau panel
     */
    public void setPanelRoutage(PanelRoutage newPanelRoutage) {
        panelEst.remove(panelRoutage);
        panelRoutage = newPanelRoutage;
        panelEst.add(panelRoutage, BorderLayout.EAST, 0);
    }

    /**
     * Efface les différentes modifications graphique sur le graphe
     */
    public void resetUI() {
        for (Iterator<Node> it = graph.nodes().iterator(); it.hasNext(); ) {
            Node n = it.next();
            if (n.getAttribute("ui.class").equals("depart") || n.getAttribute("ui.class").equals("arrivee")) {
                n.setAttribute("ui.class", "machine");
            }

            if (!n.getAttribute("ui.class").equals("machine")) {
                n.setAttribute("ui.class", "not_selected");
            }
        }

        graph.edges().forEach(e -> e.setAttribute("ui.class", ""));
    }
}


