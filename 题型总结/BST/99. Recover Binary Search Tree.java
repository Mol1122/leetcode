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
    TreeNode firstNode = null;
    TreeNode secondNode = null;
    TreeNode preNode = new TreeNode(Integer.MIN_VALUE);
    
    public void recoverTree(TreeNode root) {
        if (root == null) {
            return;
        }    
        traverse(root);
        if (firstNode != null) {
            int val = firstNode.val;
            firstNode.val = secondNode.val;
            secondNode.val = val;
        }
    }
    
    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        traverse(root.left);
        if (firstNode == null && preNode.val >= root.val) {
            firstNode = preNode;
        }
        if (firstNode != null && preNode.val >= root.val) {
            secondNode = root;
        }
        preNode = root;
        traverse(root.right);
    }
}

/* 时间复杂度：O(n)
** 空间复杂度：O(n) */