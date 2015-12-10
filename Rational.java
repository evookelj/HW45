/*
   Team bleh - Nobel Gautam
   APCS1 pd9
   HW37 -- Rational Equality
   2015-11-24
*/

public class Rational implements Comparable {
    private int p, q; //p = numerator; q = denominator (q!=0)

    public static void main(String[] args) {
	//=====CONSTRUCTOR======
	System.out.println("\n=====CONSTRUCTOR TESTS=====");
	//Test overloaded constructor
	Rational kramer = new Rational(1,0);
	System.out.println("kramer:\t" + kramer.toString()); //should be 0/1

	//Test overloaded but for legit denominator
	Rational elaine = new Rational(1, 4);
	System.out.println("elaine:\t" + elaine.toString()); //should be 1/4

	//Test default constructor
	Rational george = new Rational();
	System.out.println("george:\t" + george.toString()); //should be 0/1

	//Make more purposeful fraction
	Rational jerry = new Rational(1,2);
	System.out.println("jerry:\t" + jerry.toString());

	//=====equals()=====
	System.out.println("\n=====equals() tests=====" + 
			   "\nelaine.equals(jerry):\t" + elaine.equals(jerry) +
			   "\nkramer.equals(george):\t" + kramer.equals(george));
    }
    
    //constructors
    public  Rational()
    {
        p = 0;
        q = 1;
    }

    public Rational(int numerator, int denominator)
    {
        p = numerator;
        q = denominator;
        if (q==0)
        {
            p = 0;
            q = 1;
            System.out.println("Invalid demoninator submitted. Number set to 0/1");
        }
        reduce();
    }

    //accessors
    public int getNumerator()
    {
        return p;
    }

    public int getDenominator()
    {
        return q;
    }

    //methods
    public String toString()
    {
        return p + "/" + q;
    }

    public double floatValue()
    {
        return (double)p / q;
    }

    public static int gcd(int a, int b)
    {
        if (b == 0) return a;
        return gcd(b, a%b);
    }
    public void reduce()
    {
        int g = gcd(p, q);
        p /= g;
        q /= g;
    }
    public int compareTo(Object other) {
	int retInt=0;
	if (!(other instanceof Rational)) throw new ClassCastException ("");
	else {
	    this.subtract((Rational)other);
	    if (this.floatValue() > 0) retInt=1;
	    else if (this.floatValue() < 0) retInt=-1;
	    this.add((Rational)other);
	    return retInt;
	}
    }

    public void multiply(Rational r)
    {
        this.p *= r.getNumerator();
        this.q *= r.getDenominator();
        reduce();
    }


    public void divide(Rational r)
    {
        this.p *= r.getDenominator();
        this.q *= r.getNumerator();
        reduce();
    }

    public void add(Rational r)
    {
        this.p = this.p * r.q + this.q * r.p;
        this.q *= r.q;
        reduce();
    }

    public void subtract(Rational r)
    {
        this.p = this.p * r.q - this.q * r.p;
        this.q *= r.q;
        reduce();
    }

    public boolean equals(Object other)
    {
        if (other != null && other instanceof Rational)
            return p == ((Rational)other).p
                && q == ((Rational)other).q;
        return false;
    }
}

