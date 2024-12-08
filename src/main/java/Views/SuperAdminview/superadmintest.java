/*
class SuperAdminViewTest {

    private SuperAdminView view;
    private SuperAdminController mockController;
    private ActionListener mockListener;
    private JPanel contentPanel;

    @BeforeEach
    void setUp() {
        mockController = mock(SuperAdminController.class);
        mockListener = mock(ActionListener.class);
        view = new SuperAdminView(mockListener, mockController);
        contentPanel = view.getContentPanel();
    }

    @Test
    void testInitialization() {
        assertNotNull(view, "View should be initialized.");
    }

    @Test
    void testLeftPanelMenuItems() {
        Component[] components = view.getComponents();
        boolean leftPanelExists = Arrays.stream(components)
                .anyMatch(c -> c instanceof JPanel && ((JPanel) c).getLayout() instanceof BorderLayout);
        assertTrue(leftPanelExists, "Left panel with menu items should be initialized.");
    }

    @Test
    void testOpenCreateBranchForm() {
        view.openCreateBranchForm();
        assertNotNull(contentPanel.getComponent(0), "Create Branch form should be displayed.");
    }

    @Test
    void testOpenCreateBranchManagerForm() {
        view.openCreateBranchManagerForm();
        assertNotNull(contentPanel.getComponent(0), "Create Branch Manager form should be displayed.");
    }

    @Test
    void testOpenViewUpdateDeleteForm() {
        view.openViewUpdateDeleteForm();
        assertNotNull(contentPanel.getComponent(0), "View/Update/Delete form should be displayed.");
    }

    @Test
    void testOpenReportForm() {
        view.openReportForm();
        assertNotNull(contentPanel.getComponent(0), "Report form should be displayed.");
    }

    @Test
    void testGetBranchIdToUpdate() {
        int mockBranchId = 101;
        SuperAdminViewUpdateDeleteBranchespanel mockViewUpdateDelete = mock(SuperAdminViewUpdateDeleteBranchespanel.class);
        when(mockViewUpdateDelete.getBranchIdtoUpdate()).thenReturn(mockBranchId);
        view.viewUpdateDelete = mockViewUpdateDelete;

        assertEquals(mockBranchId, view.getBranchIdToUpdate(), "Branch ID to update should match.");
    }

    @Test
    void testGetManagerDetails() {
        String mockManagerName = "John Doe";
        String mockEmail = "john.doe@example.com";
        float mockSalary = 5000.0f;
        int mockManagerId = 123;

        SuperAdminAddBranchManagerpanel mockCreateBranchManager = mock(SuperAdminAddBranchManagerpanel.class);
        when(mockCreateBranchManager.getmanagerName()).thenReturn(mockManagerName);
        when(mockCreateBranchManager.getEmail()).thenReturn(mockEmail);
        when(mockCreateBranchManager.getSalary()).thenReturn(String.valueOf(mockSalary));
        when(mockCreateBranchManager.getmanagerId()).thenReturn(String.valueOf(mockManagerId));
        view.createBranchManager = mockCreateBranchManager;

        assertEquals(mockManagerName, view.getManagerName(), "Manager name should match.");
        assertEquals(mockEmail, view.getManagerEmail(), "Manager email should match.");
        assertEquals(mockSalary, view.getManagerSalary(), "Manager salary should match.");
        assertEquals(mockManagerId, view.getManagerId(), "Manager ID should match.");
    }

    @Test
    void testGetBranchDetailsToRegister() {
        String mockCity = "New York";
        String mockAddress = "123 Main St";
        String mockPhone = "123-456-7890";
        String mockNoOfEmployees = "10";
        String mockStatus = "Open";
        int mockBranchId = 1;

        SuperAdminAddBranchpanel mockCreateBranch = mock(SuperAdminAddBranchpanel.class);
        when(mockCreateBranch.getCity()).thenReturn(mockCity);
        when(mockCreateBranch.getAddress()).thenReturn(mockAddress);
        when(mockCreateBranch.getPhoneNumber()).thenReturn(mockPhone);
        when(mockCreateBranch.getNumberOfEmployees()).thenReturn(mockNoOfEmployees);
        when(mockCreateBranch.getStatus()).thenReturn(mockStatus);
        when(mockCreateBranch.getBranchId()).thenReturn(String.valueOf(mockBranchId));
        view.createBranch = mockCreateBranch;

        assertEquals(mockCity, view.getCity(), "Branch city should match.");
        assertEquals(mockAddress, view.getAddress(), "Branch address should match.");
        assertEquals(mockPhone, view.getPhoneNo(), "Branch phone number should match.");
        assertEquals(Integer.parseInt(mockNoOfEmployees), view.getNoOfEmployees(), "Number of employees should match.");
        assertEquals(Boolean.parseBoolean(mockStatus), view.getStatus(), "Branch status should match.");
        assertEquals(mockBranchId, view.getBranchidtoRegister(), "Branch ID should match.");
    }

    @Test
    void testGetUpdateBranchDetails() {
        String mockCity = "San Francisco";
        String mockAddress = "456 Elm St";
        String mockPhone = "987-654-3210";
        int mockNoOfEmployees = 15;
        boolean mockStatus = true;

        SuperAdminViewUpdateDeleteBranchespanel mockViewUpdateDelete = mock(SuperAdminViewUpdateDeleteBranchespanel.class);
        when(mockViewUpdateDelete.getCityToUpdate()).thenReturn(mockCity);
        when(mockViewUpdateDelete.getAdressToUpdate()).thenReturn(mockAddress);
        when(mockViewUpdateDelete.getPhonenoToUpdate()).thenReturn(mockPhone);
        when(mockViewUpdateDelete.getNoofEmployeesToUpdate()).thenReturn(mockNoOfEmployees);
        when(mockViewUpdateDelete.getStatusToUpdate()).thenReturn(mockStatus);
        view.viewUpdateDelete = mockViewUpdateDelete;

        assertEquals(mockCity, view.getCityToUpdate(), "Updated branch city should match.");
        assertEquals(mockAddress, view.getAdressToUpdate(), "Updated branch address should match.");
        assertEquals(mockPhone, view.getPhonenoToUpdate(), "Updated branch phone number should match.");
        assertEquals(mockNoOfEmployees, view.getNoofEmployeesToUpdate(), "Updated number of employees should match.");
        assertEquals(mockStatus, view.getStatusToUpdate(), "Updated branch status should match.");
    }
}
*/
