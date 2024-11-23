import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameState {
    public static Map<String, List<String>> inventory = new HashMap<>();

    static {
        inventory.put("Food", new ArrayList<>());
        inventory.put("Gifts", new ArrayList<>());
        inventory.put("Meds", new ArrayList<>());
    }

    public static void addItem(String category, String item) {
        if (inventory.containsKey(category)) {
            inventory.get(category).add(item);
        }
    }

    public static List<String> getItems(String category) {
        return inventory.getOrDefault(category, new ArrayList<>());
    }
}
