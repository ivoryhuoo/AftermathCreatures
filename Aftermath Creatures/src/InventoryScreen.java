import javax.swing.*;
import java.awt.*;

public class InventoryScreen extends Screen {
    private JPanel foodPanel;
    private JPanel giftPanel;
    private JPanel medsPanel;
    private JTabbedPane bottomPanel;

    public InventoryScreen() {
        JLabel title = new JLabel("Inventory");
        setH1(title);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create panels for tabs
        foodPanel = new JPanel(new GridLayout(2, 2, 20, 20)); // Add spacing
        giftPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        medsPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        bottomPanel = new JTabbedPane();

        JButton backToGame = new JButton("Back");
        backToGame.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add functionality to buttons
        backToGame.addActionListener(e -> ScreenManager.swapView("5"));

        // Add panels to tabs
        bottomPanel.addTab("Food", foodPanel);
        bottomPanel.addTab("Gift", giftPanel);
        bottomPanel.addTab("Meds", medsPanel);

        // Add components to main panel
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        this.panel.add(title);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing
        this.panel.add(bottomPanel);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing
        this.panel.add(backToGame);
    }

    public void refreshPanels() {
        // Update Food Panel
        foodPanel.removeAll();
        for (String item : GameState.getItems("Food")) {
            foodPanel.add(createLargeButton(item));
        }

        // Update Gift Panel
        giftPanel.removeAll();
        for (String item : GameState.getItems("Gifts")) {
            giftPanel.add(createLargeButton(item));
        }

        // Update Meds Panel
        medsPanel.removeAll();
        for (String item : GameState.getItems("Meds")) {
            medsPanel.add(createLargeButton(item));
        }

        // Repaint and revalidate panels
        foodPanel.revalidate();
        foodPanel.repaint();
        giftPanel.revalidate();
        giftPanel.repaint();
        medsPanel.revalidate();
        medsPanel.repaint();
    }

    private JButton createLargeButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Serif", Font.BOLD, 36)); // Larger font
        button.setPreferredSize(new Dimension(150, 100)); // Larger button size
        return button;
    }
}
