import java.util.ArrayList;

class Node2 {
    int val;
    Node2 left, right, parent;
    boolean color; // true = Black, false = Red

    public Node2(int val) {
        this.val = val;
        this.color = false;
    }
}

public class BRTree_Q6 {
    private static final boolean BLACK = true;
    private static final boolean RED = false;
    public Node2 root;

    public BRTree_Q6() {
        this.root = null;
    }

    // --- Core Range Logic ---
    public void inRange(int a, int b) {
        ArrayList<Integer> valuesToDelete = new ArrayList<>();
        collectInRange(this.root, a, b, valuesToDelete);

        for (int i = 0; i < valuesToDelete.size(); i++) {
            int targetValue = valuesToDelete.get(i);
            Node2 targetNode = findNode(this.root, targetValue);
            if (targetNode != null) {
                prepareAndRemove(targetNode);
            }
        }
    }

    private void collectInRange(Node2 currentNode, int a, int b, ArrayList<Integer> list) {
        if (currentNode == null) return;
        collectInRange(currentNode.left, a, b, list);
        if (currentNode.val >= a && currentNode.val <= b) {
            list.add(currentNode.val);
        }
        collectInRange(currentNode.right, a, b, list);
    }

    //  BR Tree Rebalancing
    private void prepareAndRemove(Node2 node) {
        // Case 1
        if (node.color == RED || node.parent == null) {
            performBSTRemoval(node);
            if (root != null) root.color = BLACK; // Root must be black
            return;
        }

        Node2 parent = node.parent;
        Node2 sibling = (node == parent.left) ? parent.right : parent.left;

        // Case 2
        if (isRed(sibling)) {
            parent.color = RED;
            sibling.color = BLACK;
            if (node == parent.left) rotateLeft(parent);
            else rotateRight(parent);
            prepareAndRemove(node);
            return;
        }

        // Case 3 & 4
        if (isBlack(sibling.left) && isBlack(sibling.right)) {
            if (parent.color == BLACK) { // Case 3: Parent is black
                sibling.color = RED;
                prepareAndRemove(parent);
            } else { // Case 4: Parent is red
                parent.color = BLACK;
                sibling.color = RED;
                performBSTRemoval(node);
            }
            return;
        }

        // Case 5 & 6: Rotations based on sibling child color
        if (node == parent.left && isRed(sibling.left) && isBlack(sibling.right)) {
            sibling.color = RED;
            sibling.left.color = BLACK;
            rotateRight(sibling); // Case 5
            prepareAndRemove(node);
        } else if (node == parent.right && isRed(sibling.right) && isBlack(sibling.left)) {
            sibling.color = RED;
            sibling.right.color = BLACK;
            rotateLeft(sibling); // Case 5 Symmetric
            prepareAndRemove(node);
        } else {
            // Case 6: Final terminal rotation
            sibling.color = parent.color;
            parent.color = BLACK;
            if (node == parent.left) {
                if (sibling.right != null) sibling.right.color = BLACK;
                rotateLeft(parent);
            } else {
                if (sibling.left != null) sibling.left.color = BLACK;
                rotateRight(parent);
            }
            performBSTRemoval(node);
        }
    }

    private void performBSTRemoval(Node2 z) {
    }

    private void rotateLeft(Node2 x) {
        Node2 y = x.right;
        x.right = y.left;
        if (y.left != null) y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == null) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;
        y.left = x;
        x.parent = y;
    }

    private void rotateRight(Node2 y) {
        Node2 x = y.left;
        y.left = x.right;
        if (x.right != null) x.right.parent = y;
        x.parent = y.parent;
        if (y.parent == null) root = x;
        else if (y == y.parent.right) y.parent.right = x;
        else y.parent.left = x;
        x.right = y;
        y.parent = x;
    }

    private boolean isRed(Node2 n) { return n != null && n.color == RED; }
    private boolean isBlack(Node2 n) { return n == null || n.color == BLACK; }

    private Node2 findNode(Node2 n, int val) {
        if (n == null || n.val == val) return n;
        return (val < n.val) ? findNode(n.left, val) : findNode(n.right, val);
    }

    public void insert(int val) {
    }

    public void printTree() {
        System.out.print("{ ");
        printInOrder(root);
        System.out.println("}");
    }

    private void printInOrder(Node2 n) {
        if (n != null) {
            printInOrder(n.left);
            System.out.print(n.val + " ");
            printInOrder(n.right);
        }
    }

    public static void main(String[] args) {
        BRTree_Q6 tree = new BRTree_Q6();
        int[] keys = {10, 19, 20, 30, 42, 55, 77}; // [cite: 134]
        for (int k : keys) tree.insert(k);

        System.out.print("Original Keys: ");
        tree.printTree();

        tree.inRange(15, 20);
        System.out.print("After inRange(15, 20): ");
        tree.printTree();
    }
}