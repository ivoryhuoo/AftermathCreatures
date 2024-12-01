import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Harshi
 * Manages the game state, including inventory management for different item categories.
 * Provides functionality to add items to the inventory, retrieve items by category, 
 * and enforce a maximum limit for each category.
 */
public class GameState {
    private static final int MAX_ITEMS = 4; // Maximum number of items allowed per category
    private static final Map<String, List<String>> inventory = new HashMap<>();

    // Static initializer block to set up the inventory categories
    static {
        inventory.put("Food", new ArrayList<>()); // Food items category
        inventory.put("Gifts", new ArrayList<>()); // Gifts items category
        inventory.put("Meds", new ArrayList<>()); // Medicines items category
    }

    /**
     * Adds an item to the specified inventory category.
     *
     * @param category The category to which the item belongs (e.g., "Food", "Gifts", "Meds").
     * @param item     The item to add, formatted as "Name-StatIncrement".
     * @return {@code true} if the item was added successfully, {@code false} if the inventory is full
     *         or the category does not exist.
     */
    public static boolean addItem(String category, String item) {
        if (inventory.containsKey(category)) {
            List<String> items = inventory.get(category);
            if (items.size() < MAX_ITEMS) {
                items.add(item); // Add the item to the category
                return true; // Item added successfully
            } else {
                // Inventory full for the specified category
                System.out.println("Inventory full for category: " + category);
                return false;
            }
        }
        // Category does not exist
        return false;
    }

    /**
     * Retrieves the list of items for the specified category.
     *
     * @param category The category for which items are requested.
     * @return A list of items in the specified category. If the category does not exist,
     *         an empty list is returned.
     */
    public static List<String> getItems(String category) {
        return inventory.getOrDefault(category, new ArrayList<>());
    }
}
