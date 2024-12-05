package Views.Components;

import Utils.Values;

import javax.swing.*;
import java.awt.*;

public class CustomComboBox extends JComboBox<String> {

    public CustomComboBox(String[] items) {
        super(items);
        initComboBox();
    }

    private void initComboBox() {
        setFont(new Font(Values.TEXT_FIELD_FONT, Font.PLAIN, Values.TEXT_FIELD_FONT_SIZE));
        setBackground(Color.decode(Values.TEXT_FIELD_BACKGROUND_COLOR));
        setForeground(Color.decode(Values.TEXT_FIELD_TEXT_COLOR));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode(Values.TEXT_FIELD_BORDER_COLOR), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }
}
