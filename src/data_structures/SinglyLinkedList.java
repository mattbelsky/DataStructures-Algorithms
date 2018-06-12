package data_structures;

public class SinglyLinkedList {

    private int counter;
    private Node head;

    public SinglyLinkedList() {

        this.counter = 0;
        this.head = null;
    }

    /**
     * Adds the specified object to a new node in the list.
     * @param obj the object to add
     */
    public void add(Object obj) {

        if (head == null) {
            head = new Node(obj);
        } else {
            Node element = head;
            for (int i = 0; i < counter; i++) {
                if (element.getNext() == null) {
                    element.setNext(new Node(obj));
                } else element = element.getNext();
            }
        }

        counter++;
    }

    /**
     * Gets the object contained within the node at the specified index.
     * @param index the index of the node to get
     * @return the object within retrieved node
     */
    public Object get(int index) {

        isIndexOutOfBounds(index);

        if (index == 0) return head.getData();
        else {
            Node element = head;
            Object data = element.getData();
            for (int i = 0; i < index; i++) {
                element = element.getNext();
                data = element.getData();
            }
            return data;
        }
    }

    /**
     * Removes the reference to the node at the specified index and links the previous node with the subsequent one.
     * @param index the index of the node to be removed from the reference chain
     */
    public void remove(int index) {

        isIndexOutOfBounds(index);
        Node element = head.getNext();

        if (index == 0) {
            head.setData(head.getNext().getData());
            head.setNext(element.getNext());
        }

        for (int i = 1; i < index; i++) {
            element = element.getNext();
        }

        try {
            element.setNext(element.getNext().getNext());
        } catch (NullPointerException e) {
            // Ignore exception
        }

        counter--;
    }

    /**
     * Adds an object to a new node at the start of the list.
     * @param obj the object to add
     */
    public void addFirst(Object obj) {

        Node temp = new Node(head.getData());
        temp.setNext(head.getNext());
        head.setData(obj);
        head.setNext(temp);
        counter++;
    }

    /**
     * Adds an object to a new node at the end of the list.
     * @param obj the object to add
     */
    public void addLast(Object obj) {

        Node element = head;

        for (int i = 0; i < counter - 1; i++) {
            element = element.getNext();
        }

        element.setNext(new Node(obj));
        counter++;
    }

    /**
     * Removes the first node in the list by setting the object it contains to that of its linked node and referring its
     * linked node to the node linked to that one.
     */
    public void removeFirst() {

        head.setData(head.getNext().getData());
        head.setNext(head.getNext().getNext());
        counter--;
    }

    /**
     * Removes the last node in the list by setting the reference to its linked node as null.
     */
    public void removeLast() {

        Node element = head;

        for (int i = 0; i < counter - 1; i++) {
            element = element.getNext();
        }

        element.setNext(null);
        counter--;
    }

    /**
     * Returns the number of items in the list.
     * @return the integer contained in the counter
     */
    public int size() {
        return counter;
    }

    /**
     * Returns the objects the list in string format.
     * @return the string representation of the objects in the list
     */
    @Override
    public String toString() {

        StringBuilder str = new StringBuilder("SinglyLinkedList =\n{");

        for (int i = 0; i < this.size(); i++) {
            str.append("\n\t" + this.get(i).toString());
        }

        str.append("\n}");

        return str.toString();
    }

    /**
     * Checks if the index is out of bounds and throws an exception with a custom message if so.
     * @param index
     */
    public void isIndexOutOfBounds(int index) {
        if (index < 0 || index >= counter) throw new ArrayIndexOutOfBoundsException("Invalid index.");
    }

}
