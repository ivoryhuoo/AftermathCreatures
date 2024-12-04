import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * SaveScreen class to display save slots and load games
 * @author Numan
 */

public class SaveScreen extends Screen {
    // Create SaveSlot objects
    SaveSlot saveSlot1 = new SaveSlot(1);
    SaveSlot saveSlot2 = new SaveSlot(2);
    SaveSlot saveSlot3 = new SaveSlot(3);
    public SaveScreen() {
        
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
            File myObj = new File("saveSlot"+saveSlotNumber+".txt");
            if (myObj.createNewFile()) {
              System.out.println("File created: " + myObj.getName());
            } 
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }


          try {
            FileWriter myWriter = new FileWriter("saveSlot"+saveSlotNumber+".txt");
            myWriter.write(main.pet.score+","+Coins.getCoins()+","+main.pet.name+","+main.pet.health+","+main.pet.fullness+","+main.pet.sleep+","+main.pet.happiness+","+main.pet.state+","+main.pet.typePet);
            myWriter.close();

          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        switch(saveSlotNumber) {
            case 1 -> saveSlot1.updateUI(main.pet.name,main.pet.score);
            case 2 -> saveSlot2.updateUI(main.pet.name,main.pet.score);
            case 3 -> saveSlot3.updateUI(main.pet.name,main.pet.score);
        }
          

    }

    /**
     * Load the game from the selected save slot.
     */
    private void loadGame(int saveSlotNumber) {

        try {
            File myObj = new File("saveSlot"+saveSlotNumber+".txt");
            if (myObj.createNewFile()) {
              System.out.println("File created: " + myObj.getName());
            } 
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        String data = "";
        try {
            File myObj = new File("saveSlot"+saveSlotNumber+".txt");
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
            switch(split[8]) {
                case "robot" -> main.pet = new Robot(split[2],split[8]);
                case "human" -> main.pet = new Human(split[2],split[8]);
                case "zombie" -> main.pet = new Zombie(split[2],split[8]);
            }
            ScreenManager.mainGameScreen.resetPetState(); 
			ScreenManager.swapView("5");

            main.pet.setStats(Integer.parseInt(split[0]), Integer.parseInt(split[1]), split[2], Integer.parseInt(split[3]), 
            Integer.parseInt(split[4]), Integer.parseInt(split[5]), Integer.parseInt(split[6]), split[7]);
        } else {
            String message=("No Save file!");
            JOptionPane.showMessageDialog(ScreenManager.mainGameScreen.panel,message,"Notice",JOptionPane.ERROR_MESSAGE);
        }
    }

}