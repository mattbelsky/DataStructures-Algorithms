/**
 * Swaps the values of two string variables without using a third temporary variable.
 */
class SwapTwoValuesNoThirdVar {

	public static void main(String[] args) {

		String a = "howdy";
		String b = "pardner";

		System.out.println("a = " + a + "\nb = " + b);

		int lengthA = a.length();
		
		a = a.concat(b);
		b = a.substring(0, lengthA);
		a = a.substring(lengthA);

		System.out.println("a = " + a + "\nb = " + b);

	}
}
