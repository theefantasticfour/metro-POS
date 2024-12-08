package Views;

import Utils.Values;
import Views.CashierView.CashierPanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class InvoicePanel extends JPanel {
    CashierPanel cashierPanel;

    public InvoicePanel(JPanel parentPanel) {
        // Remove all components from the parent panel
        parentPanel.removeAll();
        parentPanel.setLayout(new BorderLayout());

        // Set the background and layout of the InvoicePanel
        setBackground(Color.WHITE);
        setLayout(new BorderLayout()); // Use BorderLayout for the main container

        // Create the main invoice panel
        JPanel invoicePanel = new JPanel();
        invoicePanel.setLayout(null); // Null layout for manual positioning

        // Set the size of the invoicePanel to be large enough to hold all components
        int panelWidth = parentPanel.getWidth();
        int panelHeight = parentPanel.getHeight();
        int invoicePanelWidth = panelWidth - 40; // invoicePanel width = parentPanel width - padding
        int invoicePanelHeight = 1000; // Set a large height to make the panel scrollable (you can adjust this as needed)

        // Set bounds for invoicePanel (center it in the parentPanel)
        invoicePanel.setBounds(20, 10, invoicePanelWidth, invoicePanelHeight);
        invoicePanel.setBackground(new Color(240, 248, 255)); // Light background
        invoicePanel.setBorder(new LineBorder(Color.BLUE, 2)); // Border for the invoice panel

        // Add Metro Logo at the top-left
        JLabel logoLabel = new JLabel(new ImageIcon(Values.LOGO_ICON));
        logoLabel.setBounds(10, 10, 100, 70); // Adjust size and position
        invoicePanel.add(logoLabel);

        // Replace the Invoice Icon with Branch Id and Cashier Name
        JLabel branchLabel = new JLabel("Helpline: 111-222-333");
        branchLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        branchLabel.setBounds(invoicePanelWidth - 140, 10, 150, 20);
        invoicePanel.add(branchLabel);

        JLabel cashierLabel = new JLabel("Cashier: John Doe");  // You can dynamically set the name here
        cashierLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        cashierLabel.setBounds(invoicePanelWidth - 140, 30, 150, 20);
        invoicePanel.add(cashierLabel);

        // Add the "INVOICE" title in the center
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

        // Sample Data for items (replace with actual data)
        // Format: Product, Quantity, Price
        Object[][] cartItems = {
                {"Product A", 2, 100},
                {"Product B", 1, 150},
                {"Product C", 3, 50},
                {"Product D", 1, 200},
                {"Product A", 2, 100},
                {"Product B", 1, 150},
                {"Product C", 3, 50},
                {"Product D", 1, 200}
        };

        // Display the items in the invoice
        double totalAmount = 0;
        for (int i = 0; i < cartItems.length; i++) {
            String product = (String) cartItems[i][0];
            int quantity = (int) cartItems[i][1];
            double price = (int) cartItems[i][2];
            double amount = quantity * price;
            totalAmount += amount;

            // Dynamic Y-position for each row
            addRow(invoicePanel, product, String.valueOf(quantity), price + " Rs", 170 + (i * 30));
        }

        // Calculate the tax and grand total
        double taxAmount = totalAmount * 0.10;  // Assuming 10% tax rate
        double grandTotal = totalAmount + taxAmount;

        // Total
        JLabel totalLabel = new JLabel("TOTAL");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalLabel.setBounds(20, 280 + (cartItems.length * 30), 100, 20);
        invoicePanel.add(totalLabel);

        JLabel totalAmountLabel = new JLabel(totalAmount + " Rs");
        totalAmountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        totalAmountLabel.setBounds(invoicePanelWidth - 120, 280 + (cartItems.length * 30), 100, 20);
        invoicePanel.add(totalAmountLabel);

        // Tax (10%)
        JLabel taxLabel = new JLabel("TAX (10%)");
        taxLabel.setFont(new Font("Arial", Font.BOLD, 14));
        taxLabel.setBounds(20, 300 + (cartItems.length * 30), 100, 20);
        invoicePanel.add(taxLabel);

        JLabel taxAmountLabel = new JLabel(taxAmount + " Rs");
        taxAmountLabel.setFont(new Font("Arial", Font.BOLD, 14));
        taxAmountLabel.setBounds(invoicePanelWidth - 120, 300 + (cartItems.length * 30), 100, 20);
        invoicePanel.add(taxAmountLabel);

        // Grand Total
        JLabel grandTotalLabel = new JLabel("GRAND TOTAL");
        grandTotalLabel.setFont(new Font("Arial", Font.BOLD, 14));
        grandTotalLabel.setBounds(20, 320 + (cartItems.length * 30), 150, 20);
        invoicePanel.add(grandTotalLabel);

        JLabel grandTotalAmount = new JLabel(grandTotal + " Rs");
        grandTotalAmount.setFont(new Font("Arial", Font.BOLD, 14));
        grandTotalAmount.setBounds(invoicePanelWidth - 120, 320 + (cartItems.length * 30), 100, 20);
        invoicePanel.add(grandTotalAmount);

        // Payment Method and Note
        JLabel paymentMethodLabel = new JLabel("PAYMENT METHOD: CASH");
        paymentMethodLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        paymentMethodLabel.setBounds(20, 340 + (cartItems.length * 30), 200, 20);
        invoicePanel.add(paymentMethodLabel);

        JLabel noteLabel = new JLabel("NOTE: THANK YOU FOR CHOOSING US!");
        noteLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        noteLabel.setBounds(20, 360 + (cartItems.length * 30), 300, 20);
        invoicePanel.add(noteLabel);

        // Add OK and Print buttons at the bottom left and bottom right of the invoice panel
        int buttonWidth = 80;
        int buttonHeight = 30;
        JButton okButton = createButton("OK", 20, invoicePanelHeight - 70 + (cartItems.length * 30), e -> closeInvoice(parentPanel)); // Bottom-left
        invoicePanel.add(okButton);

        JButton printButton = createButton("Print", invoicePanelWidth - buttonWidth - 20, invoicePanelHeight - 70 + (cartItems.length * 30), e -> printInvoice()); // Bottom-right
        invoicePanel.add(printButton);

        // Wrap invoicePanel in JScrollPane for scrolling
        JScrollPane scrollPane = new JScrollPane(invoicePanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, invoicePanelWidth, panelHeight - 50); // Set bounds for JScrollPane

        // Add the scrollPane to this container
        parentPanel.add(scrollPane, BorderLayout.CENTER);

        // Add this InvoicePanel to the parent panel
        parentPanel.add(this, BorderLayout.CENTER);

        // Refresh the parent panel
        parentPanel.revalidate();
        parentPanel.repaint();
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
        cashierPanel.openGenerateSale();
    }

    private void printInvoice() {
        JOptionPane.showMessageDialog(this, "Printing Invoice...");
        // Add printing logic here
    }
}
