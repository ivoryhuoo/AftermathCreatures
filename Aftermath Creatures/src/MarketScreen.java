import java.awt.*;
import javax.swing.*;

public class MarketScreen extends Screen {

    private static final String IMAGE_PATH = "items/"; // Base path for item images

    public MarketScreen() {
        JLabel title = new JLabel("Market");
        setH1(title);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create panels for each tab
        JPanel foodPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        JPanel giftPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        JPanel medsPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        JTabbedPane bottomPanel = new JTabbedPane();

        // Create buttons with icons
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

        // Add buttons to Food panel
        foodPanel.add(foodItem1);
        foodPanel.add(foodItem2);
        foodPanel.add(foodItem3);
        foodPanel.add(foodItem4);

        // Add buttons to Gift panel
        giftPanel.add(giftItem1);
        giftPanel.add(giftItem2);
        giftPanel.add(giftItem3);
        giftPanel.add(giftItem4);

        // Add buttons to Meds panel
        medsPanel.add(medsItem1);
        medsPanel.add(medsItem2);
        medsPanel.add(medsItem3);
        medsPanel.add(medsItem4);

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
