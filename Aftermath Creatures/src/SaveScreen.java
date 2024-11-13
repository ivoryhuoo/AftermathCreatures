import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
public class SaveScreen extends Screen{
	public SaveScreen() {
		//create saveslot objects
		SaveSlot saveSlot1 = new SaveSlot(1);
		SaveSlot saveSlot2 = new SaveSlot(2);
		SaveSlot saveSlot3 = new SaveSlot(3);

		//place saveslot panels onto savescreen panel
		setVertical(this.panel);
		this.panel.add(saveSlot1.panel);
		this.panel.add(Box.createRigidArea(new Dimension(0, 30)));
		this.panel.add(saveSlot2.panel);
		this.panel.add(Box.createRigidArea(new Dimension(0, 30)));
		this.panel.add(saveSlot3.panel);
	}
}
