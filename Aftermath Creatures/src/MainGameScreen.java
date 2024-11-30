import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Calendar;
import java.awt.*;
/**
 * Screen displays the pet, the pet's stats/condition. This is where main gameplay happens.
 * @see		Screen
 * @author 	Terry
 */
public class MainGameScreen extends Screen{
	static JLabel curTime;
	static JLabel petName;
	static JLabel money;
	static JLabel score;
	static JLabel health;
	static JLabel sleep;
	static JLabel fullness;
	static JLabel happiness;
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
		
	}
	public void updateScore() {
		
	}
	public void updateTime() {
		//update time
		Calendar currentTime = Calendar.getInstance();
		if(!((String.valueOf(currentTime.get(Calendar.HOUR_OF_DAY)))+":"+String.valueOf(currentTime.get(Calendar.MINUTE))).equals(curTime.getText())) {
			curTime.setText((String.valueOf(currentTime.get(Calendar.HOUR_OF_DAY)))+":"+String.valueOf(currentTime.get(Calendar.MINUTE)));
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
}
