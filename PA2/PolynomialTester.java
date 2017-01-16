import java.util.ArrayList;

public class PolynomialTester{
	public static void main(String[] args){
		
		// test that default constructor correctly creates polynomial
		Polynomial p1 = new Polynomial();
		System.out.println("Polynomial: " + p1.toFormattedString());
		System.out.println("The value of polynomial: " + p1.eval(3));
		System.out.println("Expected results for value: 0\n");

		// test that one-arg constructor correctly creates polynomial
		Polynomial p2 = new Polynomial(new Term(3,2));
		System.out.println("Polynomial: " + p2.toFormattedString());
		System.out.println("The value of polynomial P(2): " + p2.eval(2));
		System.out.println("Expected results for value: 12.0\n");
		
		// test add function with same exponent
		System.out.println("Test addition with same exponent");
		Polynomial p3 = new Polynomial(new Term(3, 3)).add(new Polynomial(new Term(-3, 3)));
		System.out.println("Polynomial: " + p3.toFormattedString());
		 
		// test add function with different exponent
		System.out.println("Test addition with different exponent");
		Polynomial p4 = new Polynomial(new Term(3, 1)).add(new Polynomial(new Term(-1, 2)));
		System.out.println("Polynomial: " + p4.toFormattedString());
		
		// test that add polynomial to the other		
		System.out.println("\nTest that add polynomial to the other");
		Polynomial p5 = new Polynomial(new Term(3, 0)).add(new Polynomial(new Term(-2, 1)));
		System.out.println("Polynomial: " + p4.toFormattedString());
		System.out.println("Polynomial: " + p5.toFormattedString());
		p5 = p5.add(p4);
		System.out.println("Result Polynomial: " + p5.toFormattedString());
		
		// test value of a polynomial
		System.out.println("The value of this polynomial P(3): " + p5.eval(3));
		System.out.println("Expected results for value: -3.0");
		
	}
}