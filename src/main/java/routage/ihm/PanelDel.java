package routage.ihm;

import routage.metier.Commutateur;

import javax.swing.*;
import java.awt.*;

public class PanelDel extends JPanel implements PanelSaisie{
    private final AffichageReseau ihm;
    private JComboBox<Commutateur> lComm; 
    

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

        lComm = new JComboBox<>(ihm.getReseau().getCommutateurs().toArray(new Commutateur[0]));
        lComm.setPreferredSize(new Dimension(50, 20));
        c.gridx = 1;
        add(lComm, c);

        c = new GridBagConstraints();

        c.gridy = 6;
        c.gridx = 1;
        JButton supprimer = new JButton("Supprimer");
        supprimer.addActionListener(ae -> supprimerCommutateur());
        add(supprimer, c);

        setVisible(true);
    }

    private void supprimerCommutateur() {
        ihm.getReseau().supprimerCommutateur((Commutateur) lComm.getSelectedItem());
        ihm.majIHM();
    }
}
