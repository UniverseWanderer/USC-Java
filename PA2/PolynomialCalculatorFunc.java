// Name: Deyu Ma
// USC loginid: deyuma@usc.edu
// CS 455 PA2
// Fall 2016


import java.util.ArrayList;
import java.util.Scanner;

/**
   A polynomial calculator functions.
   To accomplish every move for the commands. 
*/
public class PolynomialCalculatorFunc {

    /**
       Creates 10 initial polynomials
    */
    public PolynomialCalculatorFunc() {
    	poly = new ArrayList<Polynomial>();
  		for (int i=0; i<INIT_LENGTH; i++){
  			poly.add(new Polynomial());
  		}
    }

    /**
    	Creates specific polynomial with input terms.
    	ERROR CHECK:
    	The polynomial number should be 0-9.
    	
    	WARN:
    	if user input a negative exponent
    	if user input odd number of coefficient and exponent pair
     */
	public void polyCreate(String para){
		int num = Integer.parseInt(para.trim());
		if (!(num>=0 && num<INIT_LENGTH)){
			System.out.println("ERROR: illegal index for a poly.  must be between 0 and 9, inclusive");
			return;
		}
		
		// Get the terms from user
		System.out.println("Enter a space-separated sequence of coeff-power pairs terminated by <nl>");		
		Scanner in = new Scanner(System.in);
		String line = "";
		
		line = in.nextLine();
		
		// Scanner the coeff & expon of every term
		ArrayList<Double> operands = new ArrayList<Double>();	
		Scanner lineScanner = new Scanner(line);		
		while(lineScanner.hasNextDouble()){
			operands.add(lineScanner.nextDouble());
		}
		
		// Refresh the polynomial and add terms 
		poly.set(num, new Polynomial());
		for (int i=0; i<operands.size(); i++){
			if(i<operands.size()-1){
				double coeff = operands.get(i++);
				int expo = (int)(double)operands.get(i);
				if(expo<0)
					System.out.println("WARNING:The absolute value has been used for this negative exponent!");
				Term t = new Term(coeff,Math.abs(expo));
				poly.set(num, poly.get(num).add(new Polynomial(t)));
			}
			else{
				System.out.println("WARNING:The last value has been ignored!");
			}
		}
		
	}
 
	/**
		Prints specific polynomial on screen.
		with the following format, shown by example:
        zero poly:   "0.0"
        1-term poly: "3.2x^2"
        4-term poly: "3.0x^5 + -x^2 + x + -7.9"
	 */
	public void polyPrint(String para){
		int num = Integer.parseInt(para.trim());
		if (!(num>=0 && num<INIT_LENGTH)){
			System.out.println("ERROR: illegal index for a poly.  must be between 0 and 9, inclusive");
			return;
		}
		
		System.out.println(poly.get(num).toFormattedString());
	}
 
	/**
		Adds two polynomials.
		
		Add a b c 
		is the standard form for a=b+c. 
		Without error checking for unpredictable input.
	 */
	public void polyAdd(String para){
		int[] num = new int[3];	
		Scanner lineScanner = new Scanner(para);	
		
		// Get the polynomial number from parameter
		for (int i=0; i<3 && lineScanner.hasNextInt(); i++){
			num[i] = lineScanner.nextInt();
			if (!(num[i]>=0 && num[i]<INIT_LENGTH)){
				System.out.println("ERROR: illegal index for a poly.  must be between 0 and 9, inclusive");
				return;
			}
		}
		
		poly.set(num[0], poly.get(num[1]).add(poly.get(num[2])));		
		
	}

	/**
		Calculates the value of polynomial and output the result.
	 */
	public void polyEval(String para){
		int num = Integer.parseInt(para.trim());
		if (!(num>=0 && num<INIT_LENGTH)){
			System.out.println("ERROR: illegal index for a poly.  must be between 0 and 9, inclusive");
			return;
		}
		
		// For the user to input
		System.out.print("Enter a floating point value for x: ");
		Scanner in = new Scanner(System.in);
		
		System.out.println(poly.get(num).eval(in.nextDouble()));
	}
	
	/**
	 	Displays the system help information for every available command.
	 */
	public void polyHelp(){
		System.out.println("/*********************************************************/");
		System.out.println("HELP for PolynomialCalculator:");
		System.out.println("create 	>>Create the zero polynomial.");
		System.out.println("print 	>>Print out a specific polynomial.");
		System.out.println("add 	>>Add two polynomials.");
		System.out.println("eval 	>>Evaluate a polynomial for a specific point value.");
		System.out.println("quit 	>>Quit the program immediately.");
		System.out.println("/*********************************************************/");

	}

	// **************************************************************
    //  PRIVATE INSTANCE VARIABLE(S)
 	private ArrayList<Polynomial> poly;
 	
 	private final static int INIT_LENGTH = 10;
}