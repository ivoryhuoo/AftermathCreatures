import java.io.*;
import javax.sound.sampled.*;

public class SoundManager {
	private static SoundManager single_instance = null;
	float soundVolume;	
	static Clip bgm;
	static FloatControl gainControl;
	private SoundManager() {
		
	}
	public static void play(String fileName){ 
		// create AudioInputStream object 
		try {
			AudioInputStream audioInputStream = 
				AudioSystem.getAudioInputStream(new File(fileName).getAbsoluteFile()); 
		
			// create clip reference 
			Clip clip = AudioSystem.getClip(); 
		
			// open audioInputStream to the clip 
			clip.open(audioInputStream); 
			clip.start(); 
		}
		catch (Exception e) {
			//error
		}
	}
	//looping version
	public static void play(String fileName, boolean loop) {
		// create AudioInputStream object 
		try {
			AudioInputStream audioInputStream = 
				AudioSystem.getAudioInputStream(new File(fileName).getAbsoluteFile()); 
		
			// create clip reference 
			bgm = AudioSystem.getClip(); 
		
			// open audioInputStream to the clip 
			bgm.open(audioInputStream); 
			gainControl = (FloatControl)bgm.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(1);
			bgm.start(); 
			if(loop==true)bgm.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch (Exception e) {
			//error
		}
	}
	
//	// Method to set volume
//    public static void setVolume(int value) {
//    	gainControl = (FloatControl) bgm.getControl(FloatControl.Type.MASTER_GAIN);
//    	gainControl.setValue(value/100f);
//    }
	
	//create singleton
	public static synchronized SoundManager getInstance()
    {
        if (single_instance == null)
            single_instance = new SoundManager();
 
        return single_instance;
    }
}
