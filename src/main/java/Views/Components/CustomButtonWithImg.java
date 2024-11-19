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
        setBackground(Color.decode(Values.BUTTON_COLOR));
        setFocusPainted(false);
        setHorizontalAlignment(SwingConstants.CENTER);

        // Add icon
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledIcon = icon.getImage().getScaledInstance(15, 100, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(scaledIcon));

        // Add padding between icon and text
        setIconTextGap(20);

        // Set preferred size
        setPreferredSize(new Dimension(350, 200));

        // Add border using Values class
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode(Values.BUTTON_BORDER_COLOR), 1),
                BorderFactory.createEmptyBorder(10, 20,10,20)
        ));
    }
}
