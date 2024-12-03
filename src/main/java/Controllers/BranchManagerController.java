package Controllers;

import Models.BranchManager;
import Views.BranchManagerView.BranchManagerView;
import Views.Mainscreen;

public class BranchManagerController {
    String username;
    String password;
    BranchManager branchManagermodel;
    BranchManagerView branchManagerView;
    public BranchManagerController(String username, String password) {
        System.out.println("Branch Manager Controller initialized");
        this.username = username;
        this.password = password;

    }
    public void start() {
        System.out.println("Branch Manager Controller started");
        // setting up this panel in the mainscreen
        Mainscreen.getInstance().showBranchManager();
        // getting instance for further use
        branchManagerView = Mainscreen.getBranchManagerView();
        // setting up the model
        branchManagermodel = new BranchManager();
    }
    public Boolean changePassword(String newPassword,String confirmPassword) {
        // see if the passwords match

        return branchManagermodel.changePassword(newPassword);
    }

}
