import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
public class MainGameScreen extends Screen{
	public MainGameScreen() {
		//set layout, setup subpanels
		this.panel.setLayout(new BorderLayout());
		JPanel header = new JPanel();
		header.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		panel.add(header, BorderLayout.NORTH);
		JPanel sidebar = new JPanel();
		sidebar.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		setVertical(sidebar);
		panel.add(sidebar, BorderLayout.WEST);
		JPanel footer = new JPanel();
		footer.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		panel.add(footer, BorderLayout.SOUTH);
		//pet goes in BorderLayout.CENTER
		
		//create elements
		JLabel curTime = new JLabel("17:25");
		JLabel petName = new JLabel("pet name");
		JLabel money = new JLabel("$0");
		JLabel score = new JLabel("Score: 0");
		setH2(curTime);
		setH2(petName);
		setH2(money);
		setH2(score);
		JLabel health = new JLabel("Health: 100/100");
		JLabel sleep = new JLabel("Sleep: 100/100");
		JLabel fullness = new JLabel("Fullness: 100/100");
		JLabel happiness = new JLabel("Happiness: 100/100");
		//footer icons go here
		JButton rest = new JButton("Rest");//placeholders
		JButton inventory = new JButton("Inventory");
		JButton doctor = new JButton("doctor");
		JButton market = new JButton("market");
		JButton minigames = new JButton("minigames");
		JButton menu = new JButton("Settings Menu");
		
		//add functionality to buttons
		rest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				//pet.goToBed();
			}
		});
		inventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				ScreenManager.swapView("6");
			}
		});
		doctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				//pet.takeToVet();
			}
		});
		market.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				ScreenManager.swapView("7");
			}
		});
		minigames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
//				ScreenManager.swapView("minigames code goes here");
			}
		});
		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				ScreenManager.swapView("3");
			}
		});
		
		//add elements to subpanels
		header.add(curTime);
		header.add(petName);
		header.add(money);
		header.add(score);
		sidebar.add(health);
		sidebar.add(sleep);
		sidebar.add(fullness);
		sidebar.add(happiness);
		//footer icons go here
		footer.add(rest);
		footer.add(inventory);
		footer.add(doctor);
		footer.add(market);
		footer.add(minigames);
		footer.add(menu);
	}
}
