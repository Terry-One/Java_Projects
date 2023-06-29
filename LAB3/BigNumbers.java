import java.math.BigInteger;

public class BigNumbers {
    
    //takes the input with string b and length c, computes its hash code and return it
    public static BigInteger fnv(String b, int c) {
		char[] p = b.toCharArray();
		BigInteger h = new BigInteger("14695981039346656037");

		for (int i = 0; i < c; i++) {
			h = h.multiply(new BigInteger("1099511628211"));
			h = h.xor(new BigInteger(String.valueOf((int)p[i])));

            // simulate overflow
            // this number is 2^64-1, biggest number for uint_64 bit number
			h = h.mod(new BigInteger("18446744073709551615"));
        }
		return h;
	}

	public static void main(String args[]) {
		String str1 = "BIG NUMBERS";
		String str2 = "BIG NUMBERS";
		String str3 = "big numbers";


		System.out.println(fnv(str1,11));
		System.out.println(fnv(str2,11));
		System.out.println(fnv(str3,11));

		System.out.println();
	}
}
