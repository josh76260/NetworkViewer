package routage.ihm;

import routage.metier.Liable;
import routage.metier.Liaison;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PanelModif extends PanelSaisie {
    private final JTextField nom;
    private final JComboBox<Liable> lDest;
    private final JTextField poids;
    private final Liable selected;

    public PanelModif(AffichageReseau ihm, Liable selected) {
        super("Modification d'un élément", ihm);

        this.selected = selected;

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(0, 0, 25, 0);

        add(new JLabel("Nom : "), c);

        c.gridx = 1;
        c.gridy = 3;

        nom = new JTextField(15);
        c.gridx = 1;
        nom.setText(selected.getNom());

        add(nom, c);

        c.gridx = 0;
        c.gridy = 6;
        c.insets = new Insets(0, 0, 25, 0);

        add(new JLabel("Liaison avec : "), c);

        lDest = new JComboBox<>(Liaison.getVoisins(selected).toArray(new Liable[0]));
        lDest.setPreferredSize(new Dimension(50, 20));
        lDest.addActionListener(ie -> modifPoids());
        c.gridx = 1;
        add(lDest, c);

        c.gridx = 0;
        c.gridy = 9;
        c.insets = new Insets(0, 0, 25, 0);

        add(new JLabel("Poids : "), c);

        poids = new JTextField(15);
        c.gridx = 1;
        Liaison l = Liaison.getLiaisonEntre(selected, ((Liable) lDest.getSelectedItem()));
        if(l != null)
            poids.setText(l.getPoids() + "");

        add(poids, c);

        c.gridy = 12;
        c.gridx = 1;
        JButton supprimer = new JButton("Modifier");
        supprimer.addActionListener(ae -> modifierElement());
        add(supprimer, c);

        setVisible(true);
    }

    private void modifierElement() {
        if (!nom.getText().equals("")) {
            if (ihm.getReseau().getLiables().stream().filter(l -> l.getNom().equals(nom.getText())).findAny().isEmpty()) {
                selected.setNom(nom.getText());
                if (!poids.getText().equals(""))
                    Liaison.getLiaisonEntre(selected, ((Liable) lDest.getSelectedItem())).setPoids(Integer.parseInt(poids.getText()));

                ihm.majIHM();

            } else {
                JOptionPane.showMessageDialog(ihm,
                        "Le nom est déjà utilisé ! ",
                        "Nom déjà utilisé",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void modifPoids() {
        Liaison l = Liaison.getLiaisonEntre(selected, ((Liable) lDest.getSelectedItem()));
        poids.setText(l.getPoids() + "");
    }
}
