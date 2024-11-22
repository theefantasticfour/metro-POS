package Views.loginview;

import Views.Mainscreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Loginview is the main panel
// It contains two panels:
// 1. GeneralLoginPanel
// 2. LoginTemplatePanel
// GeneralLoginPanel has 4 buttons for different types of users.
// LoginTemplatePanel is used for specific login forms for all user types.
public class Loginview extends JPanel {
    private GeneralLoginPanel generalLoginPanel;
    private LoginTemplatePanel loginTemplate;
    private final ActionListener loginListener; // Used by LoginTemplatePanel login button
    private String typeOfUser;

    public Loginview(ActionListener loginListener) {
        this.loginListener = loginListener;
        inIt(); // Initialize the panels
    }

    public void inIt() {
        // Set layout to CardLayout for easy switching
        this.setLayout(new CardLayout());

        // Initialize the GeneralLoginPanel
        generalLoginPanel = new GeneralLoginPanel(this);
        this.add(generalLoginPanel, "General");

        // Initialize an empty placeholder for LoginTemplatePanel
        loginTemplate = new LoginTemplatePanel("", loginListener, "");
        this.add(loginTemplate, "Template");

        // Show the GeneralLoginPanel by default
        showGeneralPanel();
    }

    // Show the GeneralLoginPanel
    public void showGeneralPanel() {
        System.out.println("Reached here 2");

        // Ensure the GeneralLoginPanel is displayed
        CardLayout cl = (CardLayout) this.getLayout();
        cl.show(this, "General");
    }

    // Show the LoginTemplatePanel
    public void ShowTemplatePanel(String userType, String iconPath) {
        this.typeOfUser = userType;

        // Remove the existing login template
        this.remove(loginTemplate);

        // Recreate the LoginTemplatePanel with the updated user type and icon
        loginTemplate = new LoginTemplatePanel(userType, loginListener, iconPath);
        this.add(loginTemplate, "Template");

        // Switch to the LoginTemplatePanel
        CardLayout cl = (CardLayout) this.getLayout();
        cl.show(this, "Template");
    }

    // ----- Getters -----
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
