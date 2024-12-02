import java.io.*;

public class SaveLoadSystem {

    /**
     * Saves the game state to a file.
     * 
     * @param gameState The GameState object to save.
     * @param gameFile  The filename where the game state will be saved.
     * @return true if the game state was saved successfully, false otherwise.
     * @author Numan
     */
    public static boolean saveGameState(GameState gameState, String gameFile) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(gameFile))) {
            out.writeObject(gameState);
            return true;
        } catch (IOException e) {
            System.out.println("Error saving game state: " + e.getMessage());
            return false;
        }
    }

    /**
     * Loads the game state from a file.
     * 
     * @param gameFile The filename from which to load the game state.
     * @return The loaded GameState object, or null if loading failed.
     */
    public static GameState loadGameState(String gameFile) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(gameFile))) {
            return (GameState) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading game state: " + e.getMessage());
            return null;
        }
    }

    /**
     * Saves the pet data to a file.
     * 
     * @param pet     The Pet object to save.
     * @param petFile The filename where the pet data will be saved.
     * @return true if the pet data was saved successfully, false otherwise.
     */
    public static boolean savePet(Pet pet, String petFile) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(petFile))) {
            out.writeObject(pet);
            return true;
        } catch (IOException e) {
            System.out.println("Error saving pet: " + e.getMessage());
            return false;
        }
    }

    /**
     * Loads the pet data from a file.
     * 
     * @param petFile The filename from which to load the pet data.
     * @return The loaded Pet object, or null if loading failed.
     */
    public static Pet loadPet(String petFile) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(petFile))) {
            return (Pet) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading pet: " + e.getMessage());
            return null;
        }
    }
}
