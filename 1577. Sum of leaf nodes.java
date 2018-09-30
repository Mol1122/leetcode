/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param root: 
     * @return: the sum of leafnode
     */
    int sum = 0;
    public int sumLeafNode(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return sum;
    }
    
    private void dfs(TreeNode root) {
        if (root.left == null && root.right == null) {
            sum += root.val;
        }
        if (root.left != null) {
            dfs(root.left);
        }
        if (root.right != null) {
            dfs(root.right);
        }
    }
}