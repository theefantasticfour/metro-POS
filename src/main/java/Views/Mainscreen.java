package Views;

import Models.BranchManager;
import Session.Session;
import Views.BranchManagerView.BranchManagerView;
import Views.SuperAdminview.SuperAdminView;
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
    SuperAdminView superAdminView;
    BranchManagerView branchManagerView;


    private Mainscreen() {
        this.setTitle("Main Screen");
        c1 = new CardLayout();
        this.setLayout(c1);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Make the JFrame fill the screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    // getters
    public static Loginview getLoginview() {
        return instance.loginview;
    }

    public static Welcomeview getWelcomeview() {
        return instance.welcomeview;
    }

    public static SuperAdminView getSuperAdminView() {
        return instance.superAdminView;
    }
    public static BranchManagerView getBranchManagerView() {
        return instance.branchManagerView;
    }

    public static Mainscreen getInstance() {
        if (instance == null) {
            instance = new Mainscreen();
        }
        return instance;
    }

    public static Welcomeview getWelcomeView() {
        return instance.welcomeview;
    }

    public CardLayout getLayout() {
        return c1;
    }

    // gui operations
    // show welcome screen
    public void showWelcomeScreen(Session sessionInstance) {
        if (welcomeview == null) {
            welcomeview = new Welcomeview(sessionInstance);
            this.add(welcomeview, "Welcome");
        }
        c1.show(this.getContentPane(), "Welcome");
    }

    // show login screen
    public void showLogin(ActionListener loginListener) {
        if (loginview == null) {
            loginview = new Loginview(loginListener);
            this.add(loginview, "Login");
        }
        c1.show(this.getContentPane(), "Login");
    }

    // show super admin screen
    public void showSuperAdmin(ActionListener LISTNER) {
        if (superAdminView == null) {
            superAdminView = new SuperAdminView(LISTNER);
            this.add(superAdminView, "SuperAdmin");
        }
        c1.show(this.getContentPane(), "SuperAdmin");
    }

    // show branch manager screen
    public void showBranchManager() {
        // Switch to manager screen
        if (branchManagerView == null)
        {
            branchManagerView = new BranchManagerView();
            this.add(branchManagerView, "BranchManager");
        }
        c1.show(this.getContentPane(), "BranchManager");
    }
}

