package Session;

import Controllers.Logincontroller;
import Controllers.SuperAdminController;
import Controllers.Welcomecontroller;
import Views.Mainscreen;
import Views.Welcomeview.Welcomeview;

public class Session {
    Mainscreen mainscreen;
    public Session() {
        System.out.println("Session initialized");
        showWelcomeScreen();
        Mainscreen mainscreen = Mainscreen.getInstance();

    }

    private void showWelcomeScreen() {
        Welcomecontroller welcomecontroller = new Welcomecontroller(this);
        welcomecontroller.start();
    }

    public void showLogin() {
        // Switch to login screen
        Logincontroller loginController = new Logincontroller(this);
        loginController.start();
    }

    public void showSuperAdmin(String username, String password) {
        System.out.println("Super Admin Controller called.");
        SuperAdminController superAdminController = new SuperAdminController();
        superAdminController.start(username, password);
    }
}
