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
        loginview = new Loginview(loginListener); // gui
        loginmodel = new Login(); // db
    }

    public ActionListener setActionListeners() {
        // set action listner for login button for all the users

        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginmodel.setTypeOfUser(loginview.getTypeOfUser());
                loginmodel.setUsername(loginview.Getusername());
                loginmodel.setPassword(loginview.Getpassword());
                if (loginmodel.validateUser()) {
                    session.showSuperAdmin();
                    //JOptionPane.showMessageDialog(null, loginview.Getusername() + " validated");

                } else {
                    JOptionPane.showMessageDialog(null, loginview.Getusername() + " Not validated Enter correct username and password");
                }
            }
        };
    }
}

