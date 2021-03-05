package routage.ihm;

import routage.metier.Commutateur;
import routage.metier.Liaison;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelAddArc extends JPanel implements PanelSaisie {
    private final AffichageReseau ihm;
    private JComboBox<Commutateur> lDep;
    private JComboBox<Commutateur> lArr;
    private JTextField poids;


    public PanelAddArc(AffichageReseau ihm) {
        super(new GridBagLayout());
        this.ihm = ihm;
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.CENTER;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 35, 0);


        add(new JLabel("Ajout d'une route "), c);

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

        ArrayList<Commutateur> lTemp = new ArrayList<>(ihm.getReseau().getCommutateurs());
        c = new GridBagConstraints();

        if (lArr != null) remove(lArr);

        c.gridx = 0;
        c.gridy = 6;
        c.insets = new Insets(0, 0, 25, 0);

        lTemp.removeAll(Liaison.getVoisins((Commutateur) lDep.getSelectedItem()));
        lTemp.remove(lDep.getSelectedItem());
        add(new JLabel("Arrivée : "), c);
        lArr = new JComboBox<>(lTemp.toArray(new Commutateur[0]));
        lArr.setPreferredSize(new Dimension(50, 20));
        c.gridx = 1;

        add(lArr, c);

        c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 9;
        add(new JLabel("poids : "), c);

        poids = new JTextField(20);
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
        ArrayList<Commutateur> lTemp = new ArrayList<>(ihm.getReseau().getCommutateurs());
        lTemp.removeAll(Liaison.getVoisins((Commutateur) lDep.getSelectedItem()));
        lTemp.remove(lDep.getSelectedItem());
        for (Commutateur c: lTemp) {
            lArr.addItem(c);
        }
        repaint();
        revalidate();
    }

    private void ajouterRoute() {
        if(!poids.getText().equals("") && poids.getText().matches("^\\p{Digit}+$")) {
            Commutateur c = (Commutateur) lDep.getSelectedItem();
            Liaison.creerLiaison(Integer.parseInt(poids.getText()), c, (Commutateur) lArr.getSelectedItem());
            ihm.majIHM();
        }
    }
}
