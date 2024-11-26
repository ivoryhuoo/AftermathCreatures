import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.time.LocalTime;
import java.util.*;
import java.awt.*;
public class main {
	int playSessions;
	public static Date playSessionStartTime;
	public static void main(String[] args) {
		//set up frame
		JFrame f = new JFrame("Aftermath Creatures");
		f.setSize(960, 720);
		ScreenManager s = ScreenManager.getInstance();
		f.add(ScreenManager.currentScreen);//???
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//terminate when window closes
		f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                calculatePlayTime();
                System.exit(0);
            }
        });
		
		playSessionStartTime = new Date();
		System.out.println(playSessionStartTime);
	}
	public void incrementPlaySessions() {
		
	}
	public static void calculatePlayTime() {
		Date playSessionEndTime = new Date();
		long playTimeDuration = playSessionEndTime.getTime() - playSessionStartTime.getTime();
		System.out.println(playTimeDuration);//testing
	}
}
