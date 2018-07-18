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
    TreeNode lastNode = null;
    public void flatten(TreeNode root) {
        //traverse, pre-order
        // if (root == null) {
        //     return;
        // }
        // //更改当前的node
        // if (lastNode != null) {
        //     lastNode.left = null;
        //     lastNode.right = root;
        // }
        // //前序遍历
        // lastNode = root;
        // TreeNode right = root.right; //难点
        // flatten(root.left);
        // flatten(right);
        
        //divided conquer, 总是后序遍历的思想
        helper(root);
    }
    
    //last node in the pre-order
    private TreeNode helper(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode leftLast = helper(root.left);
        TreeNode rightLast = helper(root.right);
        
        if (leftLast != null) {
            leftLast.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        if (rightLast != null) {
            return rightLast;
        }
        if (leftLast != null) {
            return leftLast;
        }
        return root;
    }
}