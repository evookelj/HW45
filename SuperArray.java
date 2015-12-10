public class SuperArray implements Comparable{
 
    //~~~~~INSTANCE VARS~~~~~
    //underlying container, or "core" of this data structure:
    private Comparable[] _data;

    //position of last meaningful value
    private int _lastPos;

    //size of this instance of SuperArray
    private int _size;
	
    //~~~~~METHODS~~~~~
    //default constructor - initializes 10-item array
    public SuperArray() { 
	_data = new Comparable[10];
	_lastPos = -1; //flag to indicate no lastpos yet
	_size = 0;	
    }

    public boolean isSorted() {
	for (int i=0; i<_size-1; i++) {
	    if (_data[i].compareTo(_data[i+1]) > 0) return false;
	}
	return true;
    }

    public int compareTo(Object other) {
        if (_data == ((SuperArray)other)._data) return 0;
	else return 1;
    }
		
    //output array in [a,b,c] format, eg
    // {1,2,3}.toString() -> "[1,2,3]"
    public String toString() { 
	String retStr = "[";
	for( int i = 0; i < _size; i++ ) {
	    retStr += _data[i] + ",";
	}
	if ( retStr.length() > 1 )
	    retStr = retStr.substring( 0, retStr.length()-1 );
	retStr += "]";
	return retStr;
    }
		
    //double capacity of this SuperArray
    private void expand() {
	Comparable[] temp = new Comparable[ _data.length * 2 ];
	for( int i = 0; i < _data.length; i++ )
	    temp[i] = _data[i];
	_data = temp;
    }
		
    //accessor -- return value at specified index
    public Comparable get( int index ) { return _data[index]; }
	
    //mutator -- set value at index to newVal, 
    //           return old value at index
    public Comparable set( int index, Comparable newVal ) { 
 	Comparable temp = _data[index];
	_data[index] = newVal;
	return temp;
    }

    // ~~~~~~~~~~~~~~ PHASE II ~~~~~~~~~~~~~~
    //adds an item after the last item
    public void add( Comparable newVal ) {
	if (_size == _data.length) 
	    expand();
	_lastPos += 1;
	_size += 1;
	_data[_lastPos] = newVal;
    }

    //inserts an item at index
    //shifts existing elements to the right
    public void add( int index, Comparable newVal ) {
	if (_size == _data.length)
	    expand();
	else if (index < _size && index > -1) {
	    for (int i = _lastPos; i > index - 1; i--) {
	        _data[i + 1] = _data[i];
	    }
	    _data[index] = newVal;
	    _lastPos++;
	    _size++;
	}
	else
	    System.out.println("Invalid index");	
    }

    //removes the item at index
    //shifts elements left to fill in newly-empted slot
    public void remove( int index ) {
        if (index < _size && index > -1) {
	    for (;index < _lastPos; index++) {
		_data[index] = _data[index+1];
	    }
	    _data[_lastPos] = new Rational();
	    _lastPos--;
	    _size--;
	}
	else
	    System.out.println("Invalid index");	
    }
    
    //return number of meaningful items in _data
    public int size() {return _size;}

    //main method for testing
    public static void main( String[] args ) {
		//Rational array
        SuperArray jerry = new SuperArray();
	jerry.add(new Rational(23, 12));
	jerry.add(new Rational(9, 84));
	jerry.add(new Binary(8));
	jerry.add(new Binary("1000"));
	jerry.add(new Hexadecimal(79));
	jerry.add(new Hexadecimal("4F"));
	System.out.println("jerry:\t" + jerry);

	jerry.remove(3);
	System.out.println("remove@3:\t" + jerry);
	jerry.remove(3);
	System.out.println("remove@3:\t" + jerry);
	
	jerry.add(3, new Rational(3, 5));
	System.out.println("insert Rational:\t" + jerry);
	jerry.add(2, new Binary(16));
	System.out.println("insert Binary:\t" + jerry);
	jerry.add(1, new Hexadecimal(135));
	System.out.println("insert Hex:\t" + jerry);
    }//end main
		
}//end class
