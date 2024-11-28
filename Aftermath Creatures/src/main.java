import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalTime;
import java.util.*;
import java.awt.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.*;
public class main {
	int playSessions;
	public static Date playSessionStartTime;
	public static void main(String[] args) {
		//set up frame
		JFrame f = new JFrame("Aftermath Creatures");
		f.setSize(960, 720);
		ScreenManager s = ScreenManager.getInstance();
		SoundManager soundManager = SoundManager.getInstance();
		f.add(ScreenManager.currentScreen);//???
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//terminate when window closes
		
		//update play session counter
		incrementPlaySessions();
		
		//calculate playtime for this session
		f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                calculatePlayTime();
                System.exit(0);
            }
        });
		
		playSessionStartTime = new Date();
	}
	public static void incrementPlaySessions() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode jsonNode = objectMapper.readTree("playtimeData.json");
			int playSessions = jsonNode.get("Play Sessions").asInt();
			playSessions+=1;
			objectMapper.writeValue(new File("playtimeData.json"), playSessions);
		}catch(Exception e) {
			System.out.println("oops");
		}
	}
	public static void calculatePlayTime() {
		Date playSessionEndTime = new Date();
		long playTimeDuration = playSessionEndTime.getTime() - playSessionStartTime.getTime();
		System.out.println(playTimeDuration);//testing, replace this line with writing to file
	}
}
