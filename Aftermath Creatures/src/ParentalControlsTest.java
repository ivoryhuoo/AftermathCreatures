import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ParentalControlsTest {

	@Test
	public void testSetStartHr() {
		ParentalControls parentalControls = new ParentalControls();
		parentalControls.setStartHr(0);
		assertNotNull(parentalControls.getStartHr());
	}
	@Test
	public void testSetStartMin() {
		ParentalControls parentalControls = new ParentalControls();
		parentalControls.setStartMin(0);
		assertNotNull(parentalControls.getStartMin());
	}
	@Test
	public void testSetEndHr() {
		ParentalControls parentalControls = new ParentalControls();
		parentalControls.setEndHr(0);
		assertNotNull(parentalControls.getEndHr());
	}
	@Test
	public void testSetEndMin() {
		ParentalControls parentalControls = new ParentalControls();
		parentalControls.setEndMin(0);
		assertNotNull(parentalControls.getEndMin());
	}
	@Test
	public void testGetStartHr() {
		ParentalControls parentalControls = new ParentalControls();
		parentalControls.setStartHr(10);
		int expected = 10;
		int actual = parentalControls.getStartHr();
		assertEquals(expected,actual);
	}
	@Test
	public void testGetStartMin() {
		ParentalControls parentalControls = new ParentalControls();
		parentalControls.setStartMin(15);
		int expected = 15;
		int actual = parentalControls.getStartMin();
		assertEquals(expected,actual);
	}
	@Test
	public void testGetEndHr() {
		ParentalControls parentalControls = new ParentalControls();
		parentalControls.setEndHr(10);
		int expected = 10;
		int actual = parentalControls.getEndHr();
		assertEquals(expected,actual);
	}
	@Test
	public void testGetEndMin() {
		ParentalControls parentalControls = new ParentalControls();
		parentalControls.setEndMin(15);
		int expected = 15;
		int actual = parentalControls.getEndMin();
		assertEquals(expected,actual);
	}

}
