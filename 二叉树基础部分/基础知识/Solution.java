public class Solution {
}

//种类：
// 满二叉树，全满 2^k-1
//完全二叉树，除了底层，其他全满，并且底部从左到右依次的
//二叉搜索树，左边小于根，右边大于根（子树同理）
//平衡二叉搜索树，左右子树高度差不超过1


//存储方式：
//链式存储（链表）
//线性存储（数组）（左孩子：2i+1,右孩子：2i+2）


// 二叉树的遍历
// 深度优先搜索（DFS，一般用递归，也可以用迭代法（用栈模拟））
// 前序（中左右）、中序（左中右）、后序（左右中），记忆：前中后指的是中节点

// 广度优先搜索（BFS，迭代法（队列））层序遍历