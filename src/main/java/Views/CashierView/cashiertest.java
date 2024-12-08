/*
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;

class CashierPanelViewTest {
    private CashierPanelView cashierPanelView;
    private MockCashierController controller;

    @BeforeEach
    void setUp() {
        controller = new MockCashierController();
        cashierPanelView = new CashierPanelView(e -> {}, controller);
    }

    @Test
    void testInitialDisplayIsChangePasswordForm() {
        JPanel contentPanel = cashierPanelView.getContentPanel();
        assertTrue(contentPanel.getComponent(0) instanceof CustomChangePassword, "Initial view should display Change Password form.");
    }

    @Test
    void testOpenGenerateSaleForm() {
        controller.setPasswordChanged(true); // Simulate password change
        cashierPanelView.openGenerateSaleForm();
        JPanel contentPanel = cashierPanelView.getContentPanel();
        assertTrue(contentPanel.getComponent(0) instanceof CashierGenerateSalePanel, "Generate Sale form should be displayed.");
    }

    @Test
    void testGetSaleDetails() {
        controller.setPasswordChanged(true);
        cashierPanelView.openGenerateSaleForm();
        assertNotNull(cashierPanelView.getSaleDetails(), "Sale details should not be null.");
    }

    @Test
    void testPreventAccessWithoutPasswordChange() {
        controller.setPasswordChanged(false); // Password not changed
        cashierPanelView.openGenerateSaleForm();
        assertTrue(cashierPanelView.getContentPanel().getComponent(0) instanceof CustomChangePassword, "Access should be restricted to the Change Password form.");
    }
}
*/
