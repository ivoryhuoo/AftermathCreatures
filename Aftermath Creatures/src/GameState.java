import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameState {
    private static final int MAX_ITEMS = 4; // Maximum number of items per category
    public static Map<String, List<String>> inventory = new HashMap<>();

    static {
        inventory.put("Food", new ArrayList<>());
        inventory.put("Gifts", new ArrayList<>());
        inventory.put("Meds", new ArrayList<>());
    }

    public static boolean addItem(String category, String item) {
        if (inventory.containsKey(category)) {
            List<String> items = inventory.get(category);
            if (items.size() < MAX_ITEMS) {
                items.add(item);
                return true; // Item added successfully
            } else {
                System.out.println("Inventory full for category: " + category);
                return false; // Inventory full
            }
        }
        return false; // Category not found
    }

    public static List<String> getItems(String category) {
        return inventory.getOrDefault(category, new ArrayList<>());
    }
}
