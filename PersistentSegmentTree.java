class Node {
    int sum;
    Node left, right;

    Node(int sum) {
        this.sum = sum;
    }
}

public class PersistentSegmentTree {

    static int[] arr = {12, 7, 25, 18, 9, 14, 6, 30};

    static Node build(int start, int end) {

        if (start == end)
            return new Node(arr[start]);

        int mid = (start + end) / 2;

        Node node = new Node(0);

        node.left = build(start, mid);
        node.right = build(mid + 1, end);

        node.sum = node.left.sum + node.right.sum;

        return node;
    }

    static Node update(Node prev, int start,
                       int end, int idx, int val) {

        if (start == end)
            return new Node(prev.sum + val);

        int mid = (start + end) / 2;

        Node node = new Node(0);

        if (idx <= mid) {
            node.left = update(prev.left, start, mid, idx, val);
            node.right = prev.right;
        } else {
            node.left = prev.left;
            node.right = update(prev.right, mid + 1, end, idx, val);
        }

        node.sum = node.left.sum + node.right.sum;

        return node;
    }

    static void print(Node node, int start, int end) {

        if (node == null)
            return;

        System.out.println("Range [" + (start + 1) + ".."
                + (end + 1) + "] = " + node.sum);

        if (start == end)
            return;

        int mid = (start + end) / 2;

        print(node.left, start, mid);
        print(node.right, mid + 1, end);
    }

    public static void main(String[] args) {

        Node v0 = build(0, 7);

        System.out.println("Version v0:");
        print(v0, 0, 7);

        Node v1 = update(v0, 0, 7, 2, 50);

        System.out.println("\nVersion v1 after stock[3] += 50:");
        print(v1, 0, 7);
    }
}