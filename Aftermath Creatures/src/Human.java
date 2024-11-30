import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

public class Human extends Pet{

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
        timer.scheduleAtFixedRate(new TimerTask() { // Schedule tasks at fixed rates of time 
            @Override
            public void run() {
                if (!state.equals("Dead")) {
                    // Gradual decrease in stats if pet is NOT dead 
                    fullness = Math.max(MIN_STAT, fullness - 5); // Decrease fullness by 5
                    sleep = Math.max(MIN_STAT, sleep - 3);       // Decrease sleep by 3
                    happiness = Math.max(MIN_STAT, happiness - 5); // Decrease happiness by 5

                    // Delegate state management to updateState
                    updateState();

                    // Output updated stats
                    System.out.println("Stats updated: Fullness=" + fullness + ", Sleep=" + sleep + ", Happiness=" + happiness);
                }
            }
        }, 0, 30000); // Update stats every 30 seconds
    }
}
