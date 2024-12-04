import java.awt.CardLayout;

import javax.swing.JPanel;
/**
 * Creates all game screens and swaps between them using CardLayout.
 * @author Terry, Ivory
 */
public class ScreenManager {
	
	// Static reference singleton
	private static ScreenManager single_instance = null;
	
	// CardLayout to manage screen transitions
	static CardLayout c = new CardLayout();
	static JPanel currentScreen = new JPanel(c);
	public static String currentScreenNum="0";
	private InventoryScreen inventoryScreen;
	public static MainGameScreen mainGameScreen= new MainGameScreen(); // Allows game loop in main
	
	/**
     * Private constructor to enforce singleton pattern.
     * Initializes the default pet and sets up all game screens.
     */
	private ScreenManager(){	
		
		// Initialize default pet
        main.pet = new Zombie("Default Zombie");
        
		//create screens
		Screen mainMenu = new MainMenuScreen();
		Screen tutorialScreen = new TutorialScreen();
		Screen settingsScreen = new SettingsScreen();
		Screen saveScreen = new SaveScreen();
		MainGameScreen mainGameScreen = new MainGameScreen();
		inventoryScreen = new InventoryScreen();
		Screen petSelectScreen = new PetSelectScreen();
		Screen marketScreen = new MarketScreen();
		//add screens to cardlayout
		currentScreen.add(mainMenu.panel,"0");
		currentScreen.add(petSelectScreen.panel,"1");
		currentScreen.add(saveScreen.panel,"2");
		currentScreen.add(settingsScreen.panel,"3");
		currentScreen.add(tutorialScreen.panel,"4");
		currentScreen.add(mainGameScreen.panel,"5");
		currentScreen.add(inventoryScreen.panel,"6");
		currentScreen.add(marketScreen.panel,"7");
		
		//default is mainMenu
		c.show(currentScreen, "0");
		SoundManager.play("game_bgm.wav",true);
	}
	
	/**
	 * Changes the displayed screen
	 * <p>
	 * Changes the CardLayout's displayed screen according to a key. Each game screen
	 * is assigned a different key.
	 * @param key
	 * @see CardLayout
	 */
	public static void swapView(String key) {
		currentScreenNum=key;
		c.show(currentScreen,key);
		if (key.equals("6")) { // Refresh Inventory Screen when viewing
            getInstance().inventoryScreen.refreshPanels();
        }
	}
	
	/**
	 * Singleton method
	 * <p>
	 * Creates one single instance of the ScreenManager. Any future references will
	 * call upon this same instance.
	 * @return singe_instance Singleton 
	 */
	public static synchronized ScreenManager getInstance()
    {
        if (single_instance == null)
            single_instance = new ScreenManager();
 
        return single_instance;
    }
	
	/**
	 * Set current screen number to input String
	 * @param s
	 */
	public static void setCurrentScreenNum(String s) {
		currentScreenNum=s;
	}
}
