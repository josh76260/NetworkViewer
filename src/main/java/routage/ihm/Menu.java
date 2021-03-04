package routage.ihm;

import routage.ihm.action.*;

import javax.swing.*;
import java.awt.*;

public class Menu extends JToolBar {

    public Menu(AffichageReseau parent) {
        super();
        setPreferredSize(new Dimension(parent.getWidth(), 40));
        addSeparator();
        add(new JButton(new ActionLoad()));
        addSeparator();
        add(new JButton(new ActionSave()));
        addSeparator();
        add(new JButton(new ActionAdd(parent)));
        addSeparator();
        add(new JButton(new ActionDel()));
        addSeparator();
        add(new JButton(new ActionAddArc()));
        addSeparator();
        add(new JButton(new ActionDelArc()));

        setFloatable(false);
        setVisible(true);
    }

}
