package data_structures;

public class GenericStack<T> {
    private T[] arr;
    private int putloc, getloc;

    public GenericStack(T[] arr) {
        this.arr = arr;
        putloc = 0;
        getloc = arr.length - 1;
    }

    public void push(T a) {
        if (putloc == arr.length) {
            System.out.println("Stack is full.");
            return;
        }
        arr[putloc++] = a;
    }

    public T pop() {
        if (getloc < 0) {
            System.out.println("Stack is empty");
            return null;
        }
        return arr[getloc--];
    }
}