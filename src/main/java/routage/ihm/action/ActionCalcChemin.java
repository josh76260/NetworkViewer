package routage.ihm.action;

import routage.ihm.AffichageReseau;
import routage.ihm.PanelChemin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ActionCalcChemin extends AbstractAction {

    private final AffichageReseau ihm;

    public ActionCalcChemin(AffichageReseau ihm) {
        this.ihm = ihm;
        putValue(Action.SHORT_DESCRIPTION, "Calculer un chemin");
        putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getClassLoader().getResource("images/path.png")));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_C);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ihm.setPanelSaisie(new PanelChemin(ihm) );
    }
}
