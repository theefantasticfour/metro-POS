package Views.SideBarAndHeader;

import Utils.Values;
import javax.swing.*;
import java.awt.*;

public class RightPanelHeader extends JPanel {

    // Constructor to accept icon and label
    public RightPanelHeader(String iconPath, String labelText) {
        setLayout(new FlowLayout(FlowLayout.LEFT)); // Align horizontally
        setBackground(Color.WHITE); // Background color
        setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10)); // Padding from top

        // Add icon
        ImageIcon icon = new ImageIcon(iconPath);
        JLabel iconLabel = new JLabel(new ImageIcon(icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH)));

        // Add label
        JLabel titleLabel = new JLabel(labelText);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30)); // Font size
        titleLabel.setForeground(Color.decode(Values.TEXT_COLOR)); // Text color

        // Add icon and label to the header panel
        add(iconLabel);
        add(titleLabel);
    }
}
