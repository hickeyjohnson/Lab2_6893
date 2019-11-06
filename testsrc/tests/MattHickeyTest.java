package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import paragraphing.ParagrapherI;

public class MattHickeyTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testEmpty() {
		MockDestination destination = new MockDestination();
		ParagrapherI p = new Paragrapher(destination);
		p.ship();
		String[] expected = new String[] {};
		assertArrayEquals(expected, destination.result());
	}

}
