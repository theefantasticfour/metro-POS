package Views;

import Session.Session;

import javax.swing.*;
import java.awt.*;
import Utils.Values;

public class MetroIntroPage extends JFrame {

    private final JLayeredPane layeredPane;

    public MetroIntroPage(Session instance) {
        setTitle(Values.APP_NAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen mode
        setLayout(new BorderLayout());

        layeredPane = new JLayeredPane();
        add(layeredPane, BorderLayout.CENTER);

        setVisible(true);
        initializeIntroPage(instance);


    }

    private void initializeIntroPage(Session instance) {
        // Ensure dimensions are fetched after frame is rendered
        SwingUtilities.invokeLater(() -> {
            int frameWidth = getWidth();
            int frameHeight = getHeight();

            // Background Image
            try {
                JLabel backgroundLabel = new JLabel();
                ImageIcon bgIcon = new ImageIcon(Values.BACKGROUND_ICON);
                Image scaledBgImage = bgIcon.getImage().getScaledInstance(frameWidth, frameHeight, Image.SCALE_SMOOTH);
                backgroundLabel.setIcon(new ImageIcon(scaledBgImage));
                backgroundLabel.setBounds(0, 0, frameWidth, frameHeight);
                layeredPane.add(backgroundLabel, Integer.valueOf(0));
            } catch (Exception e) {
                System.err.println("Error loading background image: " + e.getMessage());
            }

            // Logo
            JLabel logoLabel = createScaledLabel(Values.LOGO_ICON, Values.LOGO_WIDTH, Values.LOGO_HEIGHT);
            if (logoLabel != null) {
                logoLabel.setBounds(Values.LOGO_X, Values.LOGO_Y, Values.LOGO_WIDTH, Values.LOGO_HEIGHT);
                layeredPane.add(logoLabel, Integer.valueOf(1));
            }

            // Welcome Image
            JLabel welcomeImage = createScaledLabel(Values.WELCOME_ICON, frameWidth / 4, frameHeight / 3);
            if (welcomeImage != null) {
                welcomeImage.setBounds((frameWidth - frameWidth / 4) / 2, (frameHeight - frameHeight / 3) / 2 - 100, frameWidth / 4, frameHeight / 3);
                layeredPane.add(welcomeImage, Integer.valueOf(1));
            }

            // App Name Label
            JLabel appName = new JLabel(Values.APP_NAME);
            appName.setFont(new Font(Values.LABEL_FONT, Font.BOLD, Values.LABEL_FONT_SIZE));
            appName.setForeground(Color.decode(Values.LABEL_COLOR));
            appName.setHorizontalAlignment(SwingConstants.CENTER);
            appName.setBounds((frameWidth - 400) / 2, (frameHeight + frameHeight / 3) / 2 - 80, 400, 50);
            layeredPane.add(appName, Integer.valueOf(1));

            // Loading Bar
            JProgressBar loadingBar = new JProgressBar();
            loadingBar.setIndeterminate(true);
            loadingBar.setBounds((frameWidth - 400) / 3, (frameHeight + frameHeight / 3) / 2, 800, 30);
            layeredPane.add(loadingBar, Integer.valueOf(1));

            // Timer to transition to General Login Page
            Timer timer = new Timer(Values.INTRO_PAGE_DELAY, e -> {
                dispose(); // Close the intro page
                instance.showGeneralLoginPage(); // Transition to login page
            });
            timer.setRepeats(false);
            timer.start();
        });
    }

    private JLabel createScaledLabel(String imagePath, int width, int height) {
        try {
            ImageIcon icon = new ImageIcon(imagePath);
            Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(scaledImage));
        } catch (Exception e) {
            System.err.println("Error loading image: " + imagePath + " - " + e.getMessage());
            return null;
        }
    }
}
