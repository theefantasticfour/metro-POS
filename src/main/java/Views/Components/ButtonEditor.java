package Views.Components;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.function.Consumer;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private final JButton button;
    private Consumer<Integer> action;
    private int currentRow;

    public ButtonEditor(String label, Consumer<Integer> action) {
        this.button = new JButton(label);
        this.action = action;

        // Add action listener for button
        button.addActionListener(e -> {
            fireEditingStopped(); // Notify the table that editing is stopped
            if (action != null) {
                action.accept(currentRow); // Pass the row index to the action
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        currentRow = row; // Save the row index
        button.setText(value == null ? "" : value.toString());
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return button.getText();
    }
}
