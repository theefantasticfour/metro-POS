package Views.SideBarAndHeader;

public class MenuPanel {
    private String label;
    private String iconPath;

    public MenuPanel(String label, String iconPath) {
        this.label = label;
        this.iconPath = iconPath;
    }

    public String getLabel() {
        return label;
    }

    public String getIconPath() {
        return iconPath;
    }

}
