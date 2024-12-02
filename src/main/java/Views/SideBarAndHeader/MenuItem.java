package Views.SideBarAndHeader;

import javax.swing.*;
import java.awt.*;

public class MenuItem {
    private String label;
    private String iconPath;

    public MenuItem(String label, String iconPath) {
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
