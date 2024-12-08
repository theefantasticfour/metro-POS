package Views.CashierView;
import Controllers.CashierController;
import Utils.Values;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

import java.util.Map;

public class CashierBillShowpanel extends JPanel {
    CashierPanelView cashierPanel;
    CashierGenerateSalePanel generatebill;
    JScrollBar verticalScrollBar;

    public CashierBillShowpanel(JPanel parentPanel, CashierController cashierController, CashierPanelView instance) {

            parentPanel.removeAll();
            parentPanel.setLayout(new BorderLayout());
            setBackground(Color.WHITE);
            setLayout(new BorderLayout());
            JPanel invoicePanel = new JPanel();
            invoicePanel.setLayout(null);
            cashierPanel = instance;
            int panelWidth = parentPanel.getWidth();
            int panelHeight = parentPanel.getHeight();
            int invoicePanelWidth = panelWidth - 40; // invoicePanel width = parentPanel width - padding
            int invoicePanelHeight = 1000; // Set a large height to make the panel scrollable (you can adjust this as needed)

            // Set bounds for invoicePanel (center it in the parentPanel)
            invoicePanel.setBounds(20, 10, invoicePanelWidth, invoicePanelHeight);
            invoicePanel.setBackground(new Color(240, 248, 255)); // Light background
            invoicePanel.setBorder(new LineBorder(Color.BLUE, 2));


            JLabel logoLabel = new JLabel(new ImageIcon(Values.LOGO_ICON));
            logoLabel.setBounds(10, 10, 100, 70);
            invoicePanel.add(logoLabel);

            // Replace the Invoice Icon with Branch Id and Cashier Name
            JLabel branchLabel = new JLabel("Helpline: 111-222-333");
            branchLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            branchLabel.setBounds(invoicePanelWidth - 140, 10, 150, 20);
            invoicePanel.add(branchLabel);



            String name =cashierPanel.getCashierName();
            JLabel cashierLabel = new JLabel("Cashier: "+name);
            cashierLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            cashierLabel.setBounds(invoicePanelWidth - 140, 30, 150, 20);
            invoicePanel.add(cashierLabel);


            JLabel invoiceTitle = new JLabel("INVOICE");
            invoiceTitle.setFont(new Font("Arial", Font.BOLD, 28)); // Bold and large font
            invoiceTitle.setHorizontalAlignment(SwingConstants.CENTER);
            invoiceTitle.setBounds((invoicePanelWidth - 200) / 2, 70, 200, 40); // Center the title
            invoicePanel.add(invoiceTitle);

            // Add separators and column headers
            addSeparator(invoicePanel, 10, 120, invoicePanelWidth - 20);
            addColumnHeader(invoicePanel, "PRODUCT", 20, 130);
            addColumnHeader(invoicePanel, "QUANTITY", 150, 130);
            addColumnHeader(invoicePanel, "PRICE", 300, 130);
            addSeparator(invoicePanel, 10, 160, invoicePanelWidth - 20);
           Map<String, Integer> saleDetails = cashierPanel.getSaleDetails();
        double totalAmount = 0;
        int yPosition = 170; // Starting y-position
        for (Map.Entry<String, Integer> entry : saleDetails.entrySet()) {
            String product = entry.getKey();
            int quantity = entry.getValue();
            double price = 10;//implement logic to get price
            double amount = quantity * price;
            totalAmount += amount;

            addRow(invoicePanel, product, String.valueOf(quantity), price + " Rs", yPosition);
            yPosition += 20; // Increase y-position for next row
        }

            double taxAmount = totalAmount *  0.16 ; //Values.TAX;  // Assuming 16% tax rate
            double grandTotal = totalAmount + taxAmount;

        // Total
        JLabel totalLabel = new JLabel("TOTAL");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        invoicePanel.add(totalLabel);

        JLabel totalAmountLabel = new JLabel(totalAmount + " Rs");
        totalAmountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalAmountLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        invoicePanel.add(totalAmountLabel);

// Tax (16%)
        JLabel taxLabel = new JLabel("TAX (16%)");
        taxLabel.setFont(new Font("Arial", Font.BOLD, 14));
        taxLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        invoicePanel.add(taxLabel);

        JLabel taxAmountLabel = new JLabel(taxAmount + " Rs");
        taxAmountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        taxAmountLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        invoicePanel.add(taxAmountLabel);

// Grand Total
        JLabel grandTotalLabel = new JLabel("GRAND TOTAL");
        grandTotalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        grandTotalLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        invoicePanel.add(grandTotalLabel);

        JLabel grandTotalAmount = new JLabel(grandTotal + " Rs");
        grandTotalAmount.setFont(new Font("Arial", Font.BOLD, 14));
        grandTotalAmount.setAlignmentX(Component.RIGHT_ALIGNMENT);
        invoicePanel.add(grandTotalAmount);

// Payment Method and Note
        JLabel paymentMethodLabel = new JLabel("PAYMENT METHOD: CASH");
        paymentMethodLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        paymentMethodLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        invoicePanel.add(paymentMethodLabel);

        JLabel noteLabel = new JLabel("NOTE: THANK YOU FOR CHOOSING US!");
        noteLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        noteLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        invoicePanel.add(noteLabel);

           // Add OK and Print buttons at the bottom left and bottom right of the invoice panel
        // Add OK and Print buttons at the bottom left and bottom right of the invoice panel
        JButton okButton = createButton("OK", 20, invoicePanelHeight - 40, e -> closeInvoice(parentPanel)); // Bottom-left
        invoicePanel.add(okButton);

        JButton printButton = createButton("Print", invoicePanelWidth - 100, invoicePanelHeight - 40, e -> printInvoice()); // Bottom-right
        invoicePanel.add(printButton);

        verticalScrollBar = new JScrollBar(JScrollBar.VERTICAL);
        verticalScrollBar.setBounds(invoicePanelWidth - 20, 0, 20, panelHeight); // Add the scrollbar to the right
        verticalScrollBar.addAdjustmentListener(e -> {
            int scrollValue = verticalScrollBar.getValue();
            invoicePanel.setLocation(0, -scrollValue); // Adjust the position of the invoicePanel based on scroll value
        });
        parentPanel.add(verticalScrollBar, BorderLayout.EAST);

        // Add the invoicePanel to the parent panel
        parentPanel.add(invoicePanel, BorderLayout.CENTER);
        }




        // Helper Methods
        private void addSeparator(JPanel panel, int x, int y, int width) {
            JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
            separator.setBounds(x, y, width, 5);
            separator.setForeground(Color.decode(Values.TEXT_COLOR));
            panel.add(separator);
        }

        private void addColumnHeader(JPanel panel, String text, int x, int y) {
            JLabel header = new JLabel(text);
            header.setFont(new Font("Arial", Font.BOLD, 14));
            header.setBounds(x, y, 100, 20);
            panel.add(header);
        }

        private void addRow(JPanel panel, String item, String quantity, String price, int y) {
            JLabel itemLabel = new JLabel(item);
            itemLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            itemLabel.setBounds(20, y, 100, 20);
            panel.add(itemLabel);

            JLabel quantityLabel = new JLabel(quantity);
            quantityLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            quantityLabel.setBounds(150, y, 100, 20);
            panel.add(quantityLabel);

            JLabel priceLabel = new JLabel(price);
            priceLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            priceLabel.setBounds(300, y, 100, 20);
            panel.add(priceLabel);
        }

        private JButton createButton(String text, int x, int y, ActionListener action) {
            JButton button = new JButton(text);
            button.setBounds(x, y, 80, 30);
            button.setBackground(new Color(138, 43, 226)); // Purple button
            button.setForeground(Color.WHITE);
            button.addActionListener(action);
            return button;
        }

        private void closeInvoice(JPanel parentPanel) {
            JOptionPane.showMessageDialog(this, "Invoice closed.");
            cashierPanel.openGenerateSaleForm();
        }

        private void printInvoice() {
            JOptionPane.showMessageDialog(this, "Printing Invoice...");
            // Add printing logic here
        }
    }

