import java.awt.CardLayout;

import javax.swing.JPanel;

public class ScreenManager {
	//static reference
	private static ScreenManager single_instance = null;
	
	static CardLayout c = new CardLayout();
	static JPanel currentScreen = new JPanel(c);
	public static String currentScreenNum="0";
	public static MainGameScreen mainGameScreen= new MainGameScreen();//allows game loop in main
	
	// Shared Pet instance for the game
    private Pet pet = null;
    
	//private constructor (for singleton)
	private ScreenManager(){		
		//create screens
		Screen mainMenu = new MainMenuScreen();
		Screen tutorialScreen = new TutorialScreen();
		Screen settingsScreen = new SettingsScreen();
		Screen saveScreen = new SaveScreen();
		//MainGameScreen mainGameScreen = new MainGameScreen();
		Screen inventoryScreen = new InventoryScreen(); 
		Screen petSelectScreen = new PetSelectScreen();
		//add screens to cardlayout
		currentScreen.add(mainMenu.panel,"0");
		currentScreen.add(petSelectScreen.panel,"1");
		currentScreen.add(saveScreen.panel,"2");
		currentScreen.add(settingsScreen.panel,"3");
		currentScreen.add(tutorialScreen.panel,"4");
		// currentScreen.add(mainGameScreen.panel,"5");
		currentScreen.add(inventoryScreen.panel,"6");
		
		//default is mainMenu
		c.show(currentScreen, "0");
		SoundManager.play("game_bgm.wav",true);
	}
	
	/**
     * Dynamically initializes the `MainGameScreen` after the pet is created.
     *
     * @param petName The name of the pet chosen by the user.
     * @param petType The type of the pet chosen by the user (Zombie, Human, Robot).
     */
    public void initializeMainGameScreen(String petName, String petType) {
        // Create the Pet instance with the specified name
        this.pet = new Pet(petName);

        // Customize the Pet based on its type
        switch (petType.toLowerCase()) {
            case "zombie":
                // Health = 100, Fullness = 100, Sleep = 100, Happiness = 50
                pet.setHealth(100);
                pet.setFullness(100);
                pet.setSleep(100);
                pet.setHappiness(50);
                break;

            case "human":
                // Health = 100, Fullness = 80, Sleep = 80, Happiness = 70
                pet.setHealth(100);
                pet.setFullness(80);
                pet.setSleep(80);
                pet.setHappiness(70);
                break;

            case "robot":
                // Health = 100, Fullness = 70, Sleep = 70, Happiness = 100
                pet.setHealth(100);
                pet.setFullness(70);
                pet.setSleep(70);
                pet.setHappiness(100);
                break;

            default:
                // Default to generic pet configuration
                pet.setHealth(100);
                pet.setFullness(100);
                pet.setSleep(100);
                pet.setHappiness(100);
                break;
        }

        // Dynamically create MainGameScreen with the customized pets
        mainGameScreen = new MainGameScreen(this.pet);

        // Add the dynamically created MainGameScreen to CardLayout
        currentScreen.add(mainGameScreen.panel, "5");
    }

	
	//swap view
	public static void swapView(String key) {
		currentScreenNum=key;
		c.show(currentScreen,key);
	}
	
	//create singleton
	public static synchronized ScreenManager getInstance()
    {
        if (single_instance == null)
            single_instance = new ScreenManager();
 
        return single_instance;
    }
	
	//set current screen number = param s
	public static void setCurrentScreenNum(String s) {
		currentScreenNum=s;
	}
}
