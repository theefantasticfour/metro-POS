package Views.SuperAdminview;

import Utils.Values;

import javax.swing.*;
import java.awt.*;

public class SuperAdminFrame extends JFrame {
    public SuperAdminFrame() {
        // Frame setup
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Full-screen window
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create the Main Panel and add it to the JFrame
        SuperAdminPanel mainPanel = new SuperAdminPanel();
        add(mainPanel);

        setVisible(true); // Ensure the frame is visible
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SuperAdminFrame::new);
    }
}
