/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param root: the given BST
     * @param p: the given node
     * @return: the in-order predecessor of the given node in the BST
     */
    public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }
        if (p.val <= root.val) {
            return inorderPredecessor(root.left, p);
        }
        TreeNode right = inorderPredecessor(root.right, p);
        return right == null ? root : right;
    }
}

/* 算法：找中序的前一个数和后一个数，分治法更快更不容易出错
** 时间复杂度：O(h) */