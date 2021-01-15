/**
 * @author FOS
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Arrays;

import static java.lang.Math.floor;

public class FoodOrderingSystem extends JFrame {

    //Calling constructors of the classes to build a panel
    private final Entree entreePanel;
    private final Side sidePanel;
    private final Drink drinkPanel;
    private final Dessert dessertPanel;

    private JFrame exitFrame;
    private JPanel buttonPanel;
    private JPanel mainPanel;
    private JPanel menuPanel;
    private JPanel lastPanel;
    private final JPanel totalPanel;
    private final JPanel paymentPanel;
    private final JPanel changePanel;
    private final JLabel banner;
    private final JLabel totalLabel;
    private final JLabel changeLabel;
    private final JLabel cashLabel;
    private JButton totalButton, orderButton, resetButton, exitButton;

    //private static double fields for actionPerformed method
    private static double entreeTotal, sideTotal, drinkTotal, dessertTotal;
    private static double totalAmount, cashPaid, cashChange;

    private static String customerName = null, customerPhone = "0";

    private final JFormattedTextField cashInput;
    DecimalFormat dollar = new DecimalFormat("###.00");

    public FoodOrderingSystem() {
        //display a title.
        setTitle("Food Ordering System");

        //configure frame size
        this.setMinimumSize(new Dimension(600, 300));
        setResizable(false);

        //specify what happens when the close button is clicked.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //create a panel objects from food category classes
        entreePanel = new Entree();
        sidePanel = new Side();
        drinkPanel = new Drink();
        dessertPanel = new Dessert();

        //create the banner.
        banner = new JLabel("Food Ordering System");
        banner.setFont(new Font("SanSerif", Font.BOLD, 24));

        buttonPanel();

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel bannerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel lastPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel cashPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        bannerPanel.add(banner);

        cashPanel.setBorder(BorderFactory.createTitledBorder("Payment"));
        cashPanel.setLayout(new BorderLayout());
        cashPanel.setPreferredSize(new Dimension(300, 150));

        totalPanel = new JPanel();
        totalLabel = new JLabel();
        totalLabel.setText("Total: $0.00");

        paymentPanel = new JPanel();
        cashLabel = new JLabel();
        cashLabel.setText("Cash Payment: ");

        cashInput = new JFormattedTextField(dollar);
        cashInput.setPreferredSize(new Dimension(200, 30)); // w,h
        cashInput.setEditable(false);

        changePanel = new JPanel();
        changeLabel = new JLabel();
        changeLabel.setText("Your change is: $0.00");

        totalPanel.add(totalLabel);
        paymentPanel.add(cashLabel);
        paymentPanel.add(cashInput);
        changePanel.add(changeLabel);

        cashPanel.add(totalPanel, BorderLayout.NORTH);
        cashPanel.add(paymentPanel, BorderLayout.CENTER);
        cashPanel.add(changePanel, BorderLayout.SOUTH);

        menuPanel.add(entreePanel);
        menuPanel.add(sidePanel);
        menuPanel.add(drinkPanel);
        menuPanel.add(dessertPanel);
        menuPanel.add(cashPanel);
        lastPanel.add(buttonPanel);

        mainPanel.add(bannerPanel, BorderLayout.NORTH);
        mainPanel.add(menuPanel, BorderLayout.CENTER);
        mainPanel.add(lastPanel, BorderLayout.SOUTH);
        add(mainPanel);

        //pack and display the window.
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cashPaid = Double.parseDouble(cashInput.getText());

                } catch (NumberFormatException e1) {
                    cashInput.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, "Please enter numbers only");
                    cashInput.setText("");
                    cashInput.setForeground(Color.black);
                }
                if (cashPaid >= totalAmount) {
                    cashChange = cashPaid - totalAmount;
                    cashChange = floor(cashChange * 100.0 + .5) / 100.0;
                    changeLabel.setText("Your change is: " + "$" + dollar.format(cashChange));
                    orderButton.setEnabled(true);
                } else {
                    cashChange = 0;
                    cashInput.setForeground(Color.red);
                    changeLabel.setText("Your change is: $0.00");
                    JOptionPane.showMessageDialog(null, "Does not meet the total amount");
                    orderButton.setEnabled(false);
                    cashInput.setText("");
                    cashInput.setForeground(Color.black);

                }
            }

        };

        cashInput.addActionListener(action);
    }

    private void buttonPanel() {
        //create a button for total.
        totalButton = new JButton("Calculate Total");
        totalButton.setPreferredSize(new Dimension(150, 60));

        //add an action listener to the total.
        totalButton.addActionListener(new TotalButtonListener());

        //create a button to Submit Order.
        orderButton = new JButton("Submit Order");
        orderButton.setPreferredSize(new Dimension(150, 60));
        orderButton.setEnabled(false);

        //add an action listener to the button.
        orderButton.addActionListener(new OrderButtonListener());

        //create a button to reset the checkboxes.
        resetButton = new JButton("Reset");
        resetButton.setPreferredSize(new Dimension(150, 60));

        //add an action listener to the button.
        resetButton.addActionListener(new ResetButtonListener());

        //create a button to exit the application.
        exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(150, 60));

        //add an action listener to the button.
        exitButton.addActionListener(new ExitButtonListener());

        //put the buttons in their own panel.
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));
        buttonPanel.add(totalButton);
        buttonPanel.add(orderButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(exitButton);
    }

    private double getTotalCharges() {
        double totalCharges;

        entreeTotal = entreePanel.getRate();
        sideTotal = sidePanel.getRate();
        drinkTotal = drinkPanel.getRate();
        dessertTotal = dessertPanel.getRate();
        totalCharges = entreeTotal + sideTotal + drinkTotal + dessertTotal;

        return totalCharges;
    }

    private String[] getTotalSelected() {
        String[] totalSelect = new String[entreePanel.getSelected().length + sidePanel.getSelected().length + drinkPanel.getSelected().length + dessertPanel.getSelected().length];

        int a = 0;

        for (int i = 0; i < entreePanel.getSelected().length; i++) {
            totalSelect[a] = entreePanel.getSelected()[i];
            a++;
        }

        for (int i = 0; i < sidePanel.getSelected().length; i++) {
            totalSelect[a] = sidePanel.getSelected()[i];
            a++;
        }

        for (int i = 0; i < drinkPanel.getSelected().length; i++) {
            totalSelect[a] = drinkPanel.getSelected()[i];
            a++;
        }

        for (int i = 0; i < dessertPanel.getSelected().length; i++) {
            totalSelect[a] = dessertPanel.getSelected()[i];
            a++;
        }

        totalSelect = Arrays.stream(totalSelect)
                .filter(s -> (s != null && s.length() > 0))
                .toArray(String[]::new);

        return totalSelect;
    }

    private class TotalButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                entreePanel.disableMenu();
                sidePanel.disableMenu();
                drinkPanel.disableMenu();
                dessertPanel.disableMenu();

                totalAmount = getTotalCharges();
                if (totalAmount == 0) {
                    JOptionPane.showMessageDialog(null, "Please select at least one item from the menu!");
                } else {
                    cashChange = 0;
                    totalLabel.setText("Total: $" + dollar.format(totalAmount));
                    cashInput.setText("");

                    changeLabel.setText("Your change is: $0.00");
                    cashInput.setEditable(true);
                }
            } catch (Exception evt) {
                JOptionPane.showMessageDialog(null, "Sorry, could not perform action!");
            }
        }
    }

    private class OrderButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                if (totalAmount == 0.0 || cashPaid < totalAmount) {
                    cashInput.setForeground(Color.red);
                    JOptionPane.showMessageDialog(null, "Please calculate the total and enter a valid cash amount!");
                    cashInput.setText("");
                    cashInput.setForeground(Color.black);
                } else {
                    while (customerName == null || customerName.length() < 1) {
                        customerName = JOptionPane.showInputDialog("Please enter your name.");
                        if (customerName.length() != 0 && customerName.length() <= 12) {
                            customerName = customerName;
                        } else {
                            JOptionPane.showMessageDialog(null, "Please enter your name again.");
                        }
                    }
                    int temp;
                    temp = Integer.parseInt(customerPhone);
                    temp = Integer.toString(temp).length();
                    while (customerPhone == null || temp < 10) {
                        try {
                            customerPhone = JOptionPane.showInputDialog("Please enter your phone number.");
                            temp = Integer.parseInt(customerPhone);
                            temp = Integer.toString(temp).length();
                            if (temp == 10) {
                                customerPhone = customerPhone;
                            } else {
                                JOptionPane.showMessageDialog(null, "Please enter your phone number again.");
                            }
                        } catch (NumberFormatException evt) {
                            JOptionPane.showMessageDialog(null, "Please enter numbers only.");
                        }
                    }
                    try {
                        MySQLUtils.newOrder(customerName, customerPhone, getTotalSelected(), getTotalCharges());
                        JOptionPane.showMessageDialog(null, "Your order has been received");
                    } catch (Exception evt) {
                        JOptionPane.showMessageDialog(null, "Sorry, your order cannot be processed!");
                    }
                }
            } catch (Exception evt) {
                JOptionPane.showMessageDialog(null, "Sorry, could not perform action!");
            }
        }
    }

    private class ResetButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                entreePanel.reset();
                sidePanel.reset();
                drinkPanel.reset();
                dessertPanel.reset();
                entreePanel.enableMenu();
                sidePanel.enableMenu();
                drinkPanel.enableMenu();
                dessertPanel.enableMenu();
                customerName = "";
                customerPhone = "";
                totalAmount = 0;
                cashChange = 0;
                totalLabel.setText("Total: $0.00");
                changeLabel.setText("Your change is: $0.00");
                cashInput.setText("");
                cashInput.setEditable(false);
                orderButton.setEnabled(false);
            } catch (Exception evt) {
                JOptionPane.showMessageDialog(null, "Sorry, could not perform action!");
            }
        }
    }

    private class ExitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (JOptionPane.showConfirmDialog(exitFrame, "Confirm to exit", "Food Ordering System",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                System.exit(0);
            }
        }

    }
}
