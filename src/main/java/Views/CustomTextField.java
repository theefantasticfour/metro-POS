package Views;
import javax.swing.*;
import java.awt.*;

public class CustomTextField extends JTextField {
    public CustomTextField(int columns) {
        super(columns);
        setFont(new Font("Arial", Font.PLAIN, 14));
        setBackground(new Color(245, 245, 245));
        setForeground(Color.BLACK);
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    // Method to update the text field's text style
    public void setTextStyle(Font font) {
        setFont(font);
    }

    // Method to update the text field's background color
    public void setFieldColor(Color color) {
        setBackground(color);
    }
}

