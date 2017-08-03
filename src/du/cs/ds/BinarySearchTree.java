package du.cs.ds;

/**
 * Binary Search Tree implementations
 * where most of its operations have
 * a time complexity of O(log n) except
 * for the worst cases where the tree
 * behaves as a list O(n)
 * @author daviduvalle
 *
 * @param <T> any type
 */
public class BinarySearchTree<T extends Comparable<T>> {

    public static class Node<T extends  Comparable<T>>{
        private Node<T> left;
        private Node<T> right;
        private Node<T> parent;
        private T data;

        public Node(T data) {
            this.data = data;
        }

        public T getData() {
            return this.data;
        }
    }

    private Node<T> root;

    public BinarySearchTree() { }

    /**
     * Returns the tree root node
     * @return tree root node
     */
    public Node<T> getRoot() {
        return this.root;
    }

    /**
     * Inserts a new node into the tree
     * @param data contained into the new node
     */
    public void insert(T data) {
        Node<T> node = new Node<>(data);

        Node<T> tmpParent = null;
        Node<T> tmpRoot = root;

        // Find the parent node
        while (tmpRoot != null) {
            tmpParent = tmpRoot;
            if (node.data.compareTo(tmpRoot.data) < 0) {
                tmpRoot = tmpRoot.left;
            } else {
                tmpRoot = tmpRoot.right;
            }
        }
        // Set the parent node
        node.parent = tmpParent;

        // The tree is empty
        if (tmpParent == null) {
            this.root = node;
        } else if (node.data.compareTo(tmpParent.data) < 0) {
            tmpParent.left = node;
        } else {
            tmpParent.right = node;
        }
    }

    /**
     * Deletes a specified node from the tree
     * @param node a node
     */
    public void delete(Node<T> node) {
        if (node.left == null) {
            transplant(node, node.right);
        } else if (node.right == null) {
            transplant(node, node.left);
        } else {
            Node<T> y = treeMininum(node.right);
            if (y.parent != node) {
                transplant(y, y.right);
                y.right = node.right;
                y.right.parent = y;
            }

            transplant(node, y);
            y.left = node.left;
            y.left.parent = y;

        }
    }

    /**
     * Replaces one subtree with another subtree
     * @param a a subtree
     * @param b b subtree
     */
    private void transplant(Node<T> a, Node<T> b) {
        if (a.parent == null) {
            this.root = a;
        } else if (a.data.compareTo(a.parent.left.data) == 0) {
            a.parent.left = b;
        } else {
            a.parent.right = b;
        }

        if (b != null) {
            b.parent = a.parent;
        }
    }

    /**
     * Returns a dynamic array containing tree elements in order
     * starting from the subtree root passed as argument
     * @param root subtree root
     * @return a dynamic array containing tree elements in order
     */
    public DynamicArray<T> inorder(Node<T> root) {

        DynamicArray<T> dynamicArray = new DynamicArray<>();
        inorder(dynamicArray, root);
        return dynamicArray;
    }

    private void inorder(DynamicArray<T> darray, Node<T> root) {
        if (root != null) {
            inorder(darray, root.left);
            darray.add(root.data);
            inorder(darray, root.right);

        }
    }

    /**
     * Search for a key and return a node reference if it
     * exits, otherwise returns null.
     * @param key key to search
     * @return a node reference if exists, null otherwise
     */
    public Node<T> search(T key) {
        return recursiveSearch(this.root, key);
    }

    private Node<T> recursiveSearch(Node<T> root, T key) {
        if (root == null || key.compareTo(root.data) == 0) {
            return root;
        }

        if (key.compareTo(root.data) < 0) {
            return recursiveSearch(root.left, key);
        } else {
            return recursiveSearch(root.right, key);
        }
    }

    /**
     * Determines the minimum node of a subtree rooted at
     * the provided node
     * @param root subtree root
     * @return minimum node of a subtree
     */
    public Node<T> treeMininum(Node<T> root) {

        while (root.left != null) {
            root = root.left;
        }

        return root;
    }

    /**
     * Determines the maximun node of a subtree rooted at
     * the provided node
     * @param root subtree root
     * @return maximun node of a subtree
     */
    public Node<T> treeMaximun(Node<T> root) {

        while (root.right != null) {
            root = root.right;
        }

        return root;
    }

    /**
     * Returns the successor of a given node or
     * null if its the largest node in the tree
     * @param node selected node
     * @return successor
     */
    public Node<T> successor(Node<T> node) {

        if (node.right != null) {
            return treeMininum(node.right);
        }

        Node<T> successor = node.parent;

        while (successor != null &&
                node.data.compareTo(successor.right.data) == 0) {
            node = successor;
            successor = successor.parent;
        }

        return successor;
    }

    /**
     * Returns the predecessor of a given node or
     * null if its the smallest node in the tree
     * @param node selected node
     * @return predecessor
     */
    public Node<T> predecessor(Node<T> node) {

        if (node.left != null) {
            return treeMaximun(node.left);
        }

        Node<T> predecessor = node.parent;

        while (predecessor != null &&
                node.data.compareTo(predecessor.left.data) == 0) {
            node = predecessor;
            predecessor = predecessor.parent;
        }

        return predecessor;
    }
}
