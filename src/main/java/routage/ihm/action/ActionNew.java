package routage.ihm.action;

import routage.ihm.AffichageReseau;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Action permettant la création d'un nouveau réseau
 *
 * @author Joshua Galien
 */
public class ActionNew extends AbstractAction {

    /**
     * Fenêtre parente
     */
    private final AffichageReseau ihm;

    /**
     * Constructeur de l'action
     *
     * @param ihm la fenêtre parente
     */
    public ActionNew(AffichageReseau ihm) {
        this.ihm = ihm;
        putValue(Action.SHORT_DESCRIPTION, "Nouveau réseau");
        putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getClassLoader().getResource("images/new.png")));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_N);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ihm.resetReseau();
    }
}
