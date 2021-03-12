package routage.ihm;

import routage.metier.Liable;
import routage.metier.Liaison;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Panel permettant de supprimer une route
 *
 * @author Joshua Galien
 */
public class PanelDelArc extends PanelSaisie {

    /**
     * Liste des élément au départ
     */
    private final JComboBox<Liable> lDep;

    /**
     * Liste des élément à l'arrivée
     */
    private JComboBox<Liable> lArr;

    /**
     * Constructeur du panel
     *
     * @param ihm la fenêtre parente
     */
    public PanelDelArc(AffichageReseau ihm) {
        super("Suppression d'une route", ihm);

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(0, 0, 25, 0);

        add(new JLabel("Départ : "), c);

        lDep = new JComboBox<>(ihm.getReseau().getLiables().toArray(new Liable[0]));
        lDep.setPreferredSize(new Dimension(50, 20));
        lDep.addActionListener(ie -> modifArrivee());
        c.gridx = 1;
        add(lDep, c);

        ArrayList<Liable> lTemp = new ArrayList<>(Liaison.getVoisins(((Liable) lDep.getSelectedItem())));
        c = new GridBagConstraints();

        if (lArr != null) remove(lArr);

        c.gridx = 0;
        c.gridy = 6;
        c.insets = new Insets(0, 0, 25, 0);

        add(new JLabel("Arrivée : "), c);
        lArr = new JComboBox<>(lTemp.toArray(new Liable[0]));
        lArr.setPreferredSize(new Dimension(50, 20));
        c.gridx = 1;

        add(lArr, c);

        c = new GridBagConstraints();

        c.gridy = 9;
        c.gridx = 1;
        JButton supprimer = new JButton("Supprimer");
        supprimer.addActionListener(ae -> supprimerRoute());
        add(supprimer, c);

        setVisible(true);
    }

    /**
     * Méthode permettant de supprimer une route
     */
    private void supprimerRoute() {
        Liable l = ((Liable) lDep.getSelectedItem());
        Liaison.supprLiaison(l, (Liable) lArr.getSelectedItem());
        ihm.majIHM();
    }

    /**
     * Méthode permettant de mettre à jour l'arrivée en fonction du départ choisi
     */
    private void modifArrivee() {
        lArr.removeAllItems();

        for (Liable liable : Liaison.getVoisins(((Liable) lDep.getSelectedItem()))) {
            lArr.addItem(liable);
        }

        repaint();
        revalidate();
    }
}
