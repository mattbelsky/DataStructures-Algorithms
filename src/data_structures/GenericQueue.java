package data_structures;

public class GenericQueue<T> {
    private T[] arr;
    private int putloc, getloc;

    public GenericQueue(T[] arr) {
        this.arr = arr;
        putloc = getloc = 0;
    }

    public void put(T a) {
        if (putloc == arr.length) {
            System.out.println("Queue is full.");
            return;
        }
        arr[putloc++] = a;
    }

    public T get() {
        if (getloc == putloc) {
            System.out.println("Queue is empty.");
            return null;
        }
        return arr[getloc++];
    }
}