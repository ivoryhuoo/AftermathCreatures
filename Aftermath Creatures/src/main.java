import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalTime;
import java.util.*;
import java.awt.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.*;

public class main {
	static PlaytimeData playtimeData;
	static File playtimeDataFile = new File("playtimeData.json");
	public static Date playSessionStartTime;
	static Pet pet;
	
	public static void main(String[] args) {
		//set up frame
		JFrame f = new JFrame("Aftermath Creatures");
		f.setSize(960, 720);
		ScreenManager s = ScreenManager.getInstance();
		SoundManager soundManager = SoundManager.getInstance();
		f.add(ScreenManager.currentScreen);//???
		f.setVisible(true);
		//terminate when window closes
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//read data from file to PlaytimeData object
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			playtimeData = objectMapper.readValue(playtimeDataFile, PlaytimeData.class);
		}catch(Exception eRead) {
			System.out.println("Error reading playtime data file");
		}

		//update play session counter
		incrementPlaySessions();
		
		//calculate playtime for this session
		f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                calculatePlayTime();
                try {
                	objectMapper.writeValue(playtimeDataFile,playtimeData);
                }catch(Exception eWrite) {
                	System.out.println("Error writing to playtime data file");
                }
                System.exit(0);
            }
        });
		
		//mark start of play session for playtime duration calculation
		playSessionStartTime = new Date();
		
		//game loop
		while(true) {
			System.out.println("coconut tf2");//DISPLAY BREAKS WITHOUT THIS LINE
			if(ScreenManager.currentScreenNum.equals("5")) {
				//update values on main game screen
				ScreenManager.mainGameScreen.updatePetName();
//				ScreenManager.mainGameScreen.updateCoins(); //deprecated
				ScreenManager.mainGameScreen.updateScore();
				ScreenManager.mainGameScreen.updateTime();
				ScreenManager.mainGameScreen.updateHealth();
				ScreenManager.mainGameScreen.updateSleep();
				ScreenManager.mainGameScreen.updateFullness();
				ScreenManager.mainGameScreen.updateHappiness();
				ScreenManager.mainGameScreen.updateIcon();
				ScreenManager.mainGameScreen.animatePet();
			}
		}
	}
	public static void incrementPlaySessions() {
		int n = playtimeData.getPlaySessions();
		n+=1;
		playtimeData.setPlaySessions(n);
	}
	public static void calculatePlayTime() {
		Date playSessionEndTime = new Date();
		long playTimeDuration = playSessionEndTime.getTime() - playSessionStartTime.getTime();
		long total = playtimeData.getTotalPlaytime()+playTimeDuration;
		playtimeData.setTotalPlaytime(total);
	}
}
