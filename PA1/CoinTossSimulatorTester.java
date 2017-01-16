/**
 * class CoinTossSimulatorTester
 * 
 * Test its full functionality of the CoinTossSimulator class.
 * A new displaySim function is created to print the result of every simulation.
 * 
 */

public class CoinTossSimulatorTester
{
	/**
    Display the test result of coin toss. 
    @param simulator  an object of CoinTossSimulator
	*/
	private static void displaySim(CoinTossSimulator simulator){
		System.out.println("Number of trials: " + simulator.getNumTrials());
		System.out.println("Two-head tosses: " + simulator.getTwoHeads());
		System.out.println("Two-tail tosses: " + simulator.getTwoTails());
		System.out.println("One-head one-tail tosses: " + simulator.getHeadTails());
		
		System.out.print("Tosses add up correctly? ");
		if (simulator.getTwoHeads()+simulator.getTwoTails()+simulator.getHeadTails() == simulator.getNumTrials())
			System.out.println("True\n");
		else
			System.out.println("False\n");
	}
	
	public static void main(String[] args)
	{		
		CoinTossSimulator sim = new CoinTossSimulator();
		System.out.println("After constructor:");
		displaySim(sim);
		
		sim.run(1);
		System.out.println("After run(1):");
		displaySim(sim);
		
		sim.run(10);
		System.out.println("After run(10):");
		displaySim(sim);
		
		sim.run(100);
		System.out.println("After run(100):");
		displaySim(sim);
		
		sim.reset();
		System.out.println("After reset:");
		displaySim(sim);
		
		sim.run(1000);
		System.out.println("After run(1000):");
		displaySim(sim);
	}
	
}
