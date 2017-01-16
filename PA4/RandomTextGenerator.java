// Name: Deyu Ma
// USC loginid: deyuma@usc.edu
// CS 455 PA4
// Fall 2016

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

/**
 * RandomTextGenerator class
 * 
 * which is a generator contains the format about generating text. 
 * Also, it contains the method to generate word from known text structure.
 * 
 */

public class RandomTextGenerator {
	
	private Random randNum;
	private int prefixLen;
	private ArrayList<String> inputFile;
	private boolean debugMode;
	
	private Prefix curPrefix;
	private HashMap<Prefix,ArrayList<String>> textMarkovMap;
	
	/**
	 * Constructor to initiate the text generator
	 * @param prefixLen
	 * 		the length of the related prefix words
	 * @param inputFile
	 * 		an array list contains the context of input file
	 * @param debugMode
	 * 		set if using debugMode
	 */
	public RandomTextGenerator(int prefixLen, ArrayList<String> inputFile, boolean debugMode){
		
		randNum = debugMode? new Random(1):new Random();		
		this.prefixLen = prefixLen;		
		this.inputFile = inputFile;
		this.debugMode = debugMode;
		curPrefix = new Prefix(prefixLen);
		textMarkovMap = new HashMap<Prefix,ArrayList<String>>();
		
		// initiate the MarkovMap using inputFile
		createMarkovMap();
	}

	private void createMarkovMap(){

		Iterator<String> iter = inputFile.iterator();
		
		// initiate temporary variables  
		String tempString = "";
		Prefix tempPre = new Prefix(prefixLen);
		ArrayList<String> tempSuc = new ArrayList<String>();
		
		for(int i=0; i<prefixLen && iter.hasNext(); i++){
			tempString = iter.next();
			tempPre.refreshPrefix(tempString);
		}	
		  
		// a process to obtain textMarkovMap which contains the structure of the text
		while(iter.hasNext()){
			tempString = iter.next();
			if(textMarkovMap.containsKey(tempPre)){	// has such prefix now, add a new element to the value
				tempSuc = textMarkovMap.get(tempPre);
				tempSuc.add(tempString);				  
			}
			else{	// no such prefix at present, create a new entry
				Prefix newPrefix = new Prefix(prefixLen);
				for (int i=0; i<prefixLen; i++){
					newPrefix.prefix.add(tempPre.prefix.get(i));
				}
			  
				tempSuc = new ArrayList<String>();
				tempSuc.add(tempString);
					  
				textMarkovMap.put(newPrefix, tempSuc);
			}
		  
			//refresh tempPre Prefix
			tempPre.refreshPrefix(tempString);		  
		}
	}
	
	/**
	 * According to the current prefix and text Markov map to generate next word
	 */
	public String genNext(){		
		int temp = 0;
		String word = "";
		
		if(debugMode){	//output current prefix
			System.out.println("DEBUG: prefix: "+curPrefix);
		}
		
		// if no such entry, then return a random word from the input file
		if(!textMarkovMap.containsKey(curPrefix)){
			temp = randNum.nextInt(inputFile.size());
			word = inputFile.get(temp);
			if(debugMode){	//output no successor and new word
				System.out.println("DEBUG: successors: none");
				System.out.println("DEBUG: word generated: "+word);
			}			
			curPrefix.refreshPrefix(word);
			return word;
		}	
		
		// else, generate the possible successors of the specific prefix	
		ArrayList<String> successor = new ArrayList<String>();
		successor = textMarkovMap.get(curPrefix);		
		if(debugMode){	//output successors
			System.out.print("DEBUG: successors:");
			for(int i=0; i<successor.size(); i++){
				System.out.print(" "+successor.get(i));
			}
		}
		
		temp = randNum.nextInt(successor.size());
		word = successor.get(temp);		
		if(debugMode){	//output new word
			System.out.println("\nDEBUG: word generated: "+word);
		}
		
		curPrefix.refreshPrefix(word);
		return word;
	}

	/**
	 * initiate the first prefixLength long prefix and return
	 * @return
	 * 		return the initial prefix to output
	 */
	public ArrayList<String> initPrefix(){
		int temp = randNum.nextInt(inputFile.size());
		String tempString;
		ArrayList<String> result = new ArrayList<String>();
		
		for (int i=0; i<prefixLen; i++){			
			tempString = inputFile.get(temp++);
			curPrefix.refreshPrefix(tempString);
			result.add(tempString);
		}
		
		if(debugMode){	//output new initial prefix
			System.out.println("DEBUG: chose a new initial prefix: "+curPrefix);
		}

		return result;
	}
	
}