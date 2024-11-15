import javax.swing.BoxLayout;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SettingsScreen extends Screen{
	public SettingsScreen(){
		this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
		
		//create elements
		JLabel bgmText = new JLabel("Music");
		JSlider bgmSlider = new JSlider();
		bgmSlider.setMaximumSize(new Dimension(500,20));
		JLabel sfxText = new JLabel("Sound Effects");
		JSlider sfxSlider = new JSlider();
		sfxSlider.setMaximumSize(new Dimension(500,20));
		JLabel parentalHeader = new JLabel("Parental Controls");
		JPasswordField parentalPasswordEntry = new JPasswordField();
		parentalPasswordEntry.setMaximumSize(new Dimension(100,30));
		JLabel screentimeLabel = new JLabel("Screentime Restrictions");
		JLabel playStatsTitle = new JLabel("Statistics");
		JLabel totalLabel = new JLabel("Total Playtime");
		setH2(totalLabel);
		JLabel totalPlaytime = new JLabel("");//change?
		JLabel avgLabel = new JLabel("Average Playtime");
		setH2(avgLabel);
		JLabel avgPlaytime = new JLabel("");//change?
		//navigation buttons
		JButton backToMainMenu = new JButton("Back to Main Menu");
		JButton backToGame = new JButton("Back to Game");
		//hide backToGame if game savefile is not loaded
		if(true) {
			backToGame.setVisible(false);
		}
		
		//create subpanels
		JPanel topPanel = new JPanel();
		setVertical(topPanel);
		topPanel.setAlignmentX((float) 0.1);
		JPanel bottomPanel = new JPanel();
		setVertical(bottomPanel);
		bottomPanel.setAlignmentX((float) 0.1);
		bottomPanel.setVisible(false);//hide parental controls by default
		JPanel buttonPanel = new JPanel();
		
		//add functionality to buttons
		backToMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenManager.swapView("0");
			}
		});
		backToGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenManager.swapView("5");
			}
		});
		
		//add elements to panel
		topPanel.add(bgmText);
		topPanel.add(bgmSlider);
		topPanel.add(sfxText);
		topPanel.add(sfxSlider);
		topPanel.add(parentalHeader);
		topPanel.add(parentalPasswordEntry);
		bottomPanel.add(screentimeLabel);
		bottomPanel.add(playStatsTitle);
		bottomPanel.add(totalLabel);
		bottomPanel.add(totalPlaytime);
		bottomPanel.add(avgLabel);
		bottomPanel.add(avgPlaytime);
		buttonPanel.add(backToMainMenu);
		buttonPanel.add(backToGame);
		
		this.panel.add(topPanel);
		this.panel.add(bottomPanel);
		this.panel.add(buttonPanel);
	}
	
}
