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
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        if (root.left != null) {
            TreeNode left = root.left;
            if (left.left == null && left.right == null) {
                sum += left.val;
            } else {
                sum += sumOfLeftLeaves(left);
            }
        }
        
        if (root.right != null) {
            TreeNode right = root.right;
            sum += sumOfLeftLeaves(right);
        }
        return sum;
    }
}

/* 时间复杂度：O(n) */