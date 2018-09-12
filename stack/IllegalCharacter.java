package stack;

public class IllegalCharacter extends Exception{
    private String message = "IllegalCharacter ";
    private int index;
    private Character character;
    public IllegalCharacter(int index,Character c) {
    	this.index = index;
    	this.character = c;
    }
    public String toString() {
    	return this.message + this.character + " at index "+index;
    }
}
