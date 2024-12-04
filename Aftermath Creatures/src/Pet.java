import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

/**
 * Represents a virtual pet with various states and attributes like health, fullness, sleep, and happiness. 
 * <p>
 * The pet's stats gradually decline over time, requiring the player to interact with it using commands. 
 * The pet can be fed, played with, taken to the vet, or given gifts. 
 * It also has different states such as "Normal", "Hungry", "Angry", "Sleeping", and "Dead".
 * <p>
 * Key features include:
 * <ul>
 *   <li>Automatic stat decline over time</li>
 *   <li>Cooldowns for certain actions (e.g., taking the pet to the vet)</li>
 *   <li>State-based restrictions on commands</li>
 *   <li>Interaction with various items like food, gifts, and medicine</li>
 * </ul>
 * 
 * @author Ivory, Harshi, Terry
 */
public class Pet {
	
	/** The name of the pet. */
    protected String name; 
    
    /** The health level of the pet, ranges from MIN_STAT to MAX_STAT. */
    protected int health; // Health level of the pet 
    
    /** The fullness level of the pet, ranges from MIN_STAT to MAX_STAT. */
    protected int fullness; 
    
    /** The sleep level of the pet, ranges from MIN_STAT to MAX_STAT. */
    protected int sleep; 
    
    /** The happiness level of the pet, ranges from MIN_STAT to MAX_STAT. */
    protected int happiness;
    
    /** The current state of the pet (e.g., Normal, Hungry, Angry, Sleeping, Dead). */
    protected String state; 
    
    /** Indicates if the pet can visit the vet (cooldown mechanism). */
    protected boolean canUseVet = true; 
    
    /** Shared timer for scheduling pet actions (e.g., sleeping, vet cooldown). */
    protected final Timer sharedTimer = new Timer();
    
    /** Timer for managing the gradual decline of pet stats over time. */
    private final Timer statDeclineTimer = new Timer(true);
    
    /** Maximum stat value for the pet's attributes. */
    protected final int MAX_STAT = 100; 
    
    /** Minimum stat value for the pet's attributes. */
    protected final int MIN_STAT = 0;

    /**
     * Constructs a Pet instance with a given name.
     * <p>
     * Initializes the pet's stats to default values and starts the automatic stat decline process.
     *
     * @param name the name of the pet
     */
    public Pet(String name) {
        this.name = name;
        startStatDecline();
    }
    
    /**
     * Default constructor.
     * <p>
     * Primarily used in subclasses or scenarios where the name is assigned later.
     */
    public Pet() {
    }
    
    /**
     * Determines if a command can be executed based on the pet's current state.
     * <p>
     * The following rules apply:
     * <ul>
     * 		<li> Dead State: No commands are allowed.
     * 		<li> Sleeping State: No commands are allowed.
     * 		<li> Angry State: Only "Give Gift" and "Play" commands are allowed.
     * 		<li> Hungry State: All commands are allowed.
     * 		<li> Normal State: All commands are allowed.
     *</ul>
     *
     * @param command the name of the command being checked
     * @return true if the command is allowed, false otherwise
     */
    private boolean canExecuteCommand(String command) {
        if (state.equals("Dead")) { // If the pet is dead, no commands are available to the player
            String message=("Cannot " + command + ". The pet is dead.");
            JOptionPane.showMessageDialog(ScreenManager.mainGameScreen.panel,message,"Notice",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (state.equals("Sleeping")) { // If the pet is asleep, no commands are available to the player
            String message=("Cannot " + command + ". The pet is sleeping.");
            JOptionPane.showMessageDialog(ScreenManager.mainGameScreen.panel,message,"Notice",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (state.equals("Angry") && happiness < MAX_STAT / 2) { // If the pet is angry, the pet will only respond to commands that increase happiness.
            String message=("Cannot " + command + ". The pet is angry and refuses to listen!");
            JOptionPane.showMessageDialog(ScreenManager.mainGameScreen.panel,message,"Notice",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true; 
    }

    /**
     * Feeds the pet with the specified food item, increasing its fullness.
     * 
     * @param amount the amount of stat increase
     */
    
    public void feed(int amount) {
        if (!canExecuteCommand("feed")) return; // Check whether the canExecuteCommand method returns false (command is not allowed)

        fullness = Math.min(fullness + amount, MAX_STAT); // Different food types have different nutritional values
        updateState(); // Check state after feeding
    }
    
    /**
     * Puts the pet to sleep, transitioning it to the "Sleeping" state until fully rested.
     * <p>
     * This action restores the pet's sleep stat to maximum after a fixed duration.
     */
    public void goToBed() {
        if (!state.equals("Normal")) {
            String message=("Cannot sleep in the current state.");
            JOptionPane.showMessageDialog(ScreenManager.mainGameScreen.panel,message,"Notice",JOptionPane.ERROR_MESSAGE);
            return;
        }

        state = "Sleeping"; // Once state is set to sleeping, canExecuteCommand will restrict all other commands to become available
        System.out.println(name + " is sleeping...");
        
        // Schedule sleep logic using shared timer
        sharedTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                sleep = MAX_STAT; // Restore sleep to maximum
                state = "Normal"; // Pet wakes up
                System.out.println(name + " woke up refreshed!");
            }
        }, 5000); // Sleep for 5 seconds
    }
    
    /**
     * Allows the player to play with the pet, increasing its happiness.
     */
    public void play() {
        if (!canExecuteCommand("play")) return; // Check whether the canExecuteCommand method returns false (command is not allowed)

        happiness = Math.min(happiness + 20, MAX_STAT); // Increase happiness by fixed amount of 20
        String message = (name + " is playing and feels happier!");
        JOptionPane.showMessageDialog(ScreenManager.mainGameScreen.panel,message,"Notice",JOptionPane.INFORMATION_MESSAGE);
        updateState(); // Check state after playing
    }
    
    /**
     * Gives a gift to the pet, increasing its happiness.
     * <p>
     * The amount of happiness gained depends on the gift's properties.
     * 
     * @param amount the amount of stat increase
     */
    public void giveGift(int amount) {
        if (!canExecuteCommand("give a gift")) return; // Check whether the canExecuteCommand method returns false (command is not allowed)

        happiness = Math.min(happiness + amount, MAX_STAT); // Increase happiness based on the type of gift 
        String message = (name + " received a gift and feels happier!");
        JOptionPane.showMessageDialog(ScreenManager.mainGameScreen.panel,message,"Notice",JOptionPane.INFORMATION_MESSAGE);
        updateState(); // Check state after giving the gift
    }
    
    /**
     * Takes the pet to the vet, increasing its health.
     * <p>
     * This command has a cooldown period before it can be used again.
     */
    public void takeToVet() {
        if (!canExecuteCommand("visit the vet")) return; // Check whether the canExecuteCommand method returns false (command is not allowed)

        if (!canUseVet) { // Cooldown flag for vet (If unable to use vet at the moment, command is unavail and return)
            String message = ("The vet is unavailable right now. Please wait.");
            JOptionPane.showMessageDialog(ScreenManager.mainGameScreen.panel,message,"Notice",JOptionPane.ERROR_MESSAGE);
            return;
        }

        //If cooldown timer is done (command works)
        health = Math.min(health + 30, MAX_STAT); // Increase health by a fixed amount, 30
        String message = (name + " was taken to the vet and is feeling better!");
        JOptionPane.showMessageDialog(ScreenManager.mainGameScreen.panel,message,"Notice",JOptionPane.PLAIN_MESSAGE);
        updateState(); // Check state after visiting the vet

        // Start cooldown
        canUseVet = false;
        sharedTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                canUseVet = true; // Reset cooldown
                String message = ("The vet is now available again!");
                JOptionPane.showMessageDialog(ScreenManager.mainGameScreen.panel,message,"Notice",JOptionPane.PLAIN_MESSAGE);
            }
        }, 10000); // Cooldown duration: 10 seconds
    }
    
    /**
     * Exercise: The player takes the pet for a walk.
     * Increases health but decreases sleep and fullness.
     */
    public void exercise() {
        if (!canExecuteCommand("exercise")) return; // Check whether the canExecuteCommand method returns false (command is not allowed)

        health = Math.min(health + 20, MAX_STAT); // Increase health points by 20
        fullness = Math.max(fullness - 10, MIN_STAT); // Decrease fullness (increase hunger) by 10
        sleep = Math.max(sleep - 10, MIN_STAT); // Decrease sleep points (increase sleepiness) by 10
        String message = (name + " exercised and is healthier but hungrier and sleepier.");
        JOptionPane.showMessageDialog(ScreenManager.mainGameScreen.panel,message,"Notice",JOptionPane.PLAIN_MESSAGE);
        updateState(); // Check state after exercise
    }
    
    /**
     * Use Medicine: The player uses a medItem to heal the pet by a certain increment.
     * 
     * @param amount the amount of stat increase
     */
    public void useMedicine(int amount) {
        if (!canExecuteCommand("use medicine")) return; // Check if the command is executable

        health = Math.min(health + amount, MAX_STAT); // Increase health, but don't exceed max
        String message = (name + " was healed with and feels better!");
        JOptionPane.showMessageDialog(ScreenManager.mainGameScreen.panel,message,"Notice",JOptionPane.PLAIN_MESSAGE);
        updateState(); // Check and update the pet's state after healing
    }

    /**
     * Updates the pet's state based on its current stats.
     */
    protected void updateState() {
        if (health <= MIN_STAT) {
            state = "Dead";
            System.out.println(name + " has died.");
        } else if (fullness == MIN_STAT) {
            state = "Hungry";
            System.out.println(name + " is starving!");
        } else if (sleep == MIN_STAT) {
            state = "Sleeping";
            System.out.println(name + " is too tired!");
        } else if (happiness == MIN_STAT) {
            state = "Angry";
            System.out.println(name + " is angry!");
        } else {
            state = "Normal";
        }
    }
    
    /**
     * Starts the automatic decline of the pet's stats while the game is running.
     * <p>
     * All of the statistics except health gradually decline over time during gameplay.
     * This decline rate can vary for each statistic and pet type 
     * (e.g., sleep points might decline faster than fullness points).
     * </p>
     * <p>
     * Decline Rates (updated every 30 seconds):
     * <ul>
     *   <li><b>Fullness:</b> Decreases by 5 points</li>
     *   <li><b>Sleep:</b> Decreases by 2 points</li>
     *   <li><b>Happiness:</b> Decreases by 5 points</li>
     * </ul>
     * </p>
     */
    private void startStatDecline() {
        statDeclineTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!state.equals("Dead")) {
                    // Gradual decline
                    fullness = Math.max(MIN_STAT, fullness - 5); // Decrease fullness
                    sleep = Math.max(MIN_STAT, sleep - 2);       // Decrease sleep
                    happiness = Math.max(MIN_STAT, happiness - 3); // Decrease happiness

                    // Health penalty logic
                    if (fullness == MIN_STAT && sleep == MIN_STAT && happiness == MIN_STAT) {
                        health = Math.max(MIN_STAT, health - 10); // All stats at 0: decrement by 10
                    } else if (fullness == MIN_STAT || sleep == MIN_STAT) {
                        health = Math.max(MIN_STAT, health - 4); // Either Sleep or Fullness at 0: decrement by 2
                    }

                    // Update state after each tick
                    updateState();

                    // Debugging output (can be removed)
                    System.out.println("Stats updated: Fullness=" + fullness + ", Sleep=" + sleep + ", Happiness=" + happiness + ", Health=" + health);
                }
            }
        }, 0, 30000); // Update every 30 seconds
    }
    
    /**
     * Displays the current stats of the pet in a readable format.
     * <p>
     * Outputs the values of:
     * <ul>
     *   <li>Fullness</li>
     *   <li>Sleep</li>
     *   <li>Happiness</li>
     *   <li>Health</li>
     *   <li>State</li>
     * </ul>
     * Example Output: 
     * <code>Stats: Fullness=50, Sleep=40, Happiness=60, Health=80, State=Normal</code>
     */
    public void displayStats() {
        System.out.println("Stats: Fullness=" + fullness + ", Sleep=" + sleep + ", Happiness=" + happiness + ", Health=" + health + ", State=" + state);
    }
    
    /**
     * Gets the current health value of the pet.
     * 
     * @return the health of the pet as an integer.
     */
    public int getHealth() { return health;}
    
    /**
     * Sets the health value of the pet.
     * <p>
     * Ensures that the health value is within the allowed range 
     * (between <code>MIN_STAT</code> and <code>MAX_STAT</code>).
     * Automatically updates the pet's state after the value is changed.
     * </p>
     * 
     * @param health the new health value to be set.
     */
    public void setHealth(int health) {
        this.health = Math.max(MIN_STAT, Math.min(health, MAX_STAT));
        updateState();
    }
    
    /**
     * Gets the current fullness value of the pet.
     * 
     * @return the fullness of the pet as an integer.
     */
    public int getFullness() { return fullness; }
    
    /**
     * Sets the fullness value of the pet.
     * <p>
     * Ensures that the fullness value is within the allowed range 
     * (between <code>MIN_STAT</code> and <code>MAX_STAT</code>).
     * Automatically updates the pet's state after the value is changed.
     * </p>
     * 
     * @param fullness the new fullness value to be set.
     */
    public void setFullness(int fullness) {
        this.fullness = Math.max(MIN_STAT, Math.min(fullness, MAX_STAT));
        updateState();
    }
    
    /**
     * Gets the current happiness value of the pet.
     * 
     * @return the happiness of the pet as an integer.
     */
    public int getHappiness() { return happiness; }
    
    /**
     * Sets the happiness value of the pet.
     * <p>
     * Ensures that the happiness value is within the allowed range 
     * (between <code>MIN_STAT</code> and <code>MAX_STAT</code>).
     * Automatically updates the pet's state after the value is changed.
     * </p>
     * 
     * @param happiness the new happiness value to be set.
     */
    public void setHappiness(int happiness) {
        this.happiness = Math.max(MIN_STAT, Math.min(happiness, MAX_STAT));
        updateState();
    }
    
    /**
     * Gets the current sleep value of the pet.
     * 
     * @return the sleep of the pet as an integer.
     */
    public int getSleep() { return sleep; }
    
    /**
     * Sets the sleep value of the pet.
     * <p>
     * Ensures that the sleep value is within the allowed range 
     * (between <code>MIN_STAT</code> and <code>MAX_STAT</code>).
     * Automatically updates the pet's state after the value is changed.
     * </p>
     * 
     * @param sleep the new sleep value to be set.
     */
    public void setSleep(int sleep) {
        this.sleep = Math.max(MIN_STAT, Math.min(sleep, MAX_STAT));
        updateState();
    }
    
    /**
     * Gets the current state of the pet.
     * <p>
     * The state reflects the pet's overall condition, which could be one of:
     * <ul>
     *   <li><b>Normal:</b> Pet is in good condition</li>
     *   <li><b>Sleeping:</b> Pet is resting</li>
     *   <li><b>Hungry:</b> Pet needs food</li>
     *   <li><b>Angry:</b> Pet is unhappy</li>
     *   <li><b>Dead:</b> Pet is no longer alive</li>
     * </ul>
     * </p>
     * 
     * @return the current state of the pet as a string.
     */
    public String getState() { return state; }
    
    /**
     * Get the name of the pet
     * @return name The name of the pet
     */
    public String getName() {return name;}
    
    /**
     * Revive the pet
     * <p>
     * Resets all stats to maximum values, including health. There is no check
     * in this method, but it is only ever used if the pet's state is "Dead".
     * Then updates the pet's state.
     */
    public void revive() {
    	this.health = MAX_STAT;
    	this.fullness = MAX_STAT;
    	this.sleep = MAX_STAT;
    	this.happiness = MAX_STAT;
    	updateState();
    }
}

