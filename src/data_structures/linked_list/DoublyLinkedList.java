package data_structures.linked_list;

public class DoublyLinkedList<T> {

    private int counter;
    private Node<T> head;
    private Node<T> tail;

    public DoublyLinkedList() {

        this.counter = 0;
        this.head = null;
        this.tail = null;
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
                    tail = new Node(obj);
                    element.setNext(tail);
                    if (element != head) {
                        tail.setPrev(element);
                    } else continue;
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

        if (index == 0) return head.getValue();
        else {
            Node element = head;
            Object Value = element.getValue();
            for (int i = 0; i < index; i++) {
                element = element.getNext();
                Value = element.getValue();
            }
            return Value;
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
            head.setValue(head.getNext().getValue());
            head.setNext(element.getNext());
        }

        for (int i = 1; i < index; i++) {
            Node prev = element;
            element = element.getNext();
            element.setPrev(prev);
        }

        element = tail.getPrev();
        element.setNext(null);
        tail = element;

        counter--;
    }

    /**
     * Adds an object to a new node at the start of the list.
     * @param obj the object to add
     */
    public void addFirst(T obj) {

        Node temp = new Node(head.getValue());
        temp.setNext(head.getNext());
        head.setValue(obj);
        head.setNext(temp);
        counter++;
    }

    /**
     * Adds an object to a new node at the end of the list.
     * @param obj the object to add
     */
    public void addLast(Object obj) {

        Node element = new Node(obj);
        tail.setNext(element);
        element.setPrev(tail);
        tail = element;
        counter++;
    }

    /**
     * Removes the first node in the list by setting the object it contains to that of its linked node and referring its
     * linked node to the node linked to that one.
     */
    public void removeFirst() {

        head.setValue(head.getNext().getValue());
        head.setNext(head.getNext().getNext());
        counter--;
    }

    /**
     * Removes the last node in the list by setting the reference to its linked node as null.
     */
    public void removeLast() {

        Node element = tail.getPrev();
        tail = element;
        tail.setNext(null);
        counter--;
    }

    public Node getFirst() {
        return head;
    }

    public Node getLast() {
        return tail;
    }

    /**
     * Returns the number of items in the list.
     * @return the integer contained in the counter
     */
    public int size() {
        return counter;
    }

    /**
     * Returns the objects in the list in string format.
     * @return the string representation of the objects in the list
     */
    @Override
    public String toString() {

        StringBuilder str = new StringBuilder("DoublyLinkedList =\n{");

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
    private void isIndexOutOfBounds(int index) {
        if (index < 0 || index >= counter) throw new ArrayIndexOutOfBoundsException("Invalid index.");
    }

}
