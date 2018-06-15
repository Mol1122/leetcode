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
    int max = 0;
    
    public int diameterOfBinaryTree(TreeNode root) {
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
        
        return Math.max(left, right) + 1; //易错点，找一条长度最长的路径
    }
}

/* 算法：基础dfs, 用一个class变量去保存max，其他的就是分配工作的思想
** 时间复杂度：O(n) */