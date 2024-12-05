package Views.Components;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class ButtonEditor extends DefaultCellEditor {
    private final JButton button;

    public ButtonEditor(JButton button, Consumer<Integer> action) {
        super(new JTextField());
        this.button = button;
        this.button.setOpaque(true);

        this.button.addActionListener(e -> {
            fireEditingStopped();
            action.accept(((JTable) button.getParent()).getSelectedRow());
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return button;
    }
}
