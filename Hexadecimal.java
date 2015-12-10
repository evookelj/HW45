/*
Team Garfinkel: Emma Vukelj, Jennifer Yu
HW44: Hexadecimal
APCS1 pd9
2015-12-08
 */

public class Hexadecimal implements Comparable {

    //To search and replace: C-s M-% y:replace, n:skip

    /*=====================================
      INSTANCE VARIABLES
      =====================================*/
    private int _decNum;
    private String _hexNum;
    private final static String HEXDIGITS = "0123456789ABCDEF"; 

    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _hexNum to "0"
      =====================================*/
    public Hexadecimal() { 
	_hexNum = "0";
	_decNum = 0;  
    }

    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _hexNum to equiv string of bits
      =====================================*/
    public Hexadecimal( int n ) {
	_decNum = n;
	_hexNum = decToHex(n);  
    }

    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative binary number
      post: sets _hexNum to input, _decNum to decimal equiv
      =====================================*/
    public Hexadecimal( String s ) {
	_hexNum = s;
	_decNum = hexToDec(s);
    }

    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String representing value of this Object
      =====================================*/
    public String toString() { 
	return _hexNum;
    }


    /*=====================================
      String decToHex(int) -- converts base-10 input to hexadecimal
      pre:  n >= 0
      post: returns String of bits
      =====================================*/
    public static String decToHex( int decNum ) {
	String retStr = "";
	String remainder;

	while (decNum != 0) {
	//while there IS a decNum
	    remainder = HEXDIGITS.substring((decNum%16),(decNum%16+1));
	    //set the remainder to be the corresponding hexdigit
	    retStr = remainder + retStr;
	    //add the hexdigit to the beginning of the string
	    decNum /= 16;
	    //redefine decNum as the quotient and loop
	}

	return retStr; //return String
    }

    /*=====================================
      String decToHexR(int) -- converts base-10 input to hexadecimal, recursively
      pre:  n >= 0
      post: returns String of bits
	    =====================================*/

    public static String decToHexR( int n ) { 
	//YOUR IMPLEMENTATION HURRR
	if (n<16) {
	//if the number is less than 16
	    return HEXDIGITS.substring(n,n+1);
	    //easy! just return the value at the same index in hexdigit
	} else {
	    //if not :(
	    return decToHexR(n/16) + HEXDIGITS.substring(n%16,n%16+1);
	    //return corresponding hexdigit of the remainder when dividing by 16
	    //and append it to the recursive call of the quotient
	}
    }
	
    /*=====================================
      String hexToDec(String) -- converts base-10 input to hex
      pre:  s represents non-negative hexadecimal number
      post: returns String of bits
      =====================================*/
    public static int hexToDec( String s ) {
	int retInt = 0;
	for (int i=0; i<s.length(); i++) {
	//initialize a counter
	    String curr = s.substring(i,i+1);
	    //take the first hexdigit in the string given
	    retInt += (int)Math.pow(16,s.length()-i-1) * HEXDIGITS.indexOf(curr);
	    //return the index of the hexdigit and multiply it with 16 to the power of the last index
	}
	return retInt; //return int
    }


    /*=====================================
      String hexToDecR(String) -- converts base-10 input to hexadecimal, recursively
      pre:  s represents non-negative hexadecimal number
      post: returns String of bits
      =====================================*/
    public static int hexToDecR( String s ) { 
	if (s.length() == 1) {
	//if the length is 1
	    return HEXDIGITS.indexOf(s);
	    //easy! return the index
	} else {
	  //if not
	    return (Integer.parseInt(s.substring(0,1)) * (int)Math.pow(16,s.length()-1))
		+ hexToDecR(s.substring(1));
	    //return the first index of s as an int and multiply it with
	    //16 to the power of the last index and add it
	    //to the recursive call of the same string without the index just used
	}
    }


    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Hexadecimal
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal hex values
    =============================================*/
    public boolean equals( Object other ) {
	if (!(other instanceof Hexadecimal)) //catches an incorrect input error
	    throw new ClassCastException ("\nequals() input not a Hexadecimal");
	String theOth = ((Hexadecimal)other)._hexNum;
	//initialize a string and set it to the hexNum of other
	//other is typecasted to as Hexadecimal because we know
	//it will be an instance of class Hexadecimal
	return (_hexNum.equals(theOth));
	//return t/f if the hexNum of this is equal to other's hexNum
    }

    /*=============================================
      int compareTo(Object) -- tells which of two Hexadecimal objects is greater
      pre:  other is instance of class Hexadecimal
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
    =============================================*/
    public int compareTo( Object other ) {
	int retInt=0;
	if (!(other instanceof Comparable)) //catches an incorrect input error
	    throw new ClassCastException ("\ncompareTo() input not a Hexadecimal");

	if (other instanceof Hexadecimal) {
	    int othDec = ((Hexadecimal)other)._decNum;
	    if (_decNum > othDec) {
		retInt =1;
	    } else if (_decNum == othDec) {
		retInt=0;
	    } else {
		retInt =-1;
	    }
	}

	if (other instanceof Binary) {
	    Hexadecimal othH = new Hexadecimal(Binary.binToDec(other.toString()));
	    this.compareTo(othH);
	}

	if (other instanceof Rational) {
	    Rational thisB = new Rational(_decNum,1);
	    thisB.compareTo(other);
	}
	return retInt;
    }

    //main method for testing
    public static void main( String[] args ) {
	System.out.println("\nTesting ...");
	Hexadecimal b1 = new Hexadecimal(5);
	Hexadecimal b2 = new Hexadecimal(24);
	Hexadecimal b3 = new Hexadecimal(16);

	System.out.println(b1.compareTo("dfksgjjfk"));
	System.out.println(b1.equals("dfksgjjfk"));

	System.out.println( b1 );
	System.out.println( b2 );
        System.out.println( b3 );

	System.out.println("\ndecToHex(79):\t" + decToHex(79) +
			   "\ndecToHexR(79):\t" + decToHexR(79) +
			   "\nhexToDec(4F):\t" + hexToDec("4F") +
			   "\nhexToDecR(4F):\t" + hexToDecR("4F"));
    }//end main()

} //end class
