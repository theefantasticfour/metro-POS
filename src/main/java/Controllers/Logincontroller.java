package Controllers;

import Models.Login;
import Session.Session;
import Utils.Values;
import Views.Mainscreen;
import Views.loginview.Loginview;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Logincontroller {
    Loginview loginview;
    Login loginmodel;
    Session session;
    String typeOfUser = "";
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
                String message = "User not validated";
                String username = Mainscreen.getLoginview().Getusername();
                String password = Mainscreen.getLoginview().Getpassword();
                loginmodel.setTypeOfUser(Mainscreen.getLoginview().getTypeOfUser());
                typeOfUser = Mainscreen.getLoginview().getTypeOfUser();
                loginmodel.setUsername(username);
                loginmodel.setPassword(password);
                if (loginmodel.validateUser())
                {
                    System.out.println("boss main yahan agya");
                    if (typeOfUser.equals(Values.SUPER_ADMIN)) {
                        session.showSuperAdmin(username, password);
                        message = "Super Admin" + username + "validated";
                    } else if (typeOfUser == Values.BRANCH_MANAGER) {
                        session.showBranchManager(username, password);
                        message = "Branch Manager" + username + "validated";
                    } else if (typeOfUser == Values.DATA_ENTRY){
                        session.showDataEntry(username, password);
                        message = "Data Entry" + username + "validated";
                    } else if (typeOfUser == Values.CASHIER) {
                        session.showCashier(username, password);
                        message = "Cashier" + username + "validated";
                    }
                    JOptionPane.showMessageDialog(null, message);

                }
                else {
                    JOptionPane.showMessageDialog(null, username + " Not validated Enter correct username and password");
                }
            }
        };
    }
}

