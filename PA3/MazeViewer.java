// Name: Deyu Ma
// USC loginid: deyuma@usc.edu
// CS 455 PA3
// Fall 2016


import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;
import java.io.File;
import java.util.Scanner;


/**
 * MazeViewer class
 * 
 * Program to read in and display a maze and a path through the maze. At user
 * command displays a path through the maze if there is one.
 * 
 * How to call it from the command line:
 * 
 *      java MazeViewer mazeFile
 * 
 * where mazeFile is a text file of the maze. The format is the number of rows
 * and number of columns, followed by one line per row, followed by the start location, 
 * and ending with the exit location. Each maze location is
 * either a wall (1) or free (0). Here is an example of contents of a file for
 * a 3x4 maze, with start location as the top left, and exit location as the bottom right
 * (we count locations from 0, similar to Java arrays):
 * 
 * 3 4 
 * 0111
 * 0000
 * 1110
 * 0 0
 * 2 3
 * 
 */

public class MazeViewer {
   
   private static final char WALL_CHAR = '1';
   private static final char FREE_CHAR = '0';

   public static void main(String[] args)  {

      String fileName = "";

      try {

         if (args.length < 1) {
            System.out.println("ERROR: missing file name command line argument");
         }
         else {
            fileName = args[0];
    	 // fileName = "F://software/eclipse/WorkSpace/PA3/src/smallmaze.txt";
            
            JFrame frame = readMazeFile(fileName);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setVisible(true);
         }

      }
      catch (FileNotFoundException exc) {
         System.out.println("File not found: " + fileName);
      }
      catch (IOException exc) {
         exc.printStackTrace();
      }
   }

   /**
    readMazeFile reads in maze from the file whose name is given and 
    returns a MazeFrame created from it.
   
   @param fileName
         ema    the name of a file to read from (file format shown in class comments, above)
   @returns a MazeFrame containing the data from the file.
        
   @throws FileNotFoundException
              if there's no such file (subclass of IOException)
   @throws IOException
              (hook given in case you want to do more error-checking.
               that would also involve changing main to catch other exceptions)
   */
   private static MazeFrame readMazeFile(String fileName) throws IOException {
	   // initiate a scanner for the file
	   File inputFile = new File(fileName);
	   Scanner in = new Scanner(inputFile);
	   String lineData = "";
	   
	   // read maze data from file
	   int row = in.nextInt();
	   int col = in.nextInt();
	   boolean[][] mazeData = new boolean[row][col];
	   for (int i=0; i<row; i++){
		   lineData = in.next();
		   for (int j=0; j<col; j++){
			   if(lineData.charAt(j)==WALL_CHAR)
				   mazeData[i][j] = true;
			   if(lineData.charAt(j)==FREE_CHAR)
				   mazeData[i][j] = false;
		   }
	   }	   
	   
	   MazeCoord startLoc = new MazeCoord(in.nextInt(), in.nextInt());
	   MazeCoord endLoc = new MazeCoord(in.nextInt(), in.nextInt());

      return new MazeFrame(mazeData, startLoc, endLoc);

   }

}