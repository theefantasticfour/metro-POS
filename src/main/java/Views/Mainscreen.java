package Views;

import Session.Session;
import Views.Welcomeview.Welcomeview;

import javax.swing.*;
import java.awt.*;

public class Mainscreen extends JFrame {
    Mainscreen instance; // singelton
    Session sessionInstance;
    CardLayout c1;
    private Mainscreen() {
        this.setTitle("Main Screen");
        c1 = new CardLayout();
        this.setLayout(c1);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Make the JFrame fill the screen
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void showWelcomeScreen() {
        Welcomeview welcomeview = new Welcomeview(sessionInstance);
        this.add(welcomeview, "Welcome");
        c1.show(this.getContentPane(), "Welcome");


    }
    public Mainscreen getInstance() {
        if (instance == null) {
            instance = new Mainscreen();
        }
        return instance;
    }

    public void setSessionInstance(Session instance) {
        this.sessionInstance = instance;
    }
}

