/**
* Prompts for the number of trials, and creates the JFrame containing the CoinSimComponent.
*/

import javax.swing.JFrame;
import java.awt.Graphics;
import java.util.Scanner;

public class CoinSimViewer{
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in);		
		System.out.print("Please input trials number: ");
		int trials = in.nextInt();
		
		while (trials <= 0) {
		    System.out.println("ERROR: It is not a legal trial.");
		    System.out.print("Please input trials number: ");
		    trials = in.nextInt();
		}
		
		JFrame frame = new JFrame();
		
		frame.setSize(600,700);
		frame.setTitle(trials + " Time(s) Coin Toss Simulation Result");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		CoinSimComponent component = new CoinSimComponent(trials);
		frame.add(component);
		
		frame.setVisible(true);
	}
}