/*
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;

class DataEntryOperatorViewTest {
    private DataEntryOperatorView view;
    private MockDataEntryOperatorController controller;

    @BeforeEach
    void setUp() {
        controller = new MockDataEntryOperatorController();
        view = new DataEntryOperatorView(e -> {}, controller);
    }

    @Test
    void testChangePasswordFormDisplayedInitially() {
        JPanel contentPanel = view.getContentPanel();
        assertTrue(contentPanel.getComponent(0) instanceof CustomChangePassword, "Change password form should be displayed initially.");
    }

    @Test
    void testAddVendorFormAccessibility() {
        controller.setPasswordChanged(true); // Simulate password being changed
        view.openAddNewVendorForm();
        JPanel contentPanel = view.getContentPanel();
        assertTrue(contentPanel.getComponent(0) instanceof DataEntryOperatorAddVendor, "Add Vendor form should be accessible.");
    }

    @Test
    void testGetVendorName() {
        view.openAddNewVendorForm();
        assertNotNull(view.getVendorName(), "Vendor name should not be null after adding a vendor.");
    }
}
*/
