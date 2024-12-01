import java.util.Timer;
import java.util.TimerTask;

public class Human extends Pet {

    public Human(String name) {
        this.name = name;
        this.health = MAX_STAT; // Start with full health
        this.fullness = 50; // Moderate starting fullness level
        this.sleep = 50; // Moderate starting sleep level
        this.happiness = 80; // High starting happiness level
        this.state = "Normal"; // Initial state of the pet
        startStatDecline(); // Start automatic stat decline once pet initialized 
    }

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
