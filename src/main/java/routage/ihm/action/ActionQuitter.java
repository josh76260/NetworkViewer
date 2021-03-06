package routage.ihm.action;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Action permettant de quitter le logiciel
 *
 * @author Joshua Galien
 */
public class ActionQuitter extends AbstractAction {

    /**
     * Constructeur de l'action
     */
    public ActionQuitter(){
        super("Quitter");
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_Q);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
