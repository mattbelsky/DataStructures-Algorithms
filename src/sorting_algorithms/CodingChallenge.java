package sorting_algorithms;

import java.util.Arrays;

class CodingChallenge {
	public static void main(String[] args) {

		// Populates the array with a loop.
		/*for (int i = 1; i < 6; i++) {
			nums[i - 1] = i;
		}*/
	
		// Populates the array with CLI args.	
		try {
			if (args.length == 0) throw new Exception();
			
			int[] nums = new int[args.length];

			for (int i = 0; i < args.length; i++) {
				nums[i] = Integer.parseInt(args[i]);
			}

			// Prints the original array.
			System.out.println("Original:");
			printArray(nums);

			// Reverses the array.
			// reverseArray(nums);

			// Sorts the array.
			bubbleSort(nums);
		}
		catch (Exception exc) {
			System.out.println("Array is empty.");
			return;
		}
	}
	
	// Reverses the array and displays the result.
	public static void reverseArray(int[] array) {
		// Reverses the array.
		int t;
		for (int i = 0; i < array.length / 2; i++) {
			t = array[array.length - 1 - i];
			array[array.length - 1 - i] = array[i];
			array[i] = t;
		}

		// Prints the reversed array.
		System.out.println("Reversed:");
		printArray(array);
	}

	// Sorts the array and displays the result.
	public static void bubbleSort(int[] array) {
		// This is the Bubble Sort.
		for (int i = 0; i < array.length; i++) {
			for (int j = 1; j < array.length - i; j++) {
				if (array[j] < array[j - 1]) {
					int temp = array[j];
					array[j] = array[j - 1];
					array[j - 1] = temp;
				}
			}

			/*	Helpful expression for seeing each step of the sorting proces:
				System.out.println(Arrays.toString(array));	*/
		}

		// Prints the sorted array.
		System.out.println("Sorted:");
		printArray(array);
	}

	// Prints the array.
	public static void printArray(int[] array) {
		for (int element : array) {
			System.out.println(element);
		}
		System.out.println();
	}
}
