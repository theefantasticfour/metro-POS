/*
package Views.BranchManagerView;

import Controllers.BranchManagerController;
import Views.Components.CustomComboBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BranchManagerAddEmployeepanelTest {

    private BranchManagerAddEmployeepanel panel;
    private ActionListener mockListener;
    private BranchManagerController mockController;
    private BranchManagerView mockBranchManagerView;

    @BeforeEach
    void setUp() {
        mockListener = mock(ActionListener.class);
        mockController = mock(BranchManagerController.class);
        mockBranchManagerView = mock(BranchManagerView.class);
        panel = new BranchManagerAddEmployeepanel(mockListener, mockController);
    }

    @Test
    void testComponentInitialization() {
        assertNotNull(panel, "Panel should be initialized.");
    }

    @Test
    void testDisplayAddsComponents() {
        JPanel mockContentPanel = new JPanel();
        when(mockBranchManagerView.getContentPanel()).thenReturn(mockContentPanel);

        panel.display(mockBranchManagerView);

        assertTrue(mockContentPanel.getComponentCount() > 0, "Content panel should contain components after display.");
    }

    @Test
    void testApplyButtonAction_ValidInputs() {
        JPanel mockContentPanel = new JPanel();
        when(mockBranchManagerView.getContentPanel()).thenReturn(mockContentPanel);

        panel.display(mockBranchManagerView);

        // Simulate entering valid data
        for (Component component : mockContentPanel.getComponents()) {
            if (component instanceof JPanel) {
                for (Component subComponent : ((JPanel) component).getComponents()) {
                    if (subComponent instanceof CustomComboBox) {
                        ((CustomComboBox) subComponent).setSelectedItem("Cashier");
                    }
                    if (subComponent instanceof JTextField) {
                        JTextField textField = (JTextField) subComponent;
                        if (textField.getToolTipText() == null) {
                            textField.setText("Valid Input");
                        }
                    }
                }
            }
        }

        JButton applyButton = findApplyButton(mockContentPanel);
        assertNotNull(applyButton, "Apply button should be present.");

        // Simulate clicking the apply button
        applyButton.doClick();

        assertEquals("Cashier", panel.getEmpType(), "Employee type should match input.");
        assertEquals("Valid Input", panel.getEmpName(), "Employee name should match input.");
        assertEquals("Valid Input", panel.getEmail(), "Email should match input.");
        assertEquals("Valid Input", panel.getSalary(), "Salary should match input.");
    }

    @Test
    void testApplyButtonAction_InvalidInputs_ShowsError() {
        JPanel mockContentPanel = new JPanel();
        when(mockBranchManagerView.getContentPanel()).thenReturn(mockContentPanel);

        panel.display(mockBranchManagerView);

        // Simulate leaving fields empty
        JButton applyButton = findApplyButton(mockContentPanel);
        assertNotNull(applyButton, "Apply button should be present.");

        applyButton.doClick(); // Simulate clicking the button

        assertNull(panel.getEmpType(), "Employee type should not be set.");
        assertNull(panel.getEmpName(), "Employee name should not be set.");
        assertNull(panel.getEmail(), "Email should not be set.");
        assertNull(panel.getSalary(), "Salary should not be set.");
    }

    private JButton findApplyButton(JPanel contentPanel) {
        for (Component component : contentPanel.getComponents()) {
            if (component instanceof JPanel) {
                for (Component subComponent : ((JPanel) component).getComponents()) {
                    if (subComponent instanceof JButton) {
                        JButton button = (JButton) subComponent;
                        if ("Apply".equals(button.getText())) {
                            return button;
                        }
                    }
                }
            }
        }
        return null;
    }
}
*/
