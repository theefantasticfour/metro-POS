package Views;

import Controllers.BranchManagerController;
import Controllers.DataEntryOperatorController;
import Controllers.SuperAdminController;
import Models.BranchManager;
import Models.SuperAdmin;
import Session.Session;
import Views.BranchManagerView.BranchManagerView;
import Views.DataEntryOperatorView.DataEntryOperatorView;
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
    DataEntryOperatorView dataEntryOperatorView;


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

    public static DataEntryOperatorView getDataEntryOperatorView() {
        return instance.dataEntryOperatorView;
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

            loginview = new Loginview(loginListener);
            this.add(loginview, "Login");

        System.out.println("Reached here 1");
        c1.show(this.getContentPane(), "Login");
    }

    // show super admin screen
    public void showSuperAdmin(ActionListener LISTNER, SuperAdminController insntance) {
        if (superAdminView == null) {
            superAdminView = new SuperAdminView(LISTNER,insntance);
            this.add(superAdminView, "SuperAdmin");
        }
        c1.show(this.getContentPane(), "SuperAdmin");
    }

    // show branch manager screen
    public void showBranchManager(ActionListener LISTNER,BranchManagerController instance) {
        // Switch to manager screen
        if (branchManagerView == null)
        {
            branchManagerView = new BranchManagerView(LISTNER,instance);
            this.add(branchManagerView, "BranchManager");

        }
        c1.show(this.getContentPane(), "BranchManager");
    }
    // show data entry operator screen
    public void showDataEntryOperator(ActionListener LISTNER,DataEntryOperatorController instance) {
        // Switch to Data Entry Operator screen
        if (dataEntryOperatorView == null)
        {
            dataEntryOperatorView = new DataEntryOperatorView(LISTNER,instance);
            this.add(dataEntryOperatorView, "DataEntryOperator");
        }
        c1.show(this.getContentPane(), "DataEntryOperator");
    }
}

