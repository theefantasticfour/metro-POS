package Views.CashierView;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Map;

public class CashierGenerateSalePanel extends JPanel {
    ActionListener actionListener;
    public CashierGenerateSalePanel(ActionListener Listener) {
        this.actionListener = Listener;

    }

    public Map<String, Integer> getCartDetails() {
        // whatever the cart detials are return them.
        return null;
    }
}
