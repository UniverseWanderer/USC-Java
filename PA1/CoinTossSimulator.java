/**
 * class CoinTossSimulator
 * 
 * Simulates trials of tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */

import java.util.Random;

public class CoinTossSimulator {

	private int num2Heads;
	private int num2Tails;
	private int numHeadTails;

   /**
      Creates a coin toss simulator with no trials done yet.
   */
   public CoinTossSimulator() {
	   num2Heads	=	0;
	   num2Tails	=	0;
	   numHeadTails	=	0;
   }


   /**
      Runs the simulation for numTrials more trials. Multiple calls to this
      without a reset() between them add these trials to the simulation
      already completed.
      
      @param numTrials  number of trials to for simulation; must be >= 0
    */
   public void run(int numTrials) {
	   int coin1	=	-1;
	   int coin2	=	-1;
	   Random coin	= new Random();
	   
	   for(int i = 0; i < numTrials; i++)
	   {
		   coin1 = coin.nextInt(2);
		   coin2 = coin.nextInt(2);
		   
		   switch(coin1 + coin2)
		   {
		   		case 0: num2Heads++;	break;
		   		case 1: numHeadTails++;	break;
		   		case 2: num2Tails++;	break;
		   		default: System.out.print("error!"); break;
		   }
	   }
   }


   /**
      Get number of trials performed since last reset.
   */
   public int getNumTrials() {
       return getTwoHeads() + getTwoTails() + getHeadTails(); 
   }


   /**
      Get number of trials that came up two heads since last reset.
   */
   public int getTwoHeads() {
       return num2Heads; 
   }


   /**
     Get number of trials that came up two tails since last reset.
   */  
   public int getTwoTails() {
       return num2Tails; 
   }


   /**
     Get number of trials that came up one head and one tail since last reset.
   */
   public int getHeadTails() {
       return numHeadTails; 
   }


   /**
      Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() {
	   num2Heads	=	0;
	   num2Tails	=	0;
	   numHeadTails	=	0;	   
   }

}
