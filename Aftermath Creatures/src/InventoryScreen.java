import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
public class InventoryScreen extends Screen{
	public InventoryScreen() {
		//add header from main game screen??
		
		//create elements
		JLabel title = new JLabel("Inventory");
		setH1(title);
		title.setAlignmentX((float) 0.5);
		JPanel foodPanel = new JPanel();
		JPanel giftPanel = new JPanel();
		JPanel medsPanel = new JPanel();
		JTabbedPane bottomPanel = new JTabbedPane();
		JButton item1 = new JButton("a");
		JButton item2 = new JButton("b");
		JButton item3 = new JButton("b");
		JButton item4 = new JButton("b");
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
		
		//add elements to subpanels
		foodPanel.add(item1);
		foodPanel.add(item2);
		foodPanel.add(item3);
		foodPanel.add(item4);
		
		//add subpanels to main panel
		this.panel.add(title);
		this.panel.add(bottomPanel);
		bottomPanel.addTab("Food",foodPanel);//default
		bottomPanel.addTab("Gift",giftPanel);
		bottomPanel.addTab("Meds", medsPanel);
		this.panel.add(backToGame);
	}
	
}
