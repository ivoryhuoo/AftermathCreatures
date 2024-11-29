import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class MainGameScreen extends Screen {
    private JLabel money; // Reference to the money label
    private Timer updateTimer; // Timer for updating the coin display

    public MainGameScreen() {
        // Set layout, setup subpanels
        this.panel.setLayout(new BorderLayout());
        JPanel header = new JPanel();
        header.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(header, BorderLayout.NORTH);
        JPanel sidebar = new JPanel();
        sidebar.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        setVertical(sidebar);
        panel.add(sidebar, BorderLayout.WEST);
        JPanel footer = new JPanel();
        footer.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panel.add(footer, BorderLayout.SOUTH);
        // Pet goes in BorderLayout.CENTER

        // Create elements
        JLabel curTime = new JLabel("17:25");
        JLabel petName = new JLabel("Pet Name");
        money = new JLabel("Coins: " + Coins.getCoins()); // Display initial coin count
        JLabel score = new JLabel("Score: 0");
        setH2(curTime);
        setH2(petName);
        setH2(money);
        setH2(score);
        JLabel health = new JLabel("Health: 100/100");
        JLabel sleep = new JLabel("Sleep: 100/100");
        JLabel fullness = new JLabel("Fullness: 100/100");
        JLabel happiness = new JLabel("Happiness: 100/100");

        // Footer icons go here
        JButton rest = new JButton("Rest"); // Placeholders
        JButton inventory = new JButton("Inventory");
        JButton doctor = new JButton("Doctor");
        JButton market = new JButton("Market");
        JButton minigames = new JButton("Minigames");
        JButton menu = new JButton("Settings Menu");

        // Add functionality to buttons
        rest.addActionListener(e -> {
            // Placeholder action
        });
        inventory.addActionListener(e -> ScreenManager.swapView("6"));
        doctor.addActionListener(e -> {
            // Placeholder action
        });
        market.addActionListener(e -> ScreenManager.swapView("7"));
        minigames.addActionListener(e -> {
            // Placeholder action
        });
        menu.addActionListener(e -> ScreenManager.swapView("3"));

        // Add elements to subpanels
        header.add(curTime);
        header.add(petName);
        header.add(money);
        header.add(score);
        sidebar.add(health);
        sidebar.add(sleep);
        sidebar.add(fullness);
        sidebar.add(happiness);
        // Footer icons go here
        footer.add(rest);
        footer.add(inventory);
        footer.add(doctor);
        footer.add(market);
        footer.add(minigames);
        footer.add(menu);

        // Start updating the money label
        startUpdatingCoins();
    }

    private void startUpdatingCoins() {
        updateTimer = new Timer(true); // Daemon thread
        updateTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> money.setText("Coins: " + Coins.getCoins()));
            }
        }, 0, 1000); // Update every second
    }
}
