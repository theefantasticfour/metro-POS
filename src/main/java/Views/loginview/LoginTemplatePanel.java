package Views.loginview;

import Utils.Values;
import Views.Components.CustomTextField;
import Views.Components.CustomPasswordField;  // Import the CustomPasswordField

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class LoginTemplatePanel extends JPanel {
    String type;
    JLabel username;
    JLabel password;
    JButton login;
    ActionListener loginListener;
    private CustomTextField usernameField;  // Use CustomTextField
    private CustomPasswordField passwordField;   // Use CustomPasswordField for password
    private String iconPath;  // Store the iconPath
    private JSplitPane splitPane; // Declare the split pane for dynamic adjustment

    public LoginTemplatePanel(String type, ActionListener loginListener, String iconPath) {
        this.loginListener = loginListener;
        this.type = type;
        this.iconPath = iconPath;  // Initialize iconPath
        inIt();
    }

    public void inIt() {
        // Main Panel
        this.setLayout(new BorderLayout());
        this.setBackground(Color.decode(Values.BG_COLOR));

        // Left Panel (Custom Panel with Background Image)
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setPreferredSize(new Dimension(600, 800));

        // Create a Custom Panel with Background Image
        JPanel customPanelWithBg = new JPanel() {
            private Image bgImage;

            // Load the background image
            {
                try {
                    ImageIcon bgIcon = new ImageIcon(Values.LEFT_ULOGIN_ICON); // Use relative path
                    if (bgIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                        bgImage = bgIcon.getImage();
                    } else {
                        System.err.println("Error: Background image not loaded properly!");
                    }
                } catch (Exception e) {
                    System.err.println("Error: Image loading failed.");
                }
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bgImage != null) {
                    g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(0, 0, getWidth(), getHeight());
                    g.setColor(Color.BLACK);
                    g.drawString("Background image failed to load.", 10, 20);
                }
            }
        };
        customPanelWithBg.setLayout(null);
        customPanelWithBg.setPreferredSize(new Dimension(600, 800));

        // Add Metro Logo to Custom Panel
        ImageIcon logoIcon = new ImageIcon(Utils.Values.LOGO_ICON);
        Image scaledLogo = logoIcon.getImage().getScaledInstance(Utils.Values.LOGO_WIDTH, Utils.Values.LOGO_HEIGHT, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
        logoLabel.setBounds(Utils.Values.LOGO_X, Utils.Values.LOGO_Y, Utils.Values.LOGO_WIDTH, Utils.Values.LOGO_HEIGHT);
        leftPanel.add(logoLabel);

        // Add User Title
        JLabel userTitleLabel = new JLabel(type); // Set type (Super Admin, Cashier, etc.)
        userTitleLabel.setFont(new Font(Values.LABEL_FONT, Font.BOLD, Values.LABEL_FONT_SIZE));
        userTitleLabel.setForeground(Color.decode(Values.TEXT_COLOR));
        userTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        userTitleLabel.setBounds(70, 180, 600, 50);
        customPanelWithBg.add(userTitleLabel);

        // Add Custom Panel to Left Panel
        leftPanel.add(customPanelWithBg, BorderLayout.CENTER);

        // Right Panel (User Login Form)
        JPanel rightPanel = new JPanel(null);
        rightPanel.setBackground(Color.decode(Values.BG_COLOR));

        // Add Role Image
        ImageIcon roleImageIcon = new ImageIcon(iconPath);
        Image scaledRoleImage = roleImageIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        JLabel roleImageLabel = new JLabel(new ImageIcon(scaledRoleImage));
        roleImageLabel.setBounds(270, 50, 250, 250);
        rightPanel.add(roleImageLabel);

        // Username Label
        username = new JLabel("Username:");
        username.setFont(new Font(Values.LABEL_FONT, Font.PLAIN, Values.LABEL_FONT_SMALLSIZE));
        username.setBounds(100, 340, 100, 30);
        rightPanel.add(username);

        // Username Field (Using CustomTextField)
        usernameField = new CustomTextField(20);
        usernameField.setBounds(100, 370, 600, 30);
        rightPanel.add(usernameField);

        // Password Label
        password = new JLabel("Password:");
        password.setFont(new Font(Values.LABEL_FONT, Font.PLAIN, Values.LABEL_FONT_SMALLSIZE));
        password.setBounds(100, 420, 100, 30);
        rightPanel.add(password);

        // Password Field (Using CustomPasswordField)
        passwordField = new CustomPasswordField(20); // Use CustomPasswordField instead of default JPasswordField
        passwordField.setBounds(100, 450, 600, 30);
        rightPanel.add(passwordField);

        // Login Button
        login = new JButton("Login");
        login.setBounds(100, 530, 600, 40);
        login.setBackground(Color.decode(Values.BUTTON_COLOR));
        login.addActionListener(loginListener);
        rightPanel.add(login);

        // JSplitPane for splitting the layout
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerSize(0);
        splitPane.setResizeWeight(0.5); // This makes the division dynamic
        splitPane.setBorder(null);

        this.add(splitPane, BorderLayout.CENTER);

        // Listen for resizing events to adjust the split divider dynamically
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustSplitPaneDivider();
            }
        });

        // Initial divider adjustment
        adjustSplitPaneDivider();
    }

    private void adjustSplitPaneDivider() {
        // Get the screen width
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;

        // Set divider location to half the screen width
        int dividerLocation = screenWidth / 2;

        // Adjust divider location dynamically
        splitPane.setDividerLocation(dividerLocation);
    }

    // ----- getters -----
    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return passwordField.getPasswordString();
    }
}
