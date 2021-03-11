package routage.ihm.action;

import routage.ihm.AffichageReseau;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ActionLoad extends AbstractAction {

    private final AffichageReseau ihm;

    public ActionLoad(AffichageReseau ihm) {
        this.ihm = ihm;
        putValue(Action.SHORT_DESCRIPTION, "Charger un fichier");
        putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getClassLoader().getResource("images/open.png")));
        putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK));
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_C);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jf = new JFileChooser("./");
        jf.showDialog(ihm, "Charger");
        File f = jf.getSelectedFile();
        if (f != null) {
            try {
                ihm.charger(new FileInputStream(f));
                ihm.majIHM();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }
    }
}
