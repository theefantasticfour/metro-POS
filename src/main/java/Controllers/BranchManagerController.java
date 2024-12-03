package Controllers;

import Models.BranchManager;
import Utils.Values;
import Views.BranchManagerView.BranchManagerView;
import Views.Mainscreen;

import javax.swing.*;
import java.awt.event.ActionListener;

public class BranchManagerController {
    String username;
    String password;
    static BranchManager branchManagermodel;
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
    public static Boolean changePassword(String newPassword,String confirmPassword) {
        // see if the passwords match
        if (newPassword.isEmpty() || confirmPassword.isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Password cannot be empty");
            return false;
        }
        else if(!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match");
            return false;
        } else if (branchManagermodel.changePassword(newPassword)) {
            JOptionPane.showMessageDialog(null, "Password changed successfully");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Password change error Try Again!");
            return false;
        }
    }
    public ActionListener setActionListeners() {
        // to be used by
        // 1 : Apply  button of change password panel
        // 2 : Logout button of branch manager panel
        // 3 : Update button of view/update panel
        // 4 : Delete button of view/update panel
        // 5 : Sale report of reports panel
        // 6 : remaining stock reports panel
        // 7 : profit report of reports panel
        // 8 : Apply button of add employee panel
        return e -> {
            if (e.getActionCommand().equals(Values.CHANGE_PASSWORD)) {
                String newPassword = branchManagerView.getNewPassword();
                String confirmPassword = branchManagerView.getConfirmPassword();
                changePassword(newPassword,confirmPassword);
            } else if (e.getActionCommand().equals(Values.LOGOUT)) {
               // Mainscreen.getInstance().showLogin();
            }

        };
    }

}
