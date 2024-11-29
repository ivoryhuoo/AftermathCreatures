
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Coins {
    private static int coins = 10; // Starting with 10 coins
    private static Timer coinTimer;
    
    public static int getCoins() {
        return coins;
    }

    public static void addCoins(int amount) {
        coins += amount;
    }

    public static boolean spendCoins(int amount) {
        if (coins >= amount) {
            coins -= amount;
            return true; // Successful purchase
        } else {
            return false; // Not enough coins
        }
    }

    public static void startCoinTimer() {
        if (coinTimer == null) {
            coinTimer = new Timer();
            coinTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    addCoins(10);
                    JOptionPane.showMessageDialog(null, "You have received 10 coins for playing. Keep it up!", "Coins Earned", JOptionPane.INFORMATION_MESSAGE);
                }
            }, 60000, 60000); // Delay 1 minute, repeat every 1 minute
        }
    }
}
