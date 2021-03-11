package routage.ihm.action;

import routage.ihm.AffichageReseau;
import routage.ihm.PanelDel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ActionDel extends AbstractAction {

    private final AffichageReseau ihm;

    public ActionDel(AffichageReseau ihm ) {
        this.ihm = ihm;
        putValue(Action.SHORT_DESCRIPTION, "Supprimer un élément");
        putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getClassLoader().getResource("images/minus.png")));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_P);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ihm.setPanelSaisie(new PanelDel(ihm));
    }
}