package data_structures.tree;

public class AVLTree<T extends Comparable<T>> {

    private Node<T> root;

    public AVLTree() {
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
     * Returns the maximum height of the tree.
     * @return
     */
    public int height() {
        return getHeight(root);
    }

    /**
     * Empties the tree by setting the root node to null.
     */
    public void clear() {
        root = null;
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

        Node<T> node = new Node<>(value);

        // Traverses the tree to the left if the new node's key is less than the parent's key.
        if (value.compareTo(parent.getValue()) == -1) {

            // If the parent's left branch is empty, sets it as the new node.
            if (parent.getLeft() == null) parent.setLeft(node);
            // Otherwise, calls this method again, with the parent's left branch as the new parent.
            else insertRecursively(parent.getLeft(), value);

        // Traverses to the right with reversed logic.
        } else if (value.compareTo(parent.getValue()) == 1) {

            if (parent.getRight() == null) parent.setRight(node);
            else insertRecursively(parent.getRight(), value);
        }

        balance(parent.getLeft());
        balance(parent.getRight());
        balance(parent);
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

        // Declares and initializes the node whose value is being searched for and begins a recursive search.
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
            if (parent.getLeft() == null) {
                if (parent.getRight().getValue().compareTo(value) == 0)
                    parent.setRight(null);
            } else if (parent.getRight() == null) {
                if (parent.getLeft().getValue().compareTo(value) == 0)
                    parent.setLeft(null);
            } else if (parent.getLeft().getValue().compareTo(value) == 0) {
                parent.setLeft(null);
            } else if (parent.getRight().getValue().compareTo(value) == 0) {
                parent.setRight(null);
            }

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
        balance(root);
    }

    /**
     * Balances the tree from the point of view of the specified node so that abs(height(left) - height(right)) <= 1.
     * @param a -- the node from whose point of view the tree will be balanced.
     */
    private void balance(Node<T> a) {

        if (a == null) return;
        Node<T> temp = new Node<>(a.getValue());
        Node<T> b;

        // If current node has a left node but no right...
        if ((b = a.getLeft()) != null && a.getRight() == null) {
            rotateRight(a, temp, b);

        // If current node has a right node but no left...
        } else if ((b = a.getRight()) != null && a.getLeft() == null) {
            rotateLeft(a, temp, b);

        // If current node has two children...
        } else {

            // If node is unbalanced and weighted left...
            if (getBalance(a) > 1) {
                doubleRotateRight(a, temp);
                // After rotation, branch is weighted right, therefore right branch should be rebalanced.
                rebalanceBranch(a, "right");

            // If node is unbalanced and weighted right...
            } else if (getBalance(a) < -1) {
                doubleRotateLeft(a, temp);
                // After rotation, branch is weighted left, therefore left branch should be rebalanced.
                rebalanceBranch(a, "left");
            }
        }
    }

    /**
     * Helper method used to complete a double rotation. Called from the balance() method, which executes the first of
     * two rotations, but because balance() is recursively balancing the tree from the bottom up, a double rotation is
     * not complete unless the specified branch can be traversed down and rotated once more.
     * @param node -- the node to begin from
     * @param direction -- the branch to traverse down from the given node
     */
    private void rebalanceBranch(Node<T> node, String direction) {

        if (node == null) return;
        Node<T> child;
        switch (direction) {
            case "left":
                child = node.getLeft();
                balance(child);
                rebalanceBranch(child, "left");
            case "right":
                child = node.getRight();
                balance(child);
                rebalanceBranch(child, "right");
            default:
                return;
        }
    }

    private void doubleRotateLeft(Node<T> a, Node<T> temp) {
        Node<T> b, c, d, e;
        b = a.getLeft();
        c = a.getRight();
        d = c.getLeft();
        e = c.getRight();
        a.setValue(c.getValue());
        a.setLeft(d);
        a.setRight(e);
        temp.setLeft(b);
        a.getLeft().setLeft(temp);
    }

    private void doubleRotateRight(Node<T> a, Node<T> temp) {
        Node<T> b, c, d, e;
        c = a.getLeft();
        b = a.getRight();
        d = c.getRight();
        e = c.getLeft();
        a.setValue(c.getValue());
        a.setLeft(e);
        a.setRight(d);
        temp.setRight(b);
        a.getRight().setRight(temp);
    }

    private void rotateLeft(Node<T> a, Node<T> temp, Node<T> b) {
        Node<T> c, d;
        // If right node has a right node...
        if ((c = b.getRight()) != null) {
            // If right node has a left node...
            if ((d = b.getLeft()) != null) {
                a.setValue(b.getValue());
                a.setLeft(d);
                a.setRight(c);
                d.setLeft(temp);
            }
            a.setValue(b.getValue());
            a.setLeft(temp);
            a.setRight(c);
        // If right node has a left node (and no right)...
        } else if ((c = b.getLeft()) != null) {
            a.setValue(c.getValue());
            a.setLeft(temp);
            b.setLeft(null);
        }
    }

    private void rotateRight(Node<T> a, Node<T> temp, Node<T> b) {
        Node<T> c, d;
        // If left node has a left node...
        if ((c = b.getLeft()) != null) {
            // If left node has a right node...
            if ((d = b.getRight()) != null) {
                a.setValue(d.getValue());
                a.setRight(temp);
                b.setRight(null);
            }
            a.setValue(b.getValue());
            a.setLeft(c);
            a.setRight(temp);
        // If left node has a right node (and no left)...
        } else if ((c = b.getRight()) != null) {
            a.setValue(c.getValue());
            a.setRight(temp);
        }
    }

    private Node<T> getParent(T value) {

        if (root.getLeft().getValue().compareTo(value) == 0 || root.getRight().getValue().compareTo(value) == 0) return root;
        else return getParentRecursively(root, value);
    }

    private Node<T> getParentRecursively(Node node, T value) {

        Node<T> left = node.getLeft();
        Node<T> right = node.getRight();

        if (left == null) {
            if (right.getValue().compareTo(value) == 0)
                return node;
        } else if (right == null) {
            if (left.getValue().compareTo(value) == 0)
                return node;
        } else if (left.getValue().compareTo(value) == 0 || right.getValue().compareTo(value) == 0)
            return node;
        else if (node.getValue().compareTo(value) > 0)
            return getParentRecursively(left, value);
        return getParentRecursively(right, value);
    }

    private int getBalance(Node<T> node) {

        if (node == null) return 0;
        return getHeight(node.getLeft()) - getHeight(node.getRight());
    }

    private int getHeight(Node<T> node) {

        int heightLeft = 0;
        int heightRight = 0;
        if (node == null || node.getLeft() == null && node.getRight() == null) return 0;
        if (node.getLeft() != null) heightLeft = getHeight(node.getLeft()) + 1;
        if (node.getRight() != null) heightRight = getHeight(node.getRight()) + 1;
        return Math.max(heightLeft, heightRight);
    }

    // A debugging aid
    boolean isBalanced() {

        int heightLeft = getHeight(root.getLeft());
        int heightRight = getHeight(root.getRight());
        return Math.abs(heightLeft - heightRight) <= 1 ? true : false;
    }
}
