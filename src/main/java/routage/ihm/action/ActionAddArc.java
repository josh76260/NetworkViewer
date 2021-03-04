package routage.ihm.action;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ActionAddArc extends AbstractAction {

    public ActionAddArc() {
        putValue(Action.SHORT_DESCRIPTION, "Ajouter une route");
        putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getClassLoader().getResource("images/addarc.png")));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}