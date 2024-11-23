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
        JButton foodItem1 = createLargeButton("A");
        JButton foodItem2 = createLargeButton("B");
        JButton foodItem3 = createLargeButton("C");
        JButton foodItem4 = createLargeButton("D");

        JButton giftItem1 = createLargeButton("A");
        JButton giftItem2 = createLargeButton("B");
        JButton giftItem3 = createLargeButton("C");
        JButton giftItem4 = createLargeButton("D");

        JButton medsItem1 = createLargeButton("A");
        JButton medsItem2 = createLargeButton("B");
        JButton medsItem3 = createLargeButton("C");
        JButton medsItem4 = createLargeButton("D");

        JButton backToGame = new JButton("Back");

        // Add functionality to Food buttons
        foodItem1.addActionListener(e -> GameState.addItem("Food", "A"));
        foodItem2.addActionListener(e -> GameState.addItem("Food", "B"));
        foodItem3.addActionListener(e -> GameState.addItem("Food", "C"));
        foodItem4.addActionListener(e -> GameState.addItem("Food", "D"));

        // Add functionality to Gift buttons
        giftItem1.addActionListener(e -> GameState.addItem("Gifts", "A"));
        giftItem2.addActionListener(e -> GameState.addItem("Gifts", "B"));
        giftItem3.addActionListener(e -> GameState.addItem("Gifts", "C"));
        giftItem4.addActionListener(e -> GameState.addItem("Gifts", "D"));

        // Add functionality to Meds buttons
        medsItem1.addActionListener(e -> GameState.addItem("Meds", "A"));
        medsItem2.addActionListener(e -> GameState.addItem("Meds", "B"));
        medsItem3.addActionListener(e -> GameState.addItem("Meds", "C"));
        medsItem4.addActionListener(e -> GameState.addItem("Meds", "D"));

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
}
