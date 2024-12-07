package Views.SuperAdminview;

import Controllers.SuperAdminController;
import Entites.Branch;
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
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SuperAdminViewUpdateDeleteBranchespanel extends JPanel {
    private ActionListener superAdminListener; // Listener for buttons
    private SuperAdminController superAdminController;
    private JTable table;
    private DefaultTableModel tableModel;
    public JPanel parentPanel;

    // Column names for the table
    private final String[] columnNames = {
            "Branch Code", "Branch City", "Branch Address", "Branch Ph#", "Branch Status",
            "No. of Empl.", "Branch Manager", "Manager's Salary", "Update", "Delete"
    };

    public SuperAdminViewUpdateDeleteBranchespanel(ActionListener superAdminListener,SuperAdminController instance, JPanel parentPanel) {
        this.superAdminController = instance;
        this.parentPanel = parentPanel;
        this.superAdminListener = superAdminListener;
        System.out.println("SuperAdminViewUpdateDeleteBranchespanel initialized");
        inIt();
    }

    public void inIt() {
        parentPanel.removeAll();
        parentPanel.setLayout(new BorderLayout());
        parentPanel.setBackground(Color.decode(Values.BG_COLOR));

        // Search Panel
        JPanel searchPanel = createSearchPanel();
        parentPanel.add(searchPanel, BorderLayout.NORTH);

        // Table Panel
        JScrollPane tableScrollPane = new JScrollPane(createTable());
        parentPanel.add(tableScrollPane, BorderLayout.CENTER);
        parentPanel.revalidate();
        parentPanel.repaint();
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchPanel.setBackground(Color.decode(Values.LEFT_PANEL_BG_COLOR)); // Use constant for panel background

        // Create search label
        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setFont(new Font(Values.LABEL_FONT, Font.PLAIN, Values.LABEL_FONT_SMALLSIZE));
        searchLabel.setPreferredSize(new Dimension(60, 30));

        // Create search field
        CustomTextField searchField = new CustomTextField(20);
        searchField.setBackground(Color.decode(Values.TEXT_FIELD_BACKGROUND_COLOR));
        searchField.setForeground(Color.decode(Values.TEXT_FIELD_TEXT_COLOR));

        // Add search filter functionality
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
        // Fetch branch data from the controller
        ArrayList<Branch> branches = this.superAdminController.getBranches();
        Object[][] branchData = new Object[branches.size()][columnNames.length];

        // Populate data for the table
        for (int i = 0; i < branches.size(); i++) {
            Branch branch = branches.get(i);
            branchData[i][0] = branch.getBranchId();
            branchData[i][1] = branch.getCity();
            branchData[i][2] = branch.getAddress();
            branchData[i][3] = branch.getPhoneNo();
            branchData[i][4] = branch.getStatus();
            branchData[i][5] = branch.getNoOfEmployees();
            branchData[i][6] = branch.getManagerName();
            branchData[i][7] = branch.getManagerSalary();
            branchData[i][8] = "Update";
            branchData[i][9] = "Delete";
        }

        // Create table model
        tableModel = new DefaultTableModel(branchData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column >= 0; // Make only "Update" and "Delete" columns editable
            }
        };

        // Create table
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setFont(new Font(Values.LABEL_FONT, Font.BOLD, Values.LABEL_FONT_SMALLSIZE));
        table.getTableHeader().setBackground(Color.decode(Values.BUTTON_GCOLOR));
        table.getTableHeader().setForeground(Color.decode(Values.BUTTON_TEXT_COLOR));
        table.setFont(new Font(Values.TEXT_FIELD_FONT, Font.PLAIN, Values.TEXT_FIELD_FONT_SIZE));


        // Add custom button renderers and editors
        table.getColumn("Update").setCellRenderer(new ButtonRenderer("Update", Color.decode(Values.BUTTON_GCOLOR)));
        table.getColumn("Update").setCellEditor(new ButtonEditor("Update", row -> {
            System.out.println("Update clicked for row: " + row);
            superAdminController.UpdateBranch(); // Call controller method
            inIt(); // Refresh the table
        }));

        table.getColumn("Delete").setCellRenderer(new ButtonRenderer("Delete", Color.decode(Values.BUTTON_GCOLOR)));
        table.getColumn("Delete").setCellEditor(new ButtonEditor("Delete", row -> {
            System.out.println("Delete clicked for row: " + row);
            int confirmation = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete this branch?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirmation == JOptionPane.YES_OPTION) {
                superAdminController.DeleteBranch(); // Call controller method
                inIt(); // Refresh the table
            }
        }));

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

    public int getBranchIdtoUpdate() {
        int row = table.getSelectedRow();
        if (row != -1) {
            return (int) table.getValueAt(row, 0);
        }
        throw new IllegalStateException("No branch selected.");
    }

    public String getCityToUpdate() {
        int row = table.getSelectedRow();
        if (row != -1) {
            return (String) table.getValueAt(row, 1);
        }
        throw new IllegalStateException("No branch selected.");
    }

    public String getPhonenoToUpdate() {
        int row = table.getSelectedRow();
        if (row != -1) {
            return (String) table.getValueAt(row, 3);
        }
        throw new IllegalStateException("No branch selected.");
    }

    public String getAdressToUpdate() {
        int row = table.getSelectedRow();
        if (row != -1) {
            return (String) table.getValueAt(row, 2);
        }
        throw new IllegalStateException("No branch selected.");
    }

    public int getNoofEmployeesToUpdate() {
        int row = table.getSelectedRow();
        if (row != -1) {
            return (int) table.getValueAt(row, 5);
        }
        throw new IllegalStateException("No branch selected.");
    }

    public Boolean getStatusToUpdate() {
        int row = table.getSelectedRow();
        if (row != -1) {
            return (Boolean) table.getValueAt(row, 4);
        }
        throw new IllegalStateException("No branch selected.");
    }

    public String getManagerNameToUpdate() {
        int row = table.getSelectedRow();
        if (row != -1) {
            return (String) table.getValueAt(row, 6);
        }
        throw new IllegalStateException("No branch selected.");
    }

    public String getManagerSalaryToUpdate() {
        int row = table.getSelectedRow();
        if (row != -1) {
            return table.getValueAt(row, 7).toString();
        }
        throw new IllegalStateException("No branch selected.");
    }
}
