import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Calendar;
import java.awt.*;
public class MainGameScreen extends Screen{
	static JLabel curTime;
	static JLabel petName;
	static JLabel money;
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
		//pet image goes in BorderLayout.CENTER
		
		//create elements
		curTime = new JLabel("17:25");
		petName = new JLabel("pet name");
		money = new JLabel("$0");
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
		JButton doctor = new JButton("doctor");
		JButton market = new JButton("market");
		JButton minigames = new JButton("minigames");
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
		//change size?
		
		//change image-set based on type of pet ie.
		//normalPet = new ImageIcon("icons/" + main.pet.getWhichPet + "/normal.png");
		//where main.pet.getWhichPet returns a String like "Robot" or something
		
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
//				ScreenManager.swapView("market code goes here");
			}
		});
		minigames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				SoundManager.play("button_sound.wav");
//				ScreenManager.swapView("minigames code goes here");
			}
		});
		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				SoundManager.play("button_sound.wav");
				ScreenManager.swapView("3");
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
		//footer icons go here
		footer.add(rest);
		footer.add(inventory);
		footer.add(doctor);
		footer.add(market);
		footer.add(minigames);
		footer.add(menu);
		
	}
	public void updatePetName() {
		//update pet name
		if(main.pet.getName()!=petName.getText()) {
			petName.setText(main.pet.getName());
		}
	}
	public void updateCoins() {
		//UNtESTEd
	}
	public void updateScore() {
		//UNTESTED
	}
	public void updateTime() {
		//update time
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
	//update pet stats
	public void updateHealth() {
		if(!(String.valueOf(main.pet.getHealth()).equals(health.getText()))) {
			health.setText("Health: "+String.valueOf(main.pet.getHealth()));
		}
	}
	public void updateSleep() {
		if(!(String.valueOf(main.pet.getSleep()).equals(sleep.getText()))) {
			sleep.setText("Sleep: "+String.valueOf(main.pet.getSleep()));
		}
	}
	public void updateFullness() {
		if(!(String.valueOf(main.pet.getFullness()).equals(fullness.getText()))) {
			fullness.setText("Fullness: "+String.valueOf(main.pet.getFullness()));
		}
	}
	public void updateHappiness() {
		if(!(String.valueOf(main.pet.getHappiness()).equals(happiness.getText()))) {
			happiness.setText("Happiness: "+String.valueOf(main.pet.getHappiness()));
		}
	}
	public void updateIcon() {
		//change petStateIcon in header, change pet sprite
		if(!(main.pet.getState().equals(petState))) {
			petState = main.pet.getState();
			switch(main.pet.getState()){
				case "Normal":
					petStateIcon.setIcon(normalIcon);
					//change pet sprite
					break;
				case "Hungry":
					petStateIcon.setIcon(hungryIcon);
					//change pet sprite
					break;
				case "Angry":
					petStateIcon.setIcon(angryIcon);
					//change pet sprite
					break;
				case "Sleeping":
					petStateIcon.setIcon(sleepingIcon);
					//change pet sprite
					break;
				case "Dead":
					petStateIcon.setIcon(deadIcon);
					//change pet sprite
					break;
			}
		}
	}
	public void resetPetState() {
		petState="Normal";
	}
}
