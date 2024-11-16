package Views.loginview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Login view is the J frame
// it has 2 panels
// 1. GeneralLoginPanel
// 2. LoginTemplatePanel
// GeneralLoginPanel has 4 buttons for 4 types of users
// LoginTemplatePanel is the general login panel for all the users
public class Loginview extends JFrame {
    CardLayout cl;
    GeneralLoginPanel generalLoginPanel;
    LoginTemplatePanel loginTemplate;
    ActionListener loginListener; // will be used by Login template login button
    private String typeOfUser;

    public Loginview(ActionListener loginListener) {
        this.loginListener = loginListener;
        inIt(); // set the frame in the init method
    }

    public void inIt() {
        this.setTitle("Login");
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cl = new CardLayout();
        this.setLayout(cl);
        this.showGeneralPanel();
        this.setVisible(true);
    }


    // ------- Navigation usage --------
    public void showGeneralPanel() {
        generalLoginPanel = new GeneralLoginPanel(this);
        this.add(generalLoginPanel, "General");
    }

    public void ShowTemplatePanel(String type) {
        this.typeOfUser = type;
        loginTemplate = new LoginTemplatePanel(typeOfUser, loginListener);
        this.add(loginTemplate, "Template");
        cl.show(this.getContentPane(), "Template");
    }

    //  ----- getters ------
    public String getTypeOfUser() {
        return this.typeOfUser;
    }

    public String Getusername() {
        return loginTemplate.getUsername();
    }

    public String Getpassword() {
        return loginTemplate.getPassword();
    }

}
