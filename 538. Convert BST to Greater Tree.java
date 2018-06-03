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
    int sum = 0;
    
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.right = convertBST(root.right);
        sum += root.val;
        root.val = sum;
        root.left = convertBST(root.left);
        
        return root;
    }
    
}

/* BST里的dfs, 可以合在一起写，也可以分开写，例如：
** int sum = 0;

    void dfs(TreeNode cur) {
        if (cur == null) {
            return;
        }
        dfs(cur.right);
        sum += cur.val;
        cur.val = sum;
        dfs(cur.left);
    }

    public TreeNode convertBST(TreeNode root) {
        // Write your code here
        dfs(root);
        return root;
    }
    
    */