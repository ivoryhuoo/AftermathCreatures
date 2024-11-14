import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
public class InventoryScreen extends Screen{
	public InventoryScreen() {
		//add header from main game screen??
		
		//create elements
		JLabel title = new JLabel("Inventory");
		JPanel buttonPanel = new JPanel();
		JPanel foodPanel = new JPanel();
		JPanel giftPanel = new JPanel();
		JPanel medsPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		JButton foodButton = new JButton("Food");
		JButton giftButton = new JButton("Gift");
			
		
		//set layouts
		setVertical(this.panel);
		foodPanel.setLayout(new GridLayout(3,3));
		giftPanel.setLayout(new GridLayout(3,3));
		medsPanel.setLayout(new GridLayout(3,3));
		
		//testing
		foodPanel.add(new JLabel("temp1"));
		giftPanel.add(new JLabel("temp2"));
		
		//add elements to subpanels
		buttonPanel.add(foodButton);
		buttonPanel.add(giftButton);
		
		//add subpanels to main panel
		this.panel.add(title);
		this.panel.add(buttonPanel);
		this.panel.add(bottomPanel);
		bottomPanel.add(foodPanel);//default
		
		//swap panels when button is clicked
		class switchGift implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				bottomPanel.remove(0);
				bottomPanel.add(giftPanel);
				//doesn't work yet
			}
		}
		giftButton.addActionListener(new switchGift());
	}
	
}
