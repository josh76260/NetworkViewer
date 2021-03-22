package routage.ihm;

import routage.ihm.action.*;

import javax.swing.*;
import java.awt.*;

/**
 * Menu du logiciel
 *
 * @author Joshua Galien
 */
public class Menu extends JToolBar {

    /**
     * Contructeur du menu
     *
     * @param parent la fenêtre parente
     */
    public Menu(AffichageReseau parent) {
        super();
        setPreferredSize(new Dimension(parent.getWidth(), 40));
        addSeparator();
        add(new JButton(new ActionNew(parent)));
        addSeparator();
        add(new JButton(new ActionLoad(parent)));
        addSeparator();
        add(new JButton(new ActionSave(parent)));
        addSeparator();
        add(new JButton(new ActionAdd(parent)));
        addSeparator();
        add(new JButton(new ActionDel(parent)));
        addSeparator();
        add(new JButton(new ActionAddArc(parent)));
        addSeparator();
        add(new JButton(new ActionDelArc(parent)));
        addSeparator();
        add(new JButton(new ActionCalcChemin(parent)));
        addSeparator();
        add(new JButton(new ActionReset(parent)));

        setFloatable(false);
        setVisible(true);
    }
}
