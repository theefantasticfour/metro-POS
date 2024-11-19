package Views.Components;

import Utils.Values;
import javax.swing.*;
import java.awt.*;

public class CustomButtonWithImg extends JButton {

    public CustomButtonWithImg(String text, String imagePath) {
        super(text);

        // Set font and color using Values class
        setFont(new Font(Values.BUTTON_FONT, Font.BOLD, Values.BUTTON_FONT_SIZE));
        setForeground(Color.decode(Values.BUTTON_TEXT_COLOR));
        setBackground(Color.decode(Values.BUTTON_GCOLOR));
        setFocusPainted(false);
        setHorizontalAlignment(SwingConstants.CENTER);

        // Add icon
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledIcon = icon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH); // Updated size
        setIcon(new ImageIcon(scaledIcon));

        // Add padding between icon and text
        setIconTextGap(20);

        // Set preferred size
        setPreferredSize(new Dimension(350, 200)); // Adjusted for better layout

        // Add border using Values class
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode(Values.BUTTON_COLOR), 1),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
    }
}
