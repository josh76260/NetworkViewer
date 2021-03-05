package routage.ihm.action;

import routage.ihm.AffichageReseau;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class ActionSave extends AbstractAction {

    private final AffichageReseau ihm;

    public ActionSave(AffichageReseau ihm) {
        this.ihm = ihm;
        putValue(Action.SHORT_DESCRIPTION, "Sauvegarder le réseau");
        putValue(Action.LARGE_ICON_KEY, new ImageIcon(getClass().getClassLoader().getResource("images/save.png")));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jf = new JFileChooser("./");
        jf.showDialog(ihm, "Sauvegarder");
        File f = jf.getSelectedFile();
        if(f!=null) {
            ihm.sauvegarder(f);
        }
    }
}