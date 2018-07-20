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
    boolean canGet = false;
    public boolean hasPathSum(TreeNode root, int sum) {
        helper(root, sum);
        return canGet;
    }
    
    private void helper(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        int temp = sum - root.val;
        if (root.left == null && root.right == null && temp == 0) {
            canGet = true;
            return;
        }
        helper(root.left, sum - root.val);
        helper(root.right, sum - root.val);
    }
}

/* 算法：二叉树上的DFS
** 时间复杂度: O(n) */