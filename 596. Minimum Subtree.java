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
     * @param root: the root of binary tree
     * @return: the root of the minimum subtree
     */
    TreeNode subtree = null;
    int subtreeSum = Integer.MAX_VALUE;
    public TreeNode findSubtree(TreeNode root) {
        helper(root);
        return subtree;
    }
    
    private int helper(TreeNode curr) {
        if (curr == null) {
            return 0;
        }
        int sum = helper(curr.left) + helper(curr.right) + curr.val;
        if (sum <= subtreeSum) {
            subtreeSum = sum;
            subtree = curr;
        }
        return sum;
    }
}

/* 算法：traverse + divide conquer
** 时间复杂度：O(n) */