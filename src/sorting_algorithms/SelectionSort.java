package sorting_algorithms;

public class SelectionSort {

    public static int[] sort(int[] unsorted) {

        int[] sorted = new int[unsorted.length];

        // Iterate through each element in the array.
        for (int i = 0; i < unsorted.length; i++) {

            // Initialize min with the the value of the current element -- all elements preceding it become irrelevant
            // with each iteration.
            int min = unsorted[i];
            int positionMin = i;

            // Compare current element with each element from itself to the end of the array.
            for (int j = i; j < unsorted.length; j++) {
                // If new element is less than min, set min and its position to the qualifying element.
                if (unsorted[j] < min) {
                    min = unsorted[j];
                    positionMin = j;
                }
            }
            // Set the value of the sorted array at the current position to the value of the lowest element from this
            // iteration.
            sorted[i] = min;
            // Move the current element to the position of the lowest value element.
            unsorted[positionMin] = unsorted[i];
        }
        return sorted;
    }
}
