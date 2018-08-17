package data_structures;

public class BinarySearchTree {

    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void convertArrayToBST(Object[] arr) {

        Node[] nodeArr = assignKeys(arr);
    }

    /**
     * Prepares an array of objects to be converted to a binary search tree by adding each element of the array to an
     * array of nodes where each element has a unique key.
     * @param arr -- the array of objects to add
     * @return an array of nodes
     */
    private Node[] assignKeys(Object[] arr) {

        int length = arr.length;
        Node[] node = new Node[length];

        for (int i = 0; i < length; i++) {

            node[i] = new Node(arr[i], i);
        }

        return node;
    }

    /**
     * Adds a new node to the tree.
     * @param newNode -- the node to add
     */
    public void insert(Node newNode) {

        // Assigns the new node as the root if root is empty.
        if (root == null) root = newNode;

        // Otherwise, traverses the tree until an appropriate empty branch is found.
        else insertRecursively(root, newNode);
    }

    /**
     * Recursive method that adds a new node to the tree.
     * @param parent -- the potential parent of the node to add
     * @param newNode -- the node to add
     */
    private void insertRecursively(Node parent, Node newNode) {

        // Traverses the tree to the left if the new node's key is less than the parent's key
        if (newNode.getKey() < parent.getKey()) {

            // If the parent's left branch is empty, sets it as the new node.
            if (parent.getPrevLeft() == null) parent.setPrevLeft(newNode);

            // Otherwise, calls this method again, with the parent's left branch as the new parent.
            else insertRecursively(parent.getPrevLeft(), newNode);

        // Traverses to the right with the same logic.
        } else if (newNode.getKey() > parent.getKey()) {

            if (parent.getNextRight() == null) parent.setNextRight(newNode);
            else insertRecursively(parent.getNextRight(), newNode);
        }
    }

    /**
     * Searches the tree for the node containing the specified key.
     * @param key -- the key to search for
     * @return the node containing the specified key
     */
    public Node search(int key) {

        if (root.getKey() == key) return root;
        else return searchRecursively(root, key);
    }

    /**
     * Recursively searches the tree for the node containing the specified key.
     * @param node -- the node whose key will be compared to the given key
     * @param key -- the key to search for
     * @return the node containing the specified key
     */
    private Node searchRecursively(Node node, int key) {

        // If the current node contains the key, returns this node.
        if (key == node.getKey()) return node;

        // If the key is less than that of the current node, calls this method again with the current node's
        // left branch as the new node to compare keys with.
        else if (key < node.getKey()) return searchRecursively(node.getPrevLeft(), key);

        // Otherwise, calls the method again, comparing with the current node's right branch.
        else return searchRecursively(node.getNextRight(), key);
    }

    public void delete(int key) {

        /*
            Node node -- the node to be deleted
            Node parent -- the parent of the node to be deleted
            how many children does leaf have?
            node = search(key)
            if node.left == null AND node.right == null
                if node.key == root.key
                    root = null
                    return
                parent = getParent(key)
                if parent.left.key == key, parent.left = null
                else if parent.right.key == key, parent.right = null
            else if node.left == null OR node.right == null
                if node.left == null
                    if node.key == root.key, root = root.right
                    else
                        parent = getParent(key)
                        parent = parent.right
                if node.right == null
                    if node.key == root.key, root = root.left
                    else
                        parent = getParent(key)
                        parent = parent.left
            else (implied if node to be deleted has 2 children)
                need to
                    1) find in order successor
                    2) copy its contents to the node to delete
                    3) delete successor
                1) Find successor
                Node successor = getSuccessor(node.right)
                    Node parent = node
                    if parent.left && parent.right == null, return parent
                    else return getSuccessor(parent.left)
                2) Copy its contents onto the node to delete
                    (key phrase "copy contents" -- don't replace the node)
                if node.key == root.key
                    root.data = successor.data
                    root.key = successor.key
                else
                    node.data = successor.data
                    node.key = successor.key
                3) Delete the successor
                Node parentOfSuccessor = getParent(successor.key)
                parentOfSuccessor.left = null
         */
        Node node = search(key);
        if (node.getPrevLeft() == null && node.getNextRight() == null) {
            if (node.getKey() == root.getKey()) {
                root = null;
                return;
            }
            Node parent = getParent(key);
            if (parent.getPrevLeft().getKey() == key) parent.setPrevLeft(null);
            else if (parent.getNextRight().getKey() == key)  parent.setNextRight(null);
        } else if (node.getPrevLeft() == null || node.getNextRight() == null) {
            if (node.getPrevLeft() == null) {
                if (node.getKey() == root.getKey()) root = root.getNextRight();
                else {
                    Node parent = getParent(key);
                    parent = parent.getNextRight();
                }
            } else if (node.getNextRight() == null) {
                if (node.getKey() == root.getKey()) root = root.getPrevLeft();
                else {
                    Node parent = getParent(key);
                    parent = parent.getPrevLeft();
                }
            }
        } else {
            Node successor = getSuccessor(node.getNextRight());
            if (node.getKey() == root.getKey()) {
                root.setData(successor.getData());
                root.setKey(successor.getKey());
            } else {
                node.setData(successor.getData());
                node.setKey(successor.getKey());
            }
            Node parentOfSuccessor = getParent(successor.getKey());
            parentOfSuccessor.setPrevLeft(null);
        }
    }

    private Node getSuccessor(Node node) {

        Node parent = node;
        if (parent.getPrevLeft() == null && parent.getNextRight() == null) return parent;
        else return getSuccessor(parent.getPrevLeft());
    }

    private Node getParent(int key) {

        if (key == root.getPrevLeft().getKey() || key == root.getNextRight().getKey()) return root;
        else return getParentRecursively(root, key);
    }

    private Node getParentRecursively(Node node, int key) {

        Node left = node.getPrevLeft();
        Node right = node.getNextRight();

        if (left.getKey() == key || right.getKey() == key) return node;
        else if (left.getKey() < key) return getParentRecursively(left, key);
        else return getParentRecursively(right, key);
    }
}
