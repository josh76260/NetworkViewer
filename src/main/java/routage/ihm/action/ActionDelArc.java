package routage.ihm.action;

import routage.ihm.AffichageReseau;
import routage.ihm.PanelAdd;
import routage.ihm.PanelDelArc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ActionDelArc extends AbstractAction {

    private final AffichageReseau ihm;

    public ActionDelArc(AffichageReseau ihm) {
        this.ihm = ihm;
        putValue(Action.SHORT_DESCRIPTION, "Supprimer une route");
        putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getClassLoader().getResource("images/delarc.png")));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK));
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_I);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ihm.setPanelSaisie(new PanelDelArc(ihm));
    }
}