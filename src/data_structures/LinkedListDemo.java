package data_structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListDemo {
    public static void main(String[] args) {

        // LinkedList declaration & initialization
        LinkedList<String> stringList = new LinkedList();
        stringList.add("Red");
        stringList.add("Orange");
        stringList.add("Yellow");
        stringList.add("Green");
        stringList.add("Blue");
        stringList.add("Indigo");
        stringList.add("Violet");

        // Prints original list
        System.out.println(stringList);

        // Prints first element in list
        System.out.println(stringList.getFirst());

        // Prints last element in list
        System.out.println(stringList.getLast());

        // Adds a new element to the start and end of the list as well as to a specified index
        stringList.addFirst("Infrared");
        stringList.addLast("Microwave");
        stringList.add(stringList.size() - 1, "Ultraviolet");

        // Prints the modified list
        System.out.println(stringList);
        System.out.println();

        // Removes the last item in the list
        stringList.removeLast();
        
        // Iterates though the list and prints each element and its index
        for (int i = 0; i < stringList.size(); i++) {
            System.out.println(i + " " + stringList.get(i));
        }
        System.out.println();

        // Add new LinkedList reference to stringList object and prints the new list
        LinkedList<String> newStringList = new LinkedList(stringList);
        System.out.println(newStringList);
        System.out.println();

        // Instantiates a new Iterator and uses it to print the remainder of the list
        Iterator<String> p = stringList.listIterator(2);
        while (p.hasNext()) {
            System.out.println(p.next());
        }
        System.out.println();

        // Instantiates a new Iterator and uses it to print the list in reverse order
        Iterator<String> q = stringList.descendingIterator();
        while (q.hasNext()) {
            System.out.println(q.next());
        }
        System.out.println();
    }
}
