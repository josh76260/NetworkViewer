package routage.ihm.action;

import routage.ihm.AffichageReseau;
import routage.ihm.PanelAdd;
import routage.ihm.PanelDelArc;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ActionDelArc extends AbstractAction {

    private final AffichageReseau ihm;

    public ActionDelArc(AffichageReseau ihm) {
        this.ihm = ihm;
        putValue(Action.SHORT_DESCRIPTION, "Supprimer une route");
        putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getClassLoader().getResource("images/delarc.png")));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ihm.setPanelSaisie(new PanelDelArc(ihm));
    }
}