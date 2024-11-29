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
		JLabel gameplayBasicsText = new JLabel("<html>Your creature has 4 stats: Health, Fullness, Happiness, and Sleep. Try your best to"
				+ "<br>keep these stats high. Play with your creature, feed your creature, or give gifts! "
				+ "<br>See what happens! But watch out: if you neglect your creature, its stats will go down."
				+ "<br>And when that happens, it may not want to play with you anymore...</html>");
		JLabel petStatusTitle = new JLabel("Pet Statuses");
		petStatusTitle.setFont(new Font("Serif", Font.PLAIN, 24));
		JLabel controlsTitle = new JLabel("Quick Controls");
		controlsTitle.setFont(new Font("Serif", Font.PLAIN, 24));
		JLabel controlsList = new JLabel("<html>P - pause <br> F - feed pet <br> G - give gift <br> Esc - return to main menu</html>");
		JLabel commandsTitle = new JLabel("Commands");
		commandsTitle.setFont(new Font("Serif", Font.PLAIN, 24));
		JButton backToMainMenu = new JButton("Back");
		
		//create sub-panels
		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
		JPanel rightPanel = new JPanel();
		rightPanel.setBorder(BorderFactory.createLineBorder(Color.gray,5));
		JPanel gameplayBasicsPanel = new JPanel();
		gameplayBasicsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
		JPanel petStatusPanel = new JPanel();
		petStatusPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
		JPanel petStatusSubPanel = new JPanel();
		//TODO add pictures on petStatusSubPanel
		JPanel gameControlsPanel = new JPanel();
		JPanel commandsPanel = new JPanel();
		//TODO add pictures on commandsPanel
		
		//add functionality to buttons
		backToMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenManager.swapView("0");
			}
		});
		
		//organize elements onto sub-panels
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.add(title);
		leftPanel.add(introText);
		leftPanel.add(gameplayBasicsPanel);
		leftPanel.add(petStatusPanel);
		leftPanel.add(gameControlsPanel);
		setVertical(gameplayBasicsPanel);
		gameplayBasicsPanel.add(gameplayBasicsTitle);
		gameplayBasicsPanel.add(gameplayBasicsText);
		setVertical(petStatusPanel);
		petStatusPanel.add(petStatusTitle);
		petStatusPanel.add(petStatusSubPanel);
		//add pictures onto petStatusSubPanel
		setVertical(gameControlsPanel);
		gameControlsPanel.add(controlsTitle);
		gameControlsPanel.add(controlsList);
		setVertical(rightPanel);
		rightPanel.add(commandsPanel);
		rightPanel.add(commandsTitle);
		commandsPanel.setLayout(new GridLayout(4,2));
		//add pictures onto commandsPanel
		
		//add sub-panels to main panel
		this.panel.add(leftPanel);
		this.panel.add(rightPanel);
		this.panel.add(backToMainMenu);
		}

}
