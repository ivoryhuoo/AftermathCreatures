import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PlaytimeDataTest {

	@Test
	public void testSetPlaySessions() {
		PlaytimeData playtimeData = new PlaytimeData();
		playtimeData.setPlaySessions(0);
		assertNotNull(playtimeData.getPlaySessions());
	}
	@Test
	public void testSetTotalPlaytime() {
		PlaytimeData playtimeData = new PlaytimeData();
		playtimeData.setTotalPlaytime(0);
		assertNotNull(playtimeData.getTotalPlaytime());
	}
	@Test
	public void testGetPlaySessions() {
		PlaytimeData playtimeData = new PlaytimeData();
		playtimeData.setPlaySessions(0);
		playtimeData.setTotalPlaytime(0);
		int expected = 0;
		int actual = playtimeData.getPlaySessions();
		assertEquals(expected,actual);
	}
	@Test
	public void testGetTotalPlaytime() {
		PlaytimeData playtimeData = new PlaytimeData();
		playtimeData.setPlaySessions(0);
		playtimeData.setTotalPlaytime(0);
		long expected = 0;
		long actual = playtimeData.getTotalPlaytime();
		assertEquals(expected,actual);
	}

}
