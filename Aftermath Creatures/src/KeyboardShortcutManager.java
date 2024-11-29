import javax.swing.*;
import java.awt.event.ActionEvent;

public class KeyboardShortcutManager {

    /**
     * Sets up global keyboard shortcuts for navigating to the Settings Menu.
     *
     * Global shortcuts:
     * <ul>
     *     <li><b>ESCAPE</b>: Open the settings menu.</li>
     * </ul>
     *
     * @param panel The panel to which the global shortcuts will be added.
     */
    public static void setupGlobalShortcuts(JPanel panel) {
        InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = panel.getActionMap();

        // Bind ESCAPE key to navigate to the Settings Menu
        bindKey(inputMap, actionMap, "openSettings", "ESCAPE", () -> {
            ScreenManager.swapView("3"); // Navigate to the Settings Menu
        });
    }

    /**
     * Sets up gameplay-specific keyboard shortcuts for a given panel and pet.
     *
     * Gameplay-specific shortcuts:
     * <ul>
     *     <li><b>F</b>: Feed the pet.</li>
     *     <li><b>S</b>: Make the pet sleep.</li>
     *     <li><b>G</b>: Give a gift.</li>
     *     <li><b>V</b>: Take the pet to the vet.</li>
     *     <li><b>M</b>: Use medicine.</li>
     *     <li><b>E</b>: Exercise the pet.</li>
     * </ul>
     *
     * @param panel The panel to which the gameplay-specific shortcuts will be added.
     * @param pet   The {@code Pet} instance to interact with.
     */
    public static void setupGameplayShortcuts(JPanel panel, Pet pet) {
        InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = panel.getActionMap();

        // Bind gameplay-specific keys
        //bindKey(inputMap, actionMap, "feed", "F", () -> pet.feed(new FoodItem("Default Food", 20)));
        bindKey(inputMap, actionMap, "sleep", "S", pet::goToBed);
        //bindKey(inputMap, actionMap, "giveGift", "G", () -> pet.giveGift(new GiftItem("Default Gift", 20)));
        bindKey(inputMap, actionMap, "takeToVet", "V", pet::takeToVet);
        //bindKey(inputMap, actionMap, "useMedicine", "M", () -> pet.useMedicine(new MedItem("Default Medicine", 20)));
        bindKey(inputMap, actionMap, "exercise", "E", pet::exercise);
    }

    /**
     * Binds a key to a specific action
     *
     * @param inputMap  The InputMap for key bindings.
     * @param actionMap The ActionMap for actions.
     * @param actionName The name of the action.
     * @param keyStroke The key to bind.
     * @param action    The action to perform.
     */
    private static void bindKey(InputMap inputMap, ActionMap actionMap, String actionName, String keyStroke, Runnable action) {
        inputMap.put(KeyStroke.getKeyStroke(keyStroke), actionName);
        actionMap.put(actionName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action.run(); // Execute the provided action
            }
        });
    }
}
