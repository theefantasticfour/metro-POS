package Views.BranchManagerView;

import javax.swing.*;
import java.awt.event.ActionListener;

public class BranchManagerView extends JPanel {
    Boolean isPasswordChanged = false; // other buttons will only work once password is changed
    // you can try some disabled colours like light grey or something and change it once
    // isPasswordChanged is true
    ActionListener Listner;
    // to be used by
    // 1 : Apply  button of change password panel
    // 2 : Logout button of branch manager panel
    // 3 : Update button of view/update panel
    // 4 : Delete button of view/update panel
    // 5 : Sale report of reports panel
    // 6 : remaining stock reports panel
    // 7 : profit report of reports panel
    // 8 : Apply button of add employee panel

    public BranchManagerView() {
        System.out.println("Branch Manager View initialized");
    }
    public String getNewPassword() {
        return "newPassword"; // in actual it will return the text from the text field of change password
    }
    public String getConfirmPassword() {
        return "confirmPassword"; // in actual it will return the text from the text field of change password
    }
}
