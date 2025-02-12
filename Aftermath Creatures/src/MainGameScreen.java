import javax.swing.*;
/**
 * Screen where most gameplay happens
 * @see Screen
 * @author Ivory, Numan, Harshi, Terry
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;
import java.io.*;
/**
 * Main gameplay screen for the virtual pet application.
 * <p>
 * This class handles the primary user interface where the player interacts with their pet,
 * manages the pet's stats, and navigates between various game features such as inventory,
 * settings, and the marketplace.
 * </p>
 * 
 * <h2>Key Features:</h2>
 * <ul>
 *   <li>Displays the pet's current stats (health, sleep, fullness, happiness).</li>
 *   <li>Dynamic pet state management and visual updates (e.g., Normal, Hungry, Angry).</li>
 *   <li>Interactive buttons for gameplay actions (Rest, Play, Exercise, etc.).</li>
 *   <li>Keyboard shortcuts for faster interaction.</li>
 *   <li>Supports updating coin balance, score, and real-time clock.</li>
 * </ul>
 * 
 * @see Screen
 * @author Ivory, Numan, Harshi, Terry
 * @version 1.0
 * @since Fall 2024
 */
public class MainGameScreen extends Screen{
	static JLabel curTime; // Displays current game time
	static JLabel petName; //Displays pet's name
	static JLabel score; // Displays player's current score
	static JLabel petStateIcon; // Displays an icon representing the pet's state
	static JLabel health; // Displays the pet's health
    static JLabel sleep; // Displays the pet's sleep level
    static JLabel fullness; // Displays the pet's fullness level
    static JLabel happiness; // Displays the pet's happiness level
	static ImageIcon normalIcon; // Icons for pet states
	static ImageIcon hungryIcon; // Icons for pet states
	static ImageIcon angryIcon; // Icons for pet states
	static ImageIcon sleepingIcon; // Icons for pet states
	static ImageIcon deadIcon; // Icons for pet states
	static String petState;//store value to check against Pet's state
	static JLabel petSprite; // Sprite representation of the pet
	static ImageIcon normalPet; // Sprites for pet states
	static ImageIcon hungryPet; // Sprites for pet states
	static ImageIcon angryPet; // Sprites for pet states
	static ImageIcon sleepingPet; // Sprites for pet states
	static ImageIcon deadPet; // Sprites for pet states
	private JLabel money; // Reference to the money label
    private Timer updateTimer; // Timer for updating the coin display
    
    /**
     * Constructor for the MainGameScreen class.
     * Sets up the layout, UI components, and initializes gameplay elements.
     */
	public MainGameScreen() {
        
        // Set up pet state icons
		petStateIcon = new JLabel();
  		petStateIcon.setPreferredSize(new Dimension(50,50));
  		normalIcon = new ImageIcon("icons/normal.png");
  		hungryIcon = new ImageIcon("icons/hungry.png");
  		angryIcon = new ImageIcon("icons/angry.png");
  		sleepingIcon = new ImageIcon("icons/sleeping.png");
  		deadIcon = new ImageIcon("icons/dead.png");
  		petStateIcon.setIcon(normalIcon); // Default state is normal   
  		
        resetPetState();
        
		// Set layout, setup subpanels
		this.panel.setLayout(new BorderLayout());
		JPanel header = new JPanel();
		header.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		panel.add(header, BorderLayout.NORTH);
		JPanel sidebar = new JPanel();
		sidebar.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		setVertical(sidebar);
		panel.add(sidebar, BorderLayout.WEST);
		JPanel footer = new JPanel();
		footer.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		panel.add(footer, BorderLayout.SOUTH);
		JPanel center = new JPanel();
		center.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.add(center, BorderLayout.CENTER);
		center.setBackground(Color.DARK_GRAY);
		
		// Create elements, Initialize UI Components
		curTime = new JLabel("17:25");
		petName = new JLabel("pet name");
		money = new JLabel("Coins: " + Coins.getCoins()); // Display initial coin count
		score = new JLabel("Score: 0");
		setH2(curTime);
		setH2(petName);
		setH2(money);
		setH2(score);
		health = new JLabel("Health: 100/100");
		sleep = new JLabel("Sleep: 100/100");
		fullness = new JLabel("Fullness: 100/100");
		happiness = new JLabel("Happiness: 100/100");
		setH2(health);
		setH2(sleep);
		setH2(fullness);
		setH2(happiness);
		
		// Footer icons go here, Setup buttons for gameplay action 
		JButton rest = new JButton("Rest"); // Placeholders
		JButton inventory = new JButton("Inventory");
		JButton doctor = new JButton("Doctor");
		JButton market = new JButton("Market");
		JButton play = new JButton("Play");
		JButton exercise = new JButton("Exercise");
		JButton menu = new JButton("Settings Menu");
		// Create the Save Game button
		JButton saveButton = new JButton("Save Game");
		
		// Set up pet sprite
		petSprite = new JLabel();
		petSprite.setHorizontalAlignment(SwingConstants.CENTER);
		petSprite.setVerticalAlignment(SwingConstants.CENTER);
		
		// Add functionality to buttons
		rest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				SoundManager.play("button_sound.wav");
				main.pet.goToBed();
			}
		});
		inventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				SoundManager.play("button_sound.wav");
				ScreenManager.swapView("6");
			}
		});
		doctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				SoundManager.play("button_sound.wav");
				main.pet.takeToVet();
			}
		});
		market.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				SoundManager.play("button_sound.wav");
				ScreenManager.swapView("7");
			}
		});
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				SoundManager.play("button_sound.wav");
				main.pet.play();
			}
		});
		exercise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				SoundManager.play("button_sound.wav");
				main.pet.exercise();
			}
		});
		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				SoundManager.play("button_sound.wav");
				ScreenManager.swapView("3");
			}
		});
		
		// You can click on the pet to make it happy
		petSprite.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			/**
             * Increases pet's happiness when clicked.
             */
			public void mouseClicked(MouseEvent e) {
				SoundManager.play("pet_interact_sound.wav");
				main.pet.setHappiness(main.pet.getHappiness()+5);
			}
		});

		// Create the Save Game button
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SoundManager.play("button_sound.wav");
                ScreenManager.swapView("2"); // Swap to SaveScreen (or relevant screen)
            }
        });
		
		// Add elements to subpanels
		header.add(curTime);
		header.add(petName);
		header.add(petStateIcon);
		header.add(money);
		header.add(score);
		sidebar.add(health);
		sidebar.add(sleep);
		sidebar.add(fullness);
		sidebar.add(happiness);
		center.add(petSprite);
		
		// Footer icons go here
		footer.add(rest);
		footer.add(inventory);
		footer.add(doctor);
		footer.add(market);
		footer.add(play);
		footer.add(exercise);
		footer.add(menu);
		footer.add(saveButton); // Add it to the footer

		// Start updating the money label
        startUpdatingCoins();
        
        // Setup keyboard shortcuts for this screen
        setupKeyboardShortcuts();
	}
	
	/**
	 * Setup keyboard shortcuts for this screen.
	 */
	private void setupKeyboardShortcuts() {
	    // Global shortcut: ESC navigates to the settings menu
	    KeyboardShortcutManager.setupGlobalShortcuts(this.panel);

	    // Gameplay-specific shortcuts
	    if (main.pet != null) {
	        KeyboardShortcutManager.setupGameplayShortcuts(this.panel, main.pet);
	    } else {
	        System.err.println("Pet is not initialized. Gameplay shortcuts skipped.");
	    }

	    // Ensure the panel gains focus for shortcuts
	    this.panel.requestFocusInWindow();
	}
    
	/**
	 * Update shortcuts dynamically when the pet changes.
	 */
	public void updateShortcuts() {
	    if (main.pet != null) {
	        KeyboardShortcutManager.setupGameplayShortcuts(this.panel, main.pet);
	        System.out.println("Gameplay shortcuts updated for pet: " + main.pet.getName());
	        updatePetName(); // Refresh the pet name display
	    } else {
	        System.err.println("Cannot update shortcuts. Pet is null.");
	    }
	}

	/**
	 * Change the name of the pet
	 * <p>
	 * The screen is initialized with a default pet name when the program is run, 
	 * so this method exists to change from that default value to whatever name 
	 * the player chose for the pet.
	 */
    /**
     * Change the name of the pet dynamically.
     * Ensures the pet's name displayed matches the actual name.
     */
    public void updatePetName() {
        if (main.pet != null && petName != null) {
            petName.setText(main.pet.getName()); // Update the label with the pet's actual name
        } else {
            petName.setText("No Pet Selected"); // Fallback if no pet is selected
        }
    }
    
	/**
	 * Update coins
	 * <p>
	 * 
	 */
	private void startUpdatingCoins() {
        updateTimer = new Timer(true); // Daemon thread
        updateTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> money.setText("Coins: " + Coins.getCoins()));
            }
        }, 0, 1000); // Update every second
    }
	
	/**
	 * Update score
	 */
    public void updateScore() {
        if(!(String.valueOf(main.pet.getScore()).equals(score.getText()))) {
            score.setText("Score: "+String.valueOf(main.pet.getScore()));
        }
    }
	
	/**
	 * Update time
	 * <p>
	 * Uses a Calendar object to get the current time, and updates the in-game 
	 * time display. Uses a 24-hour format.
	 * @see Calendar
	 */
	public void updateTime() {
		//get current time
		Calendar currentTime = Calendar.getInstance();
		if(!((String.valueOf(currentTime.get(Calendar.HOUR_OF_DAY)))+":"+String.valueOf(currentTime.get(Calendar.MINUTE))).equals(curTime.getText())) {
			//fix abnormal time formatting (eg. 12:5 instead of 12:05)
			if(currentTime.get(Calendar.MINUTE)<10) {
				curTime.setText((String.valueOf(currentTime.get(Calendar.HOUR_OF_DAY)))+":0"+String.valueOf(currentTime.get(Calendar.MINUTE)));
			}else {
				curTime.setText((String.valueOf(currentTime.get(Calendar.HOUR_OF_DAY)))+":"+String.valueOf(currentTime.get(Calendar.MINUTE)));
			}
		}
	}
	
	/**
	 * Update health
	 * <p>
	 * Checks if the pet's health is different from the displayed value, then changes
	 * the display to match. Health is converted to a String.
	 * @see Pet
	 */
	public void updateHealth() {
		if(!(String.valueOf(main.pet.getHealth()).equals(health.getText()))) {
			health.setText("Health: "+String.valueOf(main.pet.getHealth()));
		}
		if(main.pet.getHealth()<=25) {
			health.setForeground(Color.red);
		}
	}
	
	/**
	 * Update sleep
	 * <p>
	 * Checks if the pet's sleep stat is different from the displayed value, then changes
	 * the display to match. Sleep is converted to a String.
	 * @see Pet
	 */
	public void updateSleep() {
		if(!(String.valueOf(main.pet.getSleep()).equals(sleep.getText()))) {
			sleep.setText("Sleep: "+String.valueOf(main.pet.getSleep()));
		}
		if(main.pet.getSleep()<=25) {
			sleep.setForeground(Color.red);
		}
	}
	
	/**
	 * Update fullness
	 * <p>
	 * Same as updateHealth and updateSleep, but instead for the fullness stat.
	 * @see Pet
	 */
	public void updateFullness() {
		if(!(String.valueOf(main.pet.getFullness()).equals(fullness.getText()))) {
			fullness.setText("Fullness: "+String.valueOf(main.pet.getFullness()));
		}
		if(main.pet.getFullness()<=25) {
			fullness.setForeground(Color.red);
		}
	}
	
	/**
	 * Update happiness
	 * <p>
	 * Same as updateHealth and updateSleep, but instead for the happiness stat.
	 * @see Pet
	 */
	public void updateHappiness() {
		if(!(String.valueOf(main.pet.getHappiness()).equals(happiness.getText()))) {
			happiness.setText("Happiness: "+String.valueOf(main.pet.getHappiness()));
		}
		if(main.pet.getHappiness()<=25) {
			happiness.setForeground(Color.red);
		}
	}
	
	/**
	 * Update pet status icon
	 * <p>
	 * Creates a set of ImageIcons using png files according to the type of pet chosen
	 * by the player. If the current pet state does not match the display, the display
	 * will switch to a different ImageIcon.
	 * @see Pet
	 * @see ImageIcon
	 */
	public void updateIcon() {
		//change image-set based on type of pet
		normalPet = new ImageIcon("sprites/Normal"+main.pet.getClass().getName()+".png");
		angryPet = new ImageIcon("sprites/Angry"+main.pet.getClass().getName()+".png");
		hungryPet = new ImageIcon("sprites/Hungry"+main.pet.getClass().getName()+".png");
		sleepingPet = new ImageIcon("sprites/Sleepy"+main.pet.getClass().getName()+".png");
		deadPet = new ImageIcon("sprites/Dead"+main.pet.getClass().getName()+".png");
		//change petStateIcon in header, change pet sprite
		if(!(main.pet.getState().equals(petState))) {
			petState = main.pet.getState();
			switch(main.pet.getState()){
				case "Normal":
					//change icon and pet sprite
					petStateIcon.setIcon(normalIcon);
					petSprite.setIcon(normalPet);
					break;
				case "Hungry":
					//change icon and pet sprite
					petStateIcon.setIcon(hungryIcon);
					petSprite.setIcon(hungryPet);
					break;
				case "Angry":
					//change icon and pet sprite
					petStateIcon.setIcon(angryIcon);
					petSprite.setIcon(angryPet);
					break;
				case "Sleeping":
					//change icon and pet sprite
					petStateIcon.setIcon(sleepingIcon);
					petSprite.setIcon(sleepingPet);
					break;
				case "Dead":
					//change icon and pet sprite
					petStateIcon.setIcon(deadIcon);
					petSprite.setIcon(deadPet);
					break;
			}
		}
	}
	
	/**
	 * Change pet state to "Normal".
	 */
	public void resetPetState() {
	    petState = "Normal";
	    if (main.pet != null) {
	        normalPet = new ImageIcon("sprites/Normal" + main.pet.getClass().getName() + ".png");
	        petStateIcon.setIcon(normalIcon);
	        petSprite.setIcon(normalPet);
	        updatePetName(); // Update the pet's name dynamically
	    }
	}

	/**
	 * Animate pet
	 * <p>
	 * Changes the pet's X and Y position based on a regular arbitrary timer.
	 * The pet slides around on the screen only if it is in a "Normal" state.
	 */
	public void animatePet() {
		Calendar currentTime = Calendar.getInstance();
		int curX = petSprite.getX();
		int curY = petSprite.getY();
		if(currentTime.get(Calendar.SECOND)%5<2&&main.pet.getState().equals("Normal")) {
			if(currentTime.get(Calendar.MILLISECOND)%863==100&&curY<300) {
				petSprite.setLocation(curX, curY+1);
			}
			if(currentTime.get(Calendar.MILLISECOND)%863==0&&curX>100) {
				petSprite.setLocation(curX-1, curY);
			}
			if(currentTime.get(Calendar.MILLISECOND)%863==250&&curX<300) {
				petSprite.setLocation(curX+1, curY);
			}
			if(currentTime.get(Calendar.MILLISECOND)%863==350&&curY>0) {
				petSprite.setLocation(curX, curY-1);
			}
		}
	}
	
	
	/**
	 * Updates the money JLabel to reflect the current coin balance.
	 */
	public void updateMoneyText() {
	    if (money != null) {
	        money.setText("Coins: " + Coins.getCoins());
	    }
	}

	/**
	 * Retrieves the text displayed in the money JLabel.
	 *
	 * @return The text showing the player's coin balance.
	 */
	public String getMoneyText() {
	    return money != null ? money.getText() : "";
	}


}
