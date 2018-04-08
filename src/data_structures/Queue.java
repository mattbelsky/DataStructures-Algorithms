package data_structures;

class Queue {
    private int[] intArray;
    private int putLoc, getLoc;
    private int size;

    public Queue(int size) {
        intArray = new int[size];
        putLoc = getLoc = 0;
        this.size = size;
    }

    public void push(int x) {
        if (putLoc == intArray.length) {
            System.out.println("Queue is full.");
            return;
        }
        intArray[putLoc++] = x;
    }

    public int pop() {
        if (getLoc == putLoc) {
            System.out.println("Queue is empty.");
            return 0;
        }
        return intArray[getLoc++];
    }
}

