/**
Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary 
tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

Example:
Given a binary tree
          1
         / \
        2   3
       / \     
      4   5    
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3]. */

class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        int[] max = {0};
        dfs(root, max);
        return max[0];
    }
    
    private int dfs(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left, max);
        int right = dfs(root.right, max);
        max[0] = Math.max(max[0], left + right);
        
        return Math.max(left, right) + 1;
    }
}

/* 算法：一棵树的周长就是对于每一个node找leftHeight + rightHeight的最大值
** 时间复杂度：O(n)
** 空间复杂度：O(n) */