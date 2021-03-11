package routage.ihm;

import javax.swing.*;
import java.awt.*;

public class PanelSaisie extends JPanel {
    protected AffichageReseau ihm;

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
