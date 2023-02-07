package _03_Printing_Binary;

public class _01_BinaryPrinter {
    /*
     * Complete the methods below so they print the passed in parameter in binary.
     * Do not use the Integer.toBinaryString method!
     * Use the main method to test your methods.
     */


    public void printByteBinary(byte b) {
        // We first want to print the bit in the one's place
    	//System.out.println(b); // b == 1110 0001
    	byte a = b;
    	int p = 7;
    	for (int i = 0; i < 8; i++) {
    		b = (byte) (b >> p); // 0000 0001
            // Use the & operator to "mask" the bit in the one's place
            // This can be done by "anding" (&) it with the value of 1
        	b = (byte) (b & 1); 
            // Print the result using System.out.print (NOT System.out.println)
        	System.out.print(b);
            //Use this method to print the remaining 7 bits of b
        	b = a;
        	p--;
    	}
    }

    public void printShortBinary(short s) {
        // Create 2 byte variables
    	byte a = 0;
    	byte b = 0;
        // Use bit shifting and masking (&) to save the first
        // 8 bits of s in one byte, and the second 8 bits of
        // s in the other byte
    	short other = s;
    	System.out.println("s = " + s);
    	int p = 7;
    	byte actualA = 0;
    	for (int i = 0; i < 8; i++) {
    		a = (byte) (s >> p);
    		a = (byte) (a & 1);
    		actualA+=a;
    		s = other;
    		p--;
    	}
    	System.out.println("This is a: " + actualA);
    	a = (byte) (s << 8);
    	b = (byte) s;
        // Call printByteBinary twice using the two bytes
        // Make sure they are in the correct order
    	printByteBinary(a);
    	printByteBinary(b);
    }

    public void printIntBinary(int o) {
        // Create 2 short variables
    	short a = 0;
    	short b = 0;
    	int other = o;
    	System.out.println("Integer = " + o);
    	int p = 7;
    	short actualA = 0;
    	for (int i = 0; i < 16; i++) {
    		a = (short) (o >> p);
    		a = (short) (a & 1);
    		actualA+=a;
    		o = other;
    		p--;
    	}
    	System.out.println("This is a: " + actualA);
    	a = (short) (o << 8);
    	b = (short) o;
    	printShortBinary(a);
    	printShortBinary(b);
    	   // Use bit shifting and masking (&) to save the first
        // 16 bits of i in one short, and the second 16 bits of
        // i in the other short

        // Call printShortBinary twice using the two short variables
        // Make sure they are in the correct order
    }

    public void printLongBinary(long l) {
    	   // Create 2 short variables
    	int a = 0;
    	int b = 0;
    	long other = l;
    	System.out.println("Integer = " + l);
    	int p = 7;
    	short actualA = 0;
    	for (int i = 0; i < 32; i++) {
    		a = (int) (l >> p);
    		a = (int) (a & 1);
    		actualA+=a;
    		l = other;
    		p--;
    	}
    	System.out.println("This is a: " + actualA);
    	a = (short) (l << 8);
    	b = (short) l;
    	printIntBinary(a);
    	printIntBinary(b);
    }

    public static void main(String[] args) {
        // Test your methods here
    	_01_BinaryPrinter b = new _01_BinaryPrinter();
    	//b.printByteBinary((byte) 0b1010);
    	//b.printShortBinary((short) 0b1010);
    	//b.printIntBinary(20);
    	b.printLongBinary(50);
    }
}
