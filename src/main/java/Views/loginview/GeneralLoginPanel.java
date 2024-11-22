package Views.loginview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// this is the inital login page
// it has 4 buttons for 4 types of users
//
public class GeneralLoginPanel extends JPanel {
    JButton student;
    JButton teacher;
    JButton admin;
    JButton parent;
    Loginview loginview;

    public GeneralLoginPanel(Loginview instance) {
        this.loginview = instance; // navigation purposes
        inIt();
    }

    public void inIt() {
        // create GUI here
        GeneralLoginPage loginPage = new GeneralLoginPage(loginview);
        JPanel mainPanel = loginPage.getPanel();

        // Add the GeneralLoginPage's GUI to this panel
        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    // Action listener for the 4 buttons

}


