package sorting_algorithms;

import java.util.Arrays;

public class QuickSort {

    public static void sort(int[] array, int left, int right) {

        // This implementation sets the pivot as the midpoint (or nearest point) of the array.
        int pivot = array[(left + right) / 2];
        // Left & right are fixed values, whereas i & j are meant to increment or decrement from them.
        int i = left;
        int j = right;
        // Prevents an out of bounds exception from occurring.
        while (i <= j) {
            while (array[i] < pivot) {
                i++;
            }
            while (array[j] > pivot) {
                j--;
            }
            // Why not "if (array[i] >= pivot && pivot >= array[j])"? Without the check "i <= j", the resulting code
            // could switch two previously ordered values out of order.
            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        // Since i > j at this point, this splits the array into two and recursively sorts each side.
        if (left < j) sort(array, left, j);
        if (i < right) sort(array, i, right);
    }
}
