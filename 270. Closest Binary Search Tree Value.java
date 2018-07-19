class Solution {
    public int closestValue(TreeNode root, double target) {
        if (root == null) {
            return -1; 
        }
        TreeNode lowerBound = getLowerbound(root, target);
        TreeNode upperBound = getUpperbound(root, target);
        
        if (lowerBound == null) {
            return upperBound.val;
        }
        if (upperBound == null) {
            return lowerBound.val;
        }
        return Math.abs(upperBound.val - target) < Math.abs(lowerBound.val - target) ? upperBound.val : lowerBound.val;
    }
    
    private TreeNode getLowerbound(TreeNode root, double target) {
        if (root == null) {
            return null;
        }
        if (target < root.val) {
            return getLowerbound(root.left, target);
        }
        TreeNode right = getLowerbound(root.right, target);
        if (right != null) {
            return right;
        }
        return root;
    }
    
    private TreeNode getUpperbound(TreeNode root, double target) {
        if (root == null) {
            return null;
        }
        if (target >= root.val) {
            return getUpperbound(root.right, target);
        }
        TreeNode left = getUpperbound(root.left, target);
        if (left != null) {
            return left;
        }
        return root;
    }
}

/* 算法：利用lowerbound, upperbound去找最接近的那个值
** 时间复杂度:O(h)
** 难点：时间复杂度的分析，因为并不需要遍历所有的点，所以当走完整个高度就一定会找到lowerbound/upperbound */