package data_structures;

// TODO Figure out how to compare generic objects and fix methods indexOf(Object) and remove(Object).
public class ArrayList<T> {

    private Object[] array;
    private int numObjects;

    public ArrayList() {

        this.array = new Object[10];
        this.numObjects = 0;
    }

    public ArrayList(int initialSize) {

        this.array = new Object[initialSize];
        this.numObjects = 0;
    }

    /**
     * Appends the specified object to the end of the list.
     * @param obj the object to add
     */
    public void add(T obj) {

        numObjects++;
        resize();

        for (int i = 0; i < numObjects; i++) {
            if (array[i] == null) {
                array[i] = obj;
                break;
            }
        }
    }

    /**
     * Adds the specified object at the specified index.
     * @param index the index at which to add the object
     * @param obj the object to add
     */
    public void add(int index, T obj) {

        numObjects++;
        isIndexOutOfBounds(index);
        resize();

        for (int i = numObjects - 1; i >= index; i--) {
            if (i == index) {
                array[i] = obj;
                break;
            }
            array[i] = array[i - 1];
        }
    }

    /**
     * Gets the object at the specified index.
     * @param index the index of the object to return
     * @return the object at the specified index
     */
    public Object get(int index) {

        isIndexOutOfBounds(index);
        return array[index];
    }

    /**
     * Removes the object at the specified index.
     * @param index the index of the object to remove
     */
    public void remove(int index) {

        isIndexOutOfBounds(index);
        numObjects--;
        for (int i = index; i < numObjects; i++) {
            array[i] = array[i + 1];
        }
        array[numObjects] = null;
        resize();
    }

    /**
     * Removes the specified object from the list.
     * @param obj the object to remove
     */
    public void remove(Object obj) {

        boolean exists = false;
        for (int i = 0; i < numObjects; i++) {
            if (array[i].equals(obj)) {
                remove(i);
                exists = true;
            }
        }

        if (exists == false) throw new IllegalArgumentException("Object not found in ArrayList.");
    }

    /**
     * Gets the index of the specified object if it exists in the list.
     * @param obj the object to search for
     * @return the index of the object, -1 if it doesn't exist
     */
    public int indexOf(Object obj) {

        for (int i = 0; i < numObjects; i++) {
            if (array[i].equals(obj)) return i;
        }

        return -1;
    }

    /**
     * Checks if the list contains the specified object.
     * @param obj the object to check for
     * @return true if the list contains the object, false otherwise
     */
    public boolean contains(T obj) {

        if (indexOf(obj) == -1) return false;
        else return true;
    }

    /**
     * Resizes the array containing all items in the list to the number of objects contained in the list.
     */
    public void trimToSize() {

        Object[] temp = new Object[numObjects];

        for (int i = 0; i < numObjects; i++) {
            temp[i] = array[i];
        }

        array = temp;
    }

    /**
     * Removes all items from the list.
     */
    public void clear() {

        array = new Object[0];
        numObjects = 0;
    }

    /**
     * Checks if the list contains no objects.
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {

        return numObjects == 0;
    }

    /**
     * Returns the number of items in the list.
     * @return the integer contained in the counter
     */
    public int size() {
        return numObjects;
    }

    /**
     * Returns the objects in the the list in string form;
     * @return the string representation of the objects in the list
     */
    @Override
    public String toString() {

        StringBuilder str = new StringBuilder("ArrayList =\n{");

        for (int i = 0; i < numObjects; i++) {
            str.append("\n\t" + array[i]);
        }

        str.append("\n}");

        return str.toString();
    }

    /**
     * Checks if the index is out of bounds and throws an exception with a custom message if so.
     * @param index
     */
    private void isIndexOutOfBounds(int index) {
        if (index < 0 || index >= numObjects) throw new ArrayIndexOutOfBoundsException("Invalid index.");
    }

    /**
     * Resizes the array containing the list's objects as needed.
     */
    private void resize() {

        int length = array.length;
        int shortLength = length / 3 * 2 + 1;

        if (numObjects == length) {

            Object[] temp = new Object[numObjects * 3 / 2 + 1];
            for (int i = 0; i < numObjects; i++) temp[i] = array[i];
            array = temp;

        } else if (numObjects < shortLength) {

            Object[] temp = new Object[shortLength];
            for (int i = 0; i < numObjects; i++) {
                temp[i] = array[i];
            }
            array = temp;
        }
    }

    public int getLengthArray() {
        return array.length;
    }
}
