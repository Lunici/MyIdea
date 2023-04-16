package com.lunici.BinarySearchTree;

public class BinarySearchTree<T> {
    private Node root;
    private int size = 0;

    /**
     * Find the value by key
     * @param key The key number of value that you want to find
     * @return Return the value if exists, if not return null
     */
    public T get(Integer key) {
        Node node = findNode(key);
        if (node == null){
            return null;
        }
        return node.getValue();
    }

    /**
     * Add a new node. If the key is already used, this operation will fail
     * @param key The key number of value that you want to add
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
            if (key == head.key) {
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
     * @param key The key number of value that you want to change
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
     * @param key The key number of node that you want to delete
     * @return Return true if the node has been deleted successfully, if not return false
     */
    public boolean del(Integer key) {
        Node node = findNode(key);

        // 1.
        if (!node.hasLeft() && !node.hasRight()){

        }
        // 2.

        // 3.

        // 4.
        return false;
    }

    private Node findNode(Integer key) {
        Node head = root;
        while (true) {
            if (head == null) {
                return null;
            }
            if (head.key == key) {
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
        private Node left;
        private Node right;
        private Integer key;
        private T value;
        private boolean empty = true;
        private Node parent;

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
            return (left == null) ? false : true;
        }

        public boolean hasRight() {
            return (right == null) ? false : true;
        }

        public boolean isEmpty() {
            return empty;
        }

        public void setEmpty(boolean empty) {
            this.empty = empty;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }
    }
}

