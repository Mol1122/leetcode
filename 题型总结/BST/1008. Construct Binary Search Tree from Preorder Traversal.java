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
    int index = 0;
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return helper(preorder, Integer.MAX_VALUE);
    }
    
    private TreeNode helper(int[] preorder, int bound) {
        if (index == preorder.length || preorder[index] > bound) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[index++]);
        root.left = helper(preorder, root.val);
        root.right = helper(preorder, bound);
        return root;
    }
}

/* 时间复杂度：O(n)
** 空间复杂度：O(n) */