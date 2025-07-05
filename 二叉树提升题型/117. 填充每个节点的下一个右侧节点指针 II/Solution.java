import java.util.*;
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

// 这道题其实就是层序遍历，记录前一个节点即可，如果不是第一个节点，就将前一个节点的next指向当前节点

class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new ArrayDeque<Node>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            Node preNode = null;
            for (int i = 1; i <= n; ++i) {
                Node curNode = queue.poll();
                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
                if (i != 1) {
                    preNode.next = curNode;
                }
                preNode = curNode;
            }
        }
        return root;
    }
}

// 优化策略,空间复杂度为O(1)
class Solution2 {
    Node curNode = null;
    Node nextLineStart = null;

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Node start = root;
        // 遍历每一次
        while (start != null) {
            curNode = null;
            nextLineStart = null;
            //遍历这一层的同时，build下一层的next
            for (Node p = start; p != null; p = p.next) {
                if (p.left != null) {
                    handle(p.left);
                }
                if (p.right != null) {
                    handle(p.right);
                }
            }
            start = nextLineStart;
        }
        return root;
    }

    public void handle(Node p) {
        if (curNode != null) {
            curNode.next = p;
        }
        if (nextLineStart == null) {
            nextLineStart = p;
        }
        curNode = p;
    }
}