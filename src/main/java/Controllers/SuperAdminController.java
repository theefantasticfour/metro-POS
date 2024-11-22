package Controllers;

import Models.SuperAdmin;
import Views.Mainscreen;

public class SuperAdminController {
    SuperAdmin superAdminModel;

    public SuperAdminController() {
        System.out.println("Super Admin Controller called.");
    }
    public void start() {
        System.out.println("Super Admin Controller started.");
        // initialed View
        Mainscreen.getInstance().showSuperAdmin();
        // Initialised model
        superAdminModel = new SuperAdmin();

    }
}
