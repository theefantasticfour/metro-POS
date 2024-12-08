package Session;

import Controllers.*;
import Models.BranchManager;
//import Models.DataEntryOperator;
import Views.Mainscreen;
import Views.Welcomeview.Welcomeview;

public class Session {
    Mainscreen mainscreen;
    Logincontroller loginController;
    Welcomecontroller welcomecontroller;
    SuperAdminController superAdminController;
    BranchManagerController branchManagerController;
    DataEntryOperatorController dataEntryOperatorController;
    CashierController cashierController;
    public Session() {
        System.out.println("Session initialized");
        showWelcomeScreen();
        Mainscreen mainscreen = Mainscreen.getInstance();

        //showSuperAdmin("danish61", "1122");
    }
    private void showWelcomeScreen() {

        if (welcomecontroller == null) {
            welcomecontroller = new Welcomecontroller(this);
        }
        Welcomecontroller welcomecontroller = new Welcomecontroller(this);

        welcomecontroller.start();
    }

    public void showLogin() {
        // Switch to login screen
        if (loginController == null)
        {
            loginController = new Logincontroller(this);
        }
        loginController.start();
    }

    public void showSuperAdmin(String username, String password) {
       if (superAdminController == null) {
           System.out.println("Super Admin Controller called.");
           superAdminController = new SuperAdminController(this);
       }
       superAdminController.start(username, password);
    }

    public void showBranchManager(String username, String password) {
        if (branchManagerController == null) {
            System.out.println("Branch Manager Controller called.");
            branchManagerController = new BranchManagerController(username, password,this);
        }
        branchManagerController.start();

    }
    public void showDataEntry(String username, String password) {
        if (dataEntryOperatorController == null) {
            System.out.println("Data Entry Controller called.");
            dataEntryOperatorController = new DataEntryOperatorController(username, password,this);
        }
        dataEntryOperatorController.start();

    }

    public void showCashier(String username, String password)
    {
        if(cashierController == null)
        {
            System.out.println("Cashier Controller called.");
            cashierController = new CashierController(username, password, this);

        }
        cashierController.start();
    }
}
