package routage.ihm;

import routage.metier.Liable;
import routage.metier.Liaison;
import routage.metier.Machine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelAddArc extends PanelSaisie {
    private final JComboBox<Liable> lDep;
    private final JComboBox<Liable> lArr;
    private final JTextField poids;


    public PanelAddArc(AffichageReseau ihm) {
        super("Ajout d'une route", ihm);
        GridBagConstraints c= new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(0, 0, 25, 0);

        add(new JLabel("Départ : "), c);

        ArrayList<Liable> liables = (ArrayList<Liable>) ihm.getReseau().getLiables();
        liables.removeIf(l -> l.getClass() == Machine.class && !Liaison.getVoisins(l).isEmpty());
        lDep = new JComboBox<>(liables.toArray(Liable[]::new));
        lDep.setPreferredSize(new Dimension(50, 20));
        lDep.addActionListener(ie -> modifArrivee());
        c.gridx = 1;
        add(lDep, c);

        ArrayList<Liable> lTemp = new ArrayList<>(ihm.getReseau().getLiables());
        c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 6;
        c.insets = new Insets(0, 0, 25, 0);

        lTemp.removeAll(Liaison.getVoisins((Liable) lDep.getSelectedItem()));
        lTemp.remove(lDep.getSelectedItem());
        add(new JLabel("Arrivée : "), c);
        lArr = new JComboBox<>(lTemp.toArray(new Liable[0]));
        lArr.setPreferredSize(new Dimension(50, 20));
        c.gridx = 1;

        add(lArr, c);

        c = new GridBagConstraints();

        c.insets = new Insets(0, 0, 25, 0);

        c.gridx = 0;
        c.gridy = 9;
        add(new JLabel("poids : "), c);

        poids = new JTextField(15);
        c.gridx = 1;

        add(poids, c);

        c = new GridBagConstraints();

        c.gridy = 12;
        c.gridx = 1;
        JButton supprimer = new JButton("Ajouter");
        supprimer.addActionListener(ae -> ajouterRoute());
        add(supprimer, c);

        setVisible(true);
    }

    private void modifArrivee() {
        lArr.removeAllItems();
        ArrayList<Liable> lTemp = new ArrayList<>(ihm.getReseau().getLiables());
        lTemp.removeAll(Liaison.getVoisins((Liable) lDep.getSelectedItem()));
        if (lDep.getSelectedItem().getClass() == Machine.class) {
            lTemp.removeAll(ihm.getReseau().getMachines());
        } else {
            lTemp.remove(lDep.getSelectedItem());
        }
        for (Liable l : lTemp) {
            lArr.addItem(l);
        }
        repaint();
        revalidate();
    }

    private void ajouterRoute() {
        if (!poids.getText().equals("") && poids.getText().matches("^\\p{Digit}+$")) {
            Liable l = (Liable) lDep.getSelectedItem();
            Liaison.creerLiaison(Integer.parseInt(poids.getText()), l, (Liable) lArr.getSelectedItem());
            ihm.majIHM();
        }
    }
}
