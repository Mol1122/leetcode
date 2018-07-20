/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//Path Sum III
class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return dfs(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    private int dfs(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return (root.val == sum ? 1: 0) + dfs(root.left, sum - root.val) + dfs(root.right, sum - root.val);
    }
}

/* 算法：典型的recursion
** 难点：如果实现不一定从root开始， 并且leftPath和rightPath不能舍弃一个 
** 时间复杂度:O(n^2) */