import java.util.Timer;
import java.util.TimerTask;

public class Pet {
	
    private String name;
    private int health;
    private int fullness;
    private int sleep;
    private int happiness;
    private String state;
    
    // Cooldown flag for takeToVet
    private boolean canUseVet = true; 
    
    // Shared Timer instance for all timer-based logic (goToBed & takeToVet implementation)
    private final Timer sharedTimer = new Timer();

    //Initialize maximum and minimum stats
    private final int MAX_STAT = 100; 
    private final int MIN_STAT = 0;

    // Constructor
    public Pet(String name) {
        this.name = name;
        this.health = MAX_STAT; // Start with full health 
        // Moderate starting values
        this.fullness = 50; 
        this.sleep = 50;
        this.happiness = 50;
        this.state = "Normal";

        startStatDecline(); // Start automatic stat decline once pet initialized 
    }
    
    /**
     * Freezing Logic: Restrict commands based on the pet's state.
     * 
     * Based on the pet’s current state, the following commands are available to the player:
		1. Dead State: No commands.
		2. Sleeping State: No commands.
		3. Angry State: Give Gift and Play
		4. Hungry State: All commands.
		5. Normal State: All commands.
     * 
     * @return true if the command is allowed, false otherwise.
     */
    private boolean canExecuteCommand(String command) {
        if (state.equals("Dead")) { // If the pet is dead, no commands are available to the player
            System.out.println("Cannot " + command + ". The pet is dead.");
            return false;
        }
        if (state.equals("Sleeping")) { // If the pet is asleep, no commands are available to the player
            System.out.println("Cannot " + command + ". The pet is sleeping."); 
            return false;
        }
        if (state.equals("Angry") && happiness < MAX_STAT / 2) { // If the pet is angry, the pet will only respond to commands that increase happiness.
            System.out.println("Cannot " + command + ". The pet is angry and refuses to listen!");
            return false;
        }
        return true; 
    }

    /**
     * Feed: The pet is fed food, and their fullness value increases.
     * 
     * @param foodItem
     */
    public void feed(FoodItem foodItem) {
        if (!canExecuteCommand("feed")) return; // Check whether the canExecuteCommand method returns false (command is not allowed)

        fullness = Math.min(fullness + foodItem.getNutritionValue(), MAX_STAT); // Different food types have different nutritional values
        System.out.println(name + " was fed with " + foodItem.getName() + " and is now less hungry.");
        updateState(); // Check state after feeding
    }
    
    /**
     * Go to bed: The pet enters the sleeping state and remains in that state until their sleep value reaches the maximum.
     */
    public void goToBed() {
        if (!state.equals("Normal")) {
            System.out.println("Cannot sleep in the current state.");
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
     * Play: The player plays with the pet increasing the pet’s happiness by a set amount.
     */
    public void play() {
        if (!canExecuteCommand("play")) return; // Check whether the canExecuteCommand method returns false (command is not allowed)

        happiness = Math.min(happiness + 20, MAX_STAT); // Increase happiness by fixed amount of 20
        System.out.println(name + " is playing and feels happier!");
        updateState(); // Check state after playing
    }
    
    /**
     * Give Gift: The player gives a gift to the pet, increasing happiness.
     * 
     * Give Gift: The pet is given a gift by the player and their happiness value increases. 
     * The player should be able to select from different gift types each with their own properties
     * (e.g., some might add more happiness than others). The gifts available should be based on the player’s inventory (see requirement 3.1.8).
     */
    public void giveGift(GiftItem giftItem) {
        if (!canExecuteCommand("give a gift")) return; // Check whether the canExecuteCommand method returns false (command is not allowed)

        happiness = Math.min(happiness + giftItem.getHappinessBoost(), MAX_STAT); // Increase happiness based on the type of gift 
        System.out.println(name + " received a " + giftItem.getName() + " and feels happier!"); 
        updateState(); // Check state after giving the gift
    }
    
    /**
     * Take to the Vet: The player takes the pet to the vet and the pet’s health is increased by a set amount. 
     * Once this command is used, it should be unavailable for a set time until a cool down is over.
     */
    public void takeToVet() {
        if (!canExecuteCommand("visit the vet")) return; // Check whether the canExecuteCommand method returns false (command is not allowed)

        if (!canUseVet) { // Cooldown flag for vet (If unable to use vet at the moment, command is unavail and return)
            System.out.println("The vet is unavailable right now. Please wait.");
            return;
        }

        //If cooldown timer is done (command works)
        health = Math.min(health + 30, MAX_STAT); // Increase health by a fixed amount, 30
        System.out.println(name + " was taken to the vet and is feeling better!");
        updateState(); // Check state after visiting the vet

        // Start cooldown
        canUseVet = false;
        sharedTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                canUseVet = true; // Reset cooldown
                System.out.println("The vet is now available again!");
            }
        }, 10000); // Cooldown duration: 10 seconds
    }
    
    /**
     * Exercise: The player takes the pet for a walk.
     * Increases health but decreases sleep and fullness.
     * 
     */
    public void exercise() {
        if (!canExecuteCommand("exercise")) return; // Check whether the canExecuteCommand method returns false (command is not allowed)

        health = Math.min(health + 20, MAX_STAT); // Increase health points by 20
        fullness = Math.max(fullness - 20, MIN_STAT); // Decrease fullness (increase hunger) by 20
        sleep = Math.max(sleep - 10, MIN_STAT); // Decrease sleep points (increase sleepiness) by 10
        System.out.println(name + " exercised and is healthier but hungrier and sleepier.");
        updateState(); // Check state after exercise
    }
    
    /**
     * Use Medicine: The player uses a medItem to heal the pet by a certain increment.
     */
    public void useMedicine(MedItem medItem) {
        if (!canExecuteCommand("use medicine")) return; // Check if the command is executable

        health = Math.min(health + medItem.getHealingValue(), MAX_STAT); // Increase health, but don't exceed max
        System.out.println(name + " was healed with " + medItem.getName() + " and feels better!");
        updateState(); // Check and update the pet's state after healing
    }

    
    private void updateState() {
        if (health <= MIN_STAT) { // Health: If health points reach zero, the pet dies (enters the dead state) and the game is over. 
            state = "Dead";
            System.out.println(name + " has died.");
        } else if (sleep <= MIN_STAT) { // Sleep: If sleep reaches zero, a health penalty is applied (a set number of health points are removed)
            state = "Sleeping";
            health = Math.max(MIN_STAT, health - 10); // Apply health penalty
            System.out.println(name + " is too tired and fell asleep.");
        } else if (fullness <= MIN_STAT) { // Fullness: When fullness reaches zero, the pet enters the hungry state and the rate that happiness decline should be increased. 
            state = "Hungry";
            happiness = Math.max(MIN_STAT, happiness - 5); // Accelerated happiness decline
            System.out.println(name + " is starving!");
        } else if (happiness <= MIN_STAT) { // Happiness: When happiness reaches zero, the pet will enter the angry state. 
            state = "Angry";
            System.out.println(name + " is angry!");
        } else { // If nothing has been impacted, pet remains normal
            state = "Normal";
        }
    }
    
    /**
     * Automatic stat decline while the game is running
     * 
     * All of the statistics except health should slowly decline at a set rate during game play. 
     * This rate may be different for each type of pet and for each statistic 
     * (e.g. sleep points might decline faster than fullness points).
     * 
     * Decline Rates EVERY 30 SECONDS
     * Fullness: 10
     * Sleep: 20
     * Happiness: 10
     */
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


    /**
     * Display current stats 
     */
    public void displayStats() {
        System.out.println("Stats: Fullness=" + fullness + ", Sleep=" + sleep + ", Happiness=" + happiness + ", Health=" + health + ", State=" + state);
    }
    
    // Getters and Setters for Direct Access
    public int getHealth() { return health; }
    
    public void setHealth(int health) {
        this.health = Math.max(MIN_STAT, Math.min(health, MAX_STAT));
        updateState();
    }
    
    public int getFullness() { return fullness; }
    
    public void setFullness(int fullness) {
        this.fullness = Math.max(MIN_STAT, Math.min(fullness, MAX_STAT));
        updateState();
    }
    
    public int getHappiness() { return happiness; }
    
    public void setHappiness(int happiness) {
        this.happiness = Math.max(MIN_STAT, Math.min(happiness, MAX_STAT));
        updateState();
    }
    
    public int getSleep() { return sleep; }
    
    public void setSleep(int sleep) {
        this.sleep = Math.max(MIN_STAT, Math.min(sleep, MAX_STAT));
        updateState();
    }
    
    public String getState() { return state; }
}

