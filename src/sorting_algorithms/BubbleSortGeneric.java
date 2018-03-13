package sorting_algorithms;

// Key question here: is there a way to sort generic arrays of any type?

public class BubbleSortGeneric {
    public static void main(String[] args) {
        Integer intArray[] = {99, -10, 100123, 18, -978, 5623, 463, -9, 287, 49};
        Long longArray[] = {29834l, 98234l, -283748723l, 28l};
        String stringArray[] = {"abcdef", "Abcdef", "$4", "878"};
        int a, b, t;

        // Display original array, sort the array, display the sorted array.
        displaySortDisplay(intArray);
        displaySortDisplay(longArray);
        displaySortDisplay(stringArray);
    }

    // Why "extends Comparable<T>"? And why does this work if Comparable<T> is an interface and compareTo() is empty?
    // I can't find a Comparable<T> class anywhere, so where does the code for compareTo() actually reside?

    // Why doesn't this sort a string array?
    public static <T extends Comparable<T>> void sortArray(T[] array) {
        int size = array.length; // the number of elements to sort
        int a, b;
        T temp;

        // This is the Bubble sort.
        for (a = 1; a < size; a++) {
            for (b = size - 1; b >= a; b--) {
                if (array[b - 1].compareTo(array[b]) == 1) { // if out of order
                    // exchange elements
                    temp = array[b - 1];
                    array[b - 1] = array[b];
                    array[b] = temp;
                }
            }
        }
    }

    public static <T> void printArray(T[] array) {
        int size = array.length;
        for (int i = 0; i < size; i++) {
            System.out.println(" " + array[i]);
        }
        System.out.println();
    }

    // Without extending Comparable<T>, error "inferred type does not comfort to upper bound(s)" is thrown. Why?
    public static <T extends Comparable<T>> void displaySortDisplay(T[] array) {
        printArray(array);
        sortArray(array);
        printArray(array);
    }
}
