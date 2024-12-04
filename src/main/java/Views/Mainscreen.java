package Views;

import Controllers.Logincontroller;
import Session.Session;
import Views.BranchManagerView.BranchManagerPanel;
import Views.CashierView.CashierPanel;
import Views.DataEntryOperatorView.DataEntryOperatorPanel;
import Views.SuperAdminview.SuperAdminPanel;
import Views.Welcomeview.Welcomeview;
import Views.loginview.Loginview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Mainscreen extends JFrame {
    static Mainscreen instance; // singleton class
    Session sessionInstance;
    CardLayout c1;
    Loginview loginview;
    Welcomeview welcomeview;

    private Mainscreen() {
        this.setTitle("Main Screen");
        c1 = new CardLayout();
        this.setLayout(c1);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Make the JFrame fill the screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void showWelcomeScreen(Session sessionInstance) {
        if (welcomeview == null) {
            welcomeview = new Welcomeview(sessionInstance);
            this.add(welcomeview, "Welcome");
        }
        c1.show(this.getContentPane(), "Welcome");
    }
    public void showLogin(ActionListener loginListener) {
        if (loginview == null) {
            loginview = new Loginview(loginListener);
            this.add(loginview,"Login");
        }
        System.out.println("Reached here1");
        c1.show(this.getContentPane(), "Login");
    }
   // getters
    public static Mainscreen getInstance() {
        if (instance == null) {
            instance = new Mainscreen();
        }
        return instance;
    }
    public CardLayout getLayout() {
        return c1;
    }

    public void showSuperAdmin() {
        //SuperAdminPanel mainPanel = new SuperAdminPanel();
       // DataEntryOperatorPanel mainPanel =new DataEntryOperatorPanel();
        BranchManagerPanel mainPanel = new BranchManagerPanel();
        //CashierPanel mainPanel = new CashierPanel();
        this.add(mainPanel, "SuperAdmin");
        c1.show(this.getContentPane(), "SuperAdmin");

        System.out.println("SuperAdminPanel displayed.");
    }



}

