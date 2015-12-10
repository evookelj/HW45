//skeleton file for class Binary

public class Binary implements Comparable {

    private int _decNum;
    private String _binNum;

    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _binNum to "0"
      =====================================*/
    public Binary() { 
	_binNum = "0";
	_decNum = 0;  
    }

    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _binNum to equiv string of bits
      =====================================*/
    public Binary( int n ) {
	/****** YOUR IMPLEMENTATION HURRR ******/
	_decNum = n;
	_binNum = decToBin(n);  
    }

    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative binary number
      post: sets _binNum to input, _decNum to decimal equiv
      =====================================*/
    public Binary( String s ) {
	_binNum = s;
	_decNum = binToDec(s);
    }

    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of 1's and 0's representing value of this Object
      =====================================*/
    public String toString() { 
	return _binNum;
    }

    /*=====================================
      String decToBin(int) -- converts base-10 input to binary
      pre:  n >= 0
      post: returns String of bits
      eg  decToBin(0) -> "0"
      decToBin(1) -> "1"
      decToBin(2) -> "10"
      decToBin(3) -> "11"
      decToBin(14) -> "1110"
      =====================================*/
    public static String decToBin( int decNum ) {
	/****** YOUR IMPLEMENTATION HURRR *****
	N: Last is first
	1. Div decNum by 2, store remainder
	2. Save quotient as new decNum
	3. Repeat 1, 2 until decNum == 0
	4. Stored remainders constitute binNum
	*/
	String retStr = "";
	int remainder;

	while (decNum != 0) {
	    remainder = (decNum%2);
	    retStr = remainder + retStr;;
	    decNum /= 2;
	}

	return retStr;
    }

    /*=====================================
      String decToBinR(int) -- converts base-10 input to binary, recursively
      pre:  n >= 0
      post: returns String of bits
      eg  decToBinR(0) -> "0"
      decToBinR(1) -> "1"
      decToBinR(2) -> "10"
      decToBinR(3) -> "11"
      decToBinR(14) -> "1110"
	    =====================================*/

    public static String decToBinR( int n ) { 
	//YOUR IMPLEMENTATION HURRR
	if (n<2) {
	    return Integer.toString(n);
	} else {
	    return decToBinR(n/2) +  Integer.toString(n%2);
	}
    }
	
    /*=====================================
      String binToDec(String) -- converts base-10 input to binary
      pre:  s represents non-negative binary number
      post: returns String of bits
      eg  
      binToDec("0") -> 0
      binToDec("1") -> 1
      binToDec("10") -> 2
      binToDec("11") -> 3
      binToDec("1110") -> 14
      =====================================*/
    public static int binToDec( String s ) {
	int retInt = 0;
	for (int i=0; i<s.length(); i++) {
	    int curr = Integer.parseInt(s.substring(i,i+1));
	    retInt += curr * Math.pow(2, (s.length()-i-1));
	}
	return retInt;
    }


    /*=====================================
      String binToDecR(String) -- converts base-10 input to binary, recursively
      pre:  s represents non-negative binary number
      post: returns String of bits
      eg  
      binToDecR("0") -> 0
      binToDecR("1") -> 1
      binToDecR("10") -> 2
      binToDecR("11") -> 3
      binToDecR("1110") -> 14
      =====================================*/
    public static int binToDecR( String s ) { 
	if (s.length() == 1) {
	    return Integer.parseInt(s);
	} else {
	    return (Integer.parseInt(s.substring(0,1)) * (int)Math.pow(2,s.length()-1)) + binToDecR(s.substring(1));
	}
    }


    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Binary
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal binary values
    =============================================*/
      
    public boolean equals( Object other ) {
	String theOth = ((Binary)other)._binNum;
	return (_binNum.equals(theOth));
    }

    /*=============================================
      int compareTo(Object) -- tells which of two Binary objects is greater
      pre:  other is instance of class Binary
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
    =============================================*/

    public int compareTo( Object other ) {
	int retInt=0;
	if (!(other instanceof Comparable)) throw new ClassCastException ("");
	else if (other instanceof Binary) {
	    int othDec = ((Binary)other)._decNum;
	    if (_decNum > othDec) {
		retInt = 1;
	    } else if (_decNum < othDec) {
		retInt = -1;
	    } else {
		retInt = 0;
	    }
	}
	else if (other instanceof Hexadecimal) {
	    Binary othB = new Binary(Hexadecimal.hexToDec(other.toString()));
	    retInt = this.compareTo(othB);
	}
	else if (other instanceof Rational) {
	    Rational thisR = new Rational(_decNum,1);
	    retInt = thisR.compareTo(other);
	}

	return retInt;
    }

    //main method for testing
    public static void main( String[] args ) {

	System.out.println();
	System.out.println( "Testing ..." );

	Binary b1 = new Binary(5);
	Binary b2 = new Binary(5);
	Binary b3 = b1;
	Binary b4 = new Binary(7);

	System.out.println( b1 );
	System.out.println( b2 );
	System.out.println( b3 );       
	System.out.println( b4 );

	System.out.println("decToBin(8):\t"+decToBin(8)+
			   "\ndecToBinR(8):\t"+decToBinR(8)+
			   "\nbinToDec(1000):\t"+binToDec("1000")+
			   "\nbinToDecR(1000):\t"+binToDecR("1000"));

	/*
	System.out.println( "\n==..." );
	System.out.println( b1 == b2 ); //should be false
	System.out.println( b1 == b3 ); //should be true

	System.out.println( "\n.equals()..." );
	System.out.println( b1.equals(b2) ); //should be true
	System.out.println( b1.equals(b3) ); //should be true
	System.out.println( b3.equals(b1) ); //should be true
	System.out.println( b4.equals(b2) ); //should be false
	System.out.println( b1.equals(b4) ); //should be false

	System.out.println( "\n.compareTo..." );
	System.out.println( b1.compareTo(b2) ); //should be 0
	System.out.println( b1.compareTo(b3) ); //should be 0
	System.out.println( b1.compareTo(b4) ); //should be neg
	System.out.println( b4.compareTo(b1) ); //should be pos
	*/
    }//end main()

} //end class
