//Matthew Hickey + Nathaniel Johnson
package paragraphing;

import java.util.ArrayList;

public class Paragrapher implements ParagrapherI {

	//The width for the paragraph.
	private int width;
	
	//Sequence of strings
	private ArrayList<String> lines; 
	
	//Destination object
	private DestinationI dest;
	
	/**
	 * Constructor for paragraph. Paragraph is initialized with a default width. 
	 */
	public Paragrapher(DestinationI inDest) {
		this.width = 20;
		this.dest = inDest;
		this.lines = new ArrayList<String>();
	}
	
	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public void addWord(String[] parts) {
		
		//If only one part in array, this is a single word and will not be hyphenated.
		if (parts.length == 1) {
			addWord(parts[0]);
		}
		
		else {
			// iterate over each part of the word
			for (int i = 0; i < parts.length; i++) {
				int partLength = parts[i].length();
				int lineLength = getCurrentLineLength();
				
				if(lineLength == 0) {
					// if it's the start of the line, add the part
					addToLine(parts[i]);
					// if it's the first part and its too long, add hyphen as well
					if (partLength + 1 >= this.width && i == 0) {
						addToLine("-");
						//make a new line in the paragraph 
						lines.add("");
					}
				}
				// first part of a word, not first part of line
				else if (i == 0) {
					// check if it can fit on the line, including hyphen
					if (1 + partLength + 1 <= this.width) {
						addToLine(" ");
						addToLine(parts[i]);
					}
					else {
						lines.add(parts[i]);
					}
				}
				else if (i < parts.length){
					// check if part can fit on current line plus possible hyphen
					if (lineLength + partLength + 1 <= width) {
						addToLine(parts[i]);
					} else {
						addToLine("-");
						lines.add(parts[i]);
					}
				}
				else {
					// last element
					if (lineLength + partLength <= width) {
						addToLine(parts[i]);
					} else {
						lines.add(parts[i]);
					}
				}
			}
		}
	}
			

	@Override
	public void addWord(String word) {
		//Character length of word.
		int wordLength = word.length();
		
		//Character length of the current line.
		int lineLength = getCurrentLineLength();

		// Adding space and word needs to be <= width of paragraph lines
		if(wordLength + lineLength + 1 <= this.width) {
			// can add the word
			if (lineLength != 0) {
				addToLine(" ");
				addToLine(word);
			} else {
				//no space required as it's the start of a line
				addToLine(word);
			}
			
		} 
		else {
			lines.add(word);
		}
	}

	
	@Override
	public void ship() {
		ArrayList<String> empty = new ArrayList<>();
		empty.add("");
		// If nothing was copied, send an empty array, otherwise send the lines
		if (this.lines.equals(empty)) {
			this.dest.addLines(new String[] {});
		} else {
			this.dest.addLines(this.lines.toArray(new String[this.lines.size()]));
		}
	}
	
	/**
	 * Returns the character length of the last (current) line in paragraph. 
	 * If no lines, this method will initialize one.
	 */
	private int getCurrentLineLength() {
		if(this.lines.isEmpty) {
			lines.add("");
		}
		return this.lines.get(this.lines.size() - 1).length();
	}
	
	/**
	 * Append text to the current line.
	 * @param - text: String to be appended to the line
	 */
	private void addToLine(String text) {
		lines.set(lines.size() - 1, lines.get(lines.size() - 1).concat(text);
	}
}
