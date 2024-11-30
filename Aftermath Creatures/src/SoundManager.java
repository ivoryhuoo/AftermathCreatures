import java.io.*;
import javax.sound.sampled.*;
/**
 * Singleton sound player class used to play all music/sound in the game.
 * @author 	Terry
 */
public class SoundManager {
	private static SoundManager single_instance = null;
	static Clip bgm;//music
	static Clip clip;//sound effects
	static FloatControl gainControl;
	static FloatControl soundControl;
	private SoundManager() {
		
	}
	/**
	 * Plays sound.
	 * <p>
	 * Use this one to play sound
	 * 
	 * @param 	fileName url of sound source
	 */
	public static void play(String fileName){ 
		// create AudioInputStream object 
		try {
			AudioInputStream audioInputStream = 
				AudioSystem.getAudioInputStream(new File(fileName).getAbsoluteFile()); 
		
			// create clip reference 
			clip = AudioSystem.getClip(); 
		
			// open audioInputStream to the clip 
			clip.open(audioInputStream); 
			soundControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			clip.start(); 
		}
		catch (Exception e) {
			//error
		}
	}
	/**
	 * Plays looping sound.
	 * <p>
	 * Use this one for music, option to loop or not
	 * 
	 * @param 	fileName url of sound source
	 * @param 	loop true if you want to loop the sound, false if you want to play once only 
	 */
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
			bgm.start(); 
			if(loop==true)bgm.loop(Clip.LOOP_CONTINUOUSLY);
		}
		catch (Exception e) {
			//error
		}
	}
	public static void setSoundVol(float volume) {
		soundControl.setValue(volume);
	}
	public static void setMusicVol(float volume) {
		gainControl.setValue(volume);
	}
	public static synchronized SoundManager getInstance() {
		//create singleton
        if (single_instance == null)
            single_instance = new SoundManager();
        //get reference to singleton
        return single_instance;
    }
}
