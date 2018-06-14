package data_structures;

public class LinkedListController {

    public static void main(String[] args) {

        DoublyLinkedList list = new DoublyLinkedList();

        list.add("Hi ");
        list.add("my ");
        list.add("name ");
        list.add("is ");
        list.add("Charles.");

        System.out.println("The original list and its size.\n");
        printListHeadCurrentTailSize(list);

        System.out.println("The last element has been removed.\n");
        list.remove(list.size()-1);
        printListHeadCurrentTailSize(list);

        System.out.println("The fourth element has been removed.\n");
        list.remove(3);
        printListHeadCurrentTailSize(list);

        System.out.println("A node has been added to the front of the list.\n");
        list.addFirst("howdy");
        printListHeadCurrentTailSize(list);

        System.out.println("A node has been added to the end of the list.\n");
        list.addLast("partner");
        printListHeadCurrentTailSize(list);

        System.out.println("The first and last nodes have been removed.\n");
        list.removeFirst();
        list.removeLast();
        printListHeadCurrentTailSize(list);
    }

    static void printListHeadCurrentTailSize(DoublyLinkedList list) {
        System.out.println(list.toString());
        System.out.println("Head:");
        try {
            System.out.println("\tprev = " + list.getFirst().getPrev().getData());
        } catch (NullPointerException e) {
            System.out.println("\tprev = null");
        }
        System.out.println("\thead = " + list.getFirst().getData());
        System.out.println("\tnext = " + list.getFirst().getNext().getData());
        System.out.println("Tail:");
        System.out.println("\tprev = " + list.getLast().getPrev().getData());
        System.out.println("\ttail = " + list.getLast().getData());
        try {
            System.out.println("\tnext = " + list.getLast().getNext().getData());
        } catch (NullPointerException e) {
            System.out.println("\tnext = null");
        }
        System.out.println("Size: " + list.size() + "\n___________________________________\n");
    }
}
