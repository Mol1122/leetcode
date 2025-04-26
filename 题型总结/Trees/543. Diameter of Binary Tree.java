/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//Method 1
class Solution {
    int max = -1; //用全局变量去保存max
    
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return max;
    }
    
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        max = Math.max(max, left + right);
        
        return Math.max(left, right) + 1; //返回最长的一条路径
    }
}

//Method 2
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        int[] max = {0};
        getPath(root, max);
        return max[0];
    }

    private int getPath(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 0;
        }
        int left = getPath(root.left, max);
        int right = getPath(root.right, max);

        if (root.left != null && root.right != null) {
            max[0] = Math.max(max[0], left + right + 2);
        }
        if (root.left != null) {
            max[0] = Math.max(max[0], left + 1);
        }
        if (root.right != null) {
            max[0] = Math.max(max[0], right + 1);
        }
        return Math.max(left, right) + 1;
    }
}