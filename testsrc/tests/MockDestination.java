// Matthew Hickey + Nathaniel Johnson
package tests;

import paragraphing.DestinationI;

public class MockDestination implements DestinationI {
	
	private String[] lines = {};
	
	public MockDestination() {
	
	}
	

	@Override
	public void addLines(String[] lines) {
		// TODO Auto-generated method stub
		this.lines = lines;
	}
	
	public String[] result() {
		return this.lines;
	}
	
	
}
