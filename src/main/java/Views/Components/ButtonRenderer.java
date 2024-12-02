package Views.Components;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRenderer extends JButton implements TableCellRenderer {

    private String label;
    private Color buttonColor;

    public ButtonRenderer(String label, Color buttonColor) {
        this.label = label;
        this.buttonColor = buttonColor;
        setText(label);
        setBackground(buttonColor);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
}
