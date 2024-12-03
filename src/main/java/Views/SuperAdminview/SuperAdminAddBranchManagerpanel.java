package Views.SuperAdminview;

import java.awt.event.ActionListener;

public class SuperAdminAddBranchManagerpanel {
    ActionListener SuperadminListner;
    // The Apply button in this panel will have the action command as Values.REGISTER_BRANCH_MANAGER
    // Baqi sare notes wahi hain jo SuperAdminAddBranchpanel.java mein hain
    // read them once
    // to get all the branch ids use SuperAdminController.getAllBranchIds();
    public SuperAdminAddBranchManagerpanel(ActionListener SuperadminListner) {
        this.SuperadminListner = SuperadminListner;
        System.out.println("SuperAdminAddBranchManagerpanel initialized");
        inIt();
    }
    public void inIt() {
        // create the panel here
        // this is temporary code
        System.out.println("SuperAdminAddBranchManagerpanel initialized");
        // create the panel here


    }
}
