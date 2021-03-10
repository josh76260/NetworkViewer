package routage.ihm;

import routage.metier.Commutateur;
import routage.metier.Machine;

import javax.swing.*;
import java.awt.*;

public class PanelAdd extends JPanel {
    private final JTextField nom;
    private final AffichageReseau ihm;
    private JRadioButton estCommutateur;

    public PanelAdd(AffichageReseau ihm) {
        super(new GridBagLayout());
        this.ihm = ihm;
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.CENTER;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 35, 0);


        add(new JLabel("Ajout d'un élément "), c);

        c = new GridBagConstraints();

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
