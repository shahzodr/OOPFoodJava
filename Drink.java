/**
 * @author FOS
 */

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class Drink extends Menu {

    //private menu items
    private final double ItemOneRate = 1.00;
    private final double ItemTwoRate = 2.00;
    private final double ItemThreeRate = 3.00;
    private final double ItemFourRate = 3.75;

    //private JCheckBox for menu items
    private final JCheckBox ItemOne;
    private final JCheckBox ItemTwo;
    private final JCheckBox ItemThree;
    private final JCheckBox ItemFour;

    //overriding buildPanel method
    public Drink() {
        //create a DecimalFormat object
        DecimalFormat dollar = new DecimalFormat("#,##0.00");

        //create the check boxes
        ItemOne = new JCheckBox("Water ($"
                + dollar.format(ItemOneRate) + ")");
        ItemTwo = new JCheckBox("Soda ($"
                + dollar.format(ItemTwoRate) + ")");
        ItemThree = new JCheckBox("Lemonade ($"
                + dollar.format(ItemThreeRate) + ")");
        ItemFour = new JCheckBox("Juice ($"
                + dollar.format(ItemFourRate) + ")");

        //set prefered size
        this.setPreferredSize(new Dimension(160, 150));

        //create a layout manager //GridLayout
        setLayout(new GridLayout(4, 1));

        //set border.
        setBorder(BorderFactory.createTitledBorder("Drinks"));

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
        String[] drinkSelect = new String[4];

        if (ItemOne.isSelected()) {
            try {
                drinkSelect[0] = "Water";
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Could not get selected item!");
                ItemOne.setSelected(false);
            }
        }
        if (ItemTwo.isSelected()) {
            try {
                drinkSelect[1] = "Soda";
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Could not get selected item!");
                ItemTwo.setSelected(false);
            }
        }
        if (ItemThree.isSelected()) {
            try {
                drinkSelect[2] = "Lemonade";
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Could not get selected item!");
                ItemThree.setSelected(false);
            }
        }
        if (ItemFour.isSelected()) {
            try {
                drinkSelect[3] = "Juice";
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Could not get selected item!");
                ItemFour.setSelected(false);
            }

        }
        return drinkSelect;
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
