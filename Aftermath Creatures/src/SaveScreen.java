import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
public class SaveScreen extends Screen{
	public SaveScreen() {
		JButton saveSlot1 = new JButton();
		JButton saveSlot2 = new JButton();
		JButton saveSlot3 = new JButton();

		this.panel.add(saveSlot1);
		this.panel.add(saveSlot2);
		this.panel.add(saveSlot3);
	}
}
