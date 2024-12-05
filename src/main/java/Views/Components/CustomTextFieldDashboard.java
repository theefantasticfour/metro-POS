package Views.Components;


import Utils.Values;

import javax.swing.*;
import java.awt.*;

public class CustomTextFieldDashboard {

    public static JTextField createCustomTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font(Values.TEXT_FIELD_FONT, Font.PLAIN, Values.TEXT_FIELD_FONT_SIZE));
        textField.setBackground(Color.decode(Values.TEXT_FIELD_BACKGROUND_COLOR));
        textField.setForeground(Color.decode(Values.TEXT_FIELD_TEXT_COLOR));
        textField.setPreferredSize(new Dimension(450, 38));
        return textField;
    }
}
