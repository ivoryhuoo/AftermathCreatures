import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JLabel;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MainGameScreenTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		ScreenManager s = ScreenManager.getInstance();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testUpdatePetName() {
		main.pet = new Zombie("Jim");
		MainGameScreen m = new MainGameScreen();
		m.updatePetName();
		assertEquals(main.pet.getName(),m.petName.getText());
	}
	@Test
	void testUpdateCoins() {
		
	}
	@Test
	void testUpdateScore() {
		
	}
	@Test
	void testUpdateTime() {
		String expected = "12:49";//manually set time
		MainGameScreen m = new MainGameScreen();
		m.updateTime();
		assertEquals(expected,m.curTime.getText());
	}
	@Test
	void testUpdateHealth() {
		main.pet = new Zombie("Bartholomew");
		main.pet.setHealth(59);
		MainGameScreen m = new MainGameScreen();
		m.updateHealth();
		String expected = "Health: "+String.valueOf(main.pet.getHealth());
		String actual = m.health.getText();
		assertEquals(expected,actual);
	}
	@Test
	void testUpdateSleep() {
		main.pet = new Zombie("Gartholomew");
		main.pet.setSleep(29);
		MainGameScreen m = new MainGameScreen();
		m.updateSleep();
		String expected = "Sleep: "+String.valueOf(main.pet.getSleep());
		String actual = m.sleep.getText();
		assertEquals(expected,actual);
	}
	@Test
	void testUpdateFullness() {
		main.pet = new Zombie("Schwartholomew");
		main.pet.setFullness(57);
		MainGameScreen m = new MainGameScreen();
		m.updateFullness();
		String expected = "Fullness: "+String.valueOf(main.pet.getFullness());
		String actual = m.fullness.getText();
		assertEquals(expected,actual);
	}
	@Test
	void testUpdateHappiness() {
		main.pet = new Zombie("Ungus Bungus the 3rd");
		main.pet.setHappiness(19);
		MainGameScreen m = new MainGameScreen();
		m.updateHappiness();
		String expected = "Happiness: "+String.valueOf(main.pet.getHappiness());
		String actual = m.happiness.getText();
		assertEquals(expected,actual);
	}

}
