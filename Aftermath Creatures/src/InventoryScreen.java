import javax.swing.*;
import java.awt.*;
import java.util.List;

public class InventoryScreen extends Screen {
    private JPanel foodPanel;
    private JPanel giftPanel;
    private JPanel medsPanel;
    private JTabbedPane bottomPanel;

    public InventoryScreen() {
        JLabel title = new JLabel("Inventory");
        setH1(title);
        title.setAlignmentX((float) 0.5);

        foodPanel = new JPanel(new GridLayout(3, 3));
        giftPanel = new JPanel(new GridLayout(3, 3));
        medsPanel = new JPanel(new GridLayout(3, 3));
        bottomPanel = new JTabbedPane();

        JButton backToGame = new JButton("Back");

        // Add functionality to buttons
        backToGame.addActionListener(e -> ScreenManager.swapView("5"));

        // Add subpanels to main panel
        this.panel.add(title);
        this.panel.add(bottomPanel);
        bottomPanel.addTab("Food", foodPanel);
        bottomPanel.addTab("Gift", giftPanel);
        bottomPanel.addTab("Meds", medsPanel);
        this.panel.add(backToGame);
    }

    public void refreshPanels() {
        // Update Food Panel
        foodPanel.removeAll();
        List<String> foodItems = GameState.getItems("Food");
        for (String item : foodItems) {
            foodPanel.add(new JButton(item));
        }

        // Update Gift Panel
        giftPanel.removeAll();
        List<String> giftItems = GameState.getItems("Gifts");
        for (String item : giftItems) {
            giftPanel.add(new JButton(item));
        }

        // Update Meds Panel
        medsPanel.removeAll();
        List<String> medsItems = GameState.getItems("Meds");
        for (String item : medsItems) {
            medsPanel.add(new JButton(item));
        }

        // Repaint and revalidate panels
        foodPanel.revalidate();
        foodPanel.repaint();
        giftPanel.revalidate();
        giftPanel.repaint();
        medsPanel.revalidate();
        medsPanel.repaint();
    }
}
