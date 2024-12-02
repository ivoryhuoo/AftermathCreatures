import java.awt.Color;
import javax.swing.*;
import java.io.*;
import java.awt.*;

/**
 * UI Save slot panel
 * @author Numan
 */
public class SaveSlot {
    JPanel panel;
    String name;
    int score;
    int money;
    int time;
    Pet currentPet; // Reference to the current pet object, assuming Pet class exists

    // Constructor to initialize the save slot with game data
    public SaveSlot(int saveSlotNumber) {
        // Load game data from the corresponding save file
        loadSaveData(saveSlotNumber);

        // Set up panel
        this.panel = new JPanel();
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        this.panel.setBackground(Color.lightGray);

        // Create elements
        JLabel saveNum = new JLabel("Save Slot " + String.valueOf(saveSlotNumber));
        JPanel saveInfo = new JPanel();
        JLabel petName = new JLabel("Pet Name: " + name);
        JLabel playerScore = new JLabel("Score: " + score);
        JLabel playerMoney = new JLabel("Money: " + money);
        JLabel playTime = new JLabel("Playtime: " + time / 60 + ":" + time % 60);

        // Add subpanels to panels
        this.panel.add(saveNum);
        this.panel.add(saveInfo);

        // Add elements to subpanels
        saveInfo.add(petName);
        saveInfo.add(playerScore);
        saveInfo.add(playerMoney);
        saveInfo.add(playTime);
    }

    /**
     * Loads game data from a file corresponding to the save slot.
     */
    private void loadSaveData(int saveSlotNumber) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save_slot_" + saveSlotNumber + ".ser"))) {
            // Assuming that you have serialized Pet, Coins, and other game data objects
            name = (String) ois.readObject();  // Load the pet name
            score = (int) ois.readObject();    // Load the score
            money = (int) ois.readObject();    // Load the money
            time = (int) ois.readObject();     // Load the playtime (in seconds)
        } catch (IOException | ClassNotFoundException e) {
            // In case no save exists or the file is corrupted, set defaults
            name = "Unknown Pet";
            score = 0;
            money = 0;
            time = 0;
        }
    }

    /**
     * Save the current game data to a specific save slot file.
     */
    public void saveGame(int saveSlotNumber) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save_slot_" + saveSlotNumber + ".ser"))) {
            // Assuming the currentPet contains all the necessary game state data
            oos.writeObject(currentPet.getName()); // Save pet name
            oos.writeObject(currentPet.getScore()); // Save score
            oos.writeObject(Coins.getCoins()); // Save coins
            oos.writeObject(currentPet.getTime()); // Save playtime (in seconds)
            JOptionPane.showMessageDialog(null, "Game saved successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving the game: " + e.getMessage());
        }
    }

    /**
     * Load the game data from a specific save slot file.
     */
    public void loadGame(int saveSlotNumber) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save_slot_" + saveSlotNumber + ".ser"))) {
            String loadedPetName = (String) ois.readObject();
            int loadedScore = (int) ois.readObject();
            int loadedMoney = (int) ois.readObject();
            int loadedTime = (int) ois.readObject();

            // Assuming Pet class has a constructor that takes name, score, money, and time
            currentPet = new Pet(loadedPetName, loadedScore, loadedMoney, loadedTime);

            // Update coins and other UI components accordingly
            Coins.setCoins(loadedMoney);
            updateUI();
            JOptionPane.showMessageDialog(null, "Game loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error loading the game: " + e.getMessage());
        }
    }

    /**
     * Update the UI components after loading the game (pet name, score, etc.)
     */
    private void updateUI() {
        updatePetName(currentPet.getName());
        updateScore(currentPet.getScore());
        updateCoins(Coins.getCoins());
        updateTime(currentPet.getTime());
    }

    // Helper methods to update the UI components (e.g., labels)
    private void updatePetName(String name) { 
        // Update pet name UI (e.g., labels, text fields, etc.)
    }
    private void updateScore(int score) { 
        // Update score UI (e.g., labels, text fields, etc.)
    }
    private void updateCoins(int coins) { 
        // Update coins UI (e.g., labels, text fields, etc.)
    }
    private void updateTime(int time) { 
        // Update time UI (e.g., labels, text fields, etc.)
    }
}
