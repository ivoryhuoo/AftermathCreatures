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
	    // Reset the coin balance to ensure a clean state
	    Coins.resetCoins();

	    // Set up the initial coin value
	    Coins.addCoins(10); // Start with 10 coins
	    MainGameScreen mainGameScreen = new MainGameScreen();

	    // Verify initial coin display
	    mainGameScreen.updateMoneyText();
	    assertEquals("Coins: 10", mainGameScreen.getMoneyText(), "Initial coin balance is incorrect.");

	    // Add coins and verify
	    Coins.addCoins(5); // Add 5 coins
	    mainGameScreen.updateMoneyText();
	    assertEquals("Coins: 15", mainGameScreen.getMoneyText(), "Updated coin balance is incorrect.");

	    // Spend coins and verify
	    Coins.spendCoins(7); // Spend 7 coins
	    mainGameScreen.updateMoneyText();
	    assertEquals("Coins: 8", mainGameScreen.getMoneyText(), "Coin balance after spending coins is incorrect.");
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
