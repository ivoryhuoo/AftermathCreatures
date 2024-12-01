import javax.swing.*;
/**
 * Screen where most gameplay happens
 * @see Screen
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
public class MainGameScreen extends Screen{
	static JLabel curTime;
	static JLabel petName;
	static JLabel score;
	static JLabel petStateIcon;
	static JLabel health;
	static JLabel sleep;
	static JLabel fullness;
	static JLabel happiness;
	static ImageIcon normalIcon;
	static ImageIcon hungryIcon;
	static ImageIcon angryIcon;
	static ImageIcon sleepingIcon;
	static ImageIcon deadIcon;
	static String petState;//store value to check against Pet's state
	static JLabel petSprite;
	static ImageIcon normalPet;
	static ImageIcon hungryPet;
	static ImageIcon angryPet;
	static ImageIcon sleepingPet;
	static ImageIcon deadPet;
	private JLabel money; // Reference to the money label
    private Timer updateTimer; // Timer for updating the coin display
	public MainGameScreen() {
		//set layout, setup subpanels
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
		
		//create elements
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
		//footer icons go here
		JButton rest = new JButton("Rest");//placeholders
		JButton inventory = new JButton("Inventory");
		JButton doctor = new JButton("Doctor");
		JButton market = new JButton("Market");
		JButton play = new JButton("Play");
		JButton exercise = new JButton("Exercise");
		JButton menu = new JButton("Settings Menu");
		
		//set up pet state icons
		resetPetState();
		petStateIcon = new JLabel();
		petStateIcon.setPreferredSize(new Dimension(50,50));
		normalIcon = new ImageIcon("icons/normal.png");
		hungryIcon = new ImageIcon("icons/hungry.png");
		angryIcon = new ImageIcon("icons/angry.png");
		sleepingIcon = new ImageIcon("icons/sleeping.png");
		deadIcon = new ImageIcon("icons/dead.png");
		petStateIcon.setIcon(normalIcon);//default state is normal
		
		//set up pet sprite
		petSprite = new JLabel();
		petSprite.setHorizontalAlignment(SwingConstants.CENTER);
		petSprite.setVerticalAlignment(SwingConstants.CENTER);
		
		//add functionality to buttons
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
		//You can click on the pet to make it happy
		petSprite.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				SoundManager.play("pet_interact_sound.wav");
				main.pet.setHappiness(main.pet.getHappiness()+5);
			}
		});
		
		//add elements to subpanels
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
		//footer icons go here
		footer.add(rest);
		footer.add(inventory);
		footer.add(doctor);
		footer.add(market);
		footer.add(play);
		footer.add(exercise);
		footer.add(menu);
		
		// Start updating the money label
        startUpdatingCoins();
	}
	/**
	 * Change the name of the pet
	 * <p>
	 * The screen is initialized with a default pet name when the program is run, 
	 * so this method exists to change from that default value to whatever name 
	 * the player chose for the pet.
	 */
	public void updatePetName() {
		//update pet name
		if(main.pet.getName()!=petName.getText()) {
			petName.setText(main.pet.getName());
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
		//UNTESTED
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
	 * Change pet state to "Normal"
	 */
	public void resetPetState() {
		petState="Normal";
		if(main.pet!=null) {
			normalPet = new ImageIcon("sprites/Normal"+main.pet.getClass().getName()+".png");
			petStateIcon.setIcon(normalIcon);
			petSprite.setIcon(normalPet);
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
}
