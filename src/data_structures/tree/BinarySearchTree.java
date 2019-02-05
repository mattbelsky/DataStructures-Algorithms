package data_structures.tree;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> root;

    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * Determines whether the tree is empty by checking if it's root node is null.
     * @return true or false
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Adds the input value to a new node on the tree.
     * @param value -- the value to add
     */
    public void insert(T value) {

        // Assigns the new node as the root if root is empty.
        if (root == null) root = new Node<>(value);

        // Otherwise, traverses the tree until an appropriate empty branch is found.
        else insertRecursively(root, value);
    }

    /**
     * Recursive method that adds a new node to the tree.
     * @param parent -- the potential parent of the node to add
     * @param value -- the value to add
     */
    private void insertRecursively(Node<T> parent, T value) {

        // Traverses the tree to the left if the new node's key is less than the parent's key.
        if (value.compareTo(parent.getValue()) == -1) {

            // If the parent's left branch is empty, sets it as the new node.
            if (parent.getLeft() == null) parent.setLeft(new Node<>(value));

            // Otherwise, calls this method again, with the parent's left branch as the new parent.
            else insertRecursively(parent.getLeft(), value);

        // Traverses to the right with reversed logic.
        } else if (value.compareTo(parent.getValue()) == 1) {

            if (parent.getRight() == null) parent.setRight(new Node<>(value));
            else insertRecursively(parent.getRight(), value);
        }
    }

    /**
     * Searches the tree for the node containing the specified value.
     * @param value -- the value to search for
     * @return the node containing the specified value
     */
    public T search(T value) {

        if (root.getValue().compareTo(value) == 0) return root.getValue();
        else return searchRecursively(root, value).getValue();
    }

    /**
     * Recursively searches the tree for the node containing the specified value.
     * @param node -- the node whose key will be compared to the given value
     * @param value -- the value to search for
     * @return the node containing the specified value
     */
    private Node<T> searchRecursively(Node<T> node, T value) {

        // If the key is less than that of the current node, calls this method again with the current node's
        // left branch as the new node to compare keys with.
        if (value.compareTo(node.getValue()) == -1) {
            node = node.getLeft();
            return searchRecursively(node, value);
        }

        // Otherwise, calls the method again, comparing with the current node's right branch.
        else if (value.compareTo(node.getValue()) == 1) {
            node = node.getRight();
            return searchRecursively(node, value);
        }

        // If the current node contains the key, returns this node.
        return node;
    }

    /**
     * Removes the node containing the specified value from the tree.
     * @param value -- the value to remove
     */
    public void remove(T value) {

        // Declares and initializes the node whose value is being search for and begins a recursive search.
        Node<T> node = new Node<>(value);
        if (root.getValue().compareTo(value) == 0) node = root;
        else node = searchRecursively(root, value);

        // If node has no children...
        if (node.getLeft() == null && node.getRight() == null) {
            // If it's the root, remove root.
            if (node.getValue().compareTo(root.getValue()) == 0) {
                root = null;
                return;
            }
            // Otherwise, get its parent node.
            Node parent = getParent(value);
            // Determines whether the node is the left or right child of the parent and sets the appropriate branch of
            // the parent to null.
            if (parent.getLeft().getValue().compareTo(value) == 0) parent.setLeft(null);
            else if (parent.getRight().getValue().compareTo(value) == 0)  parent.setRight(null);

        // If the node has either no left or no right branch...
        } else if (node.getLeft() == null || node.getRight() == null) {
            // If its left branch is null...
            if (node.getLeft() == null) {
                // If the value in question belongs to the root and root has no left branch, its right branch becomes
                // the new root.
                if (node.getValue().compareTo(root.getValue()) == 0) root = root.getRight();
                // Otherwise, finds the parent, determines whether the value in question belongs to the node in the
                // parent's left or right branch, and sets the appropriate branch to reference the node's right branch.
                else {
                    Node<T> parent = getParent(value);
                    if (parent.getLeft().getValue().compareTo(value) == 0) parent.setLeft(node.getRight());
                    else parent.setRight(node.getRight());
                }
            // If its right branch is null...
            } else if (node.getRight() == null) {
                // If the value in question belongs to the root and root has no right branch, its left branch becomes
                // the new root.
                if (node.getValue().compareTo(root.getValue()) == 0) root = root.getLeft();
                else {
                    Node<T> parent = getParent(value);
                    if (parent.getLeft().getValue().compareTo(value) == 0) parent.setLeft(node.getLeft());
                    else parent.setRight(node.getRight());
                }
            }
        // If the node has two children...
        } else {
            // Iterates through the current node's left nodes until none remain, replacing its value with that of its left node.
            while (node.getLeft() != null) {
                Node<T> left = node.getLeft();
                node.setValue(left.getValue());
                if (node.getLeft().getLeft() == null && node.getLeft().getRight() == null) {
                    node.setLeft(null);
                }
                node = left;
                // If there are no left nodes but a right node exists, sets the current node's value to that of its right
                // node. Loop will continue to iterate through left nodes.
                if (node.getLeft() == null && node.getRight() != null) {
                    Node<T> right = node.getRight();
                    node.setValue(right.getValue());
                    node = right;
                }
            }
        }
    }

    private Node<T> getParent(T value) {

        if (root.getLeft().getValue().compareTo(value) == 0 || root.getRight().getValue().compareTo(value) == 0) return root;
        else return getParentRecursively(root, value);
    }

    private Node<T> getParentRecursively(Node node, T value) {

        Node<T> left = node.getLeft();
        Node<T> right = node.getRight();

        if (left.getValue().compareTo(value) == 0 || right.getValue().compareTo(value) == 0) return node;
        else if (left.getValue().compareTo(value) == -1) return getParentRecursively(left, value);
        else return getParentRecursively(right, value);
    }
}
