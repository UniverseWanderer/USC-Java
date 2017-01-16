// Name: Deyu Ma
// USC loginid: deyuma@usc.edu
// CS 455 PA2
// Fall 2016


import java.util.Scanner;

/**
A polynomial Calculator. We implement a command line interface for user
to do computations on Polynomial objects
*/
public class PolynomialCalculator{
	public static void main(String[] args){
		
		PolynomialCalculatorFunc myPoly = new PolynomialCalculatorFunc();
		boolean flag = true;
		
		String command = "";
		String cmd = "";
		String para = "";
		
		myPoly.polyHelp();
		Scanner in = new Scanner(System.in);
		
		while (flag == true){	
			// Start the command by "cmd> "
			System.out.print("cmd> ");
			command = in.nextLine();

			// Divide the command into two parts command+parameter
			int i = command.indexOf(" ");
			if (i!=-1){
				cmd = command.substring(0, i);
				para = command.substring(i);
			}
			else {
				cmd = command;
			}			
			
			// Switch and do the functionality
			switch (cmd.trim().toLowerCase()){
			case "create": 
				myPoly.polyCreate(para);
				break;
			case "print": 
				myPoly.polyPrint(para);
				break;
			case "add": 
				myPoly.polyAdd(para);
				break;
			case "eval": 
				myPoly.polyEval(para);
				break;
			case "help" :
				myPoly.polyHelp();
				break;
			case "quit" : 
				flag = false;
				break;
			default:
				System.out.println("ERROR: Illegal command.  Type 'help' for command options.");
				break;
			}									
		}
		
	}
}