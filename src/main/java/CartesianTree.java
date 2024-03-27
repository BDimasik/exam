public class CartesianTree {
    static class Node {
        int key;
        int priority;
        Node left, right;

        Node(int key, int priority) {
            this.key = key;
            this.priority = priority;
        }
    }

    static Node root;

    static Node insert(Node root, int key, int priority) {
        if (root == null) return new Node(key, priority);

        if (key <= root.key) {
            root.left = insert(root.left, key, priority);
            if (root.left.priority > root.priority)
                root = rotateRight(root);
        } else {
            root.right = insert(root.right, key, priority);
            if (root.right.priority > root.priority)
                root = rotateLeft(root);
        }

        return root;
    }

    static Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }

    static Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        return x;
    }

    static void printTree(Node root) {
        if (root == null) return;
        printTree(root.left);
        System.out.print("(" + root.key + ", " + root.priority + ") ");
        printTree(root.right);
    }

    public static void main(String[] args) {
        int[] keys = {3, 1, 4, 5, 2};
        int[] priorities = {10, 5, 8, 12, 2};

        for (int i = 0; i < keys.length; i++) {
            root = insert(root, keys[i], priorities[i]);
        }

        printTree(root);
    }
}

