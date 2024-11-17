package Views;
import javax.swing.*;
import java.awt.*;

public class GeneralLoginPage {
    private JPanel mainPanel;

    public GeneralLoginPage() {
        // Main Panel
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Left Panel (Image)
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(Color.WHITE);

        ImageIcon leftImageIcon = new ImageIcon("left_image.png");
        JLabel leftImageLabel = new JLabel();
        leftImageLabel.setIcon(new ImageIcon(leftImageIcon.getImage().getScaledInstance(800, 830, Image.SCALE_SMOOTH))); // Adjust size as needed
        leftImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftImageLabel.setVerticalAlignment(SwingConstants.CENTER);

        leftPanel.add(leftImageLabel, BorderLayout.CENTER);

        // Right Panel (Buttons)
        JPanel rightPanel = new JPanel(new GridLayout(4, 1, 20, 20));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(70, 120, 100, 120));

        // Creates buttons using CustomButtonWithImg class
        CustomButtonWithImg superAdminButton = new CustomButtonWithImg("Super Admin", "super_admin.png");
        rightPanel.add(superAdminButton);

        CustomButtonWithImg branchManagerButton = new CustomButtonWithImg("Branch Manager", "branch_manager.png");
        rightPanel.add(branchManagerButton);

        CustomButtonWithImg cashierButton = new CustomButtonWithImg("Retail Cashier", "cashier.png");
        rightPanel.add(cashierButton);

        CustomButtonWithImg dataEntryButton = new CustomButtonWithImg("Data Entry Operator", "data_entry.png");
        rightPanel.add(dataEntryButton);

        //JSplitPane to divide the panel in half
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(800);
        splitPane.setDividerSize(0);
        splitPane.setResizeWeight(0.3);
        splitPane.setBorder(null);

        // Add split pane to main panel
        mainPanel.add(splitPane, BorderLayout.CENTER);
    }

    public JPanel getPanel() {
        return mainPanel;
    }
}

