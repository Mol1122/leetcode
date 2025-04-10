/* Given a binary tree root, return the maximum sum of all keys of any sub-tree which is also a Binary Search Tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
 

Example 1:



Input: root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
Output: 20
Explanation: Maximum sum in a valid Binary search tree is obtained in root node with key equal to 3.
Example 2:



Input: root = [4,3,null,1,2]
Output: 2
Explanation: Maximum sum in a valid Binary search tree is obtained in a single root node with key equal to 2.
Example 3:

Input: root = [-4,-2,-5]
Output: 0
Explanation: All values are negatives. Return an empty BST. */


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int maxSumBST(TreeNode root) {
        int[] max = {Integer.MIN_VALUE};
        getSum(root, max);
        return Math.max(max[0], 0); //也可以不包含任何一个node
    }

    private int[] getSum(TreeNode root, int[] max) {
        if (root == null) {
            return new int[]{1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0}; //以root为根的最小值为MAX_VALUE, 这样parent进行比较的时候不会把null node包含进去
        }
        int[] left = getSum(root.left, max);
        int[] right = getSum(root.right, max);

        if (left[0] == 1 && right[0] == 1 && left[2] < root.val && right[1] > root.val) {
            max[0] = Math.max(max[0], left[3] + right[3] + root.val);
            return new int[]{1, Math.min(left[1], root.val), Math.max(right[2], root.val), left[3] + right[3] + root.val};
        }
        return new int[]{0, 0, 0, 0};
    }
}
//time: O(n), space: O(height)
/* 解题思路
方法：DFS
判断一棵树是否是二叉搜索树，需要满足以下条件：

左子树是一棵二叉搜索树
右子树是一棵二叉搜索树
左子树的最大值小于根节点的值
右子树的最小值大于根节点的值
因此，我们设计一个函数 dfs(root)，返回一个四元组 (bst, mi, mx, s)，其中：

bst 表示以 root 为根的树是否是一棵二叉搜索树，如果是二叉搜索树，则 bst = 1，否则 bst = 0
mi 表示以 root 为根的树的最小值
mx 表示以 root 为根的树的最大值
s 表示以 root 为根的树的所有节点值之和
逻辑如下：

如果 root 为空节点，则返回 (1, +∞, -∞, 0) 表示空树是一棵二叉搜索树，最小值和最大值分别为正无穷和负无穷，当前所有节点值之和为 0
递归计算 root 的左子树和右子树，分别得出 (lbst, lmin, lmax, lsum) 和 (rbst, rmin, rmax, rsum)，然后判断 root 节点是否满足二叉搜索树的条件
如果满足 lbst == 1 and rbst == 1，并且 lmax < root.val < rmin，则说明以 root 为根的树是一棵二叉搜索树，记录这棵树的所有节点值之和 s = lsum + rsum + root.val，并更新答案 res = max(res, s)，并且返回 (1, min(lmin, root.val), max(rmax, root.val), s)
否则，说明以 root 为根的树不是一棵二叉搜索树，返回 (0, 0, 0, 0) */