/**
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:

Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
Example:

Input: root = [4,2,5,1,3], target = 3.714286

    4
   / \
  2   5
 / \
1   3

Output: 4 */

class Solution {
    public int closestValue(TreeNode root, double target) {
        if (root == null) {
            return -1;
        }
        TreeNode lowerbound = getLowerBound(root, target);
        TreeNode upperbound = getUpperBound(root, target);
        
        if (lowerbound == null) {
            return upperbound.val;
        } 
        if (upperbound == null) {
            return lowerbound.val;
        }
        return Math.abs(lowerbound.val - target) < Math.abs(upperbound.val - target) ? lowerbound.val : upperbound.val;
    }
    
    private TreeNode getLowerBound(TreeNode root, double target) {
        if (root == null) {
            return null;
        }
        if (target < root.val) {
            return getLowerBound(root.left, target);
        }
        TreeNode right = getLowerBound(root.right, target);
        if (right != null) {
            return right;
        }
        return root;
    }
    
    private TreeNode getUpperBound(TreeNode root, double target) {
        if (root == null) {
            return null;
        }
        if (target > root.val) {
            return getUpperBound(root.right, target);
        }
        TreeNode left = getUpperBound(root.left, target);
        if (left != null) {
            return left;
        }
        return root;
    } 
}

/* 算法：lowerBound, upperBound，时间复杂度比in-order traversal要好
** 时间复杂度：O(h)
** 空间复杂度：O(h) */