import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SaveScreen class to display save slots and load games
 * @author Numan
 */
public class SaveScreen extends Screen {
    public SaveScreen() {
        // Create SaveSlot objects
        SaveSlot saveSlot1 = new SaveSlot(1);
        SaveSlot saveSlot2 = new SaveSlot(2);
        SaveSlot saveSlot3 = new SaveSlot(3);

        // Add functionality on click to panels
        saveSlot1.panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                showSaveLoadDialog(1); // Prompt for save/load for slot 1
            }
        });

        saveSlot2.panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                showSaveLoadDialog(2); // Prompt for save/load for slot 2
            }
        });

        saveSlot3.panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                showSaveLoadDialog(3); // Prompt for save/load for slot 3
            }
        });

        // Create Main Menu Button
        JButton mainMenuButton = new JButton("Main Menu");
        mainMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundManager.play("button_sound.wav");
                ScreenManager.swapView("0"); // Switch to main menu screen (view 0)
            }
        });

        // Create Back to Game Button
        JButton backToGameButton = new JButton("Back to Game");
        backToGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundManager.play("button_sound.wav");
                ScreenManager.swapView("5"); // Switch to the gameplay screen (view 5)
            }
        });

        // Layout the screen
        setVertical(this.panel);
        this.panel.add(saveSlot1.panel);
        this.panel.add(Box.createRigidArea(new Dimension(0, 30)));
        this.panel.add(saveSlot2.panel);
        this.panel.add(Box.createRigidArea(new Dimension(0, 30)));
        this.panel.add(saveSlot3.panel);

        // Layout buttons beside each other
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(mainMenuButton);
        buttonPanel.add(backToGameButton);
        this.panel.add(buttonPanel);  // Add button panel below the slots
    }

    /**
     * Shows a dialog box to either save or load the game for the selected slot.
     */
    private void showSaveLoadDialog(int saveSlotNumber) {
        String[] options = {"Save", "Load", "Cancel"};
        int choice = JOptionPane.showOptionDialog(
            null, 
            "Do you want to save or load the game?", 
            "Save/Load Game", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.INFORMATION_MESSAGE, 
            null, 
            options, 
            options[0]
        );

        if (choice == 0) {
            saveGame(saveSlotNumber); // Save the game if 'Save' is chosen
        } else if (choice == 1) {
            loadGame(saveSlotNumber); // Load the game if 'Load' is chosen
        }
    }

    /**
     * Save the current game to the selected save slot.
     */
    private void saveGame(int saveSlotNumber) {
        try {
            // Create a timestamp or use playtime as the filename suffix
            String timestamp = getTimestamp();
            
            // Create output stream for serialization
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save_slot_" + saveSlotNumber + "_" + timestamp + ".ser"))) {
                // Assuming Pet, Coins, and Score are global/current game objects
                oos.writeObject(main.pet); // Save the pet data
                oos.writeObject(Coins.getCoins()); // Save the current coins
                oos.writeObject(score.getText()); // Save the current score
                oos.writeObject(getCurrentPlaytime()); // Save current playtime (in seconds)

                // Provide confirmation
                JOptionPane.showMessageDialog(null, "Game saved to Slot " + saveSlotNumber + " at " + timestamp);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving the game: " + e.getMessage());
        }
    }

    /**
     * Load the game from the selected save slot.
     */
    private void loadGame(int saveSlotNumber) {
        try {
            // Open the saved file for the selected slot
            String timestamp = getTimestamp(); // Optional: Use the latest save timestamp or user selection
            String saveFile = "save_slot_" + saveSlotNumber + "_" + timestamp + ".ser";

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile))) {
                // Assuming Pet, Coins, and other game data were serialized in this order
                Pet loadedPet = (Pet) ois.readObject();
                int loadedCoins = (int) ois.readObject();
                String loadedScore = (String) ois.readObject();
                int loadedTime = (int) ois.readObject();

                // Restore the game state
                main.pet = loadedPet;
                Coins.setCoins(loadedCoins);
                score.setText(loadedScore);

                // Update the UI with the loaded pet state, health, etc.
                updateUI();

                // Switch to gameplay view (or main screen view)
                ScreenManager.swapView("5"); // Example view switch, adjust as needed
                JOptionPane.showMessageDialog(null, "Game loaded from Slot " + saveSlotNumber);

            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Error loading the game: " + e.getMessage());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error finding the save file: " + e.getMessage());
        }
    }

    /**
     * Generates a timestamp in the format YYYY-MM-DD_HH-MM-SS for filenames.
     */
    private String getTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        return sdf.format(new Date());
    }

    /**
     * Get the current playtime in seconds (or any other format you prefer).
     */
    private int getCurrentPlaytime() {
        // Assuming 'playtime' is tracked in seconds somewhere in your game
        return main.playtime;  // Replace with actual playtime tracking variable
    }

    /**
     * Updates the UI after loading the game data.
     */
    private void updateUI() {
        updatePetName(currentPet.getName());
        updateScore(currentPet.getScore());
        updateCoins(Coins.getCoins());
        updateTime(currentPet.getTime());
    }

    // Helper methods to update the UI components (e.g., labels)
    private void updatePetName(String name) { /* Update pet name UI */ }
    private void updateScore(int score) { /* Update score UI */ }
    private void updateCoins(int coins) { /* Update coins UI */ }
    private void updateTime(int time) { /* Update time UI */ }
}