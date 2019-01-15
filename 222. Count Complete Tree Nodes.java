/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getLeftSubtreeHeight(root);
        int right = getRightSubtreeHeight(root);
        
        if (left == right) {
            int res = (int)(Math.pow(2, left)) - 1;
            return res;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
    
    private int getLeftSubtreeHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int height = 0;
        while (node != null) {
            height++;
            node = node.left;
        }
        return height;
    }
    
    private int getRightSubtreeHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int height = 0;
        while (node != null) {
            height++;
            node = node.right;
        }
        return height;
    }
}

/* 分治法.因为是complete, 可以利用complete的特性，left tree height == right tree height, 那么#node = 2^h - 1. 否则就要分治递归去求
** 时间复杂度：O(n)*/