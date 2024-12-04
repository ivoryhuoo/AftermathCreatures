import java.util.Timer;
import java.util.TimerTask;
/**
 * Human pet class
 * <p>
 * One of the 3 possible pet options
 * 
 */
public class Human extends Pet {

    public Human(String name,String petType) {
        this.name = name;
        this.typePet = petType;
        this.health = MAX_STAT; // Start with full health
        this.fullness = 50; // Moderate starting fullness level
        this.sleep = 50; // Moderate starting sleep level
        this.happiness = 80; // High starting happiness level
        this.state = "Normal"; // Initial state of the pet
        startStatDecline(); // Start automatic stat decline once pet initialized 
    }

    /**
     * Unique stat decrement method
     * <p>
     * Changes the human's stats over time
     */
    private void startStatDecline() {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!state.equals("Dead")) {
                    fullness = Math.max(MIN_STAT, fullness - 5);
                    sleep = Math.max(MIN_STAT, sleep - 3);
                    happiness = Math.max(MIN_STAT, happiness - 5);

                    // Health penalty conditions
                    if (fullness == MIN_STAT && sleep == MIN_STAT && happiness == MIN_STAT) {
                        health = Math.max(MIN_STAT, health - 10);
                    } else if (fullness == MIN_STAT || sleep == MIN_STAT) {
                        health = Math.max(MIN_STAT, health - 4);
                    }

                    updateState();

                    System.out.println("Stats updated (Human): Fullness=" + fullness + ", Sleep=" + sleep + ", Happiness=" + happiness + ", Health=" + health);
                }
            }
        }, 0, 30000); // Update stats every 30 seconds
    }
}
