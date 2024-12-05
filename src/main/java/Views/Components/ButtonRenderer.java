package Views.Components;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer(String label, Color buttonColor) {
        setText(label);
        setBackground(buttonColor);
        setOpaque(true);
    }

    // New constructor to allow customization and action listener support
    public ButtonRenderer(String label, Color buttonColor, ActionListener actionListener) {
        setText(label);
        setBackground(buttonColor);
        setOpaque(true);
        addActionListener(actionListener); // Attach action listener
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setBackground(table.getSelectionBackground());
        } else {
            setBackground(table.getSelectionForeground());
        }
        return this;
    }
}
