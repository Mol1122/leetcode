/**
Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6
Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42 */

class Solution {
    public int maxPathSum(TreeNode root) {
        int[] max = {Integer.MIN_VALUE};
        getPathSum(root, max);
        return max[0];
    }
    
    private int getPathSum(TreeNode root, int[] max) {
        if (root == null) {
            return 0;
        }
        int left = getPathSum(root.left, max);
        int right = getPathSum(root.right, max);
        
        max[0] = Math.max(max[0], Math.max(left, right) + root.val);
        max[0] = Math.max(max[0], left + right + root.val);
        
        return Math.max(0, Math.max(left, right) + root.val);
    }
}
//time: O(n), space: O(height)