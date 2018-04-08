package sorting_algorithms;

public class BubbleSort extends SortingAlgorithm {
    private int[] array;
    private int length;

    public BubbleSort(int[] array) {
        this.array = new int[array.length];
        this.length = this.array.length;

        // Initializes the instance variable array with the values of the parameter array
        for (int i = 0; i < array.length; i++) {
            this.array[i] = array[i];
        }
    }

    // This is the bubble sort.
    public int[] sort() {
        int i, j, temp;

        for (i = 1; i < length; i++) {
            for (j = length - 1; j >= i; j--) {
                if (array[j - 1] > array[j]) { // if out of order
                    // exchange elements
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }

        return array;
    }
}
