package routage.ihm.action;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ActionDelArc extends AbstractAction {

    public ActionDelArc() {
        putValue(Action.SHORT_DESCRIPTION, "Supprimer une route");
        putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getClassLoader().getResource("images/delarc.png")));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}