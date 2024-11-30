import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
public class TutorialScreen extends Screen{
	/**
	 * Screen showing tutorial information.
	 * @see		Screen
	 * @author 	Terry
	 */
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
		JLabel petStatusDesc = new JLabel("Normal Angry Hungry Sleeping Dead");
		JLabel controlsTitle = new JLabel("Quick Controls");
		controlsTitle.setFont(new Font("Serif", Font.PLAIN, 24));
		JLabel controlsList = new JLabel("<html> Esc - Pause and go to Settings <br>F - Feed the pet <br>S - Make the pet sleep <br>G - Give a gift <br>V - Take the pet to the vet<br>M - Use medicine</html>");
		JLabel commandsTitle = new JLabel("Commands");
		commandsTitle.setFont(new Font("Serif", Font.PLAIN, 24));
		JLabel commandsList = new JLabel("<html><b>Rest:</b> Restores sleep"
				+ "<br><b>Doctor:</b> Restores health"
				+ "<br><b>Market:</b> Buy food and gifts"
				+ "<br><b>Inventory:</b> Use food and gifts"
				+ "<br><b>Gift:</b> Increases happiness"
				+ "<br><b>Feed:</b> Restores hunger"
				+ "<br><b>Play:</b> Increases happiness"
				+ "<br><b>Exercise:</b> Restores health</html>");
		JButton backToMainMenu = new JButton("Back");
		//add pictures
		ImageIcon normalIcon = new ImageIcon("icons/normal.png");
		ImageIcon angryIcon = new ImageIcon("icons/angry.png");
		ImageIcon hungryIcon = new ImageIcon("icons/hungry.png");
		ImageIcon sleepingIcon = new ImageIcon("icons/sleeping.png");
		ImageIcon deadIcon = new ImageIcon("icons/dead.png");
		JLabel normal = new JLabel();normal.setIcon(normalIcon);
		JLabel angry = new JLabel();angry.setIcon(angryIcon);
		JLabel hungry = new JLabel();hungry.setIcon(hungryIcon);
		JLabel sleeping = new JLabel();sleeping.setIcon(sleepingIcon);
		JLabel dead = new JLabel();dead.setIcon(deadIcon);
		
		//create sub-panels
		JPanel leftPanel = new JPanel();
//		leftPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
		JPanel rightPanel = new JPanel();
//		rightPanel.setBorder(BorderFactory.createLineBorder(Color.gray,5));
		JPanel gameplayBasicsPanel = new JPanel();
		gameplayBasicsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
		JPanel petStatusPanel = new JPanel();
		petStatusPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
		JPanel petStatusSubPanel = new JPanel();
		JPanel gameControlsPanel = new JPanel();
		JPanel commandsPanel = new JPanel();
		//TODO add pictures on commandsPanel
		
		//add functionality to buttons
		backToMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SoundManager.play("button_sound.wav");
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
		petStatusSubPanel.add(normal);
		petStatusSubPanel.add(angry);
		petStatusSubPanel.add(hungry);
		petStatusSubPanel.add(sleeping);
		petStatusSubPanel.add(dead);
		petStatusPanel.add(petStatusDesc);
		
		setVertical(gameControlsPanel);
		gameControlsPanel.add(controlsTitle);
		gameControlsPanel.add(controlsList);
		
		setVertical(rightPanel);
		rightPanel.add(commandsPanel);
		rightPanel.add(commandsTitle);
		rightPanel.add(commandsList);
		rightPanel.add(backToMainMenu);
		
		//add sub-panels to main panel
		this.panel.setLayout(new FlowLayout());
		this.panel.add(leftPanel);
		this.panel.add(rightPanel);
		}

}
