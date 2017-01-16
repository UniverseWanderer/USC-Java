/**
* Constructor initializes any necessary data and runs the simulation. Overrides paintComponent to draw the bar graph, using Bar objects for each bar in the graph.
*/

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;

public class CoinSimComponent extends JComponent{
	
	private int num2Heads;
	private int num2Tails;
	private int numHeadTails;
	private int numTrials;

  /**
    Creates a CoinSimComponent.  You give the trials of the coin toss
    simulation, and then it carries on simulations and save the result.

    @param trials  number of simulation times
  */
	public CoinSimComponent(int trials)
	{
		CoinTossSimulator sim = new CoinTossSimulator();
		sim.run(trials);
		num2Heads = sim.getTwoHeads();
		num2Tails = sim.getTwoTails();
		numHeadTails = sim.getHeadTails();
		numTrials = sim.getNumTrials();		
	}
	
	/**
    Paint the result of simulation using bar chart on the screen with
    adjustable window size.
    
    @param g  default graphics context type input in JComponent
  */
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
				
		int x = getWidth();
		int y = getHeight();
		int side = y/15;   // upper and bottom side width
		double unitsPerPixel = (double) numTrials/(y-2*side);
		
		String label1 = "Two Heads:"+num2Heads+"("+Math.round(num2Heads*100/numTrials)+"%)";
		String label2 = "A Head and A Tail:"+numHeadTails+"("+Math.round(numHeadTails*100/numTrials)+"%)";
		String label3 = "Two Tails:"+num2Tails+"("+Math.round(num2Tails*100/numTrials)+"%)";
		
		Bar bar1 = new Bar(y-side, 1*x/7, x/7, num2Heads, unitsPerPixel, Color.red, label1);
		Bar bar2 = new Bar(y-side, 3*x/7, x/7, numHeadTails, unitsPerPixel, Color.green, label2);
		Bar bar3 = new Bar(y-side, 5*x/7, x/7, num2Tails, unitsPerPixel, Color.blue, label3);
		
		bar1.draw(g2);
		bar2.draw(g2);
		bar3.draw(g2);
	}
}
