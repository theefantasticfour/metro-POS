package Session;

import Controllers.Logincontroller;

public class Session {

    public Session() {
        System.out.println("Session initialized");
        // welcome screen to be initiated from here
        // showWelcomeScreen();
        showLogin();
    }

    public void showLogin() {
        Logincontroller logincontroller = new Logincontroller(this);
        logincontroller.start();
    }

    public void showSuperAdmin() {
        // call superadmin controller
        System.out.println("Super admin controller called");
    }
}
