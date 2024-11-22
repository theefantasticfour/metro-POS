package Views.SideBarAndHeader;

import Utils.Values;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class LeftPanel extends JPanel {
    private JPanel buttonPanel;
    private JLabel logoLabel;
    private JButton selectedButton; // To keep track of the active button

    // Constructor to accept logo and button details dynamically
    public LeftPanel(String logoPath, List<MenuItem> menuItems, ActionListener actionListener) {
        setLayout(new BorderLayout());
        setBackground(Color.decode(Values.LEFT_PANEL_BG_COLOR)); // Left panel background color
        setPreferredSize(new Dimension(300, 0)); // Fixed width, height adjusts dynamically

        // Add Logo
        addLogo(logoPath);

        // Add Menu Buttons
        addButtons(menuItems, actionListener);
    }

    // Method to add the logo at the top
    private void addLogo(String logoPath) {
        logoLabel = new JLabel(new ImageIcon(new ImageIcon(logoPath)
                .getImage()
                .getScaledInstance(Values.LOGO_WIDTH, Values.LOGO_HEIGHT, Image.SCALE_SMOOTH)), SwingConstants.CENTER);
        logoLabel.setPreferredSize(new Dimension(300, 100)); // Space for the logo
        add(logoLabel, BorderLayout.NORTH);
    }

    private void addButtons(List<MenuItem> menuItems, ActionListener actionListener) {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Vertical arrangement
        buttonPanel.setBackground(Color.decode(Values.LEFT_PANEL_BG_COLOR)); // Background color

        // Add each menu item as a button
        for (MenuItem item : menuItems) {
            JButton button = createButton(item.getLabel(), item.getIconPath(), 40, 40);
            button.addActionListener(e -> {
                // Reset previous button color
                if (selectedButton != null) {
                    selectedButton.setBackground(Color.decode(Values.BUTTON_COLOR)); // Default button color
                }

                // Set the newly selected button color
                selectedButton = (JButton) e.getSource();
                selectedButton.setBackground(Color.decode(Values.BUTTON_SELECTED_COLOR)); // Highlight color

                // Trigger the provided action
                actionListener.actionPerformed(e);
            });
            button.setAlignmentX(Component.CENTER_ALIGNMENT); // Ensure alignment in a straight vertical line
            button.setMaximumSize(new Dimension(240, 50)); // Consistent button size
            buttonPanel.add(button);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add consistent spacing between buttons
        }

        // Add a vertical glue to ensure the logout button is pushed to the bottom
        buttonPanel.add(Box.createVerticalGlue());

        // Add the logout button
        MenuItem logoutItem = new MenuItem("Logout", Values.LOGOUT_ICON);
        JButton logoutButton = createButton(logoutItem.getLabel(), logoutItem.getIconPath(), 40, 40);
        logoutButton.addActionListener(actionListener);
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.setMaximumSize(new Dimension(200, 50)); // Same size as other buttons
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.CENTER); // Add button panel to the left panel
    }

    // Method to create a button with icon and label
    private JButton createButton(String label, String iconPath, int iconWidth, int iconHeight) {
        JButton button = new JButton(label);
        button.setFont(new Font(Values.BUTTON_FONT, Font.PLAIN, Values.BUTTON_FONT_SIZE)); // Font

        // Resize the icon
        ImageIcon icon = new ImageIcon(iconPath);
        Image scaledImage = icon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledImage));

        button.setHorizontalAlignment(SwingConstants.LEFT); // Align icon and text
        button.setIconTextGap(10); // Space between icon and text
        button.setBackground(Color.decode(Values.BUTTON_COLOR)); // Default button color
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.decode(Values.BG_COLOR))); // Border color
        button.setPreferredSize(new Dimension(getPreferredSize().width, 50)); // Set button width to match left panel
        return button;
    }
}
