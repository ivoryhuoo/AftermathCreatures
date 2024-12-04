import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Harshi
 * Manages the player's coin balance in the game.
 * Provides functionality for adding, spending, and automatically earning coins over time.
 */
public class Coins {
    private static int coins = 10; // Initial coin balance
    private static Timer coinTimer; // Timer for periodic coin earning

    /**
     * Retrieves the current coin balance.
     *
     * @return The current number of coins the player has.
     */
    public static int getCoins() {
        return coins;
    }

        public static void setCoins(int newCoins) {
        coins = newCoins;
    }

    /**
     * Adds a specified amount of coins to the player's balance.
     *
     * @param amount The amount of coins to add.
     */
    public static void addCoins(int amount) {
        coins += amount;
    }

    /**
     * Attempts to deduct a specified amount of coins from the player's balance.
     *
     * @param amount The amount of coins to deduct.
     * @return {@code true} if the player had enough coins to make the deduction,
     *         {@code false} otherwise.
     */
    public static boolean spendCoins(int amount) {
        if (coins >= amount) {
            coins -= amount; // Deduct coins
            return true; // Successful transaction
        } else {
            return false; // Not enough coins
        }
    }

    /**
     * Starts a timer that periodically adds coins to the player's balance.
     * The player earns 10 coins every minute as a reward for playing.
     * A pop-up message is displayed to notify the player.
     */
    public static void startCoinTimer() {
        if (coinTimer == null) { // Ensure the timer is only started once
            coinTimer = new Timer();
            coinTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    addCoins(10); // Earn 10 coins
                    // Notify the player of the reward
                    JOptionPane.showMessageDialog(null, 
                        "You have received 10 coins for playing. Keep it up!", 
                        "Coins Earned", 
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }, 60000, 60000); // Initial delay of 1 minute, repeat every 1 minute
        }
    }
    
    /**
     * Resets the coin balance to a default value.
     */
    public static void resetCoins() {
        coins = 0; // Reset to zero or any default value
    }

}
