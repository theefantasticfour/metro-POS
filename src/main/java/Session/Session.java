package Session;

import Controllers.Logincontroller;
import Controllers.Welcomecontroller;
import Views.Welcomeview.Welcomeview;

public class Session {

    public Session() {
        System.out.println("Session initialized");
        showWelcomeScreen();
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

    public void showSuperAdmin() {
        System.out.println("Super Admin Controller called.");
    }
//    show login has been implemented
//    public void showGeneralLoginPage() {
//        // Switch to general login page
//        System.out.println("Navigating to General Login Page.");
//    }
}
