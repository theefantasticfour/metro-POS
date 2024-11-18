package Session;

import Controllers.Logincontroller;
import Views.MetroIntroPage;

public class Session {

    public Session() {
        System.out.println("Session initialized");
        showWelcomeScreen();
    }

    private void showWelcomeScreen() {
        new MetroIntroPage(this);
    }

    public void showLogin() {
        // Switch to login screen
        Logincontroller loginController = new Logincontroller(this);
        loginController.start();
    }

    public void showSuperAdmin() {
        System.out.println("Super Admin Controller called.");
    }

    public void showGeneralLoginPage() {
        // Switch to general login page
        System.out.println("Navigating to General Login Page.");
    }
}
