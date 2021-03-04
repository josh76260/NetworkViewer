package routage.ihm.action;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ActionDel extends AbstractAction {

    public ActionDel() {
        putValue(Action.SHORT_DESCRIPTION, "Supprimer un commutateur");
        putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getClassLoader().getResource("images/minus.png")));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}