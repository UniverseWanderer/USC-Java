// Name: Deyu Ma
// USC loginid: deyuma@usc.edu
// CS 455 PA2
// Fall 2016


import java.util.ArrayList;

/**
   A polynomial. Polynomials can be added together, evaluated, and
   converted to a string form for printing.
*/
public class Polynomial {

    /**
       Creates the 0 polynomial
    */
    public Polynomial() {
    	this(new Term());
    }


    /**
       Creates polynomial with single term given
     */
    public Polynomial(Term term) {
    	polyFactors = new ArrayList<Term>();
    	if (term.getCoeff()==0)
    		polyFactors.add(new Term());
    	else
    		polyFactors.add(term);
    	
    	assert this.isValidPolynomial();
    }


    /**
       Returns the Polynomial that is the sum of this polynomial and b
       (neither poly is modified)
     */
    public Polynomial add(Polynomial b) {
    	assert isValidPolynomial();
    	assert b.isValidPolynomial();
    	
    	Polynomial tempPoly = new Polynomial();
    	
    	// combine two ArrayList by Merge
    	int i = 0;	// the index of current polynomial
    	int j = 0;  // the index of polynomial b
    	while (i<polyFactors.size() && j<b.polyFactors.size()){
    		Term polyA = polyFactors.get(i);
    		Term polyB = b.polyFactors.get(j);
    		
   			if (polyA.getExpon() == polyB.getExpon()){
   				if (polyA.getCoeff() + polyB.getCoeff()!=0) 					
   					tempPoly.polyFactors.add(new Term(polyA.getCoeff() + polyB.getCoeff(), polyA.getExpon()));
   				i++; 
   				j++;
   			}
   			else if (polyA.getExpon() < polyB.getExpon()){
   				tempPoly.polyFactors.add(polyB);
   				j++;
   			}
   			else if (polyA.getExpon() > polyB.getExpon()){
   				tempPoly.polyFactors.add(polyA);
   				i++;
   			}
   		}
    		
   		while (i<polyFactors.size()){
   			if (polyFactors.get(i).getCoeff()!=0 || polyFactors.get(i).getExpon()!=0)
   				tempPoly.polyFactors.add(polyFactors.get(i));
   			i++;
   		}
    		
   		while (j<b.polyFactors.size()){
   			if (b.polyFactors.get(j).getCoeff()!=0 || b.polyFactors.get(j).getExpon()!=0)
   				tempPoly.polyFactors.add(b.polyFactors.get(j));
   			j++;
   		}
    	
   		// Remove the first initialized (0,0) element
   		if (tempPoly.polyFactors.size()>1)
   			tempPoly.polyFactors.remove(0);
   		
   		assert tempPoly.isValidPolynomial();
   		
    	return tempPoly;
    }


    /**
       Returns the value of the poly at a given value of x.
     */
    public double eval(double x) {
    	double result = 0;
    	for (int i=0; i<polyFactors.size(); i++){
    		Term tempTerm = polyFactors.get(i);
    		result = result + tempTerm.getCoeff()*Math.pow(x, tempTerm.getExpon());
    	}
    	
    	return result; 
    }


    /**
       Return a String version of the polynomial with the 
       following format, shown by example:
       zero poly:   "0.0"
       1-term poly: "3.2x^2"
       4-term poly: "3.0x^5 + -x^2 + x + -7.9"

       Polynomial is in a simplified form (only one term for any exponent),
       with no zero-coefficient terms, and terms are shown in
       decreasing order by exponent.
    */
    public String toFormattedString() {
    	String tempStr = new String();
    	if (polyFactors.get(0).getCoeff()==0 && polyFactors.get(0).getExpon()==0){
    		tempStr = "0.0";
    	}
    	
    	else 		
	    for (int i=0; i<polyFactors.size(); i++){
	    	Term tempTerm = polyFactors.get(i);
			// print "+" between factors
	    	if (i!=0)
	    		tempStr = tempStr + " + ";	  
	    	
	    	// print x^0
			if (tempTerm.getExpon() == 0){
				tempStr = tempStr + tempTerm.getCoeff();
			}
			// print x^1 
			else if (tempTerm.getExpon() ==1){		
				if (tempTerm.getCoeff()==1)			// deal with Coeff=1
					tempStr = tempStr + "x";
				else if (tempTerm.getCoeff()==-1)	// deal with Coeff=-1
					tempStr = tempStr + "-x";
				else 
					tempStr = tempStr + tempTerm.getCoeff() + "x";
			}
			// print x^N
			else{
				if (tempTerm.getCoeff()==1)			// deal with Coeff=1
					tempStr = tempStr + "x^" + tempTerm.getExpon();
				else if (tempTerm.getCoeff()==-1)	// deal with Coeff=-1
					tempStr = tempStr + "-x^" + tempTerm.getExpon();
				else
					tempStr = tempStr + tempTerm.getCoeff() + "x^" + tempTerm.getExpon();
			}						
	    }
    	
    	return tempStr;   
    }


    // **************************************************************
    //  PRIVATE METHOD(S)

    /**
       Returns true iff the poly data is in a valid state.
       Valid poly data:
       1. No negative exponent.
       2. The exponents are in descending order.
       3. For two same exponent term, add their coefficients.
    */
    private boolean isValidPolynomial() {
    	int tempExpon = Integer.MAX_VALUE;
    	for (int i=0; i<polyFactors.size(); i++){
    		if (polyFactors.get(i).getExpon() < tempExpon &&
    			polyFactors.get(i).getExpon() >= 0)
    			tempExpon = polyFactors.get(i).getExpon();
    		else
    			return false;
    	}
    	return true; 
    }


    // **************************************************************
    //  PRIVATE INSTANCE VARIABLE(S)
    private ArrayList<Term> polyFactors; 

}