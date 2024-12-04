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

public class ViewVendor {

    private JTable table;
    private DefaultTableModel tableModel;

    // Sample data with Contract Start and Contract End columns
    private final String[][] vendorData = {
            {"V001", "Vendor A", "1234567890", "123 Main Street, City A", "50000", "20", "01/01/2024", "31/12/2024", "Update", "Delete"},
            {"V002", "Vendor B", "1234568990", "123 Main Street, City B", "60000", "15", "01/01/2023", "31/12/2023", "Update", "Delete"}
    };

    private final String[] columnNames = {
            "Vendor ID", "Vendor Name", "Vendor Ph#", "Address", "Total Payment Amount",
            "Total Products", "Contract Start", "Contract End", "Update", "Delete"
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

        // Create search label
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
                // Not needed for plain text fields
            }
        });

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        return searchPanel;
    }

    private JTable createTable() {
        tableModel = new DefaultTableModel(vendorData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Prevent editing for non-editable columns
                return column < 6; // Only Vendor ID, Name, Ph#, Address, Payment Amount, and Products are editable
            }
        };

        // Create table with the model
        JTable table = new JTable(tableModel);

        // Add custom button renderers and editors for "Update" and "Delete"
        table.getColumn("Update").setCellRenderer(new ButtonRenderer("Update", Color.decode(Values.BUTTON_COLOR)));
        table.getColumn("Update").setCellEditor(new ButtonEditor(new Object() {
            public void update(int row) {
                // Handle the "Update" action for the row
                System.out.println("Updating row: " + row);
            }
        }, table, "update"));

        table.getColumn("Delete").setCellRenderer(new ButtonRenderer("Delete", Color.decode(Values.BUTTON_COLOR)));
        table.getColumn("Delete").setCellEditor(new ButtonEditor(new Object() {
            public void delete(int row) {
                // Handle the "Delete" action for the row
                System.out.println("Deleting row: " + row);
                tableModel.removeRow(row);
            }
        }, table, "delete"));

        return table;
    }

    private void filterTable(String searchText) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        if (searchText.trim().isEmpty()) {
            sorter.setRowFilter(null); // Show all rows if search text is empty
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText)); // Case-insensitive search
        }
    }
}
