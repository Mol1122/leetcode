/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// Binary Tree Longest Consecutive Sequence
class Solution {
    int max = 0;
    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root);
        return max;
    }
    
    private int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left);
        int right = helper(root.right);
        
        int subtreeLongest = 1; //at least we have root
        if (root.left != null && root.left.val == root.val + 1) {
            subtreeLongest = Math.max(subtreeLongest, left + 1);
        }
        
        if (root.right != null && root.right.val == root.val + 1) {
            subtreeLongest = Math.max(subtreeLongest, right + 1);
        }
        if (subtreeLongest > max) {
            max = subtreeLongest;
        }
        return subtreeLongest;
    }
}

/* 算法：divided conquer + traverse. 只要是二叉树求路径的题，都是这个模版
** 时间复杂度：O(n) */