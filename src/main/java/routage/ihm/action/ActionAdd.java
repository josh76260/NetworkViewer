package routage.ihm.action;

import routage.ihm.AffichageReseau;
import routage.ihm.PanelAdd;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ActionAdd extends AbstractAction {

    private AffichageReseau ihm;

    public ActionAdd(AffichageReseau ihm) {
        this.ihm = ihm;
        putValue(Action.SHORT_DESCRIPTION, "Ajouter un commutateur");
        putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getClassLoader().getResource("images/plus.png")));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ihm.setPanelSaisie(new PanelAdd(ihm));
    }
}