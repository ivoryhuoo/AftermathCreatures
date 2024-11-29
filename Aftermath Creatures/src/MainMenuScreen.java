import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
public class MainMenuScreen extends Screen{
	public MainMenuScreen(){
		this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
	    this.panel.setAlignmentX(panel.CENTER_ALIGNMENT);
	    this.panel.setBorder(BorderFactory.createLineBorder(Color.blue));

	    // Ensure the coin timer starts when the game launches
	    Coins.startCoinTimer();

	    // Create title and buttons
	    JLabel title = new JLabel("Aftermath Creatures");
	    JButton newGameButton = new JButton("New Game");
	    JButton loadGameButton = new JButton("Load Game");
	    JButton tutorialButton = new JButton("Tutorial");
	    JButton settingsButton = new JButton("Settings");
	    JButton exitGameButton = new JButton("Exit Game");

	    // Add button events
	    newGameButton.addActionListener(e -> ScreenManager.swapView("1"));
	    loadGameButton.addActionListener(e -> ScreenManager.swapView("2"));
	    tutorialButton.addActionListener(e -> ScreenManager.swapView("4"));
	    settingsButton.addActionListener(e -> ScreenManager.swapView("3"));
	    exitGameButton.addActionListener(e -> System.exit(0));

	    // Adjust position and size of elements
	    title.setAlignmentX(JLabel.CENTER_ALIGNMENT);
	    title.setFont(new Font("Serif", Font.PLAIN, 50));
	    newGameButton.setPreferredSize(new Dimension(50, 250));
	    newGameButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
	    loadGameButton.setPreferredSize(new Dimension(50, 250));
	    loadGameButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
	    tutorialButton.setPreferredSize(new Dimension(50, 250));
	    tutorialButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
	    settingsButton.setPreferredSize(new Dimension(50, 250));
	    settingsButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
	    exitGameButton.setPreferredSize(new Dimension(50, 250));
	    exitGameButton.setAlignmentX(JButton.CENTER_ALIGNMENT);

	    // Add buttons to frame
	    this.panel.add(title);
	    this.panel.add(newGameButton);
	    this.panel.add(Box.createRigidArea(new Dimension(0, 30)));
	    this.panel.add(loadGameButton);
	    this.panel.add(Box.createRigidArea(new Dimension(0, 30)));
	    this.panel.add(tutorialButton);
	    this.panel.add(Box.createRigidArea(new Dimension(0, 30)));
	    this.panel.add(settingsButton);
	    this.panel.add(Box.createRigidArea(new Dimension(0, 30)));
	    this.panel.add(exitGameButton);
	}
}
