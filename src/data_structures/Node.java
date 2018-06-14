package data_structures;

public class Node {

    private Object data;
    private Node next;
    private Node prev;

    public Node(Object data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getNext() {
        return next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
