package Views.loginview;

import Views.Components.CustomButtonWithImg;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class GeneralLoginPage {
    private JPanel mainPanel;
    private JSplitPane splitPane;

    public GeneralLoginPage(Loginview parentView) {
        // Main Panel
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Left Panel (Image)
        JPanel leftPanel = new JPanel() {
            private Image bgImage;

            {
                ImageIcon bgIcon = new ImageIcon(Utils.Values.LEFT_GLOGIN_ICON);
                if (bgIcon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                    bgImage = bgIcon.getImage();
                } else {
                    System.err.println("Error: Background image not loaded properly!");
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
        leftPanel.setLayout(null);
        leftPanel.setPreferredSize(new Dimension(800, 830));

        // Metro Logo
        ImageIcon logoIcon = new ImageIcon(Utils.Values.LOGO_ICON);
        Image scaledLogo = logoIcon.getImage().getScaledInstance(Utils.Values.LOGO_WIDTH, Utils.Values.LOGO_HEIGHT, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
        logoLabel.setBounds(Utils.Values.LOGO_X, Utils.Values.LOGO_Y, Utils.Values.LOGO_WIDTH, Utils.Values.LOGO_HEIGHT);
        leftPanel.add(logoLabel);

        // Right Panel (Buttons)
        JPanel rightPanel = new JPanel(new GridLayout(4, 1, 20, 20));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(70, 120, 100, 120));

        // Adding Buttons
        addUserButton(rightPanel, "Super Admin", Utils.Values.SUPER_ADMIN_ICON, parentView);
        addUserButton(rightPanel, "Branch Manager", Utils.Values.BRANCH_MANAGER_ICON, parentView);
        addUserButton(rightPanel, "Retail Cashier", Utils.Values.CASHIER_ICON, parentView);
        addUserButton(rightPanel, "Data Entry Operator", Utils.Values.DATA_ENTRY_ICON, parentView);

        // JSplitPane to divide the panel
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerSize(0);
        splitPane.setResizeWeight(0.5); // This makes the division dynamic
        splitPane.setBorder(null);

        // Add split pane to main panel
        mainPanel.add(splitPane, BorderLayout.CENTER);

        // Listen for resizing events to adjust the split divider dynamically
        mainPanel.addComponentListener(new ComponentAdapter() {
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

    private void addUserButton(JPanel panel, String userType, String iconPath, Loginview parentView) {
        // Using CustomButtonWithImg
        CustomButtonWithImg button = new CustomButtonWithImg(userType, iconPath);
        button.addActionListener(e -> parentView.ShowTemplatePanel(userType, iconPath));
        panel.add(button);
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}
