import java.util.ArrayList;
import java.util.List;


//一、什么是 B 树（B-Tree）
//在了解代码之前，我们先来简要介绍一下 B 树：
//
//B 树是一种平衡多路查找树（Balanced M-ary Search Tree）。
//每个节点可以包含多个关键字（key），并且有多个子节点（children）。
//B 树常用于文件系统或数据库索引等需要在磁盘上进行大量读写的场合，可以减少磁盘 I/O 操作。
//它有一个重要参数——最小度数（Minimum Degree，记为 t），它决定了 B 树节点能包含的最少和最多关键字数、子节点数，以及插入、删除操作的逻辑。
//B 树的部分性质（以最小度数 t 为例）
//每个节点最多可以有 2t - 1 个关键字（keys），最少可以有 t-1 个关键字（除了根节点可以例外）。
//对非根节点来说：
//子节点数最少为 t、最多为 2t。
//关键字数比子节点数少 1。
//根节点特殊情况：如果根节点不是叶子，最少可以只有 2 个子节点；如果是叶子，直接可以为空或者有少量 key。
//当一个节点中关键字数达到最大值（2t - 1）以后，再往这个节点插入就会触发节点分裂（split）操作。

// B树节点类
class BTreeNode {
    int t;  // 最小度数
    List<Integer> keys; // 存储关键字
    List<BTreeNode> children; // 存储子节点
    boolean isLeaf; // 是否为叶子节点

    public BTreeNode(int t, boolean isLeaf) {
        this.t = t;
        this.isLeaf = isLeaf;
        this.keys = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    // 插入非满节点
    public void insertNonFull(int key) {
        int i = keys.size() - 1;

        if (isLeaf) {
            // 找到插入位置
            while (i >= 0 && key < keys.get(i)) {
                i--;
            }
            keys.add(i + 1, key);
        } else {
            // 找到要插入的子节点
            while (i >= 0 && key < keys.get(i)) {
                i--;
            }
            i++;
            BTreeNode child = children.get(i);
            if (child.keys.size() == 2 * t - 1) {
                splitChild(i, child);
                if (key > keys.get(i)) {
                    i++;
                }
            }
            children.get(i).insertNonFull(key);
        }
    }

    // 分裂子节点
    public void splitChild(int i, BTreeNode y) {
        BTreeNode z = new BTreeNode(y.t, y.isLeaf);
        // 将y的后t-1个关键字移到z
        for (int j = 0; j < t - 1; j++) {
            z.keys.add(y.keys.remove(t));
        }
        if (!y.isLeaf) {
            for (int j = 0; j < t; j++) {
                z.children.add(y.children.remove(t));
            }
        }
        children.add(i + 1, z);
        keys.add(i, y.keys.remove(t - 1));
    }

    // 中序遍历
    public void traverse() {
        int i;
        for (i = 0; i < keys.size(); i++) {
            if (!isLeaf) {
                children.get(i).traverse();
            }
            System.out.print(keys.get(i) + " ");
        }
        if (!isLeaf) {
            children.get(i).traverse();
        }
    }

    // 搜索关键字
    public BTreeNode search(int key) {
        int i = 0;
        while (i < keys.size() && key > keys.get(i)) {
            i++;
        }
        if (i < keys.size() && keys.get(i) == key) {
            return this;
        }
        if (isLeaf) {
            return null;
        }
        return children.get(i).search(key);
    }
}

// B树类
class BTree {
    BTreeNode root;
    int t; // 最小度数

    public BTree(int t) {
        this.root = null;
        this.t = t;
    }

    // 插入关键字
    public void insert(int key) {
        if (root == null) {
            root = new BTreeNode(t, true);
            root.keys.add(key);
        } else {
            if (root.keys.size() == 2 * t - 1) {
                BTreeNode s = new BTreeNode(t, false);
                s.children.add(root);
                s.splitChild(0, root);
                int i = 0;
                if (s.keys.get(0) < key) {
                    i++;
                }
                s.children.get(i).insertNonFull(key);
                root = s;
            } else {
                root.insertNonFull(key);
            }
        }
    }

    // 遍历B树
    public void traverse() {
        if (root != null) {
            root.traverse();
        }
    }

    // 搜索关键字
    public boolean search(int key) {
        return root != null && root.search(key) != null;
    }
}

public class Solution {
    public static void main(String[] args) {
        // 创建最小度数为3的B树
        BTree btree = new BTree(3);

        // 插入一系列关键字
        int[] keysToInsert = {10, 20, 5, 6, 12, 30, 7, 17};
        for (int key : keysToInsert) {
            btree.insert(key);
            System.out.println("插入 " + key + " 后的B树遍历结果:");
            btree.traverse();
            System.out.println("\n---------------------------");
        }

        // 测试搜索功能
        int[] keysToSearch = {6, 15, 20, 7, 25};
        for (int key : keysToSearch) {
            boolean found = btree.search(key);
            System.out.println("搜索关键字 " + key + ": " + (found ? "找到" : "未找到"));
        }
    }
}
