package data_structures;

public class ArrayListController {

    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i <= 10; i++) {
            list.add(i);
        }

        System.out.println("The original list and its size.");
        printListAttributes(list);

        System.out.println("Added an object at index = 7.");
        list.add(7, 278);
        printListAttributes(list);

        System.out.println("Added an object after the last element.");
        list.add(list.size(), 129);
        printListAttributes(list);

        System.out.println("Added an object at the first index.");
        list.add(0, 331);
        printListAttributes(list);

        System.out.println("Removed the first element.");
        list.remove(0);
        printListAttributes(list);

//        System.out.println("Removed the object \"4\" from the array.");
//        list.remove(Integer.valueOf(4));
//        printListAttributes(list);

        int size = list.size();
        for (int i = size - 2; i > size - 6; i--) {
            System.out.println("Removing four elements from the array:\nRemoved element at index = " + i + ".");
            list.remove(i);
            printListAttributes(list);
        }

        System.out.println("Removed object with the value \"4\" from the list.");
        list.remove(Integer.valueOf(4));
        printListAttributes(list);

        System.out.println("Trimmed the array to size.");
        list.trimToSize();
        printListAttributes(list);

        System.out.println(list.contains(32));
        System.out.println(list.indexOf(0));
        System.out.println(list.indexOf(278));
        System.out.println(list.isEmpty());
    }

    static void printListAttributes(ArrayList list) {
        System.out.println(list.toString());
        System.out.println("Size:\t\t\t" + list.size());
        System.out.println("Array length:\t" + list.getLengthArray());
        System.out.println("\n___________________________________\n");
    }
}
