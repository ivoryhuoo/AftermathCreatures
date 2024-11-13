import java.awt.Color;

import javax.swing.*;

public class SaveSlot {
	JPanel panel;
	public SaveSlot(int saveSlotNumber) {
		//set up panel
		this.panel = new JPanel();
		this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		this.panel.setBackground(Color.lightGray);
		
		//create elements
		JLabel saveNum = new JLabel("Save Slot "+String.valueOf(saveSlotNumber));
		JPanel saveInfo = new JPanel();
		JLabel petName = new JLabel("pet name");
		JLabel playerScore = new JLabel("score: 0");
		JLabel playerMoney = new JLabel("money: 0");
		JLabel playTime = new JLabel("playtime: 00:00");
		
		//add subpanels to panels
		this.panel.add(saveNum);
		this.panel.add(saveInfo);
		//add elements to subpanels
		saveInfo.add(petName);
		saveInfo.add(playerScore);
		saveInfo.add(playerMoney);
		saveInfo.add(playTime);
	}
}
