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
    final int NOT_BALANCE = -1;
    
    public boolean isBalanced(TreeNode root) {
        return maxDepth(root) != NOT_BALANCE;
    }
    
    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        if (left == NOT_BALANCE || right == NOT_BALANCE || Math.abs(left - right) > 1) {
            return NOT_BALANCE;
        }
        return Math.max(left, right) + 1;
    }
}