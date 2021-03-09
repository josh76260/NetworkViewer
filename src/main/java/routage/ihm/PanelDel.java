package routage.ihm;

import routage.metier.Commutateur;
import routage.metier.Liable;

import javax.swing.*;
import java.awt.*;

public class PanelDel extends JPanel {
    private final AffichageReseau ihm;
    private final JComboBox<Liable> lComm;
    

    public PanelDel(AffichageReseau ihm){
        super(new GridBagLayout());
        this.ihm = ihm;
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.CENTER;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 35, 0);

        add(new JLabel("Suppression d'un commutateur "), c);

        c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(0, 0, 25, 0);

        add(new JLabel("Commutateur : "), c);

        lComm = new JComboBox<>(ihm.getReseau().getLiables().toArray(new Liable[0]));
        lComm.setPreferredSize(new Dimension(50, 20));
        c.gridx = 1;
        add(lComm, c);

        c = new GridBagConstraints();

        c.gridy = 6;
        c.gridx = 1;
        JButton supprimer = new JButton("Supprimer");
        supprimer.addActionListener(ae -> supprimerLiable());
        add(supprimer, c);

        setVisible(true);
    }

    private void supprimerLiable() {
        ihm.getReseau().supprimerLiable((Liable) lComm.getSelectedItem());
        ihm.majIHM();
    }
}
