import java.awt.CardLayout;
import javax.swing.JPanel;

public class ScreenManager {
    private static ScreenManager single_instance = null;
    static CardLayout c = new CardLayout();
    static JPanel currentScreen = new JPanel(c);

    public static String currentScreenNum = "0";

    // Explicit references to screens
    private InventoryScreen inventoryScreen;

    private ScreenManager() {
        // Create screens
        Screen mainMenu = new MainMenuScreen();
        Screen tutorialScreen = new TutorialScreen();
        Screen settingsScreen = new SettingsScreen();
        Screen saveScreen = new SaveScreen();
        Screen mainGameScreen = new MainGameScreen();
        inventoryScreen = new InventoryScreen();
        Screen marketScreen = new MarketScreen();
        Screen petSelectScreen = new PetSelectScreen();

        // Add screens to CardLayout
        currentScreen.add(mainMenu.panel, "0");
        currentScreen.add(petSelectScreen.panel, "1");
        currentScreen.add(saveScreen.panel, "2");
        currentScreen.add(settingsScreen.panel, "3");
        currentScreen.add(tutorialScreen.panel, "4");
        currentScreen.add(mainGameScreen.panel, "5");
        currentScreen.add(inventoryScreen.panel, "6");
        currentScreen.add(marketScreen.panel, "7");

        // Default is mainMenu
        c.show(currentScreen, "0");
    }

    public static void swapView(String key) {
        c.show(currentScreen, key);

        if (key.equals("6")) { // Refresh Inventory Screen when viewing
            getInstance().inventoryScreen.refreshPanels();
        }
    }

    public static synchronized ScreenManager getInstance() {
        if (single_instance == null)
            single_instance = new ScreenManager();
        return single_instance;
    }

    public static void setCurrentScreenNum(String s) {
        currentScreenNum = s;
    }
}
