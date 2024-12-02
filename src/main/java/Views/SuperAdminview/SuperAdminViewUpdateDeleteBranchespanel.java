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

    private final ActionListener superAdminListener;

    private JTable table;
    private DefaultTableModel tableModel;

    public SuperAdminViewUpdateDeleteBranchespanel(ActionListener superAdminListener) {
        this.superAdminListener = superAdminListener;
        System.out.println("SuperAdminViewUpdateDeleteBranchesPanel initialized");
        initialize();
    }

    public void initialize() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.decode(Values.BG_COLOR));

        // Search Panel
        JPanel searchPanel = createSearchPanel();
        this.add(searchPanel, BorderLayout.NORTH);

        // Table
        JScrollPane tableScrollPane = createBranchTablePanel();
        this.add(tableScrollPane, BorderLayout.CENTER);
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchPanel.setBackground(Color.decode(Values.LEFT_PANEL_BG_COLOR));

        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setFont(new Font(Values.LABEL_FONT, Font.PLAIN, Values.LABEL_FONT_SMALLSIZE));
        searchLabel.setPreferredSize(new Dimension(60, 30));

        CustomTextField searchField = new CustomTextField(20);
        searchField.setBackground(Color.decode(Values.TEXT_FIELD_BACKGROUND_COLOR));
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

    private JScrollPane createBranchTablePanel() {
        // Sample data: replace with real data from SuperAdminController
        ArrayList<Branch> branches = SuperAdminController.getBranches();
        Object[][] branchData = new Object[branches.size()][10];
        int row = 0;

        // Convert branch data into table data format
        for (Branch branch : branches) {
            branchData[row][0] = branch.getBranchId();
            branchData[row][1] = branch.getCity();
            branchData[row][2] = branch.getAddress();
            branchData[row][3] = branch.getPhoneNo();
            branchData[row][4] = branch.getStatus();
            branchData[row][5] = branch.getNoOfEmployees();
            branchData[row][6] = branch.getBranchManager();
            branchData[row][7] = branch.getManagerSalary();
            branchData[row][8] = "Update";
            branchData[row][9] = "Delete";
            row++;
        }

        // Column names
        String[] columnNames = {
                "Branch Code", "Branch City", "Branch Address", "Branch Ph#", "Branch Status",
                "No. of Empl.", "Branch Manager", "Manager's Salary", "Update", "Delete"
        };

        tableModel = new DefaultTableModel(branchData, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column < 8; // Allow editing only for data columns, not actions
            }
        };

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font(Values.TABLE_FONT, Font.PLAIN, 14));
        table.setBackground(Color.decode(Values.TABLE_BG_COLOR));

        // Add custom button renderers for "Update" and "Delete" columns
        table.getColumnModel().getColumn(8).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(9).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor("Update"));
        table.getColumnModel().getColumn(9).setCellEditor(new ButtonEditor("Delete"));

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        return new JScrollPane(table);
    }

    private void filterTable(String query) {
        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) table.getRowSorter();
        RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + query);
        sorter.setRowFilter(rowFilter);
    }
}