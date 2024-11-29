import javax.swing.*;
import java.awt.*;

public class MarketScreen extends Screen {
    public MarketScreen() {
        JLabel title = new JLabel("Market");
        setH1(title);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create panels for each tab
        JPanel foodPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        JPanel giftPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        JPanel medsPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        JTabbedPane bottomPanel = new JTabbedPane();

        // Create buttons
        JButton foodItem1 = createLargeButton("Canned Beans - 2 Coins");
        JButton foodItem2 = createLargeButton("Energy Bars - 2 Coins");
        JButton foodItem3 = createLargeButton("Purified Water - 2 Coins");
        JButton foodItem4 = createLargeButton("Mystery Meat - 2 Coins");

        JButton giftItem1 = createLargeButton("Rusty Sword - 2 Coins");
        JButton giftItem2 = createLargeButton("Doom Hammer - 2 Coins");
        JButton giftItem3 = createLargeButton("Glowstick Grenade - 2 Coins");
        JButton giftItem4 = createLargeButton("Junkyard Shotgun - 2 Coins");

        JButton medsItem1 = createLargeButton("First Aid Kit - 2 Coins");
        JButton medsItem2 = createLargeButton("Herbal Remedies - 2 Coins");
        JButton medsItem3 = createLargeButton("Radiation Pills - 2 Coins");
        JButton medsItem4 = createLargeButton("Universal Syrum - 2 Coins");

        JButton backToGame = new JButton("Back");

        // Add functionality to Food buttons
        foodItem1.addActionListener(e -> attemptPurchase("Food", "Canned Beans"));
        foodItem2.addActionListener(e -> attemptPurchase("Food", "Energy Bars"));
        foodItem3.addActionListener(e -> attemptPurchase("Food", "Purified Water"));
        foodItem4.addActionListener(e -> attemptPurchase("Food", "Mystery Meat"));

        // Add functionality to Gift buttons
        giftItem1.addActionListener(e -> attemptPurchase("Gifts", "Rusty Sword"));
        giftItem2.addActionListener(e -> attemptPurchase("Gifts", "Doom Hammer"));
        giftItem3.addActionListener(e -> attemptPurchase("Gifts", "Glowstick Grenade"));
        giftItem4.addActionListener(e -> attemptPurchase("Gifts", "Junkyard Shotgun"));

        // Add functionality to Meds buttons
        medsItem1.addActionListener(e -> attemptPurchase("Meds", "First Aid Kit"));
        medsItem2.addActionListener(e -> attemptPurchase("Meds", "Herbal Remedies"));
        medsItem3.addActionListener(e -> attemptPurchase("Meds", "Radiation Pills"));
        medsItem4.addActionListener(e -> attemptPurchase("Meds", "Universal Syrum"));

        // Add functionality to Back button
        backToGame.addActionListener(e -> ScreenManager.swapView("5"));

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

    private JButton createLargeButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Serif", Font.BOLD, 24)); // Adjust font size
        button.setPreferredSize(new Dimension(200, 100)); // Adjust button size
        return button;
    }

    private void attemptPurchase(String category, String item) {
        final int cost = 2; // Each item costs 2 coins
        if (Coins.spendCoins(cost)) {
            if (GameState.addItem(category, item)) {
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
