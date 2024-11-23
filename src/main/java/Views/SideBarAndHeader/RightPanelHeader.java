package Views.SideBarAndHeader;

import Utils.Values;
import javax.swing.*;
import java.awt.*;

public class RightPanelHeader extends JPanel {

    // Constructor to accept icon and label
    public RightPanelHeader(String iconPath, String labelText) {
        setLayout(new BorderLayout()); // Use BorderLayout to manage layout
        setBackground(Color.WHITE); // Background color
        setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10)); // Padding from top

        // Create a panel for the icon and label
        JPanel headerContent = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerContent.setBackground(Color.WHITE);

        // Add icon
        ImageIcon icon = new ImageIcon(iconPath);
        JLabel iconLabel = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)));

        // Add label
        JLabel titleLabel = new JLabel(labelText);
        titleLabel.setFont(new Font(Values.LABEL_FONT, Font.BOLD, 30)); // Font size
        titleLabel.setForeground(Color.decode(Values.TEXT_COLOR)); // Text color

        // Add icon and label to the header panel
        headerContent.add(iconLabel);
        headerContent.add(titleLabel);

        // Add the header content to the main panel
        add(headerContent, BorderLayout.CENTER);

        // Add a separator line at the bottom of the header
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setForeground(Color.decode(Values.TEXT_COLOR)); // Set color of the separator line
        separator.setPreferredSize(new Dimension(getWidth()-300, 10)); // Thickness of the separator
        add(separator, BorderLayout.SOUTH);
    }
}
