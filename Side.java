/**
 * @author FOS
 */

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class Side extends Menu {

    //private menu items
    private final double ItemOneRate = 3.50;
    private final double ItemTwoRate = 4.75;
    private final double ItemThreeRate = 5.50;
    private final double ItemFourRate = 6.50;

    //private JCheckBox for menu items
    private final JCheckBox ItemOne;
    private final JCheckBox ItemTwo;
    private final JCheckBox ItemThree;
    private final JCheckBox ItemFour;

    //constructor
    public Side() {
        //create a DecimalFormat object
        DecimalFormat dollar = new DecimalFormat("#,##0.00");

        //create the check boxes
        ItemOne = new JCheckBox("Fries ($"
                + dollar.format(ItemOneRate) + ")");
        ItemTwo = new JCheckBox("Rice ($"
                + dollar.format(ItemTwoRate) + ")");
        ItemThree = new JCheckBox("Salad ($"
                + dollar.format(ItemThreeRate) + ")");
        ItemFour = new JCheckBox("Mixed Vegetables ($"
                + dollar.format(ItemFourRate) + ")");

        //set prefered size
        this.setPreferredSize(new Dimension(210, 150));

        //create a layout manager //GridLayout
        setLayout(new GridLayout(4, 1));

        //set border.
        setBorder(BorderFactory.createTitledBorder("Sides"));

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
        String[] sideSelect = new String[4];

        if (ItemOne.isSelected()) {
            try {
                sideSelect[0] = "Fries";
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Could not get selected item!");
                ItemOne.setSelected(false);
            }
        }
        if (ItemTwo.isSelected()) {
            try {
                sideSelect[1] = "Rice";
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Could not get selected item!");
                ItemTwo.setSelected(false);
            }
        }
        if (ItemThree.isSelected()) {
            try {
                sideSelect[2] = "Salad";
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Could not get selected item!");
                ItemThree.setSelected(false);
            }
        }
        if (ItemFour.isSelected()) {
            try {
                sideSelect[3] = "Mixed Vegetables";
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Could not get selected item!");
                ItemFour.setSelected(false);
            }

        }
        return sideSelect;
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
