package gui02_02.menu;

import gui02_02.DigitalClock;
import gui02_02.PropertyDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class ToolMenu extends JMenu {

    // Relationship
    private DigitalClock owner;
    private PropertyDialog property;

    // Field
    private JMenuItem menuItem;
    private MenuEventHandler menuEvent = new MenuEventHandler();

    public ToolMenu(DigitalClock owner) {
        super("Tool");
        this.owner = owner;

        menuItem = new JMenuItem(MenuEnum.PROPERTY.toString());
        menuItem.addActionListener(menuEvent);
        add(menuItem);
    }

    class MenuEventHandler implements ActionListener {

        public void actionPerformed(ActionEvent ae) {

            if (ae.getActionCommand().equals(MenuEnum.PROPERTY.toString())) {
                if (property == null) {
                    property = new PropertyDialog(owner);
                }
                property.setVisible(true);
            }

        }
    }

}
