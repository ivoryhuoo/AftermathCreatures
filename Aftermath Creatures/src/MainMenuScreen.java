import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
public class MainMenuScreen extends Screen{
	public MainMenuScreen(){
		this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
		
		//create title and buttons
		JLabel title = new JLabel("Aftermath Creatures");//may replace with something prettier
		JButton newGameButton = new JButton("New Game");
		JButton loadGameButton = new JButton("Load Game");
		JButton tutorialButton = new JButton("Tutorial");
		JButton settingsButton = new JButton("Settings");
		JButton exitGameButton = new JButton("Exit Game");
		
		//adjust position and size of buttons
		newGameButton.setPreferredSize(new Dimension(250,250));
		loadGameButton.setPreferredSize(new Dimension(50,50));
		tutorialButton.setPreferredSize(new Dimension(50,50));
		settingsButton.setPreferredSize(new Dimension(50,50));
		exitGameButton.setPreferredSize(new Dimension(50,50));
		
		//add buttons to frame
		this.panel.add(title);
		this.panel.add(newGameButton);
		this.panel.add(loadGameButton);
		this.panel.add(tutorialButton);
		this.panel.add(settingsButton);
		this.panel.add(exitGameButton);
	}
}
