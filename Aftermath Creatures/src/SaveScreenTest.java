import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.Scanner;

class SaveScreenTest {
    
    private SaveScreen saveScreen;
    private final String saveSlot1File = "saveSlot1.txt";
    private final String saveSlot2File = "saveSlot2.txt";
    private final String saveSlot3File = "saveSlot3.txt";
    
    @BeforeEach
    void setUp() {
        saveScreen = new SaveScreen();
    }

    @AfterEach
    void tearDown() {
        // Clean up save files after each test
        deleteSaveFiles();
    }

    // Helper method to delete save files
    private void deleteSaveFiles() {
        new File(saveSlot1File).delete();
        new File(saveSlot2File).delete();
        new File(saveSlot3File).delete();
    }

    // Test case for saving data to a save slot
    @Test
    void testSaveGameSlot1() {
        // Set some mock data for the pet object in main class (replace `main.pet` with the actual pet object)
        main.pet = new Pet("Fluffy", 100, 50, 10, 90, 10, 50, "robot");

        saveScreen.saveGame(1);

        // Check if the file has been created and contains the expected data
        File saveFile = new File(saveSlot1File);
        assertTrue(saveFile.exists(), "Save file should be created.");

        // Read file and verify its contents
        String savedData = readSaveFile(saveSlot1File);
        assertTrue(savedData.contains("Fluffy"), "Pet name should be saved.");
        assertTrue(savedData.contains("100"), "Pet score should be saved.");
    }

    // Test case for loading data from a save slot
    @Test
    void testLoadGameSlot1() {
        // Create a mock save file with test data
        createMockSaveFile(saveSlot1File, "100,50,Fluffy,10,90,10,50,robot");

        saveScreen.loadGame(1);

        // Verify that the pet object is properly loaded
        assertEquals("Fluffy", main.pet.name, "Pet name should match.");
        assertEquals(100, main.pet.score, "Pet score should match.");
    }

    // Test case for handling an empty save file when loading
    @Test
    void testLoadGameSlot1EmptyFile() {
        // Create an empty save file
        createMockSaveFile(saveSlot1File, "");

        saveScreen.loadGame(1);

        // Verify that the pet object is not loaded with invalid data
        assertEquals(" - ", main.pet.name, "Pet name should be empty.");
        assertEquals(0, main.pet.score, "Pet score should be zero.");
    }

    // Helper method to create a mock save file with specific data
    private void createMockSaveFile(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
        } catch (IOException e) {
            fail("Failed to create mock save file: " + e.getMessage());
        }
    }

    // Helper method to read the contents of a save file
    private String readSaveFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            fail("Failed to read save file: " + e.getMessage());
        }
        return sb.toString();
    }

    // Test case for checking UI update after saving a game
    @Test
    void testUpdateUIAfterSave() {
        main.pet = new Pet("Fluffy", 100, 50, 10, 90, 10, 50, "robot");

        saveScreen.saveGame(1);

        // Check if the UI elements in SaveSlot are updated correctly
        SaveSlot saveSlot = saveScreen.saveSlot1; // You can also test saveSlot2 or saveSlot3 similarly
        assertEquals("Fluffy", saveSlot.petName.getText(), "Pet name in UI should match.");
        assertEquals("Score: 100", saveSlot.playerScore.getText(), "Score in UI should match.");
    }
}
