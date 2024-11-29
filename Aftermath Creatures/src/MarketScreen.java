import javax.swing.*;
import java.awt.*;

public class MarketScreen extends Screen {
    public MarketScreen() {
        JLabel title = new JLabel("Market");
        setH1(title);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        //Testing
        
        // Create panels for each tab
        JPanel foodPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        JPanel giftPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        JPanel medsPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        JTabbedPane bottomPanel = new JTabbedPane();

        // Create buttons
        JButton foodItem1 = createLargeButton("Canned Beans");
        JButton foodItem2 = createLargeButton("Energy Bars");
        JButton foodItem3 = createLargeButton("Purified Water");
        JButton foodItem4 = createLargeButton("Mystery Meat");

        JButton giftItem1 = createLargeButton("Rusty Sword");
        JButton giftItem2 = createLargeButton("Doom Hammer");
        JButton giftItem3 = createLargeButton("Glowstick Grenade");
        JButton giftItem4 = createLargeButton("Junkyard Shotgun");

        JButton medsItem1 = createLargeButton("First Aid Kit");
        JButton medsItem2 = createLargeButton("Herbal Remedies");
        JButton medsItem3 = createLargeButton("Radiation Pills");
        JButton medsItem4 = createLargeButton("Universal Syrum");

        JButton backToGame = new JButton("Back");

        // Add functionality to Food buttons
        foodItem1.addActionListener(e -> addItemToInventory("Food", "Canned Beans"));
        foodItem2.addActionListener(e -> addItemToInventory("Food", "Energy Bars"));
        foodItem3.addActionListener(e -> addItemToInventory("Food", "Purified Water"));
        foodItem4.addActionListener(e -> addItemToInventory("Food", "Mystery Meat"));

        // Add functionality to Gift buttons
        giftItem1.addActionListener(e -> addItemToInventory("Gifts", "Rusty Sword"));
        giftItem2.addActionListener(e -> addItemToInventory("Gifts", "Doom Hammer"));
        giftItem3.addActionListener(e -> addItemToInventory("Gifts", "Glowstick Grenade"));
        giftItem4.addActionListener(e -> addItemToInventory("Gifts", "Junkyard Shotgun"));

        // Add functionality to Meds buttons
        medsItem1.addActionListener(e -> addItemToInventory("Meds", "First Aid Kit"));
        medsItem2.addActionListener(e -> addItemToInventory("Meds", "Herbal Remedies"));
        medsItem3.addActionListener(e -> addItemToInventory("Meds", "Radiation Pills"));
        medsItem4.addActionListener(e -> addItemToInventory("Meds", "Universal Syrum"));

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
        button.setFont(new Font("Serif", Font.BOLD, 36)); // Larger font
        button.setPreferredSize(new Dimension(150, 100)); // Larger button size
        return button;
    }

    private void addItemToInventory(String category, String item) {
        if (!GameState.addItem(category, item)) {
            JOptionPane.showMessageDialog(null, category + " inventory is full! Cannot add more items.", "Inventory Full", JOptionPane.WARNING_MESSAGE);
        }
    }
}
