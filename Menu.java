/**
 * @author FOS
 */

import javax.swing.*;

public abstract class Menu extends JPanel {
    public abstract double getRate();

    public abstract String[] getSelected();

    public abstract void reset();

    public abstract void disableMenu();

    public abstract void enableMenu();
}
