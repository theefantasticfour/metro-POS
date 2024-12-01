package Views.DataEntryOperatorView;

import Utils.Values;
import Views.Components.ButtonEditor;
import Views.Components.ButtonRenderer;
import Views.Components.CustomTextField;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class ViewProducts {

    private JTable table;
    private DefaultTableModel tableModel;

    // Sample product data (added Vendor ID)
    private final String[][] productData = {
            {"101", "Laptop", "Electronics", "70000", "75000", "750", "7200", "V001", "25", "Update", "Delete"},
            {"102", "Chair", "Furniture", "2000", "2500", "250", "2400", "V002", "50", "Update", "Delete"},
            {"103", "Pen", "Stationery", "10", "15", "15", "140", "V003", "200", "Update", "Delete"}
    };

    private final String[] columnNames = {
            "Product ID", "Product Name", "Category", "Original Price", "Sale Price per unit",
            "Sale Price Per Carton", "Quantity In Stock", "Vendor", "Update", "Delete"
    };

    public void display(JPanel mainPanel) {
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.decode("#F8F9FA"));

        // Search Panel
        JPanel searchPanel = createSearchPanel();
        mainPanel.add(searchPanel, BorderLayout.NORTH);

        // Table
        table = createTable();
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setFont(new Font(Values.LABEL_FONT, Font.PLAIN, Values.LABEL_FONT_SMALLSIZE));
        searchLabel.setPreferredSize(new Dimension(60, 30));

        CustomTextField searchField = new CustomTextField(20);

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable(searchField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable(searchField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Not used
            }
        });

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        return searchPanel;
    }

    private JTable createTable() {
        tableModel = new DefaultTableModel(productData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column < 8; // Make only data fields editable (excluding "Update" and "Delete")
            }
        };

        JTable table = new JTable(tableModel);

        // Add custom button renderers and editors for "Update" and "Delete"
        table.getColumn("Update").setCellRenderer(new ButtonRenderer("Update", Color.decode(Values.BUTTON_COLOR)));
        table.getColumn("Update").setCellEditor(new ButtonEditor(new Object() {
            public void update(int row) {
                System.out.println("Updating product at row: " + row);
                // Add logic to handle the update action
            }
        }, table, "update"));

        table.getColumn("Delete").setCellRenderer(new ButtonRenderer("Delete", Color.decode(Values.BUTTON_COLOR)));
        table.getColumn("Delete").setCellEditor(new ButtonEditor(new Object() {
            public void delete(int row) {
                System.out.println("Deleting product at row: " + row);
                tableModel.removeRow(row);
            }
        }, table, "delete"));

        return table;
    }

    private void filterTable(String searchText) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        if (searchText.trim().isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
        }
    }
}
