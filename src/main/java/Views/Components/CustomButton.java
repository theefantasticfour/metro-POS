package Views.Components;

import Utils.Values;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {

    public CustomButton(String text) {
        super(text);
        initButton();
    }

    private void initButton() {
        setFont(new Font(Values.BUTTON_FONT, Font.BOLD, Values.BUTTON_FONT_SIZE));
        setBackground(Color.decode(Values.BUTTON_COLOR));
        setForeground(Color.decode(Values.BUTTON_TEXT_COLOR));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        setFocusPainted(false); // Remove focus border
    }
}
