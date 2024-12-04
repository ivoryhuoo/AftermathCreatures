import static org.junit.jupiter.api.Assertions.*;

import javax.swing.*;
import org.junit.jupiter.api.Test;

class KeyboardShortcutManagerTest {

    @Test
    void testGlobalShortcutSetup() {
        // Create a test JPanel
        JPanel testPanel = new JPanel();

        // Set up global shortcuts
        KeyboardShortcutManager.setupGlobalShortcuts(testPanel);

        // Access InputMap and ActionMap
        InputMap inputMap = testPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = testPanel.getActionMap();

        // Check if the ESCAPE key is bound to "openSettings"
        Object actionKey = inputMap.get(KeyStroke.getKeyStroke("ESCAPE"));
        assertNotNull(actionKey, "ESCAPE key should be bound in InputMap.");
        assertEquals("openSettings", actionKey, "ESCAPE key should map to 'openSettings' action.");

        // Check if the "openSettings" action is defined in ActionMap
        Action action = actionMap.get("openSettings");
        assertNotNull(action, "'openSettings' action should exist in ActionMap.");
    }

    @Test
    void testGameplayShortcutSetup() {
        // Create a test JPanel
        JPanel testPanel = new JPanel();

        // Create a mock Pet object
        Pet mockPet = new Pet("TestPet");

        // Set up gameplay shortcuts
        KeyboardShortcutManager.setupGameplayShortcuts(testPanel, mockPet);

        // Access InputMap and ActionMap
        InputMap inputMap = testPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = testPanel.getActionMap();

        // Define expected key bindings and action names
        String[][] expectedBindings = {
            {"R", "sleep"},
            {"I", "inventory"},
            {"D", "doctor"},
            {"M", "marketplace"},
            {"P", "play"},
            {"E", "exercise"}
        };

        // Test each gameplay shortcut
        for (String[] binding : expectedBindings) {
            String keyStroke = binding[0];
            String actionName = binding[1];

            // Verify the InputMap contains the key binding
            Object actionKey = inputMap.get(KeyStroke.getKeyStroke(keyStroke));
            assertNotNull(actionKey, keyStroke + " key should be bound in InputMap.");
            assertEquals(actionName, actionKey, keyStroke + " key should map to '" + actionName + "' action.");

            // Verify the ActionMap contains the corresponding action
            Action action = actionMap.get(actionName);
            assertNotNull(action, "'" + actionName + "' action should exist in ActionMap.");
        }
    }
}

