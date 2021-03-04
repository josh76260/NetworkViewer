package routage.ihm;

import routage.metier.Commutateur;

import javax.swing.*;
import java.awt.*;

public class PanelAdd extends JPanel implements PanelSaisie {
    private JTextField nom;
    private AffichageReseau ihm;

    public PanelAdd(AffichageReseau ihm) {
        super(new GridBagLayout());
        this.ihm = ihm;
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.CENTER;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 25, 0);


        add(new JLabel("Ajout d'un commutateur"), c);

        c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(0, 0, 25, 0);

        add(new JLabel("nom : "), c);

        nom = new JTextField(20);
        c.gridx = 1;
        add(nom, c);

        c = new GridBagConstraints();

        c.gridy = 6;
        c.gridx = 1;
        JButton valider = new JButton("Valider");
        valider.addActionListener(ae -> ajouterCommutateur());
        add(valider, c);

        setVisible(true);
    }

    private void ajouterCommutateur() {
        if (!nom.getText().equals("")) {
            if(ihm.getReseau().ajouterCommutateur(new Commutateur(nom.getText()))){
                ihm.majIHM();
            }
            else{
                JOptionPane.showMessageDialog(ihm,
                        "Le commutateur existe déjà ! ",
                        "Commutateur existant",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
