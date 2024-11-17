package Views;
import javax.swing.*;
import java.awt.*;

public class MetroIntroPage {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Metro POS System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen mode

        JLayeredPane layeredPane = new JLayeredPane();
        frame.add(layeredPane);

        frame.setVisible(true);

        //get the frame dimensions
        int frameWidth = frame.getWidth();
        int frameHeight = frame.getHeight();

        JLabel backgroundLabel = new JLabel();
        ImageIcon bgIcon = new ImageIcon("bg.png");
        Image scaledBgImage = bgIcon.getImage().getScaledInstance(frameWidth, frameHeight, Image.SCALE_SMOOTH);
        backgroundLabel.setIcon(new ImageIcon(scaledBgImage));
        backgroundLabel.setBounds(0, 0, frameWidth, frameHeight);
        layeredPane.add(backgroundLabel, Integer.valueOf(0)); // Add to background layer

        // Logo
        ImageIcon logoIcon = new ImageIcon("metro_logo.png");
        JLabel logoLabel = new JLabel(new ImageIcon(logoIcon.getImage().getScaledInstance(100, 70, Image.SCALE_SMOOTH)));
        logoLabel.setBounds(17, 13, 100, 70);
        layeredPane.add(logoLabel, Integer.valueOf(1)); // Add to foreground layer

        ImageIcon welcomeIcon = new ImageIcon("welcome.png");
        int welcomeImgWidth = frameWidth / 4;
        int welcomeImgHeight = frameHeight / 3;
        Image scaledWelcomeImage = welcomeIcon.getImage().getScaledInstance(welcomeImgWidth, welcomeImgHeight, Image.SCALE_SMOOTH);
        JLabel welcomeImage = new JLabel(new ImageIcon(scaledWelcomeImage));

        welcomeImage.setBounds((frameWidth - welcomeImgWidth) / 2, (frameHeight - welcomeImgHeight) / 2 - 100, welcomeImgWidth, welcomeImgHeight);
        layeredPane.add(welcomeImage, Integer.valueOf(1));

        JLabel appName = new JLabel("Metro POS System");
        appName.setFont(new Font("Arial", Font.BOLD, 36));
        appName.setForeground(Color.DARK_GRAY);
        appName.setHorizontalAlignment(SwingConstants.CENTER);
        appName.setBounds((frameWidth - 400) / 2, (frameHeight + welcomeImgHeight) / 2 - 80, 400, 50);
        layeredPane.add(appName, Integer.valueOf(1));

        // Loading Bar
        JProgressBar loadingBar = new JProgressBar();
        loadingBar.setIndeterminate(true);
        loadingBar.setBounds((frameWidth - 400) / 3, (frameHeight + welcomeImgHeight) / 2, 800, 30); // Position below website name
        layeredPane.add(loadingBar, Integer.valueOf(1));

        // Timer to remove the intro panel after 3 secs
        Timer timer = new Timer(3000, e -> {
            layeredPane.removeAll();

            // Add General Login Page opens after 3 secs
            GeneralLoginPage loginPage = new GeneralLoginPage();
            JPanel generalLoginPanel = loginPage.getPanel();
            generalLoginPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
            layeredPane.add(generalLoginPanel, Integer.valueOf(1));

            // Refresh the layered pane
            layeredPane.revalidate();
            layeredPane.repaint();

        });
        timer.setRepeats(false); // Run only once
        timer.start();
    }
}

