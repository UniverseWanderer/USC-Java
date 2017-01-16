// Name: Deyu Ma
// USC loginid: deyuma@usc.edu
// CS 455 PA3
// Fall 2016


import java.util.LinkedList;


/**
   Maze class

   Stores information about a maze and can find a path through the maze
   (if there is one).
   
   Assumptions about structure of the maze, as given in mazeData, startLoc, and endLoc
   (parameters to constructor), and the path:
     -- no outer walls given in mazeData -- search assumes there is a virtual 
        border around the maze (i.e., the maze path can't go outside of the maze
        boundaries)
     -- start location for a path is maze coordinate startLoc
     -- exit location is maze coordinate exitLoc
     -- mazeData input is a 2D array of booleans, where true means there is a wall
        at that location, and false means there isn't (see public FREE / WALL 
        constants below) 
     -- in mazeData the first index indicates the row. e.g., mazeData[row][col]
     -- only travel in 4 compass directions (no diagonal paths)
     -- can't travel through walls
 */

public class Maze {
   
   public static final boolean FREE = false;
   public static final boolean WALL = true;
   
   private static boolean[][] mazeData;
   private static MazeCoord startLoc;
   private static MazeCoord endLoc;
   private static LinkedList<MazeCoord> path;

   /**
      Constructs a maze.
      @param mazeData the maze to search.  See general Maze comments for what
      goes in this array.
      @param startLoc the location in maze to start the search (not necessarily on an edge)
      @param endLoc the "exit" location of the maze (not necessarily on an edge)
      PRE: 0 <= startLoc.getRow() < mazeData.length and 0 <= startLoc.getCol() < mazeData[0].length
         and 0 <= endLoc.getRow() < mazeData.length and 0 <= endLoc.getCol() < mazeData[0].length

    */
   public Maze(boolean[][] mazeData, MazeCoord startLoc, MazeCoord endLoc)
   {
	   this.mazeData = mazeData;
	   this.startLoc = startLoc;
	   this.endLoc = endLoc;
	   path = new LinkedList<MazeCoord>();
   }


   /**
   Returns the number of rows in the maze
   @return number of rows
   */
   public int numRows() {
      return mazeData.length;
   }

   
   /**
   Returns the number of columns in the maze
   @return number of columns
   */   
   public int numCols() {
      return mazeData[0].length;
   } 
 
   
   /**
      Returns true iff there is a wall at this location
      @param loc the location in maze coordinates
      @return whether there is a wall here
      PRE: 0 <= loc.getRow() < numRows() and 0 <= loc.getCol() < numCols()
   */
   public boolean hasWallAt(MazeCoord loc) {
	   return mazeData[loc.getRow()][loc.getCol()];
   }
   

   /**
      Returns the entry location of this maze.
    */
   public MazeCoord getEntryLoc() {
      return startLoc;   
   }
   
   
   /**
   Returns the exit location of this maze.
   */
   public MazeCoord getExitLoc() {
      return endLoc;
   }

   
   /**
      Returns the path through the maze. First element is starting location, and
      last element is exit location.  If there was not path, or if this is called
      before search, returns empty list.

      @return the maze path
    */
   public LinkedList<MazeCoord> getPath() {
      return path;   
   }


   /**
      Find a path through the maze if there is one.  Client can access the
      path found via getPath method.
      @return whether path was found.
    */
   public boolean search()  {  
	  if (!(path.size()==0)){
		  return true;
	  }
	   
	  final int EXTRA = 2;
	  boolean[][] visited = new boolean[mazeData.length+EXTRA][mazeData[0].length+EXTRA];
      boolean[][] newMaze = new boolean[mazeData.length+EXTRA][mazeData[0].length+EXTRA];
      //transform the original Maze into a one bigger on all sides newMaze 
      for (int i=0; i<newMaze.length; i++)
    	  for (int j=0; j<newMaze[0].length; j++)
    		  if (i==0 || j==0 || i==newMaze.length-1 || j==newMaze[0].length-1)
    			  newMaze[i][j]=WALL;
    		  else
    			  newMaze[i][j] = mazeData[i-1][j-1];
      
      return recursiveSearch(newMaze, visited, startLoc);  

   }
   
   private boolean recursiveSearch(boolean[][] newMaze, boolean[][] visited, MazeCoord point){
	   // if this point has been visited before
	   if (visited[point.getRow()+1][point.getCol()+1]==true){
		   return false;
	   }
	   
	   // mark this point as visited
	   visited[point.getRow()+1][point.getCol()+1] = true;
	   
	   // if this point is a wall
	   if (newMaze[point.getRow()+1][point.getCol()+1]==WALL){
		   return false;
	   }
	   
	   // if this point is the exit
	   if (point.equals(endLoc)){
		   path.addFirst(point);
		   return true;
	   }
	   else{ // recursively search its neighbor 
		   if(recursiveSearch(newMaze, visited, new MazeCoord(point.getRow()+1, point.getCol()))
		   || recursiveSearch(newMaze, visited, new MazeCoord(point.getRow()-1, point.getCol()))
		   || recursiveSearch(newMaze, visited, new MazeCoord(point.getRow(), point.getCol()+1))
		   || recursiveSearch(newMaze, visited, new MazeCoord(point.getRow(), point.getCol()-1))){
			   path.addFirst(point);
			   return true;
		   }
		   else
			   return false;			   
	   }
	   
   }

}