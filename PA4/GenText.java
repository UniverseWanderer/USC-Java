// Name: Deyu Ma
// USC loginid: deyuma@usc.edu
// CS 455 PA4
// Fall 2016

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * GenText class
 * 
 * This contains main method which is responsible for processing the command-line 
 * arguments, opening and closing the files, and handling any errors related to 
 * the above tasks. 
 * 
 * How to call it from command line:
 * 
 * 	java GenText [-d] prefixLength numWords sourceFile outFile
 *  
 * generates numWords words of prefixLength-order text using sourceFile as the 
 * source document. The generated words are written to outFile.
 * "-d"  means the program will be running in debug mode
 */
             
public class GenText {

	public static final int LINE_CHARS = 80;
	
	public static void main(String[] args)  {
	      
		boolean debugMode = false;
		int prefixLength = 0;
		int numWords = 0;
		String sourceFile = ""; 
		String outFile = "";

		try {
			if (args.length < 1) {
				throw new IllegalArgumentException("Command: java GenText [-d] prefixLength numWords sourceFile outFile");
			}
  
			else {
				if(args[0].equals("-d")){
					debugMode = true;
					prefixLength = Integer.parseInt(args[1]);
					numWords = Integer.parseInt(args[2]);
					sourceFile = args[3];
					outFile = args[4];	        		 
				}
				else{
					debugMode = false;
					prefixLength = Integer.parseInt(args[0]);
					numWords = Integer.parseInt(args[1]);
					sourceFile = args[2];
					outFile = args[3];	
				}
				
				if(numWords < 0 || prefixLength < 1){
					throw new IllegalArgumentException("bad input: prefixLength<1 or numWords<0");
				}
				
				genText(prefixLength, numWords, sourceFile, outFile, debugMode);

			}

	      }
	      catch (NumberFormatException exc){
	    	  System.out.println("prefixLength or numWords arguments are not integers");
	      }	      
	      catch (FileNotFoundException exc) {
	    	  System.out.println("file not found: " + sourceFile);
	      }
	      catch (IOException exc) {
	    	  exc.printStackTrace();
	      }
	   }
	
	/**
	 * genText reads text from the source file and generates numWords words of prefixLength-order text to out file
	 * 
	 * @param prefixLength
	 *		the order to which degree the context relates to each other
	 * @param numWords
	 * 		the words length to generate
	 * @param sourceFile
	 * 		where the reference text come from
	 * @param outFile
	 * 		where the new text be stored to
	 * @param debugMode
	 * 		set if using debugMode
	 */
	private static void genText(int prefixLength, int numWords, String sourceFile, String outFile, boolean debugMode) throws IOException, IllegalArgumentException
	{	   
		
	   // initiate the scanner and input file
	   Scanner in = new Scanner(new File(sourceFile));
	   ArrayList<String> inputFile = new ArrayList<String>();
	   
	   // initiate the output file and print writer
	   ArrayList<String> outputFile = new ArrayList<String>();  
	   
	   // read the text from input file
	   while (in.hasNext()) {
		   inputFile.add(in.next());
	   }	   
	   
	   if(prefixLength >= inputFile.size()){
		   throw new IllegalArgumentException("prefix length exceeds input file size");
	   }
	   
	   // generate text
	   RandomTextGenerator gen = new RandomTextGenerator(prefixLength, inputFile, debugMode);
	   
	   ArrayList<String> temp = gen.initPrefix();
	   for (int i=0; i<prefixLength; i++){
		   outputFile.add(temp.get(i));
	   }   
	   	   				
	   // generate the next word from Prefix class
	   for(int i=0; i<numWords; i++){			   
		   String str=gen.genNext();				   			   
		   outputFile.add(str); 
	   }
	   
	   writeToOutFile(outputFile,outFile);
	   
	   System.out.println("Successfully generate "+prefixLength+"-order "+numWords+" words text "+outFile+" based on "+sourceFile);
	   
	   // close the Scanner
	   in.close();
	}
	
	// write the output words into a specific file
	private static void writeToOutFile(ArrayList<String> outputFile, String outFile)throws IOException
	{ 
		PrintWriter out = new PrintWriter(outFile);
		 
		// write the result to output file and check the line character number
		ListIterator<String> iter = outputFile.listIterator();
		int charNum = 0;
		String str;
		   
		while (iter.hasNext()) {
			str = iter.next();
			charNum += str.length();
			if(charNum >= LINE_CHARS){
				out.print("\n");
				out.print(str+" ");
				charNum = str.length()+1;}
			else{
				out.print(str+" ");
				charNum++;}		   			   
		}
	   
		// close the print writer
		out.close();
	}
	
}