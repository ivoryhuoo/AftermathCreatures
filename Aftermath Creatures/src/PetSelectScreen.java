import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
public class PetSelectScreen extends Screen{
	public PetSelectScreen() {
		//create elements
		JLabel title = new JLabel("Select Your Pet");
		JPanel petsPanel = new JPanel();
		JPanel zombiePanel = new JPanel();
		JLabel zombieTitle = new JLabel("Zombie");
		JLabel zombieDesc = new JLabel("A resilient pet that survives on minimal care but gets moody without attention.");
		JPanel humanPanel = new JPanel();
		JLabel humanTitle = new JLabel("Human");
		JLabel humanDesc = new JLabel("A vulnerable survivor needing diverse interactions to stay happy.");
		JPanel robotPanel = new JPanel();
		JLabel robotTitle = new JLabel("Robot");
		JLabel robotDesc = new JLabel("A reliable pet, constructed from salvaged parts. It is sturdy and reliable.");
		
		//add elements to panels
		setVertical(zombiePanel);
		//zombie image goes here
		zombiePanel.add(zombieTitle);
		zombiePanel.add(zombieDesc);
		setVertical(humanPanel);
		//human image goes here
		humanPanel.add(humanTitle);
		humanPanel.add(humanDesc);
		setVertical(robotPanel);
		//robot image goes here
		robotPanel.add(robotTitle);
		robotPanel.add(robotDesc);
		
		//organize and add subpanels to main panel
		petsPanel.setLayout(new BoxLayout(petsPanel,BoxLayout.X_AXIS));
		petsPanel.add(zombiePanel);
		petsPanel.add(humanPanel);
		petsPanel.add(robotPanel);
		setVertical(this.panel);
		this.panel.add(title);
		this.panel.add(petsPanel);
	}
}
