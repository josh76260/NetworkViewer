package routage.ihm;

import routage.ihm.action.*;

import javax.swing.*;
import java.awt.*;

public class Menu extends JToolBar {

    public Menu(AffichageReseau parent) {
        super();
        setPreferredSize(new Dimension(parent.getWidth(), 40));
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

        setFloatable(false);
        setVisible(true);
    }

}
