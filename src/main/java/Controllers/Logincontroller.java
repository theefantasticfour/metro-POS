package Controllers;

import Models.Login;
import Session.Session;
import Views.Mainscreen;
import Views.loginview.Loginview;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Logincontroller {
    Loginview loginview;
    Login loginmodel;
    Session session;

    public Logincontroller(Session instance) {
        System.out.println("Login controller initialized");
        this.session = instance;
    }

    public void start() {
        ActionListener loginListener = setActionListeners();
        Mainscreen.getInstance().showLogin(loginListener);
        //loginview = new Loginview(loginListener); // gui
        loginmodel = new Login(); // db
    }

    public ActionListener setActionListeners() {
        // set action listner for login button for all the users

        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = Mainscreen.getLoginview().Getusername();
                String password = Mainscreen.getLoginview().Getpassword();
                loginmodel.setTypeOfUser(Mainscreen.getLoginview().getTypeOfUser());
                loginmodel.setUsername(username);
                loginmodel.setPassword(password);
                if (loginmodel.validateUser()) {
                    session.showSuperAdmin(username, password);
                    JOptionPane.showMessageDialog(null, username + " validated");

                } else {
                    JOptionPane.showMessageDialog(null, username + " Not validated Enter correct username and password");
                }
            }
        };
    }
}

