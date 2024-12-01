import javax.swing.*;
import java.awt.*;

/**
 * @author Harshi
 * @see Screen
 * Represents the Inventory screen where players can view and use items they have collected.
 * The screen organizes items into categories: Food, Gifts, and Meds.
 * Each category is displayed in a tab with item details and associated icons.
 */
public class InventoryScreen extends Screen {

    private static final String IMAGE_PATH = "items/"; // Base path for item images

    private JPanel foodPanel; // Panel for food items
    private JPanel giftPanel; // Panel for gift items
    private JPanel medsPanel; // Panel for medicine items
    private JTabbedPane bottomPanel; // Tabbed pane to organize categories

    /**
     * Constructor for the InventoryScreen.
     * Sets up the UI components, including tabs for Food, Gifts, and Meds,
     * as well as a "Back" button to navigate to the previous screen.
     */
    public InventoryScreen() {
        // Screen title
        JLabel title = new JLabel("Inventory");
        setH1(title);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create panels for each tab
        foodPanel = new JPanel(new GridLayout(2, 2, 20, 20)); // Grid layout for food items
        giftPanel = new JPanel(new GridLayout(2, 2, 20, 20)); // Grid layout for gift items
        medsPanel = new JPanel(new GridLayout(2, 2, 20, 20)); // Grid layout for meds items
        bottomPanel = new JTabbedPane();

        // Back button to return to the previous screen
        JButton backToGame = new JButton("Back");
        backToGame.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add functionality to the back button
        backToGame.addActionListener(e -> ScreenManager.swapView("5")); // Replace "5" with the correct screen identifier

        // Add panels to tabs
        bottomPanel.addTab("Food", foodPanel);
        bottomPanel.addTab("Gift", giftPanel);
        bottomPanel.addTab("Meds", medsPanel);

        // Add components to the main panel
        this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
        this.panel.add(title);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing
        this.panel.add(bottomPanel);
        this.panel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing
        this.panel.add(backToGame);
    }

    /**
     * Refreshes the panels for Food, Gifts, and Meds by updating their contents
     * based on the current inventory.
     */
    public void refreshPanels() {
        // Refresh the food panel
        foodPanel.removeAll();
        for (String item : GameState.getItems("Food")) {
            JButton button = createButtonWithIcon(formatItemDisplay(item, "Fullness"), getImageFileName(item));
            button.addActionListener(e -> {
                String[] parts = item.split("-"); // Split name and stat increment
                String itemName = parts[0];
                int statIncrement = Integer.parseInt(parts[1]);

                GameState.getItems("Food").remove(item); // Remove item from inventory
                main.pet.feed(statIncrement); // Apply stat increment
                JOptionPane.showMessageDialog(null, itemName + " fed to pet. Fullness increased by " + statIncrement, "Item Used", JOptionPane.INFORMATION_MESSAGE);
                refreshPanels(); // Refresh the panel to reflect the changes
            });
            foodPanel.add(button);
        }

        // Refresh the gift panel
        giftPanel.removeAll();
        for (String item : GameState.getItems("Gifts")) {
            JButton button = createButtonWithIcon(formatItemDisplay(item, "Happiness"), getImageFileName(item));
            button.addActionListener(e -> {
                String[] parts = item.split("-");
                String itemName = parts[0];
                int statIncrement = Integer.parseInt(parts[1]);

                GameState.getItems("Gifts").remove(item); // Remove item from inventory
                main.pet.giveGift(statIncrement); // Apply stat increment
                JOptionPane.showMessageDialog(null, itemName + " given to pet. Happiness increased by " + statIncrement, "Item Used", JOptionPane.INFORMATION_MESSAGE);
                refreshPanels(); // Refresh the panel to reflect the changes
            });
            giftPanel.add(button);
        }

        // Refresh the meds panel
        medsPanel.removeAll();
        for (String item : GameState.getItems("Meds")) {
            JButton button = createButtonWithIcon(formatItemDisplay(item, "Health"), getImageFileName(item));
            button.addActionListener(e -> {
                String[] parts = item.split("-");
                String itemName = parts[0];
                int statIncrement = Integer.parseInt(parts[1]);

                GameState.getItems("Meds").remove(item); // Remove item from inventory
                main.pet.useMedicine(statIncrement); // Apply stat increment
                JOptionPane.showMessageDialog(null, itemName + " used on pet. Health increased by " + statIncrement, "Item Used", JOptionPane.INFORMATION_MESSAGE);
                refreshPanels(); // Refresh the panel to reflect the changes
            });
            medsPanel.add(button);
        }

        // Repaint and revalidate panels
        foodPanel.revalidate();
        foodPanel.repaint();
        giftPanel.revalidate();
        giftPanel.repaint();
        medsPanel.revalidate();
        medsPanel.repaint();
    }

    /**
     * Formats the display for items in the inventory.
     *
     * @param item      The item in "Name-StatIncrement" format.
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
     *
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
     *
     * @param text      The button text.
     * @param imageName The name of the image file (e.g., "cannedBeans.png").
     * @return A JButton with the image icon and text.
     */
    private JButton createButtonWithIcon(String text, String imageName) {
        JButton button = new JButton(text);
        button.setFont(new Font("Serif", Font.BOLD, 18));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);

        // Load the image and scale it
        ImageIcon icon = new ImageIcon(IMAGE_PATH + imageName);
        Image scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledImage));

        return button;
    }
}
