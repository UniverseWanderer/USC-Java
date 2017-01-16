// Name: Deyu Ma
// USC loginid: deyuma@usc.edu
// CS 455 PA3
// Fall 2016

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.JComponent;

/**
   MazeComponent class
   
   A component that displays the maze and path through it if one has been found.
*/
public class MazeComponent extends JComponent
{
   private Maze maze;
   
   private static final int START_X = 10; // where to start drawing maze in frame
   private static final int START_Y = 10;
   private static final int BOX_WIDTH = 20;  // width and height of one maze unit
   private static final int BOX_HEIGHT = 20;
   private static final int INSET = 2;  
                    // how much smaller on each side to make entry/exit inner box
   
   
   /**
      Constructs the component.
      @param maze   the maze to display
   */
   public MazeComponent(Maze maze) 
   {   
	   this.maze = maze;	   
   }

   
   /**
     Draws the current state of maze including the path through it if one has
     been found.
     @param g the graphics context
   */
   public void paintComponent(Graphics g)
   {
	   Graphics2D g2 = (Graphics2D) g;
	   
	   //draw a boundary outline
	   Rectangle outline = new Rectangle(START_X-1,START_Y-1, BOX_WIDTH*maze.numCols()+1,BOX_HEIGHT*maze.numRows()+1);
	   g2.draw(outline);
	   
	   //draw wall & free units
	   for (int i=0; i<maze.numRows(); i++)
		   for (int j=0; j<maze.numCols(); j++)
		   {
			   if (maze.hasWallAt(new MazeCoord(i,j))){
				   g2.setColor(Color.BLACK);
			   }
			   else{
				   g2.setColor(Color.WHITE);
			   }
			   Rectangle square = new Rectangle(START_X+BOX_WIDTH*j,START_Y+BOX_HEIGHT*i, BOX_WIDTH,BOX_HEIGHT);
			   g2.fill(square);
		   }
	   
	   //draw entry & exit location
	   g2.setColor(Color.YELLOW);
	   Rectangle entry = new Rectangle(START_X+INSET+BOX_WIDTH*maze.getEntryLoc().getCol(),START_Y+INSET+BOX_HEIGHT*maze.getEntryLoc().getRow(), BOX_WIDTH-2*INSET,BOX_HEIGHT-2*INSET);
	   g2.fill(entry);
	   g2.setColor(Color.GREEN);
	   Rectangle exit = new Rectangle(START_X+INSET+BOX_WIDTH*maze.getExitLoc().getCol(),START_Y+INSET+BOX_HEIGHT*maze.getExitLoc().getRow(), BOX_WIDTH-2*INSET,BOX_HEIGHT-2*INSET);
	   g2.fill(exit);
	   
	   //draw path by using polyline
	   LinkedList<MazeCoord> path = maze.getPath();
	   int pathLength = path.size();
	   
	   if (!(pathLength==0)){
		   int[] xPoints = new int[pathLength];
		   int[] yPoints = new int[pathLength];
		   for (int i=0; i<pathLength; i++){
			   xPoints[i] = START_X+BOX_WIDTH*path.get(i).getCol()+BOX_WIDTH/2;
			   yPoints[i] = START_Y+BOX_HEIGHT*path.get(i).getRow()+BOX_HEIGHT/2;
		   }
		   g2.setColor(Color.BLUE);
		   g2.drawPolyline(xPoints, yPoints, pathLength);
	   }	   	   
   }   
}


