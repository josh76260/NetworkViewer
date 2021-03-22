package routage.ihm.action;

import routage.ihm.AffichageReseau;
import routage.ihm.panel.PanelAdd;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Action permettant l'ajout d'un élément.
 *
 * @author Joshua Galien
 */
public class ActionAdd extends AbstractAction {

    /**
     * Fenêtre parente
     */
    private final AffichageReseau ihm;

    /**
     * Constructeur de l'action
     *
     * @param ihm la fenêtre parente
     */
    public ActionAdd(AffichageReseau ihm) {
        this.ihm = ihm;
        putValue(Action.SHORT_DESCRIPTION, "Ajouter un élément");
        putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getClassLoader().getResource("images/plus.png")));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_A);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ihm.setPanelSaisie(new PanelAdd(ihm));
    }
}