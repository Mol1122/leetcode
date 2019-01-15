//687. Longest Univalue Path
class Solution {
    int max = 0;
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        helper(root, root.val);
        return max;
    }
    
    private int helper(TreeNode root, int target) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left, root.val);
        int right = helper(root.right, root.val);
        
        max = Math.max(max, left + right); //算edge的时候通常都不用管root
        if (root.val == target) {
            return Math.max(left, right) + 1;
        }
        return 0;
    }
}

/* 分治法
** 时间复杂度：O(n) */

//543. Diameter of Binary Tree
class Solution {
    int max = 0;
    
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
        
        return Math.max(left, right) + 1;
    }
}