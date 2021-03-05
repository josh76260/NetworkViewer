package routage.ihm.action;

import routage.ihm.AffichageReseau;
import routage.ihm.PanelAddArc;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ActionAddArc extends AbstractAction {

    private final AffichageReseau ihm;

    public ActionAddArc(AffichageReseau ihm) {
        this.ihm = ihm;
        putValue(Action.SHORT_DESCRIPTION, "Ajouter une route");
        putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getClassLoader().getResource("images/addarc.png")));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ihm.setPanelSaisie(new PanelAddArc(ihm));
    }
}