package routage.ihm;

import routage.ihm.action.ActionLoad;
import routage.ihm.action.ActionSave;

import javax.swing.*;
import java.awt.*;

public class Menu extends JToolBar {
    private JButton charger;
    private JButton sauvegarder;
    private JButton ajouterCommutateur;
    private JButton supprCommutateur;
    private JButton ajouterLiaison;
    private JButton supprLiaison;

    public Menu(AffichageReseau parent) {
        super();
        setPreferredSize(new Dimension(parent.getWidth(), 40));
        charger = new JButton(new ActionLoad());
        sauvegarder = new JButton(new ActionSave());
        sauvegarder.setToolTipText("");
        ajouterCommutateur = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/plus.png")));
        ajouterCommutateur.setToolTipText("Ajouter un commutateur");
        supprCommutateur = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/minus.png")));
        supprCommutateur.setToolTipText("Supprimer un commutateur");
        ajouterLiaison = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/addarc.png")));
        ajouterLiaison.setToolTipText("Ajouter une route");
        supprLiaison = new JButton(new ImageIcon(getClass().getClassLoader().getResource("images/delarc.png")));
        supprLiaison.setToolTipText("Supprimer une route");

        addSeparator();
        add(charger);
        addSeparator();
        add(sauvegarder);
        addSeparator();
        add(ajouterCommutateur);
        addSeparator();
        add(supprCommutateur);
        addSeparator();
        add(ajouterLiaison);
        addSeparator();
        add(supprLiaison);

        setFloatable(false);
        setVisible(true);
    }

}
