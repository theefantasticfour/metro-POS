package Views.SuperAdminview;

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

public class ViewUpdateDelete {

    private JTable table;
    private DefaultTableModel tableModel;

    // Sample data for the table
    private final String[][] branchData = {
            {"001", "New York", "123 5th Ave", "123-456-7890", "Active", "25", "Alice Johnson", "100000", "Update", "Delete"},
            {"002", "Los Angeles", "456 Sunset Blvd", "987-654-3210", "Active", "30", "Bob Smith", "120000", "Update", "Delete"},
            {"003", "Chicago", "789 Michigan Ave", "456-789-1230", "Inactive", "15", "Eve Williams", "90000", "Update", "Delete"}
    };
    private final String[] columnNames = {
            "Branch Code", "Branch City", "Branch Address", "Branch Ph#", "Branch Status",
            "No. of Empl.", "Branch Manager", "Manager's Salary", "Update", "Delete"
    };

    public void display(SuperAdminPanel Panel) {
        JPanel contentPanel = Panel.getContentPanel();
        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.decode(Values.BG_COLOR)); // Use constant for background color

        // Search Panel
        JPanel searchPanel = createSearchPanel();
        contentPanel.add(searchPanel, BorderLayout.NORTH);

        // Table
        table = createTable();
        JScrollPane scrollPane = new JScrollPane(table);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchPanel.setBackground(Color.decode(Values.LEFT_PANEL_BG_COLOR)); // Use constant for search panel color

        // Create search label
        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setFont(new Font(Values.LABEL_FONT, Font.PLAIN, Values.LABEL_FONT_SMALLSIZE)); // Use font constants
        searchLabel.setPreferredSize(new Dimension(60, 30));

        CustomTextField searchField = new CustomTextField(20);
        searchField.setBackground(Color.decode(Values.TEXT_FIELD_BACKGROUND_COLOR)); // Use constants for text field
        searchField.setForeground(Color.decode(Values.TEXT_FIELD_TEXT_COLOR));

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
        tableModel = new DefaultTableModel(branchData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column < 8; // Allow editing only for data columns, not actions
            }
        };

        // Create table with model
        JTable table = new JTable(tableModel);

        // Add custom button renderers and editors for "Update" and "Delete"
        table.getColumn("Update").setCellRenderer(new ButtonRenderer("Update", Color.decode(Values.BUTTON_COLOR))); // Use constant for button color
        table.getColumn("Delete").setCellRenderer(new ButtonRenderer("Delete", Color.decode(Values.BUTTON_COLOR))); // Use constant for button color

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
