package routage.ihm;

import routage.metier.Commutateur;
import routage.metier.Liaison;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelDelArc extends JPanel {
    private final AffichageReseau ihm;
    private JComboBox<Commutateur> lDep;
    private JComboBox<Commutateur> lArr;

    public PanelDelArc(AffichageReseau ihm) {
        super(new GridBagLayout());
        this.ihm = ihm;
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.CENTER;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 35, 0);


        add(new JLabel("Suppression d'une route"), c);

        c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(0, 0, 25, 0);

        add(new JLabel("Départ : "), c);

        lDep = new JComboBox<>(ihm.getReseau().getCommutateurs().toArray(new Commutateur[0]));
        lDep.setPreferredSize(new Dimension(50, 20));
        lDep.addActionListener(ie -> modifArrivee());
        c.gridx = 1;
        add(lDep, c);

        ArrayList<Commutateur> lTemp = new ArrayList<>(Liaison.getVoisins(((Commutateur) lDep.getSelectedItem())));
        c = new GridBagConstraints();

        if (lArr != null) remove(lArr);

        c.gridx = 0;
        c.gridy = 6;
        c.insets = new Insets(0, 0, 25, 0);

        add(new JLabel("Arrivée : "), c);
        lArr = new JComboBox<>(lTemp.toArray(new Commutateur[0]));
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

    private void supprimerRoute() {
        Commutateur c = ((Commutateur) lDep.getSelectedItem());
        Liaison.supprLiaison(c, (Commutateur) lArr.getSelectedItem());
        ihm.majIHM();
    }

    private void modifArrivee() {
        lArr.removeAllItems();

        for (Commutateur comm : Liaison.getVoisins(((Commutateur) lDep.getSelectedItem()))) {
            lArr.addItem(comm);
        }

        repaint();
        revalidate();
    }
}
