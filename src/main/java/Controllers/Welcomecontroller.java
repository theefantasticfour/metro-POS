package Controllers;

import Models.Welcome;
import Session.Session;
import Views.Mainscreen;
import Views.Welcomeview.Welcomeview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Welcomecontroller {
    public Session instance ;
    public Welcomeview welcomeView;
    public Welcome welcomeModel;

    public Welcomecontroller(Session instance) {
        this.instance = instance;
        System.out.println("Welcome controller initialized");
    }
    public void start()
    {
        Mainscreen.getInstance().showWelcomeScreen(instance);
        welcomeView = new Welcomeview(instance);
        welcomeModel = new Welcome();
        welcomeView = Mainscreen.getWelcomeView();
    }

}
