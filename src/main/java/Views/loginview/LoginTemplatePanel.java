package Views.loginview;

import javax.swing.*;
import java.awt.event.ActionListener;

// this is the general login panel for all the users
// distinguished by the type of user
// create the login page here using more panels
public class LoginTemplatePanel extends JPanel {
    String type;
    JLabel username;
    JLabel password;
    JButton login;
    ActionListener loginListener;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginTemplatePanel(String type, ActionListener loginListener) {
        this.loginListener = loginListener;
        this.type = type;
        inIt();
    }

    public void inIt() {
        // Create GUI here
        this.setLayout(null);
        this.setSize(300, 200);
        username = new JLabel("Username");
        password = new JLabel("Password");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        login = new JButton("Login");
        username.setBounds(20, 20, 100, 30);
        password.setBounds(20, 60, 100, 30);
        usernameField.setBounds(120, 20, 100, 30);
        passwordField.setBounds(120, 60, 100, 30);
        login.setBounds(120, 100, 100, 30);
        login.addActionListener(loginListener);
        this.add(username);
        this.add(password);
        this.add(usernameField);
        this.add(passwordField);
        this.add(login);
        this.setVisible(true);
    }

    // ----- getters -----
    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return passwordField.getText();
    }
}
