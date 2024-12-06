package Views.BranchManagerView;

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

public class ViewUpdateDeleteEmployee {

    private JTable table;
    private DefaultTableModel tableModel;

    // Sample data
    private final String[][] employeeData = {
            {"Cashier", "John Doe", "john.doe@example.com", "50000", "0","Update", "Delete"},
            {"Data Entry Operator", "Jane Smith", "jane.smith@example.com", "45000","1", "Update", "Delete"},
            {"Cashier", "Robert Brown", "robert.brown@example.com", "52000","0", "Update" ,"Delete"}
    };
    private final String[] columnNames = {"Employee Type", "Employee Name", "Email Address", "Salary", "Status", "Update", "Delete"};

    public void display(BranchManagerPanel branchManagerPanel) {
        JPanel contentPanel = branchManagerPanel.getContentPanel();
        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.decode("#F8F9FA"));

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
                // will add later if needed
            }
        });

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        return searchPanel;
    }

    private JTable createTable() {
        tableModel = new DefaultTableModel(employeeData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column < 4;
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
