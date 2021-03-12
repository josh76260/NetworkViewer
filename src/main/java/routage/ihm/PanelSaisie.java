package routage.ihm;

import javax.swing.*;
import java.awt.*;

/**
 * Classe regroupant tout les panels avec une saisie utilisateur
 *
 * @author Joshua Galien
 */
public class PanelSaisie extends JPanel {

    /**
     * Fenêtre parente
     */
    protected AffichageReseau ihm;

    /**
     * Contruction du panel de saisie
     *
     * @param titre le titre du panel
     * @param ihm la fenêtre parente
     */
    public PanelSaisie(String titre, AffichageReseau ihm) {
        super(new GridBagLayout());
        this.ihm = ihm;
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.CENTER;
        c.gridwidth = 2;
        c.insets = new Insets(0, 0, 35, 0);


        add(new JLabel(titre), c);
    }
}
