package data_structures;

/**
 * A general purpose node class for various type of data structures. As such, not every property will always be used.
 */
public class Node {

    private Object data;
    private Node prevLeft;
    private Node nextRight;
    private int key;

    public Node(Object data) {
        this.data = data;
        this.prevLeft = null;
        this.nextRight = null;
        this.key = 0;
    }

    public Node(Object data, int key) {
        this.data = data;
        this.key = key;
        this.prevLeft = null;
        this.nextRight = null;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getPrevLeft() {
        return prevLeft;
    }

    public void setPrevLeft(Node prevLeft) {
        this.prevLeft = prevLeft;
    }

    public Node getNextRight() {
        return nextRight;
    }

    public void setNextRight(Node nextRight) {
        this.nextRight = nextRight;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
