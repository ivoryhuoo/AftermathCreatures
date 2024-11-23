import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MarketScreen extends Screen {
    public MarketScreen() {
        JLabel title = new JLabel("Market");
        setH1(title);
        title.setAlignmentX((float) 0.5);

        JPanel foodPanel = new JPanel(new GridLayout(3, 3));
        JPanel giftPanel = new JPanel(new GridLayout(3, 3));
        JPanel medsPanel = new JPanel(new GridLayout(3, 3));
        JTabbedPane bottomPanel = new JTabbedPane();

        JButton item1 = new JButton("a");
        JButton item2 = new JButton("b");
        JButton item3 = new JButton("c");
        JButton item4 = new JButton("d");
        JButton backToGame = new JButton("Back");

        // Add functionality to buttons
        item1.addActionListener(e -> GameState.addItem("Food", "a"));
        item2.addActionListener(e -> GameState.addItem("Gifts", "b"));
        item3.addActionListener(e -> GameState.addItem("Meds", "c"));
        item4.addActionListener(e -> GameState.addItem("Food", "d"));
        backToGame.addActionListener(e -> ScreenManager.swapView("5"));

        // Add elements to panels
        foodPanel.add(item1);
        giftPanel.add(item2);
        medsPanel.add(item3);
        foodPanel.add(item4);

        // Add subpanels to main panel
        this.panel.add(title);
        this.panel.add(bottomPanel);
        bottomPanel.addTab("Food", foodPanel);
        bottomPanel.addTab("Gift", giftPanel);
        bottomPanel.addTab("Meds", medsPanel);
        this.panel.add(backToGame);
    }
}
