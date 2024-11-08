import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.*;
public class TutorialScreen extends Screen{
	public TutorialScreen(){
		JLabel volText = new JLabel("Volume");
		JSlider volSlider = new JSlider();
		
		this.panel.add(volText);
		this.panel.add(volSlider);
		}
}
