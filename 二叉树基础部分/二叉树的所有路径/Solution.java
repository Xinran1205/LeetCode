import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

// 这里使用双端队列模拟栈
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        Deque<String> path = new LinkedList<>();
        List<String> result = new LinkedList<>();

        if (root == null) {
            return result;
        }

        recursiveTrack(root, path, result);

        return result;
    }

    public void recursiveTrack(TreeNode node, Deque<String> path, List<String> result) {
        if (node == null) {
            return;
        }

        // 添加当前节点的值到路径中
        if (path.isEmpty()) {
            // offer和addLast是一样的
            // 这里是一个双端队列，不能用push，push是往他的头放元素，pop也是从头拿元素
            path.addLast(Integer.toString(node.val));
        } else {
            path.addLast("->");
            path.addLast(Integer.toString(node.val));
        }

        // 如果是叶子节点，添加路径到结果中
        if (node.left == null && node.right == null) {
            result.add(String.join("", path));
        } else {
            // 递归遍历左子树和右子树
            recursiveTrack(node.left, path, result);
            recursiveTrack(node.right, path, result);
        }

        // 回溯：移除当前节点的值和箭头
        path.removeLast(); // 移除节点值
        if (!path.isEmpty() && path.peekLast().equals("->")) {
            path.removeLast(); // 移除箭头
        }
    }
}

//也可以直接使用栈
class Solution2 {
    public List<String> binaryTreePaths(TreeNode root) {
        Stack<String> path = new Stack<>();
        List<String> result = new LinkedList<>();

        if (root == null) {
            return result;
        }

        recursiveTrack(root, path, result);

        return result;
    }

    public void recursiveTrack(TreeNode node, Stack<String> path, List<String> result) {
        if (node == null) {
            return;
        }

        // 添加当前节点的值到路径中
        if (path.isEmpty()) {
            path.push(Integer.toString(node.val));
        } else {
            path.push("->");
            path.push(Integer.toString(node.val));
        }

        // 如果是叶子节点，添加路径到结果中
        if (node.left == null && node.right == null) {
            result.add(String.join("", path));
        } else {
            // 递归遍历左子树和右子树
            recursiveTrack(node.left, path, result);
            recursiveTrack(node.right, path, result);
        }

        // 回溯：移除当前节点的值和箭头
        path.pop(); // 移除节点值
        if (!path.isEmpty() && path.peek().equals("->")) {
            path.pop(); // 移除箭头
        }
    }
}

