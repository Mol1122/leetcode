/**
Check if a given binary tree is balanced. A balanced binary tree is one in which the depths of every node’s left and right subtree differ by at most 1.

Examples

        5

      /    \

    3        8

  /   \        \

1      4        11

is balanced binary tree,

        5

      /

    3

  /   \

1      4

is not balanced binary tree. */

class Solution {
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1; 
    }
    
    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        
        if (left == -1 || right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }
}
//算法：利用“三部曲”思想
//time: O(n), space: O(height)