import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
public class main {

	public static void main(String[] args) {
		JFrame f = new JFrame("Aftermath Creatures");
		f.setSize(960, 720);
		ScreenManager s = ScreenManager.getInstance();
		f.add(ScreenManager.currentScreen);//???
		
		//note: this has to be at the end
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//terminate when window closes
		
	}
}
