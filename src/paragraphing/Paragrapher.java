package paragraphing;

public class Paragrapher implements ParagrapherI {

	//Sets the width for the paragraph.
	private int width;
	
	//Sequence of strings
	private ArrayList<String>[] lines; 
	
	//Destination object
	private DestinationI dest;
	
	
	public Paragrapher(DestinationI inDest) {
		this.width = 20;
		this.dest = indest;
		this.lines = new ArrayList();
	
	}
	
	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public void addWord(String[] parts) {
		for(int i = 0; i < parts.length; i++) {
			int partLength = parts[i].length();
			int lineLength = lines[(lines.size() - 1)].length();
			
			if(lineLength == 0) {
				// if it's the start of the line
				lines[lines.size() - 1] = lines[lines.size() - 1].concat(parts[i]);
				// if it's the first part and its too long, add hypen as well
				if (partLength >= this.width && i == 0) {
					lines[lines.size() - 1] = lines[lines.size() - 1].concat("-");
					lines.add("");
				}
			}
			// first part of a word, not first part of line
			else if (i == 0) {
				// check if it can fit on the line, including hyphen
				if (1 + partLength + 1 <= this.width) {
					lines[lines.size() - 1] = lines[lines.size() - 1].concat(" ").concat(parts[i]);
				}
			}
			else if (i < parts.length){
				// check if part can fit on current line plus possible hyphen
				if (lineLength + partLength + 1 <= width) {
					lines[lines.size() - 1] = lines[lines.size() - 1].concat(parts[i]);
				} else {
					lines[lines.size() - 1] = lines[lines.size() - 1].concat("-");
					lines.add(parts[i]);
				}
			}
			else {
				// last element
				if (lineLength + partLength <= width) {
					lines[lines.size() - 1] = lines[lines.size() - 1].concat(parts[i]);
				} else {
					lines.add(parts[i]);
				}
			}
			

	}

	@Override
	public void addWord(String word) {
		int wordLength = word.length();
		//Get the length of the current line.
		int lineLength = lines[(lines.size() - 1)].length();
		
		// Adding space and word needs to be <= length
		if(wordLength + lineLength + 1 <= this.width) {
			// can add the word
			lines[lines.size() - 1] = lines[lines.size() - 1].concat(" ").concat(word);
		} 
		else {
			lines.add(word);
		}
	}

	@Override
	public void ship() {
		// TODO Auto-generated method stub
		dest.addLines(lines.toArray());
	}

}
