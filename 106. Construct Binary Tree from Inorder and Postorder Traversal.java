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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length != postorder.length) {
            return null;
        }
        return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }
    
    private TreeNode build(int[] inorder, int instart, int inend, 
                           int[] postorder, int poststart, int postend) {
        if (instart > inend) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postend]);
        int position = getPos(inorder, instart, inend, postorder[postend]);
        root.left = build(inorder, instart, position - 1, postorder, poststart, poststart + position - instart - 1);
        root.right = build(inorder, position + 1, inend, postorder, poststart + position - instart, postend - 1);
        
        return root;
    }
    
    private int getPos(int[] inorder, int start, int end, int key) {
        for (int i = start; i <= end; i++) {
            if (inorder[i] == key) {
                return i;
            }
        }
        return -1;
    }
}
/* 算法：二叉树上的遍历, 要对遍历方式非常熟悉 */