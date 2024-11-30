import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

public class Human extends Pet{

	public Human(String name) {
		this.name = name;
        this.health = MAX_STAT; // Start with full health
        this.fullness = 50; // Moderate starting fullness level
        this.sleep = 50; // Moderate starting sleep level
        this.happiness = 50; // Moderate starting happiness level
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
                    sleep = Math.max(MIN_STAT, sleep - 2);       // Decrease sleep by 2
                    happiness = Math.max(MIN_STAT, happiness - 5); // Decrease happiness by 5

                    // Delegate state management to updateState
                    updateState();

                    // Output updated stats
                    System.out.println("Stats updated: Fullness=" + fullness + ", Sleep=" + sleep + ", Happiness=" + happiness);
                }
            }
        }, 0, 30000); // Update stats every 30 seconds
    }
	private void updateState() {
        if (health <= MIN_STAT) { // Health: If health points reach zero, the pet dies (enters the dead state) and the game is over. 
            state = "Dead";
            String message = (name + " has died.");
            JOptionPane.showMessageDialog(ScreenManager.mainGameScreen.panel,message,"Notice",JOptionPane.ERROR_MESSAGE);
        } else if (sleep <= MIN_STAT) { // Sleep: If sleep reaches zero, a health penalty is applied (a set number of health points are removed)
            state = "Sleeping";
            health = Math.max(MIN_STAT, health - 10); // Apply health penalty
            String message = (name + " is too tired and fell asleep.");
            JOptionPane.showMessageDialog(ScreenManager.mainGameScreen.panel,message,"Notice",JOptionPane.ERROR_MESSAGE);
        } else if (fullness <= MIN_STAT) { // Fullness: When fullness reaches zero, the pet enters the hungry state and the rate that happiness decline should be increased. 
            state = "Hungry";
            happiness = Math.max(MIN_STAT, happiness - 5); // Accelerated happiness decline
            String message = (name + " is starving!");
            JOptionPane.showMessageDialog(ScreenManager.mainGameScreen.panel,message,"Notice",JOptionPane.ERROR_MESSAGE);
        } else if (happiness <= MIN_STAT) { // Happiness: When happiness reaches zero, the pet will enter the angry state. 
            state = "Angry";
            String message = (name + " is angry!");
            JOptionPane.showMessageDialog(ScreenManager.mainGameScreen.panel,message,"Notice",JOptionPane.ERROR_MESSAGE);
        } else { // If nothing has been impacted, pet remains normal
            state = "Normal";
        }
    }
}
