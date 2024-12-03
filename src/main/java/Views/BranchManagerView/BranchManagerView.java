package Views.BranchManagerView;

import javax.swing.*;

public class BranchManagerView extends JPanel {
    Boolean isPasswordChanged = false; // other buttons will only work once password is changed
    // you can try some disabled colours like light grey or something and change it once
    // isPasswordChanged is true

    public BranchManagerView() {
        System.out.println("Branch Manager View initialized");
    }
}
