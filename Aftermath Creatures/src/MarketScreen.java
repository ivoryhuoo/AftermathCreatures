import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
public class MarketScreen extends Screen{
	public MarketScreen() {
		JLabel title = new JLabel("Market");
		setH1(title);
		title.setAlignmentX((float) 0.5);
		JPanel foodPanel = new JPanel();
		JPanel giftPanel = new JPanel();
		JPanel medsPanel = new JPanel();
		JTabbedPane bottomPanel = new JTabbedPane();
		JButton item1 = new JButton("a");
		JButton item2 = new JButton("b");
		JButton item3 = new JButton("c");
		JButton item4 = new JButton("d");
		JButton backToGame = new JButton("Back");
		
		//set layouts
		setVertical(this.panel);
		foodPanel.setLayout(new GridLayout(3,3));
		giftPanel.setLayout(new GridLayout(3,3));
		medsPanel.setLayout(new GridLayout(3,3));
		
		//add functionality to buttons
		backToGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScreenManager.swapView("5");
			}
		});
		
		//add subpanel's to main panel
		this.panel.add(title);
		this.panel.add(bottomPanel);
		bottomPanel.addTab("Food",foodPanel);//default
		bottomPanel.addTab("Gift",giftPanel);
		bottomPanel.addTab("Meds", medsPanel);
		this.panel.add(backToGame);
	}
	

	}


