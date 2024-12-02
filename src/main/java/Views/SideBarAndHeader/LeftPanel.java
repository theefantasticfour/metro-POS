package Views.SideBarAndHeader;

import Utils.Values;
import Session.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

public class LeftPanel extends JPanel {
    private JButton logoutButton; // Declare logoutButton at the class level for access during resizing

    public LeftPanel(List<MenuItem> menuItems, ActionListener actionListener) {
        setLayout(null); // Using null layout for absolute positioning
        setBackground(Color.decode(Values.LEFT_PANEL_BG_COLOR)); // Left panel background color
        setPreferredSize(new Dimension(300, 0)); // Fixed width

        // Add Metro Logo with specific bounds
        addMetroLogo();

        addButtons(menuItems, actionListener);

        // Add Logout Button at the bottom of the panel
        addLogoutButton(actionListener);

        // Listen for component resize events to position the Logout button dynamically
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                positionLogoutButton(); // Adjust the logout button's position when resized
            }
        });
    }

    // Method to add the Metro logo at a fixed position
    private void addMetroLogo() {
        ImageIcon logoIcon = new ImageIcon(Utils.Values.LOGO_ICON);
        Image scaledLogo = logoIcon.getImage().getScaledInstance(Utils.Values.LOGO_WIDTH, Utils.Values.LOGO_HEIGHT, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
        logoLabel.setBounds(Utils.Values.LOGO_X, Utils.Values.LOGO_Y, Utils.Values.LOGO_WIDTH, Utils.Values.LOGO_HEIGHT); // Specific bounds
        add(logoLabel);
    }

    // Method to add menu buttons dynamically below the logo
    private void addButtons(List<MenuItem> menuItems, ActionListener actionListener) {
        int buttonY = Utils.Values.LOGO_Y + Utils.Values.LOGO_HEIGHT + 30; // Start below the logo
        int buttonHeight = 50;
        int buttonSpacing = 10;

        for (MenuItem item : menuItems) {
            JButton button = createButton(item.getLabel(), item.getIconPath(), 40, 40);
            button.setBounds(30, buttonY, 240, buttonHeight); // Set bounds manually
            button.addActionListener(actionListener);
            add(button);
            buttonY += buttonHeight + buttonSpacing; // Adjust for spacing
        }
    }

    // Method to add the Logout button
    private void addLogoutButton(ActionListener actionListener) {
        logoutButton = createButton("Logout", Values.LOGOUT_ICON, 40, 40);

        // Add action listener to the logout button
        logoutButton.addActionListener(e -> {
            //logout to general login page
        });


        logoutButton.setBounds(30, getHeight() - 70, 240, 50);
        add(logoutButton);
    }

    // Dynamically adjust the Logout button's position
    private void positionLogoutButton() {
        if (logoutButton != null) {
            int buttonHeight = 50;
            int buttonY = this.getHeight() - buttonHeight - 20; // 20px margin from the bottom
            logoutButton.setBounds(30, buttonY, 240, buttonHeight); // Update the bounds
            logoutButton.revalidate();
            logoutButton.repaint();
        }
    }

    // Method to create a button with an icon and label
    private JButton createButton(String label, String iconPath, int iconWidth, int iconHeight) {
        JButton button = new JButton(label);
        button.setFont(new Font(Values.BUTTON_FONT, Font.PLAIN, Values.BUTTON_FONT_SIZE)); // Font

        // Resize the icon
        ImageIcon icon = new ImageIcon(iconPath);
        Image scaledImage = icon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledImage));

        button.setHorizontalAlignment(SwingConstants.LEFT); // Align icon and text
        button.setIconTextGap(10); // Space between icon and text
        button.setBackground(Color.decode(Values.LEFT_PANEL_BG_COLOR)); // Default button color
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.decode(Values.LEFT_PANEL_BG_COLOR))); // Border color
        return button;
    }
}
