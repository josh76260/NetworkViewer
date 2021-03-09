package routage.ihm.action;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.ui.graphicGraph.GraphicGraph;
import org.graphstream.ui.graphicGraph.GraphicNode;
import routage.ihm.AffichageReseau;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Iterator;

public class ActionReset extends AbstractAction {

    private AffichageReseau ihm;

    public ActionReset(AffichageReseau ihm) {
        this.ihm = ihm;
        putValue(Action.SHORT_DESCRIPTION, "Réinitialiser l'affichage");
        putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getClassLoader().getResource("images/reset.png")));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        ihm.resetUI();
        ihm.setPanelSaisie(new JPanel());
    }
}
