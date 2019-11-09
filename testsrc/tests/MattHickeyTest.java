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
		// If a word is too long to fit on a line and it's the start, fit anyway
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
	
	@Test
	public void givenExample1() {
		MockDestination destination = new MockDestination();
		ParagrapherI p = new Paragrapher(destination);
		p.setWidth(7);
		p.addWord("This");
		p.addWord("is");
		p.addWord("a");
		p.addWord(new String[] {"para", "graph"});
		p.ship();
		String[] expected = {
				"This is",
				"a para-",
				"graph"
		};
		assertArrayEquals(expected, destination.result());
		
	}
	
	@Test
	public void givenExample2() {
		// One word, two hyphenation points.
		// Tests the decision to shorten line because hyphen would make it too long.
		MockDestination destination = new MockDestination();
		ParagrapherI p = new Paragrapher(destination);
		p.setWidth(5);
		p.addWord(new String[] {"123", "ab", "xy"});
		p.ship();
		String[] expected = {
				"123-",
				"abxy"
		};
		assertArrayEquals(expected, destination.result());
	}

}
