import java.awt.*;
import javax.swing.*;

/**
 * @author Harshi
 * @see Screen
 * Represents the Marketplace screen where players can purchase items to use in the game.
 * This screen allows the player to buy food, gifts, and medicines for their pet.
 * Items are organized into tabs, and each item has a name, cost, and associated icon.
 */
public class MarketScreen extends Screen {

    private static final String IMAGE_PATH = "items/"; // Base path for item images

    /**
     * Constructor for the MarketScreen.
     * Sets up the UI components, including tabs for Food, Gifts, and Meds,
     * as well as buttons for each item.
     */
    public MarketScreen() {
        // Title of the screen
        JLabel title = new JLabel("Market");
        setH1(title);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create panels for each category tab
        JPanel foodPanel = new JPanel(new GridLayout(2, 2, 20, 20)); // Food items
        JPanel giftPanel = new JPanel(new GridLayout(2, 2, 20, 20)); // Gift items
        JPanel medsPanel = new JPanel(new GridLayout(2, 2, 20, 20)); // Medicine items
        JTabbedPane bottomPanel = new JTabbedPane();

        // Create buttons for each item with icons
        JButton foodItem1 = createButtonWithIcon("Canned Beans - 2 Coins", "cannedBeans.png");
        JButton foodItem2 = createButtonWithIcon("Energy Bars - 3 Coins", "energyBars.png");
        JButton foodItem3 = createButtonWithIcon("Purified Water - 1 Coin", "purifiedWater.png");
        JButton foodItem4 = createButtonWithIcon("Mystery Meat - 4 Coins", "mysteryMeat.png");

        JButton giftItem1 = createButtonWithIcon("Rusty Sword - 2 Coins", "rustySword.png");
        JButton giftItem2 = createButtonWithIcon("Doom Hammer - 3 Coins", "doomHammer.png");
        JButton giftItem3 = createButtonWithIcon("Glowstick Grenade - 1 Coin", "glowstickGrenade.png");
        JButton giftItem4 = createButtonWithIcon("Junkyard Shotgun - 4 Coins", "junkyardShotgun.png");

        JButton medsItem1 = createButtonWithIcon("First Aid Kit - 2 Coins", "firstAidKit.png");
        JButton medsItem2 = createButtonWithIcon("Herbal Remedies - 3 Coins", "herbalRemedies.png");
        JButton medsItem3 = createButtonWithIcon("Radiation Pills - 1 Coin", "radiationPills.png");
        JButton medsItem4 = createButtonWithIcon("Universal Syrum - 4 Coins", "universalSyrum.png");

        // Back button to return to the previous screen
        JButton backToGame = new JButton("Back");
        backToGame.addActionListener(e -> ScreenManager.swapView("5")); // Replace "5" with the correct screen identifier

        // Add functionality to Food buttons
        foodItem1.addActionListener(e -> attemptPurchase("Food", "Canned Beans", 3, 2));
        foodItem2.addActionListener(e -> attemptPurchase("Food", "Energy Bars", 4, 3));
        foodItem3.addActionListener(e -> attemptPurchase("Food", "Purified Water", 2, 1));
        foodItem4.addActionListener(e -> attemptPurchase("Food", "Mystery Meat", 5, 4));

        // Add functionality to Gift buttons
        giftItem1.addActionListener(e -> attemptPurchase("Gifts", "Rusty Sword", 3, 2));
        giftItem2.addActionListener(e -> attemptPurchase("Gifts", "Doom Hammer", 4, 3));
        giftItem3.addActionListener(e -> attemptPurchase("Gifts", "Glowstick Grenade", 2, 1));
        giftItem4.addActionListener(e -> attemptPurchase("Gifts", "Junkyard Shotgun", 5, 4));

        // Add functionality to Meds buttons
        medsItem1.addActionListener(e -> attemptPurchase("Meds", "First Aid Kit", 3, 2));
        medsItem2.addActionListener(e -> attemptPurchase("Meds", "Herbal Remedies", 4, 3));
        medsItem3.addActionListener(e -> attemptPurchase("Meds", "Radiation Pills", 2, 1));
        medsItem4.addActionListener(e -> attemptPurchase("Meds", "Universal Syrum", 5, 4));

        // Add buttons to category panels
        foodPanel.add(foodItem1);
        foodPanel.add(foodItem2);
        foodPanel.add(foodItem3);
        foodPanel.add(foodItem4);

        giftPanel.add(giftItem1);
        giftPanel.add(giftItem2);
        giftPanel.add(giftItem3);
        giftPanel.add(giftItem4);

        medsPanel.add(medsItem1);
        medsPanel.add(medsItem2);
        medsPanel.add(medsItem3);
        medsPanel.add(medsItem4);

        // Add category panels to tabs
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
     * Creates a JButton with an image icon and text.
     *
     * @param text      The button text, including the item name and cost.
     * @param imageName The name of the image file for the item (e.g., "cannedBeans.png").
     * @return A JButton configured with the image icon and text.
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

    /**
     * Attempts to purchase an item from the market.
     * Deducts the cost from the player's coins and adds the item to the inventory if successful.
     *
     * @param category      The category of the item (e.g., "Food", "Gifts", "Meds").
     * @param item          The name of the item.
     * @param statIncrement The stat increment provided by the item.
     * @param cost          The cost of the item in coins.
     */
    private void attemptPurchase(String category, String item, int statIncrement, int cost) {
        if (Coins.spendCoins(cost)) {
            if (GameState.addItem(category, item + "-" + statIncrement)) {
                JOptionPane.showMessageDialog(null, "You purchased " + item + " for " + cost + " coins.", "Purchase Successful", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, category + " inventory is full! Cannot add more items.", "Inventory Full", JOptionPane.WARNING_MESSAGE);
                Coins.addCoins(cost); // Refund coins if inventory is full
            }
        } else {
            JOptionPane.showMessageDialog(null, "Not enough coins to purchase " + item + "!", "Purchase Failed", JOptionPane.WARNING_MESSAGE);
        }
    }
}
