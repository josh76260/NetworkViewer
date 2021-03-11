package routage.ihm.action;

import routage.ihm.AffichageReseau;
import routage.ihm.PanelSaisie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ActionReset extends AbstractAction {

    private AffichageReseau ihm;

    public ActionReset(AffichageReseau ihm) {
        this.ihm = ihm;
        putValue(Action.SHORT_DESCRIPTION, "Réinitialiser l'affichage");
        putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getClassLoader().getResource("images/reset.png")));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK));
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        ihm.resetUI();
        ihm.setPanelSaisie(new PanelSaisie("Cliquez sur une action ou sur un élément du graphe", ihm));
    }
}
