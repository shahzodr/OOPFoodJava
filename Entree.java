/**
 * @author FOS
 */

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class Entree extends Menu {

    //private menu items
    private final double ItemOneRate = 12.75;
    private final double ItemTwoRate = 8.75;
    private final double ItemThreeRate = 9.75;
    private final double ItemFourRate = 8.50;

    //private JCheckBox for menu items
    private final JCheckBox ItemOne;
    private final JCheckBox ItemTwo;
    private final JCheckBox ItemThree;
    private final JCheckBox ItemFour;

    //constructor
    public Entree() {

        //create a DecimalFormat object
        DecimalFormat dollar = new DecimalFormat("#,##0.00");

        //create the check boxes
        ItemOne = new JCheckBox("Chicken Alfredo ($"
                + dollar.format(ItemOneRate) + ")");
        ItemTwo = new JCheckBox("Cheeseburger ($"
                + dollar.format(ItemTwoRate) + ")");
        ItemThree = new JCheckBox("Grilled Salmon ($"
                + dollar.format(ItemThreeRate) + ")");
        ItemFour = new JCheckBox("Lasagne ($"
                + dollar.format(ItemFourRate) + ")");

        //set prefered size
        this.setPreferredSize(new Dimension(220, 150));

        //create a layout manager //GridLayout
        setLayout(new GridLayout(4, 1));

        //set border.
        setBorder(BorderFactory.createTitledBorder("Entree"));

        //add the check boxes to panel
        add(ItemOne);
        add(ItemTwo);
        add(ItemThree);
        add(ItemFour);
    }

    public double getRate() {
        double rate = 0.0;

        if (ItemOne.isSelected())
            rate += ItemOneRate;
        if (ItemTwo.isSelected())
            rate += ItemTwoRate;
        if (ItemThree.isSelected())
            rate += ItemThreeRate;
        if (ItemFour.isSelected())
            rate += ItemFourRate;

        return rate;
    }

    public String[] getSelected() {
        String[] entreeSelect = new String[4];

        if (ItemOne.isSelected()) {
            try {
                entreeSelect[0] = "Chicken Alfredo";
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Could not get selected item!");
                ItemOne.setSelected(false);
            }
        }
        if (ItemTwo.isSelected()) {
            try {
                entreeSelect[1] = "Cheeseburger";
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Could not get selected item!");
                ItemTwo.setSelected(false);
            }
        }
        if (ItemThree.isSelected()) {
            try {
                entreeSelect[2] = "Grilled Salmon";
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Could not get selected item!");
                ItemThree.setSelected(false);
            }
        }
        if (ItemFour.isSelected()) {
            try {
                entreeSelect[3] = "Lasagne";
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Could not get selected item!");
                ItemFour.setSelected(false);
            }

        }
        return entreeSelect;
    }

    public void reset() {
        ItemOne.setSelected(false);
        ItemTwo.setSelected(false);
        ItemThree.setSelected(false);
        ItemFour.setSelected(false);
    }

    public void disableMenu() {
        if (ItemOne.isSelected())
            ItemOne.setEnabled(false);
        if (ItemTwo.isSelected())
            ItemTwo.setEnabled(false);
        if (ItemThree.isSelected())
            ItemThree.setEnabled(false);
        if (ItemFour.isSelected())
            ItemFour.setEnabled(false);
    }

    public void enableMenu() {
        ItemOne.setEnabled(true);
        ItemTwo.setEnabled(true);
        ItemThree.setEnabled(true);
        ItemFour.setEnabled(true);
    }
}
