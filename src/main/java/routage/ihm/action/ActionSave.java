package routage.ihm.action;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ActionSave extends AbstractAction {

    public ActionSave() {
        putValue(Action.SHORT_DESCRIPTION, "Sauvegarder le réseau");
        putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getClassLoader().getResource("images/save.png")));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}