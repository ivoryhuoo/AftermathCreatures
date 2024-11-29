import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class testMainMenuScreen {

	@Test
	public void testIsBefore1() {
		MainMenuScreen m = new MainMenuScreen();
		//compare 0:00 and 7:15
		int h1=0;
		int m1=0;
		int h2=7;
		int m2=15;
		assertTrue(m.isBefore(h1,m1,h2,m2));
	}
	@Test
	public void testIsBefore2() {
		MainMenuScreen m = new MainMenuScreen();
		//compare 10:00 and 7:15
		int h1=10;
		int m1=0;
		int h2=7;
		int m2=15;
		assertFalse(m.isBefore(h1,m1,h2,m2));
	}

}
