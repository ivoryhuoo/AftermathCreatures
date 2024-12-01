import javax.swing.*;
import java.awt.*;
/**
 * Screen showing player's items
 * @see Screen
 */
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
        foodPanel.removeAll();
        for (String item : GameState.getItems("Food")) {
            JButton button = createLargeButton(formatItemDisplay(item, "Fullness"));
            button.addActionListener(e -> {
                String[] parts = item.split("-"); // Split name and stat increment
                String itemName = parts[0];
                int statIncrement = Integer.parseInt(parts[1]);

                GameState.getItems("Food").remove(item); // Remove from inventory
                main.pet.feed(statIncrement); // Apply stat increment
                JOptionPane.showMessageDialog(null, itemName + " fed to pet. Fullness increased by " + statIncrement, "Item Used", JOptionPane.INFORMATION_MESSAGE);
                refreshPanels();
            });
            foodPanel.add(button);
        }

        giftPanel.removeAll();
        for (String item : GameState.getItems("Gifts")) {
            JButton button = createLargeButton(formatItemDisplay(item, "Happiness"));
            button.addActionListener(e -> {
                String[] parts = item.split("-");
                String itemName = parts[0];
                int statIncrement = Integer.parseInt(parts[1]);

                GameState.getItems("Gifts").remove(item); // Remove from inventory
                main.pet.giveGift(statIncrement); // Apply stat increment
                JOptionPane.showMessageDialog(null, itemName + " given to pet. Happiness increased by " + statIncrement, "Item Used", JOptionPane.INFORMATION_MESSAGE);
                refreshPanels();
            });
            giftPanel.add(button);
        }

        medsPanel.removeAll();
        for (String item : GameState.getItems("Meds")) {
            JButton button = createLargeButton(formatItemDisplay(item, "Health"));
            button.addActionListener(e -> {
                String[] parts = item.split("-");
                String itemName = parts[0];
                int statIncrement = Integer.parseInt(parts[1]);

                GameState.getItems("Meds").remove(item); // Remove from inventory
                main.pet.useMedicine(statIncrement); // Apply stat increment
                JOptionPane.showMessageDialog(null, itemName + " used on pet. Health increased by " + statIncrement, "Item Used", JOptionPane.INFORMATION_MESSAGE);
                refreshPanels();
            });
            medsPanel.add(button);
        }

        foodPanel.revalidate();
        foodPanel.repaint();
        giftPanel.revalidate();
        giftPanel.repaint();
        medsPanel.revalidate();
        medsPanel.repaint();
    }

    /**
     * Formats the display for items in the inventory.
     * @param item The item in "Name-StatIncrement" format.
     * @param attribute The pet attribute the item affects (e.g., Fullness, Happiness, Health).
     * @return The formatted string for display in the inventory.
     */
    private String formatItemDisplay(String item, String attribute) {
        String[] parts = item.split("-");
        String itemName = parts[0];
        String statIncrement = parts[1];
        return itemName + " - " + statIncrement + " " + attribute;
    }



    private JButton createLargeButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Serif", Font.BOLD, 36)); // Larger font
        button.setPreferredSize(new Dimension(150, 100)); // Larger button size
        return button;
    }
}
