package Views.Components;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ButtonEditor extends DefaultCellEditor {

    private JButton button;
    private String label;
    private boolean isUpdate;
    private JTable table;

    public ButtonEditor(Object o, JTable table, String update) {
        super(new JTextField());
        this.table = table;
        this.label = label;
        this.isUpdate = isUpdate;
        button = new JButton(label);
        button.setOpaque(true);
        button.addActionListener((ActionEvent e) -> {
            fireEditingStopped();
            // Perform action based on `isUpdate`
            if (isUpdate) {
                updateRow(table.getSelectedRow());
            } else {
                deleteRow(table.getSelectedRow());
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        button.setText(label);
        return button;
    }

    private void updateRow(int row) {
        System.out.println("Updating row: " + row);
        // Add your update logic here
    }

    private void deleteRow(int row) {
        System.out.println("Deleting row: " + row);
        // Add your delete logic here
    }
}
