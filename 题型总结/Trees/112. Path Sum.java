/* Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.

A leaf is a node with no children.

 

Example 1:


Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
Output: true
Explanation: The root-to-leaf path with the target sum is shown.
Example 2:


Input: root = [1,2,3], targetSum = 5
Output: false
Explanation: There are two root-to-leaf paths in the tree:
(1 --> 2): The sum is 3.
(1 --> 3): The sum is 4.
There is no root-to-leaf path with sum = 5.
Example 3:

Input: root = [], targetSum = 0
Output: false
Explanation: Since the tree is empty, there are no root-to-leaf paths. */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

//Method 1
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        boolean[] flag = {false};
        getPath(root, 0, targetSum, flag);
        return flag[0];
    }
    
    private void getPath(TreeNode root, int sum, int targetSum, boolean[] flag) {
        if (root == null) {
            return;
        }
        sum += root.val;
        if (root.left == null && root.right == null) {
            if (sum == targetSum) {
                flag[0] = true;
                return;
            }
        }
        getPath(root.left, sum, targetSum, flag);
        getPath(root.right, sum, targetSum, flag);
    }
}

//Method 2
class Solution {
    boolean canGet = false;
    public boolean hasPathSum(TreeNode root, int sum) {
        helper(root, sum);
        return canGet;
    }
    
    private void helper(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        int temp = sum - root.val;
        if (root.left == null && root.right == null && temp == 0) {
            canGet = true;
            return;
        }
        helper(root.left, sum - root.val);
        helper(root.right, sum - root.val);
    }
}

/* 算法：二叉树上的DFS
** 时间复杂度: O(n) */