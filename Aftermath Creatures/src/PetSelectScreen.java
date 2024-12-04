import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
/**
 * Screen where player selects and creates a new pet
 * @see		Screen
 * @author 	Terry
 */
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
		JButton backToMainMenu = new JButton("Back to Main Menu");
		JTextField namePetLabel = new JTextField("Name of your pet");
		
		zombiePanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		humanPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		robotPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		
		//add sprites
		JLabel zombieSprite = new JLabel();
		JLabel humanSprite = new JLabel();
		JLabel robotSprite = new JLabel();
		ImageIcon zombie = new ImageIcon("sprites/NormalZombie.png");
		ImageIcon human = new ImageIcon("sprites/NormalHuman.png");
		ImageIcon robot = new ImageIcon("sprites/NormalRobot.png");
		zombieSprite.setIcon(zombie);
		humanSprite.setIcon(human);
		robotSprite.setIcon(robot);
		
		//add elements to panels
		setVertical(zombiePanel);
		zombiePanel.add(zombieSprite);
		zombiePanel.add(zombieTitle);
		zombiePanel.add(zombieDesc);
		setVertical(humanPanel);
		humanPanel.add(humanSprite);
		humanPanel.add(humanTitle);
		humanPanel.add(humanDesc);
		setVertical(robotPanel);
		robotPanel.add(robotSprite);
		robotPanel.add(robotTitle);
		robotPanel.add(robotDesc);
		
		//add eventlisteners to panels and buttons
		zombiePanel.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				SoundManager.play("button_sound.wav");
				String petNameInput = JOptionPane.showInputDialog("Name your pet:");
		        if (petNameInput != null && !petNameInput.trim().isEmpty()) {
		            main.pet = new Zombie(petNameInput.trim());
		            ScreenManager.mainGameScreen.resetPetState();
		            ScreenManager.mainGameScreen.updatePetName();
		            ScreenManager.mainGameScreen.updateShortcuts();
		            ScreenManager.swapView("5");
		        }
			}
		});
		humanPanel.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				SoundManager.play("button_sound.wav");
				String petNameInput = JOptionPane.showInputDialog("Name your pet:");
		        if (petNameInput != null && !petNameInput.trim().isEmpty()) {
		            main.pet = new Human(petNameInput.trim());
		            ScreenManager.mainGameScreen.resetPetState();
		            ScreenManager.mainGameScreen.updatePetName();
		            ScreenManager.mainGameScreen.updateShortcuts();
		            ScreenManager.swapView("5");
		        }
			}
		});
		robotPanel.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				SoundManager.play("button_sound.wav");
				String petNameInput = JOptionPane.showInputDialog("Name your pet:");
		        if (petNameInput != null && !petNameInput.trim().isEmpty()) {
		            main.pet = new Robot(petNameInput.trim());
		            ScreenManager.mainGameScreen.resetPetState();
		            ScreenManager.mainGameScreen.updatePetName();
		            ScreenManager.mainGameScreen.updateShortcuts();
		            ScreenManager.swapView("5");
		        }
			}
		});
		backToMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SoundManager.play("button_sound.wav");
				ScreenManager.swapView("0");
			}
		});
		
		//organize and add subpanels to main panel
		petsPanel.add(zombiePanel);
		petsPanel.add(humanPanel);
		petsPanel.add(robotPanel);
		setVertical(this.panel);
		this.panel.add(title);
		this.panel.add(petsPanel);
		title.setAlignmentX((float)0.5);
		petsPanel.setAlignmentX((float)0.5);
		this.panel.add(backToMainMenu);
		backToMainMenu.setAlignmentX((float)0.1);
		
		// Integrate keyboard shortcut 
		setupKeyboardShortcuts();
	}
	
	/**
	 * Setup keyboard shortcuts
	 */
	private void setupKeyboardShortcuts() {
	    // Global shortcut: ESC navigates to the settings menu
	    KeyboardShortcutManager.setupGlobalShortcuts(this.panel);

	    // Ensure the panel gains focus for shortcuts
	    this.panel.requestFocusInWindow();
	}
}
