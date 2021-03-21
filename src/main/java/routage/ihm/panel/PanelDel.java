package routage.ihm.panel;

import routage.ihm.AffichageReseau;
import routage.metier.Liable;

import javax.swing.*;
import java.awt.*;

/**
 * Panel permettant de supprimer un élément
 *
 * @author Joshua Galien
 */
public class PanelDel extends PanelSaisie {

    /**
     * la liste des éléments dans le réseau
     */
    private final JComboBox<Liable> lComm;

    /**
     * Constructeur
     *
     * @param ihm la fenêtre parente
     */
    public PanelDel(AffichageReseau ihm) {
        super("Suppression d'un élément ", ihm);

        GridBagConstraints c = new GridBagConstraints();

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

    /**
     * Méthode permettant de supprimer un élément
     */
    private void supprimerLiable() {
        ihm.getReseau().supprimerLiable((Liable) lComm.getSelectedItem());
        ihm.majIHM();
    }
}
