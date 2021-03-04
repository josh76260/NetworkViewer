package routage.ihm.action;

import routage.ihm.AffichageReseau;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ActionAdd extends AbstractAction {

    public ActionAdd(AffichageReseau ihm) {
        putValue(Action.SHORT_DESCRIPTION, "Ajouter un commutateur");
        putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getClassLoader().getResource("images/plus.png")));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}