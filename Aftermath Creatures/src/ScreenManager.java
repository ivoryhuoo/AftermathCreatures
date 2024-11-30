import java.awt.CardLayout;

import javax.swing.JPanel;
/**
 * Creates all game screens and swaps between them using CardLayout.
 * @author Terry
 */
public class ScreenManager {
	//static reference
	private static ScreenManager single_instance = null;
	
	static CardLayout c = new CardLayout();
	static JPanel currentScreen = new JPanel(c);
	public static String currentScreenNum="0";
	public static MainGameScreen mainGameScreen= new MainGameScreen();//allows game loop in main
	//private constructor (for singleton)
	private ScreenManager(){		
		//create screens
		Screen mainMenu = new MainMenuScreen();
		Screen tutorialScreen = new TutorialScreen();
		Screen settingsScreen = new SettingsScreen();
		Screen saveScreen = new SaveScreen();
		MainGameScreen mainGameScreen = new MainGameScreen();
		Screen inventoryScreen = new InventoryScreen(); 
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
