import java.awt.Color;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;
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

    // Create elements
    JLabel saveNum = new JLabel("Save Slot: " + 0);
    JPanel saveInfo = new JPanel();
    JLabel petName = new JLabel("Pet Name: " + name);
    JLabel playerScore = new JLabel("Score: " + score);
    JLabel playerMoney = new JLabel("Money: " + money);

    // Constructor to initialize the save slot with game data
    public SaveSlot(int saveSlotNumber) {

        // Set up panel
        this.panel = new JPanel();
        this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        this.panel.setBackground(Color.lightGray);

        String[] saveRes = loadSave(saveSlotNumber);

        saveNum.setText("Save Slot: "+saveSlotNumber);
        petName.setText("Pet Name: " + saveRes[0]);
        playerScore.setText("Score: " + saveRes[1]);
        playerMoney.setText("Money: " + saveRes[2]);

        // Add subpanels to panels
        this.panel.add(saveNum);
        this.panel.add(saveInfo);

        // Add elements to subpanels
        saveInfo.add(petName);
        saveInfo.add(playerScore);
        saveInfo.add(playerMoney);
    }

    private String[] loadSave(int num) {
        String[] res = new String[3];

        try {
            File myObj = new File("saveSlot"+num+".txt");
            if (myObj.createNewFile()) {
              System.out.println("File created: " + myObj.getName());
            } 
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        String data = "";
        try {
            File myObj = new File("saveSlot"+num+".txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        if (data!=""){
            String[] split = data.split(",");
            res[0] = split[2];
            res[1] = split[0];
            res[2] = split[1];
        } else {
            res[0] = " - ";
            res[1] = " - ";
            res[2] = " - ";
        }

        return res;
    }

    /**
     * Update the UI components after loading the game (pet name, score, etc.)
     */
    public void updateUI(String name,int score) {
        updatePetName(name);
        updateScore(score);
        updateCoins(Coins.getCoins());
    }

    // Helper methods to update the UI components (e.g., labels)
    private void updatePetName(String name) { 
        petName.setText("Pet Name: "+name);
    }
    private void updateScore(int score) { 
        playerScore.setText("Score: "+score);
    }
    private void updateCoins(int coins) { 
        playerMoney.setText("Coins: "+coins);
    }
}
