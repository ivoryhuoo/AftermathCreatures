import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class InventoryScreenTest {

    private InventoryScreen inventoryScreen;

    @BeforeEach
    void setUp() {
        inventoryScreen = new InventoryScreen();
        GameState.resetInventory(); // Reset inventory before each test
    }

    @Test
    void testTabsExist() {
        JTabbedPane tabbedPane = (JTabbedPane) inventoryScreen.getPanel().getComponent(2);
        assertEquals(3, tabbedPane.getTabCount(), "There should be 3 tabs in the inventory.");
        assertEquals("Food", tabbedPane.getTitleAt(0), "First tab should be Food.");
        assertEquals("Gift", tabbedPane.getTitleAt(1), "Second tab should be Gift.");
        assertEquals("Meds", tabbedPane.getTitleAt(2), "Third tab should be Meds.");
    }

    @Test
    void testAddAndRefreshFoodPanel() {
        GameState.addItem("Food", "Canned Beans-3");
        inventoryScreen.refreshPanels();

        JPanel foodPanel = (JPanel) ((JTabbedPane) inventoryScreen.getPanel().getComponent(2)).getComponentAt(0);
        assertEquals(1, foodPanel.getComponentCount(), "Food panel should have 1 item after adding.");
    }

    @Test
    void testBackButtonExists() {
        JButton backButton = (JButton) inventoryScreen.getPanel().getComponent(4);
        assertEquals("Back", backButton.getText(), "Back button should exist and have the correct text.");
    }
}
