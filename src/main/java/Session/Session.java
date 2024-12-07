package Session;

import Controllers.BranchManagerController;
import Controllers.Logincontroller;
import Controllers.SuperAdminController;
import Controllers.Welcomecontroller;
import Models.BranchManager;
import Views.Mainscreen;
import Views.Welcomeview.Welcomeview;

public class Session {
    Mainscreen mainscreen;
    Logincontroller loginController;
    Welcomecontroller welcomecontroller;
    SuperAdminController superAdminController;
    BranchManagerController branchManagerController;
    public Session() {
        System.out.println("Session initialized");
       // showWelcomeScreen();
        showBranchManager("rana123", "111");
        Mainscreen mainscreen = Mainscreen.getInstance();

    }
    private void showWelcomeScreen() {

        if (welcomecontroller == null) {
            welcomecontroller = new Welcomecontroller(this);
        }
        Welcomecontroller welcomecontroller = new Welcomecontroller(this);
        //welcomecontroller.start();
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
    }

    public void showCashier(String username, String password) {
    }
}
