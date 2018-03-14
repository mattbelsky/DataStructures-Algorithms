package sorting_algorithms;

public class MergeSort extends SortingAlgorithm {
    private int[] numbers;
    private int[] helper;

    private int number;

    public MergeSort(int[] values) {
        super();
        this.numbers = values;
        number = values.length;
        this.helper = new int[number];
        mergeSort(0, number - 1);
    }

    public void mergeSort(int low, int high) {
        // Check if low is smaller than high. If not, then the array is sorted.
        if (low < high) {

            // Get the index of the element which is in the middle.
            int middle = low + (high - low) / 2;

            // Sort the left side of the array.
            mergeSort(low, middle);

            // Sort the right side of the array.
            mergeSort(middle + 1, high);

            // Combine them both.
            merge(low, middle, high);
        }
    }

    public void merge(int low, int middle, int high) {
        // Copy both parts into the helper array.
        for (int i = low; i <= high; i++) {
            helper[i] = numbers[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;

        // Copy the smallest values from either the left or the right side back to the original array.
        while (i <= middle) {
            numbers[k] = helper[i];
            k++;
            i++;
        }

        // Since we are sorting in-place, any leftover elements from the right side are already at the right position.
    }
}
