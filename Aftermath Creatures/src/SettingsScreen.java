import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.Arrays;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SettingsScreen extends Screen{
	static File playtimeDataFile = new File("playtimeData.json");
	static PlaytimeData playtimeData;
	static File parentalControlsDataFile = new File("parentalControls.json");
	static ParentalControls parentalControls;
	public SettingsScreen(){
		this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
		//hardcoded password
		char[] parentalControlsPassword = "cs2212".toCharArray();
		
		//read data from file to PlaytimeData and ParentalControls objects
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			playtimeData = objectMapper.readValue(playtimeDataFile, PlaytimeData.class);
			parentalControls = objectMapper.readValue(parentalControlsDataFile, ParentalControls.class);
		}catch(Exception eRead) {
			System.out.println("Error reading playtime data file");
		}
		//TODO i think these are still in milliseconds lol (change to hours/minutes?)
		String totalPlaytimeAmt = String.valueOf(playtimeData.getTotalPlaytime());
		String avgPlaytimeAmt = String.valueOf(playtimeData.getTotalPlaytime()/playtimeData.getPlaySessions());
		
		
		//create menu elements
		JLabel bgmText = new JLabel("Music 100");
		JSlider bgmSlider = new JSlider(-80,6);
		bgmSlider.setMaximumSize(new Dimension(500,20));
		JLabel sfxText = new JLabel("Sound Effects");
		JSlider sfxSlider = new JSlider();
		sfxSlider.setMaximumSize(new Dimension(500,20));
		JLabel parentalHeader = new JLabel("Parental Controls");
		JPasswordField parentalPasswordEntry = new JPasswordField();
		parentalPasswordEntry.setMaximumSize(new Dimension(100,30));
		JButton passwordSubmit = new JButton("Submit password");
		JLabel screentimeLabel = new JLabel("Screentime Restrictions");
		setH2(screentimeLabel);
		JLabel startLabel = new JLabel("Start of Allowed Time");
		JLabel endLabel = new JLabel("End of Allowed Time");
		JButton submitScreentimeSettings = new JButton("Submit Screentime Settings");
		JLabel playStatsTitle = new JLabel("Statistics");
		setH2(playStatsTitle);
		JLabel totalLabel = new JLabel("Total Playtime");
		JLabel totalPlaytime = new JLabel(totalPlaytimeAmt);
		JLabel avgLabel = new JLabel("Average Playtime");
		JLabel avgPlaytime = new JLabel(avgPlaytimeAmt);
		JButton revivePet = new JButton("Revive Pet");
		//navigation buttons
		JButton backToMainMenu = new JButton("Back to Main Menu");
		JButton backToGame = new JButton("Back to Game");
		//TODO hide backToGame if game savefile is not loaded
		if(true) {
			backToGame.setVisible(false);
		}
		//dropdown boxes for parental controls screentime restrictions
		Integer[] hours = new Integer[24];
		Integer[] minutes = new Integer[4];
		for(int i=0;i<24;i++) {
			hours[i]=i;
		}
		for(int i=0;i<4;i++) {
			minutes[i]=i*15;
		}
		JComboBox startHoursDropdown = new JComboBox(hours);
		JComboBox startMinsDropdown = new JComboBox(minutes);
		JComboBox endHoursDropdown = new JComboBox(hours);
		JComboBox endMinsDropdown = new JComboBox(minutes);
		
		//create subpanels
		JPanel topPanel = new JPanel();
		setVertical(topPanel);
		topPanel.setAlignmentX((float) 0.1);
		JPanel bottomPanel = new JPanel();
		setVertical(bottomPanel);
		bottomPanel.setAlignmentX((float) 0.1);
		bottomPanel.setVisible(false);//hide parental controls by default
		JPanel buttonPanel = new JPanel();
		JPanel startScreentime = new JPanel();
		startScreentime.setLayout(new BoxLayout(startScreentime,BoxLayout.X_AXIS));
		JPanel endScreentime = new JPanel();
		endScreentime.setLayout(new BoxLayout(endScreentime,BoxLayout.X_AXIS));
		
		//add functionality to buttons
		backToMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SoundManager.play("button_sound.wav");
				bottomPanel.setVisible(false);//reset parental controls visibility
				ScreenManager.swapView("0");
			}
		});
		backToGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SoundManager.play("button_sound.wav");
				bottomPanel.setVisible(false);//reset parental controls visibility
				ScreenManager.swapView("5");
			}
		});
		passwordSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SoundManager.play("button_sound.wav");
				if (Arrays.equals(parentalPasswordEntry.getPassword(),parentalControlsPassword)) {
					bottomPanel.setVisible(true);
				}
			}
		});
		submitScreentimeSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SoundManager.play("button_sound.wav");
				parentalControls.setStartHr(((Integer) startHoursDropdown.getSelectedItem()).intValue());
				parentalControls.setStartMin(((Integer) startMinsDropdown.getSelectedItem()).intValue());
				parentalControls.setEndHr(((Integer) endHoursDropdown.getSelectedItem()).intValue());
				parentalControls.setEndMin(((Integer) endMinsDropdown.getSelectedItem()).intValue());
				try {
					objectMapper.writeValue(parentalControlsDataFile, parentalControls);
				}catch(Exception eWrite) {
					System.out.println("Error writing to parental controls data file");
				}
			}
		});
		//TODO maybe make pet a singleton as well?? silver bullet moment
		revivePet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(main.pet.getState()=="dead") {
					SoundManager.play("button_sound.wav");
					main.pet.revive();
					JLabel message = new JLabel("The pet is alive again.");
					JOptionPane.showMessageDialog(revivePet, message, "Notice", JOptionPane.OK_OPTION);
				}else {
					JLabel message = new JLabel("The pet is already alive.");
					JOptionPane.showMessageDialog(revivePet, message, "Notice", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		//add functionality to sliders
		bgmSlider.setValue(6);
		bgmSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				bgmText.setText("Music "+bgmSlider.getValue());
				SoundManager.gainControl.setValue(bgmSlider.getValue());
			}
		});
		//TODO separate bgm(music) and sfx(sound) volume control, or scrap the idea and have just a master volume
		
		//add elements to panel
		topPanel.add(bgmText);
		topPanel.add(bgmSlider);
		topPanel.add(sfxText);
		topPanel.add(sfxSlider);
		topPanel.add(parentalHeader);
		topPanel.add(parentalPasswordEntry);
		topPanel.add(passwordSubmit);
		bottomPanel.add(screentimeLabel);
		bottomPanel.add(startLabel);
		bottomPanel.add(startScreentime);
		startScreentime.add(startHoursDropdown);
		startScreentime.add(startMinsDropdown);
		bottomPanel.add(endLabel);
		bottomPanel.add(endScreentime);
		endScreentime.add(endHoursDropdown);
		endScreentime.add(endMinsDropdown);
		bottomPanel.add(submitScreentimeSettings);
		bottomPanel.add(playStatsTitle);
		bottomPanel.add(totalLabel);
		bottomPanel.add(totalPlaytime);
		bottomPanel.add(avgLabel);
		bottomPanel.add(avgPlaytime);
		bottomPanel.add(revivePet);
		buttonPanel.add(backToMainMenu);
		buttonPanel.add(backToGame);
		
		this.panel.add(topPanel);
		this.panel.add(bottomPanel);
		this.panel.add(buttonPanel);
	}
	
}
