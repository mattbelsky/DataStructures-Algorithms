package sorting_algorithms;

import java.util.ArrayList;

public class SortController {
    public static void main(String[] args) {
        int[] intArray = {99, -10, 100123, 18, -978, 5623, 463, -9, 287, 49};

        sortAndTime(intArray);
    }

    /*  Creates new objects of the various sort classes with the parameter array, sorts them, records the time to
        complete each sorting algorithm, and prints the original and sorted arrays and displays the time to completion
        for each.
     */
    public static void sortAndTime(int[] original) {
        // the empty array that will be sorted
        int[] sorted = new int[original.length];

        // Creates new objects of each sort class and passes each an array parameter for it to sort.
        BubbleSort bubble = new BubbleSort(original);
        MergeSort merge = new MergeSort(original);

        // Creates a new list to hold the sort objects.
        ArrayList<SortingAlgorithm> sortObjs = new ArrayList();
        sortObjs.add(bubble);
        sortObjs.add(merge);

        // Sorts the array and measures the time it takes to complete this sort.
        for (int i = 0; i < sortObjs.size(); i++) {
            Long startTime = System.nanoTime();

            
            // THIS STATEMENT DOES NOT WORK
            // sorted = sortObjs.get(i).sort();


            Long endTime = System.nanoTime();
            double elapsedTime = endTime.doubleValue() - startTime.doubleValue();

            // Prints the original and sorted arrays.
            printArray(original, sorted);

            System.out.println("Time to complete bubble sort is " + elapsedTime + " nanoseconds.");
        }
    }

    // Displays the original array and the sorted array.
    public static void printArray(int[] unsorted, int[] sorted) {
        // Displays the original array.
        System.out.println("Original array:");
        for (int element : unsorted) {
            System.out.print(element + " ");
        }
        System.out.println("\n");

        // Displays the sorted array.
        System.out.println("Sorted array:");
        for (int element : sorted) {
            System.out.print(element + " ");
        }
        System.out.println("\n");
    }
}