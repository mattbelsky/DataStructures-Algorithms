package data_structures;

public class DoublyLinkedList {

    private int counter;
    private Node head;
    private Node tail;

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
                if (element.getNextRight() == null) {
                    tail = new Node(obj);
                    element.setNextRight(tail);
                    if (element != head) {
                        tail.setPrevLeft(element);
                    } else continue;
                } else element = element.getNextRight();
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
                element = element.getNextRight();
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
        Node element = head.getNextRight();

        if (index == 0) {
            head.setData(head.getNextRight().getData());
            head.setNextRight(element.getNextRight());
        }

        for (int i = 1; i < index; i++) {
            Node prev = element;
            element = element.getNextRight();
            element.setPrevLeft(prev);
        }

        element = tail.getPrevLeft();
        element.setNextRight(null);
        tail = element;

        counter--;
    }

    /**
     * Adds an object to a new node at the start of the list.
     * @param obj the object to add
     */
    public void addFirst(Object obj) {

        Node temp = new Node(head.getData());
        temp.setNextRight(head.getNextRight());
        head.setData(obj);
        head.setNextRight(temp);
        counter++;
    }

    /**
     * Adds an object to a new node at the end of the list.
     * @param obj the object to add
     */
    public void addLast(Object obj) {

        Node element = new Node(obj);
        tail.setNextRight(element);
        element.setPrevLeft(tail);
        tail = element;
        counter++;
    }

    /**
     * Removes the first node in the list by setting the object it contains to that of its linked node and referring its
     * linked node to the node linked to that one.
     */
    public void removeFirst() {

        head.setData(head.getNextRight().getData());
        head.setNextRight(head.getNextRight().getNextRight());
        counter--;
    }

    /**
     * Removes the last node in the list by setting the reference to its linked node as null.
     */
    public void removeLast() {

        Node element = tail.getPrevLeft();
        tail = element;
        tail.setNextRight(null);
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
