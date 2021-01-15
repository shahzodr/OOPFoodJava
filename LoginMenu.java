import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginMenu extends JFrame implements ActionListener {

    private JFrame loginFrame;
    private final JButton customer;
    private final JButton admin;
    private static LoginMenu loginMenu = new LoginMenu();
    private JButton login;
    private JTextField usernameText;
    private JPasswordField passwordText;
    public static Boolean isAdmin = false;

    public LoginMenu() {
        super();
        this.setTitle("Food Ordering System - Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setPreferredSize(new Dimension(410, 300));
        this.setMinimumSize(new Dimension(410, 300));
        this.setResizable(false);

        customer = new JButton("Customer");
        customer.setPreferredSize(new Dimension(100, 50));
        customer.addActionListener(this);
        admin = new JButton("Admin");
        admin.addActionListener(this);
        admin.setPreferredSize(new Dimension(100, 50));

        JPanel loginPanel = new JPanel(new BorderLayout());
        loginPanel.setPreferredSize(new Dimension(100, 125));
        loginPanel.add(customer, BorderLayout.SOUTH);
        loginPanel.add(admin, BorderLayout.NORTH);
        loginPanel.setBorder(new EmptyBorder(140, 100, 140, 100));

        this.add(loginPanel, BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == customer) {
            loginMenu.setVisible(false);
            FoodOrderingSystem fos = new FoodOrderingSystem();
        } else if (e.getSource() == admin) {
            loginMenu.setVisible(false);
            openAdminLogin();
            //this.setVisible(false);
        } else if (e.getSource() == login) {
            checkCredentials();
        }
    }

    public static void main(String[] args) {
        loginMenu = new LoginMenu();
        loginMenu.setVisible(true);
    }

    public void openAdminLogin() {
        loginFrame = new JFrame();
        loginFrame.setTitle("Food Ordering System - Admin Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setMinimumSize(new Dimension(410, 300));
        loginFrame.setResizable(false);

        JLabel username = new JLabel("Username:");
        usernameText = new JTextField(20);
        username.setLabelFor(usernameText);

        JLabel password = new JLabel("Password:");
        passwordText = new JPasswordField(20);
        password.setLabelFor(passwordText);

        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        JPanel usernamePanel = new JPanel(new FlowLayout());
        JPanel passwordPanel = new JPanel(new FlowLayout());
        loginPanel.setPreferredSize(new Dimension(100, 200));
        usernamePanel.add(username, BorderLayout.WEST);
        usernamePanel.add(usernameText, BorderLayout.EAST);
        usernamePanel.setBorder(new EmptyBorder(50, 0, 0, 0));
        passwordPanel.add(password, BorderLayout.WEST);
        passwordPanel.add(passwordText, BorderLayout.EAST);
        loginPanel.add(usernamePanel, BorderLayout.CENTER);
        loginPanel.add(passwordPanel, BorderLayout.CENTER);

        JPanel loginButtonPanel = new JPanel(new FlowLayout());
        login = new JButton("Login");
        login.setPreferredSize(new Dimension(100, 50));
        login.addActionListener(this);
        loginButtonPanel.add(login, BorderLayout.CENTER);
        loginPanel.add(loginButtonPanel, BorderLayout.CENTER);

        loginFrame.add(loginPanel, BorderLayout.CENTER);
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }

    public void checkCredentials() {
        int hash = (usernameText.getText() + new String(passwordText.getPassword())).hashCode();
        // admin:password
        if (hash == -159711510) {
            isAdmin = true;
            //JOptionPane.showMessageDialog(null, "Login Successful!");
            //System.out.println("Login Successful");
            usernameText.setEditable(false);
            passwordText.setEditable(false);
            login.setEnabled(false);
            loginFrame.setVisible(false);
            OrderManager om = new OrderManager();
        } else {
            //loginFrame.setVisible(false);
            JOptionPane.showMessageDialog(null, "Incorrect Username or Password.", "Login Failed!", JOptionPane.ERROR_MESSAGE);
            //LoginMenu loginMenu = new LoginMenu();
            //loginMenu.setVisible(true);
        }
    }
}
