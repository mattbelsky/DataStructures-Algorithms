package data_structures;

class Stack {
    private int[] intArray;
    private int putloc, getloc;
    private int size;

    public Stack(int size) {
        intArray = new int[size];
        putloc = 0;
        getloc = size - 1;
        this.size = size;
    }

    public void push(int x) {
        if (putloc == intArray.length) {
            System.out.println("Stack is full.");
            return;
        }
        intArray[putloc++] = x;
    }

    public int pop() {
        if (getloc == putloc) {
            System.out.println("Stack is empty.");
            return 0;
        }
        return intArray[getloc--];
    }
}
