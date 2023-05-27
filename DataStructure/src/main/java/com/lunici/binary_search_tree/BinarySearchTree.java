package com.lunici.binary_search_tree;

public class BinarySearchTree<T> {


    private Node root;
    private int size = 0;

    /**
     * Find the value by key
     *
     * @param key The key number of value that you want to find
     * @return Return the value if exists, if not return null
     */
    public T get(Integer key) {
        Node node = findNode(key);
        if (node == null) {
            return null;
        }
        return node.getValue();
    }

    /**
     * Add a new node. If the key is already used, this operation will fail
     *
     * @param key   The key number of value that you want to add
     * @param value The value that you want to add
     * @return Return true if the new node has been added successfully, if not return false
     */
    public boolean add(Integer key, T value) {
        //
        if (root == null) {
            root = new Node(key, value);
            size++;
            return true;
        }

        Node head = root;
        while (true) {
            if (key.equals(head.getKey())) {
                return false;
            }

            if (key < head.key) {
                if (head.hasLeft()) {
                    head = head.getLeft();
                } else {
                    head.setLeft(new Node(key, value));
                    size++;
                    return true;
                }
            } else {
                if (head.hasRight()) {
                    head = head.getRight();
                } else {
                    head.setRight(new Node(key, value));
                    size++;
                    return true;
                }
            }
        }
    }

    /**
     * Change a value. If the key is not existed, this operation will fail
     *
     * @param key   The key number of value that you want to change
     * @param value The new value
     * @return Return true if the value has been changed successfully, if not return false
     */
    public boolean set(Integer key, T value) {
        Node target = findNode(key);
        if (target != null) {
            target.setValue(value);
            return true;
        }
        return false;
    }

    /**
     * Delete a node. If the key is not existed, this operation will fail
     *
     * @param key The key number of node that you want to delete
     * @return Return true if the node has been deleted successfully, if not return false
     */
    public boolean del(Integer key) {
        NodeFamily nodeFamily = findNodeFamily(key);
        Node parent;
        Node son;
        int side;
        // (parent , son )   ---   not the first ndoe
        //      1. no left, no right   ---   parent = null
        //      2. only left
        //              Node.LEFT   ---   parent.left = son.left
        //              Node.RIGHT    ---   parent.right = son.left
        //      3. only right
        //              Node.LEFT   ---   parent.left = son.right
        //              Node.RIGHT   ---   parent.right = son.right
        //      4. right and left

        // (  null , son )   ---   the first node
        //      1. no left, no right   ---   root = null
        //      2. only left   ---   root = son.left
        //      3. only right   ---   root = son.right
        //      4. right and left   ---   root = root.right.min ??? NOT PERFECT SOLUTION

        // (parent , null)   ---   operation fails

        // (  null , null)   ---   operation fails

        // if there is not the target node, operation fails
        if (!nodeFamily.hasSon()) {
            return false;
        }

        // set the parent node
        parent = (nodeFamily.hasParent()) ? nodeFamily.getParent() : root;
        // set the son node
        son = nodeFamily.getSon();
        // set the side between parent and son
        side = nodeFamily.getSide();

        // 1. if the the son does not have leftNode either rightNode
        if (!son.hasLeft() && !son.hasRight()) {
            if (side == Node.LEFT) {
                parent.setLeft(null);
            } else if (side == Node.RIGHT) {
                parent.setRight(null);
            }
        }

        // 2. if the son only has the leftNode
        if (son.hasLeft() && !son.hasRight()) {

        }

        //
        return false;
    }

    /**
     * Find a node family with parent node and son node which the son has the key.
     * The return can be: (parent, son) , (null, son) , (parent, null) , (null, null)
     *
     * @param key the key of son node that you want to find
     * @return A NodeFamily(parentNode, sonNode)
     */
    private NodeFamily findNodeFamily(Integer key) {
        Node son = root;
        Node parent = null;
        int side = Node.NONE;
        while (true) {
            if (son == null) {
                return new NodeFamily(parent, son, side); // (null, null) || (Node, null)
            }
            if (key.equals(son.getKey())) {
                return new NodeFamily(parent, son, side);
            } else if (key < son.key) {
                parent = son;
                son = son.getLeft();
                side = Node.LEFT;
            } else {
                parent = son;
                son = son.getRight();
                side = Node.RIGHT;
            }
        }
    }

    private Node findNode(Integer key) {
        Node head = root;
        while (true) {
            if (head == null) {
                return null;
            }
            if (key.equals(head.getKey())) {
                return head;
            } else if (key < head.key) {
                head = head.getLeft();
            } else {
                head = head.getRight();
            }
        }
    }

    private Node findMin(Node root) {
        while (root.hasLeft()) {
            root = root.getLeft();
        }
        return root;
    }

    private Node findMax(Node root) {
        while (root.hasRight()) {
            root = root.getRight();
        }
        return root;
    }

    public void clear() {
        root = null;
    }

    public int size() {
        return this.size;
    }

    class Node {
        private static final int NONE = 0;
        private static final int LEFT = 1;
        private static final int RIGHT = 2;
        private Node left;
        private Node right;
        private Integer key;
        private T value;
        private boolean empty = true;

        public Node() {

        }

        public Node(Integer key, T value) {
            this.key = key;
            this.value = value;
            empty = false;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public boolean hasLeft() {
            return left != null;
        }

        public boolean hasRight() {
            return right != null;
        }

        public boolean isEmpty() {
            return empty;
        }

        public void setEmpty(boolean empty) {
            this.empty = empty;
        }

    }

    class NodeFamily {
        private Node parent;
        private Node son;

        private int side; // 1:left , 2:right , 0:none

        public NodeFamily() {
        }

        public NodeFamily(Node parent, Node son, int side) {
            this.parent = parent;
            this.son = son;
            this.side = side;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Node getSon() {
            return son;
        }

        public void setSon(Node son) {
            this.son = son;
        }

        public boolean hasParent() {
            return parent != null;
        }

        public boolean hasSon() {
            return son != null;
        }

        public int getSide() {
            return side;
        }

        public void setSide(int side) {
            this.side = side;
        }
    }
}