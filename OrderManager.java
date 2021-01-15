/**
 * @author FOS
 */

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class OrderManager extends JFrame {

    private final JFrame mainFrame;
    private JFrame exitFrame;
    private JPanel buttonPanel, tablePanel;
    private final JTextField searchInput;
    private final JLabel banner, searchLabel;
    private JButton refreshButton, statusChangeButton, deleteButton, exitButton;
    private JTable table;
    private int index, id;
    private String searchText;
    //data to be displayed in the JTable
    private static String[][] data = MySQLUtils.fetchOrders();

    //constructor
    public OrderManager() {
        mainFrame = new JFrame();

        //this will make the intended frame
        mainFrame.setTitle("Order Manager");

        //exit frame
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setMinimumSize(new Dimension(1000, 650));

        //window cannot be resized
        mainFrame.setResizable(false);

        //panel for banner
        JPanel bannerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        //create the banner.
        banner = new JLabel("Order Manager");
        banner.setFont(new Font("SanSerif", Font.BOLD, 24));
        bannerPanel.add(banner);

        //creating 3 panels for the frame
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel panel1 = new JPanel(new BorderLayout());
        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        searchLabel = new JLabel();
        searchLabel.setText("Enter customer name: ");
        searchInput = new JTextField();
        searchInput.setPreferredSize(new Dimension(200, 30));
        searchInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchText = searchInput.getText();
                data = MySQLUtils.searchOrders(searchText);
                new OrderManager();
                mainFrame.setVisible(false);
                mainFrame.dispose();
            }
        });

        searchPanel.add(searchLabel, BorderLayout.WEST);
        searchPanel.add(searchInput, BorderLayout.EAST);

        tablePanel();
        buttonPanel();

        //adding components to panels and panels to the frame
        panel1.add(bannerPanel, BorderLayout.NORTH);
        panel1.add(searchPanel, BorderLayout.CENTER);
        panel2.add(tablePanel);
        panel3.add(buttonPanel);
        mainPanel.add(panel1, BorderLayout.NORTH);
        mainPanel.add(panel2, BorderLayout.CENTER);
        mainPanel.add(panel3, BorderLayout.SOUTH);
        mainFrame.add(mainPanel);

        mainFrame.setResizable(false);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private void tablePanel() {

        //data to be displayed in the JTable
        //String[][] data = MySQLUtils.fetchOrders();

        //column names
        String[] columnNames = {"OrderID", "Name", "Phone", "Order", "Price", "Status"};

        //initializing the JTable
        table = new JTable(data, columnNames);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setDefaultEditor(Object.class, null);
        table.getColumn("OrderID").setPreferredWidth(30);
        table.getColumn("Name").setPreferredWidth(70);
        table.getColumn("Phone").setPreferredWidth(60);
        table.getColumn("Order").setPreferredWidth(400);
        table.getColumn("Price").setPreferredWidth(50);
        table.getColumn("Status").setPreferredWidth(100);

        //adding it to JScrollPane
        JScrollPane sp = new JScrollPane(table);

        //put the buttons in their own panel.
        tablePanel = new JPanel();
        tablePanel.setPreferredSize(new Dimension(900, 450));
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(sp);
    }

    private void buttonPanel() {

        //create a button to refresh data.
        refreshButton = new JButton("Refresh");
        refreshButton.setPreferredSize(new Dimension(150, 60));

        //add an action listener to the refresh button.
        refreshButton.addActionListener(new OrderManager.RefreshButtonListener());

        //create a button for complete.
        statusChangeButton = new JButton("Change Status");
        statusChangeButton.setPreferredSize(new Dimension(150, 60));

        //add an action listener to update button.
        statusChangeButton.addActionListener(new StatusChangeButtonListener());

        //create a button to Delete Order.
        deleteButton = new JButton("Delete");
        deleteButton.setPreferredSize(new Dimension(150, 60));

        //add an action listener to the button.
        deleteButton.addActionListener(new DeleteButtonListener());

        //create a button to exit the application.
        exitButton = new JButton("Exit");
        deleteButton.setPreferredSize(new Dimension(150, 60));

        //add an action listener to the button.
        exitButton.addActionListener(new ExitButtonListener());

        //place the buttons in their own panel.
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));
        buttonPanel.add(refreshButton);
        buttonPanel.add(statusChangeButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(exitButton);
    }

    private int getOrderId() {
        String rowSelect;
        index = table.getSelectedRow();
        TableModel model = table.getModel();
        rowSelect = model.getValueAt(index, 0).toString();
        id = Integer.parseInt(rowSelect);

        return id;
    }

    private String getOrderStatus() {
        String rowSelect;
        String status = "";
        index = table.getSelectedRow();
        TableModel model = table.getModel();
        rowSelect = model.getValueAt(index, 5).toString();
        if (Objects.equals(rowSelect, "Not Completed")) {
            status = "Completed";
        } else if (Objects.equals(rowSelect, "Completed")) {
            status = "Not Completed";
        }
        return status;
    }

    private class RefreshButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            data = MySQLUtils.fetchOrders();
            new OrderManager();
            mainFrame.setVisible(false);
            mainFrame.dispose();
            JOptionPane.showMessageDialog(null, "List of orders is updated!");
        }
    }

    private class StatusChangeButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            getOrderId();
            MySQLUtils.updateOrderStatus(id, getOrderStatus());
            data = MySQLUtils.fetchOrders();
            new OrderManager();
            mainFrame.setVisible(false);
            mainFrame.dispose();
            JOptionPane.showMessageDialog(null, "Status of order number " + id + " is updated!");

        }
    }

    private class DeleteButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            getOrderId();
            MySQLUtils.deleteOrder(id);
            data = MySQLUtils.fetchOrders();
            new OrderManager();
            mainFrame.setVisible(false);
            mainFrame.dispose();
            JOptionPane.showMessageDialog(null, "Order number " + id + " is deleted!");
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
