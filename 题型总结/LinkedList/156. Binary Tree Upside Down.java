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
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        return dfs(root);
    }
    
    private TreeNode dfs(TreeNode curr) {
        if (curr.left == null) {
            return curr;
        }
        TreeNode newNode = dfs(curr.left);
        curr.left.right = curr;
        curr.left.left = curr.right;
        curr.left = null;
        curr.right = null;
        return newNode;
    }
}

/* time:O(n), space:O(n) */