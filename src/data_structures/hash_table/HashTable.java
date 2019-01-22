package data_structures.hash_table;

import java.util.Objects;

public class HashTable<K, V> {

    private final double LOAD_FACTOR = 0.7;
    private Node<K, V>[] buckets;
    private int numBuckets;

    public HashTable() {

        numBuckets = 10;
        buckets = new Node[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            buckets[i] = null;
        }
    }

    /**
     * Gets the value of the node with the specified key.
     * @param key
     * @return
     */
    public V get(K key) {

        int index = hash(key);
        Node<K, V> node = buckets[index];
        
        while (!node.getKey().equals(key)) {
            node = node.getNext();
            if (node == null) return null;
        }
        
        return node.getValue();
    }

    /**
     * Gets the number of buckets in the hash table.
     * @return
     */
    public int size() {

        int count = 0;
        for (Node<K, V> node : buckets) {
            if (node == null) continue;
            while (node.getNext() != null) {
                count++;
                node = node.getNext();
            }
            count++;
        }
        return count;
    }

    /**
     * Adds a new node to the hash table. If there is a collision, chains together buckets with the same index as a linked list.
     * @param key
     * @param value
     */
    public void add(K key, V value) {

        int index = hash(key);
        Node currentNode = buckets[index];
        Node<K, V> newNode = new Node<>(key, value);

        if (currentNode != null) {
            while (currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(newNode);
            return;
        }

        buckets[index] = newNode;
        resize();
    }

    /**
     * Removes the node with the matching key.
     * @param key
     */
    public void remove(K key) {

        int index = hash(key);
        Node<K, V> node = buckets[index];
        Node<K, V> previous = null;

        // If the node is the only one in the bucket, simply remove it from the list.
        if (node.getNext() == null) buckets[index] = null;

        // If the node's key doesn't match the input key, iterate through the chained linked list until it does.
        while (!node.getKey().equals(key)) {

            // Edge case: if none of the nodes in the linked list have this key, simply return without removing anything.
            if (node.getNext() == null) return;
            previous = node;
            node = node.getNext();
        }
        // Edge case: if the first node's key matches yet it is the first in a linked list, remove it from the hash table
        // by setting the next node as the first.
        if (previous == null) {
            buckets[index] = node.getNext();
            return;
        }
        // Executed when the node is either within or at the end of a linked list within the bucket.
        previous.setNext(node.getNext());
    }

    /**
     * Determines if the hash table is empty.
     * @return
     */
    public boolean isEmpty() {

        for (int i = 0; i < numBuckets; i++) {
            if (buckets[i] != null) return false;
        }
        return true;
    }

    /**
     * Hashes the key and gets the remainder of division by the number of buckets, ensuring that the index will never
     * exceed the size of the buckets list.
     * @param key
     * @return
     */
    private int hash(K key) {
        return Objects.hashCode(key) % buckets.length;
    }

    /**
     * Resizes the buckets array when the size of the hash table meets or exceeds the load factor.
     */
    private void resize() {

        double size = this.size();
        if (size / numBuckets >= LOAD_FACTOR) {
            Node[] temp = buckets;
            Node[] buckets = new Node[numBuckets *= 2];
            for (int i = 0; i < temp.length; i++) {
                buckets[i] = temp[i];
            }
        }
    }
}
