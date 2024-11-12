import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
public class TutorialScreen extends Screen{
	public TutorialScreen(){
		//create elements
		JLabel title = new JLabel("Tutorial");
		title.setFont(new Font("Serif", Font.PLAIN, 32));
		JLabel introText = new JLabel("Welcome to Aftermath Creatures! Here's how to take care of your creature:");
		JLabel gameplayBasicsTitle = new JLabel("Gameplay Basics");
		gameplayBasicsTitle.setFont(new Font("Serif", Font.PLAIN, 24));
		JLabel gameplayBasicsText1 = new JLabel("Your creature has 4 stats: Health, Fullness, Happiness, and Sleep. Please keep these stats high.");
		JLabel gameplayBasicsText2 = new JLabel("Play with your creature, feed your creature, and give your creature gifts! See what happens!");
		JLabel gameplayBasicsText3 = new JLabel("But watch out! If you leave your creature unattended, its stats will go down...");
		JLabel petStatusTitle = new JLabel("Pet Statuses");
		petStatusTitle.setFont(new Font("Serif", Font.PLAIN, 24));
		JLabel controlsTitle = new JLabel("Quick Controls");
		JLabel commandsTitle = new JLabel("Commands");
		commandsTitle.setFont(new Font("Serif", Font.PLAIN, 24));
		
		//create sub-panels
		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
		JPanel gameplayBasicsPanel = new JPanel();
		JPanel petStatusPanel = new JPanel();
		JPanel gameControlsPanel = new JPanel();
		JPanel commandsPanel = new JPanel();
		commandsPanel.setBorder(BorderFactory.createLineBorder(Color.gray,5));
		
		//organize elements onto sub-panels
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.add(title);
		leftPanel.add(introText);
		leftPanel.add(gameplayBasicsTitle);
		leftPanel.add(gameplayBasicsPanel);

		leftPanel.add(petStatusPanel);
		leftPanel.add(gameControlsPanel);
		gameplayBasicsPanel.setLayout(new BoxLayout(gameplayBasicsPanel, BoxLayout.Y_AXIS));
		gameplayBasicsPanel.add(gameplayBasicsText1);
		gameplayBasicsPanel.add(gameplayBasicsText2);
		gameplayBasicsPanel.add(gameplayBasicsText3);
		commandsPanel.add(commandsTitle);
		
		//add sub-panels to main panel
		this.panel.add(leftPanel);
		this.panel.add(commandsPanel);
		}
}
