package Views;
import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {
    public CustomButton(String text) {
        super(text);
        setFont(new Font("Arial", Font.BOLD, 16));
        setBackground(Color.decode("#A3A5EB")); // Set button color to #A3A5EB (mauve)
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding
        setFocusPainted(false);                  // Remove focus border
    }

    // Method to update the button's background color
    public void setButtonColor(Color color) {
        setBackground(color);
    }

    // Method to update the button's text style
    public void setTextStyle(Font font) {
        setFont(font);
    }
}

