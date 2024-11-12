import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JSlider;

public class SettingsScreen extends Screen{
	public SettingsScreen(){
		this.panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
		
		//create elements
		JLabel bgmText = new JLabel("Music");
		JSlider bgmSlider = new JSlider();
		JLabel sfxText = new JLabel("Sound Effects");
		JSlider sfxSlider = new JSlider();
		JLabel parentalHeader = new JLabel("Parental Controls");
		JPasswordField parentalPasswordEntry = new JPasswordField();
		
		
		//add elements to panel
		this.panel.add(bgmText);
		this.panel.add(bgmSlider);
		this.panel.add(sfxText);
		this.panel.add(sfxSlider);
		this.panel.add(parentalHeader);
		this.panel.add(parentalPasswordEntry);
	}
}
