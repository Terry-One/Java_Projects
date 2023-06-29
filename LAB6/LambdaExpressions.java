import java.util.Arrays;
interface TwoStringPredicate {
	public boolean compare(String s1, String s2);
}

public class LambdaExpressions {
	//better String method for part 2
	public static String betterString(String s1, String s2, TwoStringPredicate lambda) {
		if (lambda.compare(s1, s2))
			return s1;
		else
			return s2;
	}
	// static helper function for part 1
	public static int helper(String s1, String s2) {
		if (s1.indexOf("e") != 0 && s2.indexOf("e") == 0)
			return 1;
		else if (s1.indexOf("e") == 0 && s2.indexOf("e") != 0)
			return -1;
		else
			return 0;
	}

	public static void main(String[] args) {
		
		String[] strings = new String[] { 
			"hello world",
			"terry wan",
			"ece 325",
			"lab number 6",
			"e",
			"apple"
		};
		String[] string = strings.clone();


		//Part 1, Basic Lambdas
		System.out.println("Part 1: Basic Lambdas");
		System.out.println();
		// sort by length
		System.out.println("Sort by length");
		Arrays.sort(string, (String s1, String s2) -> s1.length() - s2.length());
		System.out.println(Arrays.asList(string));
		System.out.println();

		// sort by reverse length
		System.out.println("Sort by reverse length");
		Arrays.sort(string, (String s1, String s2) -> s2.length() - s1.length());
		System.out.println(Arrays.asList(string));
		System.out.println();

		// sort by first character
		System.out.println("Sort by first character");
		Arrays.sort(string, (String s1, String s2) -> s1.charAt(0) - s2.charAt(0));
		System.out.println(Arrays.asList(string));
		System.out.println();

		// sort by strings that contain "e" first, everything else second
		System.out.println("Strings that contain \"e\" first, everything else second");
		Arrays.sort(string, (String s1, String s2) -> {
			if (s1.indexOf("e") != 0 && s2.indexOf("e") == 0)
				return 1;
			else if (s1.indexOf("e") == 0 && s2.indexOf("e") != 0)
				return -1;
			else
				return 0;
		});
		System.out.println(Arrays.asList(string));
		System.out.println();
		
		// previous problem but using static helper method
		System.out.println("Redo the Previous problem but using static helper method");
		Arrays.sort(string, (String s1, String s2) -> helper(s1, s2));
		System.out.println(Arrays.asList(string));
		System.out.println();


		//Part 2, Better String
		System.out.println("Part 2, Better String");
		System.out.println();

		String string1 = "This is string 1";
		String string2 = "This is the longer string";
		String longer = betterString(string1, string2, (s1, s2) -> s1.length() > s2.length());
		String first  = betterString(string1, string2, (s1, s2) -> true);
		
		System.out.println("Longer string: " + longer);
		System.out.println("First  string: " + first);
	}
	
}
