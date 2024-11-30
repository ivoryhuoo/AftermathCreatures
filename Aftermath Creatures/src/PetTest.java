import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PetTest {

	@Test
	void testZombieCreation() {
		Pet charles = new Zombie("Charles");
		assertNotNull(charles);
	}
	@Test
	void testZombieNameAccess() {
		Pet charles = new Zombie("Charles");
		String expected = "Charles";
		String actual = charles.getName();
		assertEquals(expected,actual);
	}

}
