package routage.ihm;

import routage.metier.Commutateur;
import routage.metier.Machine;

import javax.swing.*;
import java.awt.*;

/**
 * Panel permettant d'ajouter un élément
 *
 * @author Joshua Galien
 */
public class PanelAdd extends PanelSaisie {

    /**
     * Champ de saisie pour le nom
     */
    private final JTextField nom;

    /**
     * Radio bouton pour savoir le type de l'élément à ajouter
     */
    private final JRadioButton estCommutateur;

    /**
     * Constructeur
     *
     * @param ihm la fenêtre parente
     */
    public PanelAdd(AffichageReseau ihm) {
        super("Ajout d'un élément ", ihm);

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(0, 0, 25, 0);

        add(new JLabel("nom : "), c);

        nom = new JTextField(15);
        c.gridx = 1;
        add(nom, c);

        c = new GridBagConstraints();

        c.insets = new Insets(0,10,25, 0);

        c.gridy = 6;
        c.gridx = 0;
        ButtonGroup bg = new ButtonGroup();
        estCommutateur = new JRadioButton("Commutateur");
        estCommutateur.setSelected(true);
        JRadioButton estMachine = new JRadioButton("Machine");
        add(estCommutateur, c);

        c.gridx = 1;
        add(estMachine, c);
        bg.add(estCommutateur);
        bg.add(estMachine);


        c = new GridBagConstraints();

        c.gridy = 9;
        c.gridx = 1;
        JButton valider = new JButton("Valider");
        valider.addActionListener(ae -> ajouterCommutateur());
        add(valider, c);

        setVisible(true);
    }

    /**
     * Méthode premettant d'ajouter un commutateur
     */
    private void ajouterCommutateur() {
        if (!nom.getText().equals("")) {
            boolean estAdd;
            if(estCommutateur.isSelected()){
                estAdd = ihm.getReseau().ajouterCommutateur(new Commutateur(nom.getText()));
            }else{
                estAdd = ihm.getReseau().ajouterMachine(new Machine(nom.getText()));
            }

            if (estAdd) {
                ihm.majIHM();
            } else {
                JOptionPane.showMessageDialog(ihm,
                        "L'élément existe déjà ! ",
                        "Élément existant",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
