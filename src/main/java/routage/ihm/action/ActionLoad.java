package routage.ihm.action;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ActionLoad extends AbstractAction {

    public ActionLoad() {
        putValue(Action.SHORT_DESCRIPTION, "Charger un fichier");
        putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getClassLoader().getResource("images/open.png")));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
