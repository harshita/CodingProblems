public class NumberOperations {
	public static int GCD(int a, int b) {
		if (b == 0)
			return a;
		return GCD(b, a%b);
	}
	
	public static int LCM(int a, int b) {
		return b * (a/GCD(a,b));	
	}
	
	public static int fromDecimalToBaseUpto10(int dec, int base) {
		//Java OOTB method to do the same if base is 2, 8
		if (base == 2) 
			System.out.println("Base 2 result using toBinaryString function: " + Integer.toBinaryString(dec));
		if (base == 8)
			System.out.println("Base 8 result using toOctalString function:" + Integer.toOctalString(dec));
				
		int multiplier = 1;
		int result = 0;
		while (dec != 0) {
			result += (dec % base) * multiplier;
			multiplier = multiplier * 10;
			dec = dec/base;	
		}
		return result;
	}
	
	public static String fromDecimalToBaseFrom10Upto20(int dec, int base) {
		String chars = "0123456789ABCDEFGHIJ";
		if (base == 16)
			System.out.println("Base 16 result using toHexString function:" + Integer.toHexString(dec));
			StringBuilder result = new StringBuilder();
		while (dec != 0) {
			result.insert(0, chars.charAt(dec % base));
			dec = dec/base;
		}
		return result.toString();
	}
	
	public static int fromBaseUpto10ToDecimal(int dec, int base) {
		//Java OOTB method to do the same. Returns the decimal value of the corresponding base
		System.out.println("Result of Java OOTB method Integer.parseInt(dec,base):" +Integer.parseInt(""+dec, base));
        int k = 0;
		int multiplier = 1;
        int result = 0;
		while (dec != 0) {
			result += (dec % 10) * multiplier;
            multiplier *= base;
            dec = dec/10;

		}
        return result;
		
	}
	
	public static int[] addFractions(int[] a, int[] b) {
		int lcm = LCM(a[1],b[1]);
		int[] result = new int[2];
		result[0] = (a[0] * lcm/a[1]) + (b[0] * lcm/b[1]);
		result[1] = lcm;
		return result;
	}
	
	public static int[] multiplyFractions(int[] a, int[] b) {
		int result[] = {a[0] * b[0], a[1] * b[1]};
		return result;
	}

	
	public static void main(String args[]) {
		System.out.println("GCD of 9 and 6 is: "+NumberOperations.GCD(25,500));
		System.out.println("LCM of 9 and 6 is: "+NumberOperations.LCM(25,500));
		System.out.println("Decimal to base upto 10: "+NumberOperations.fromDecimalToBaseUpto10(6, 2));
		System.out.println("Decimal to base 16: "+NumberOperations.fromDecimalToBaseFrom10Upto20(46447, 16));
		int[] addresult = NumberOperations.addFractions(new int[] {3, 9}, new int[] {8,6});
		int[] multiplyresult =  NumberOperations.multiplyFractions(new int[] {3,9}, new int[] {8,6});
		System.out.println("Adding fractions: "+ addresult[0]+" "+ addresult[1]);
		System.out.println("Multiplying fractions: "+ multiplyresult[0]+" "+multiplyresult[1]);
        System.out.println("Base to decimal: "+ fromBaseUpto10ToDecimal(47, 8));
		
	}
	
}