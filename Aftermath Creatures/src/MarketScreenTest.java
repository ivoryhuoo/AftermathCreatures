import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class MarketScreenTest {

    private MarketScreen marketScreen;

    @BeforeEach
    void setUp() {
        marketScreen = new MarketScreen();
        GameState.resetInventory(); // Reset inventory before each test
        Coins.resetCoins(); // Reset coins before each test
    }

    @Test
    void testFoodItemButtonsCreation() {
        JPanel foodPanel = (JPanel) ((JTabbedPane) marketScreen.getPanel().getComponent(2)).getComponentAt(0);
        assertEquals(4, foodPanel.getComponentCount(), "Food panel should have 4 buttons.");
    }

    @Test
    void testBackButtonExists() {
        JButton backButton = (JButton) marketScreen.getPanel().getComponent(4);
        assertEquals("Back", backButton.getText(), "Back button should exist and have the correct text.");
    }

    @Test
    void testAttemptPurchaseSuccess() {
        Coins.addCoins(10);
        marketScreen.attemptPurchase("Food", "Canned Beans", 3, 2);
        assertEquals(8, Coins.getCoins(), "Coins should decrease after a successful purchase.");
        assertTrue(GameState.getItems("Food").contains("Canned Beans-3"), "Canned Beans should be in the Food inventory.");
    }
}
