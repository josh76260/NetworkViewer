package routage.ihm.action;

import routage.ihm.AffichageReseau;
import routage.ihm.panel.PanelSaisie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Action permettant de réinitialiser l'affichage
 *
 * @author Joshua Galien
 */
public class ActionReset extends AbstractAction {

    /**
     * Fenêtre parente
     */
    private final AffichageReseau ihm;

    /**
     * Constructeur de l'action
     *
     * @param ihm la fenêtre parente
     */
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
