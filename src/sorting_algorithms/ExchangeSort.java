package sorting_algorithms;

import java.util.Random;

public class ExchangeSort {
    public static void main(String[] args) {
        // Randomly generates the values of the array as well as the array size.
        Random element = new Random();
        Random size = new Random();
        int[] nums = new int[size.nextInt()];
        for (int num : nums) {
            num = element.nextInt();
        }

        // Prints the unsorted array and its length.
        System.out.println("Array length is " + nums.length + " integers.\n");
        for (int num : nums) {
            System.out.println(num);
        }
        System.out.println(nums.length + "\n");

        // the exchange sort
        int temp;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[i]) {
                    temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }

        // Prints the unsorted array and its length.
        System.out.println("Array length is " + nums.length + " integers.\n");
        for (int num : nums) {
            System.out.println(num);
        }
        System.out.println(nums.length + "\n");
    }
}
