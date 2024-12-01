import javax.swing.*;
import java.awt.*;

/**
 * Screen showing player's items
 * @see Screen
 */
public class InventoryScreen extends Screen {
    private static final String IMAGE_PATH = "items/"; // Base path for item images

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
            JButton button = createButtonWithIcon(formatItemDisplay(item, "Fullness"), getImageFileName(item));
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
            JButton button = createButtonWithIcon(formatItemDisplay(item, "Happiness"), getImageFileName(item));
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
            JButton button = createButtonWithIcon(formatItemDisplay(item, "Health"), getImageFileName(item));
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

    /**
     * Gets the image file name for an item.
     * @param item The item in "Name-StatIncrement" format.
     * @return The corresponding image file name.
     */
    private String getImageFileName(String item) {
        String[] parts = item.split("-");
        String itemName = parts[0];
        return itemName.toLowerCase().replace(" ", "") + ".png";
    }

    /**
     * Creates a JButton with an image icon and text.
     * @param text The button text.
     * @param imageName The name of the image file (e.g., "cannedBeans.png").
     * @return A JButton with the image icon and text.
     */
    private JButton createButtonWithIcon(String text, String imageName) {
        JButton button = new JButton(text);
        button.setFont(new Font("Serif", Font.BOLD, 18));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);

        ImageIcon icon = new ImageIcon(IMAGE_PATH + imageName);
        Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledImage));

        return button;
    }
}
