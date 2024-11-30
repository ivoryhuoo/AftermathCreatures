import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
/**
 * Basic display template using a JPanel
 */
public class Screen {
	JPanel panel;
//	String currentScreenNum;
	public Screen() {
		JPanel p =new JPanel();//creating instance of JFrame  
		this.panel=p;
//		this.panel.setSize(960, 720);
		
	}
	/**
	 * Changes panel to vertical layout
	 * <p>
	 * Sets the given JPanel to a new BoxLayout organized top to bottom vertically
	 * @param p 	the panel being changed 
	 */
	public void setVertical(JPanel p) {
		p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
	}
	/**
	 * Makes text much larger
	 * <p>
	 * Changes the text font of a given JLabel to a larger size 
	 * @param a		the text box being changed
	 */
	public void setH1(JLabel a) {
		//header 1
		a.setFont(new Font("Serif", Font.PLAIN, 42));
	}
	/**
	 * Makes text a bit larger
	 * <p>
	 * Changes the text font of a given JLabel to a larger size 
	 * @param a		the text box being changed
	 */
	public void setH2(JLabel a) {
		//header 2
		a.setFont(new Font("Serif", Font.PLAIN, 24));
	}
}
