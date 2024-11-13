import java.awt.Color;

import javax.swing.*;

public class SaveSlot {
	JPanel panel;
	public SaveSlot(int saveSlotNumber) {
		this.panel = new JPanel();
		this.panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		this.panel.setBackground(Color.lightGray);
		JLabel saveNum = new JLabel("Save Slot "+String.valueOf(saveSlotNumber));
		
		JPanel saveInfo = new JPanel();
		JLabel petName = new JLabel("pet name");
		JLabel playerScore = new JLabel("score: 0");
		JLabel playerMoney = new JLabel("money: 0");
		JLabel playTime = new JLabel("playtime: 00:00");
		
		this.panel.add(saveNum);
		this.panel.add(saveInfo);
		saveInfo.add(petName);
		saveInfo.add(playerScore);
		saveInfo.add(playerMoney);
		saveInfo.add(playTime);
	}
}
