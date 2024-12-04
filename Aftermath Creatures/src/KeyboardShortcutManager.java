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

	    // Directly bind ESCAPE to navigate to the Settings Menu
	    inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "openSettings");
	    actionMap.put("openSettings", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            ScreenManager.swapView("3"); // Navigate to the Settings Menu
	        }
	    });
	}

    /**
     * Sets up gameplay-specific keyboard shortcuts for a given panel and pet.
     *
     * Gameplay-specific shortcuts:
     * <ul>
     *     <li><b>R</b>: Make pet sleep/rest</li>
     *     <li><b>I</b>: Navigate to Inventory</li>
     *     <li><b>D</b>: Bring pet to doctor/vet</li>
     *     <li><b>M</b>: Navigate to marketplace</li>
     *     <li><b>P</b>: Play with pet</li>
     *     <li><b>E</b>: Exercise the pet</li>
     * </ul>
     *
     * @param panel The panel to which the gameplay-specific shortcuts will be added.
     * @param pet   The {@code Pet} instance to interact with.
     */
	public static void setupGameplayShortcuts(JPanel panel, Pet pet) {
        InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = panel.getActionMap();

        // Bind gameplay-specific keys to their actions
        bindKey(inputMap, actionMap, "sleep", "R", () -> {
            if (main.pet != null) {
                main.pet.goToBed();
                ScreenManager.mainGameScreen.updatePetName(); // Ensure UI stays synced
            }
        });
        bindKey(inputMap, actionMap, "inventory", "I", () -> ScreenManager.swapView("6")); // I for inventory
        bindKey(inputMap, actionMap, "doctor", "D", () -> {
            if (main.pet != null) {
                main.pet.takeToVet();
                ScreenManager.mainGameScreen.updatePetName(); // Ensure UI stays synced
            }
        });
        bindKey(inputMap, actionMap, "marketplace", "M", () -> ScreenManager.swapView("7")); // M for marketplace
        bindKey(inputMap, actionMap, "play", "P", () -> {
            if (main.pet != null) {
                main.pet.play();
                ScreenManager.mainGameScreen.updatePetName(); // Ensure UI stays synced
            }
        });
        bindKey(inputMap, actionMap, "exercise", "E", () -> {
            if (main.pet != null) {
                main.pet.exercise();
                ScreenManager.mainGameScreen.updatePetName(); // Ensure UI stays synced
            }
        });
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
                action.run(); // Execute the provided action directly, no dialog
            }
        });
    }

}

