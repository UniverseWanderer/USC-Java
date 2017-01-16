/**
* Create bar objects for each bar in the graph and draw it.
*/

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * Bar class
 * A labeled bar that can serve as a single bar in a bar graph.
 * The text for the label is centered under the bar.
 * 
 * NOTE: we have provided the public interface for this class. Do not change
 * the public interface. You can add private instance variables, constants,
 * and private methods to the class. You will also be completing the
 * implementation of the methods given.
 * 
 */
public class Bar {
	private int bottomBar;
	private int leftBar;
	private int widthBar;
	private int numUnitsBar;
	private double unitsPerPixelBar;
	private Color colorBar;
	private String labelBar;
	   
   /**
      Creates a labeled bar.  You give the height of the bar in application
      units (e.g., population of a particular state), and then a scale for how
      tall to display it on the screen (parameter unitsPerPixel). 
  
      @param bottom  location of the bottom of the label
      @param left  location of the left side of the bar
      @param width  width of the bar
      @param numUnits  height of the bar in application units
      @param unitsPerPixel  how many application units per pixel for height
      @param color  the color of the bar
      @param label  the label at the bottom of the bar
   */
   public Bar(int bottom, int left, int width, int numUnits, 
              double unitsPerPixel, Color color, String label) {
	   bottomBar		= bottom;
	   leftBar			= left;
	   widthBar			= width;
	   numUnitsBar		= numUnits;
	   unitsPerPixelBar	= unitsPerPixel;
	   colorBar			= color;
	   labelBar			= label;
   }
   

   /**
      Draw the labeled bar. 
      @param g2  the graphics context
   */
   public void draw(Graphics2D g2) {
	   
	   int heightBar = 	(int)(numUnitsBar/unitsPerPixelBar);   //define the height pixels of bar
	   Rectangle body = new Rectangle(leftBar, bottomBar-heightBar, widthBar, heightBar);   
	   
	   g2.setColor(colorBar);  
	   g2.fill(body); 
	   
	   g2.setColor(Color.BLACK);
	   Font font = new Font("new times roma", Font.PLAIN, 15);
	   g2.setFont(font);

	   // get font pixel range to control the display position
	   FontRenderContext context = g2.getFontRenderContext();     
	   Rectangle2D labelBounds = font.getStringBounds(labelBar, context);
	   int fontWidth = (int) labelBounds.getWidth();
	   int fontHeight = (int) labelBounds.getHeight();
	   
	   g2.drawString(labelBar, (leftBar+(widthBar-fontWidth)/2), bottomBar+fontHeight);
   }
   
}
