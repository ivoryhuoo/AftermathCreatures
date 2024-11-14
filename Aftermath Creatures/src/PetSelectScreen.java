import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
public class PetSelectScreen extends Screen{
	public PetSelectScreen() {
		//create elements
		JLabel title = new JLabel("Select Your Pet");
		setH1(title);
		JPanel petsPanel = new JPanel();
		JPanel zombiePanel = new JPanel();
		JLabel zombieTitle = new JLabel("Zombie");
		setH2(zombieTitle);
		JLabel zombieDesc = new JLabel("<html>A resilient pet that survives on minimal care<br> but gets moody without attention.</html>");
		JPanel humanPanel = new JPanel();
		JLabel humanTitle = new JLabel("Human");
		setH2(humanTitle);
		JLabel humanDesc = new JLabel("<html>A vulnerable survivor needing<br> diverse interactions to stay happy.</html>");
		JPanel robotPanel = new JPanel();
		JLabel robotTitle = new JLabel("Robot");
		setH2(robotTitle);
		JLabel robotDesc = new JLabel("<html>A reliable pet, constructed from salvaged parts.<br> It is sturdy and reliable.</html>");
		
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
		
		//add eventlisteners to panels
		zombiePanel.addMouseListener(null);
		
		//organize and add subpanels to main panel
		petsPanel.add(zombiePanel);
		petsPanel.add(humanPanel);
		petsPanel.add(robotPanel);
		setVertical(this.panel);
		this.panel.add(title);
		this.panel.add(petsPanel);
		title.setAlignmentX((float)0.5);
		petsPanel.setAlignmentX((float)0.5);
	}
}
