package Views;
import javax.swing.*;
import java.awt.*;

public class CustomButtonWithImg extends JButton {

    public CustomButtonWithImg(String text, String imagePath) {
        super(text);

        // Set font and color
        setFont(new Font("Arial", Font.BOLD, 30));
        setForeground(Color.WHITE);
        setBackground(Color.decode("#A3A5EB")); // Button background color set to mauve
        setFocusPainted(false);
        setHorizontalAlignment(SwingConstants.CENTER);

        // Add icon
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledIcon = icon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(scaledIcon));

        // Add padding between icon and text
        setIconTextGap(20);

        setPreferredSize(new Dimension(350, 200));

        // Add border
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1), // Light gray border
                BorderFactory.createEmptyBorder(10, 20, 10, 20) // Padding
        ));
    }
}

