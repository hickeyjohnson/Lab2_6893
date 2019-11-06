package tests;

import static org.junit.Assert.*;
import org.junit.Test;

import paragraphing.Paragrapher;
import paragraphing.ParagrapherI;

public class MattHickeyTest {
	
	@Test
	public void testEmpty() {
		MockDestination destination = new MockDestination();
		ParagrapherI p = new Paragrapher(destination);
		p.ship();
		String[] expected = new String[] {};
		assertArrayEquals(expected, destination.result());
	}
	
	@Test
	public void fullLine() {
		MockDestination destination = new MockDestination();
		ParagrapherI p = new Paragrapher(destination);
		p.setWidth("Matthew Hickey".length());
		String[] myName = {"Matthew Hickey"};
		p.addWord("Matthew");
		p.addWord("Hickey");
		p.ship();
		assertArrayEquals(myName, destination.result());
	}
	
	@Test
	public void hyphenationWord() {
		String[] hyphenation = {"hyph", "en", "ation"};
		MockDestination destination = new MockDestination();
		ParagrapherI p = new Paragrapher(destination);
		p.setWidth(10);
		p.addWord("I");
		p.addWord("am");
		p.addWord(hyphenation);
		p.ship();
		String[] expected = {
				"I am hyph-",
				"enation"
		};
		assertArrayEquals(expected, destination.result());
	}
	
	@Test
	public void extendLine() {
		MockDestination destination = new MockDestination();
		ParagrapherI p = new Paragrapher(destination);
		p.setWidth(10);
		p.addWord("I");
		p.addWord("Love");
		p.addWord("Hyphenation");
		p.ship();
		String[] expected = {
				"I Love",
				"Hyphenation"
		};
		assertArrayEquals(expected, destination.result());
	}

}
