package routage.ihm.action;

import org.graphstream.graph.Graph;
import routage.ihm.AffichageReseau;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ActionReset extends AbstractAction {

    private AffichageReseau ihm;

    public ActionReset(AffichageReseau ihm) {
        this.ihm = ihm;
        putValue(Action.SHORT_DESCRIPTION, "Réinitialiser l'affichage");
        putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getClassLoader().getResource("images/reset.png")));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Graph graph = ihm.getGraph();
        graph.nodes().forEach(n -> n.setAttribute("ui.class", "not_selected"));
        graph.edges().forEach(e -> e.removeAttribute("ui.class"));
        ihm.setPanelSaisie(new JPanel());
    }
}
