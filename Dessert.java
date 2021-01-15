/**
 * @author FOS
 */

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class Dessert extends Menu {

    //private menu items
    private final double ItemOneRate = 6.75;
    private final double ItemTwoRate = 4.50;
    private final double ItemThreeRate = 6.50;
    private final double ItemFourRate = 2.50;

    //private JCheckBox for menu items
    private final JCheckBox ItemOne;
    private final JCheckBox ItemTwo;
    private final JCheckBox ItemThree;
    private final JCheckBox ItemFour;

    //overriding buildPanel method
    public Dessert() {
        //create a DecimalFormat object
        DecimalFormat dollar = new DecimalFormat("#,##0.00");

        //create the check boxes
        ItemOne = new JCheckBox("Chocolate Cake ($"
                + dollar.format(ItemOneRate) + ")");
        ItemTwo = new JCheckBox("Apple Pie ($"
                + dollar.format(ItemTwoRate) + ")");
        ItemThree = new JCheckBox("Strawberry Cheesecake ($"
                + dollar.format(ItemThreeRate) + ")");
        ItemFour = new JCheckBox("Cookies ($"
                + dollar.format(ItemFourRate) + ")");

        //set prefered size
        this.setPreferredSize(new Dimension(245, 150));

        //create a layout manager //GridLayout
        setLayout(new GridLayout(4, 1));

        //set border.
        setBorder(BorderFactory.createTitledBorder("Desserts"));

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
        String[] dessertSelect = new String[4];

        if (ItemOne.isSelected()) {
            try {
                dessertSelect[0] = "Chocolate Cake";
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Could not get selected item!");
                ItemOne.setSelected(false);
            }
        }
        if (ItemTwo.isSelected()) {
            try {
                dessertSelect[1] = "Apple Pie";
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Could not get selected item!");
                ItemTwo.setSelected(false);
            }
        }
        if (ItemThree.isSelected()) {
            try {
                dessertSelect[2] = "Strawberry Cheesecake";
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Could not get selected item!");
                ItemThree.setSelected(false);
            }
        }
        if (ItemFour.isSelected()) {
            try {
                dessertSelect[3] = "Cookies";
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Could not get selected item!");
                ItemFour.setSelected(false);
            }

        }
        return dessertSelect;
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
