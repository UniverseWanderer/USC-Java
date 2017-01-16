// Name: Deyu Ma
// USC loginid: deyuma@usc.edu
// CS 455 PA4
// Fall 2016

import java.util.ArrayList;

/**
 * Prefix class
 * 
 * This class includes the representation of Prefix
 * Prefix is a new class which stores an ArrayList of words
 * Also, override the toString, equals and hashCode methods
 * 
 */

public class Prefix {
	
	public int prefixLen;
	public ArrayList<String> prefix;		

	/**
	 * Construct function to initiate the Prefix class
	 * @param prefixLen
	 * 		the length of related prefix words
	 */
	public Prefix(int prefixLen){
		this.prefixLen = prefixLen;
		prefix = new ArrayList<String>();
	}
	
	/**
	 * Shift the prefix to attain new word and delete the original first element
	 * @param newWord 
	 *		the new word to refresh prefix
	 */	
	public void refreshPrefix(String newWord){
		if(prefix.size()<prefixLen){
			// add this new word to current prefix
			prefix.add(newWord);
		}
		else{
			// shift one word for current prefix
			prefix.remove(0);
			prefix.add(newWord);
		}
	}
	
	/**
	 * return the string expression of the Prefix class
	 */
	public String toString() { 
		String tempS = "";
		for(int i=0; i<prefix.size(); i++){
			tempS += prefix.get(i)+" ";
		}
	    return tempS;
	}	
	
	// override the equals and hashCode function
    @Override
    public boolean equals(Object obj) {

        if (obj == this) return true;
        if (!(obj instanceof Prefix)) {
            return false;
        }

        Prefix P = (Prefix) obj;
        boolean result = true;
        for(int i=0; i<prefixLen; i++){
        	result = result && (prefix.get(i).equals(P.prefix.get(i)));
        }
        return result;
    }

    @Override
    public int hashCode() {
        int result = 0;
        for(int i=0; i<prefixLen; i++){
        	result += (prefix.get(i).hashCode());
        }
        return result;
    }
	
}