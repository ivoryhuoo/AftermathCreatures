import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
public class Screen {
	JPanel panel;
	public Screen() {
		JPanel p =new JPanel();//creating instance of JFrame  
		this.panel=p;
//		this.panel.setSize(960, 720);
	}
	public void setVertical(JPanel p) {
		//basic helper method to set a vertical boxlayout
		p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
	}
	public void setH1(JLabel a) {
		//header 1
		a.setFont(new Font("Serif", Font.PLAIN, 42));
	}
	public void setH2(JLabel a) {
		//header 2
		a.setFont(new Font("Serif", Font.PLAIN, 24));
	}
}
