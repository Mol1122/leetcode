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
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    // TreeNode lastNode = null;
    // boolean isValid = true;
    // public boolean isValidBST(TreeNode root) {
    //     //traverse
    //     inorder(root);
    //     return isValid;
    // }
    
    // private void inorder(TreeNode root) {
    //     if (root == null) {
    //         return;
    //     }
    //     inorder(root.left);
    //     if (isValid == false) {
    //         return;
    //     }
    //     if (lastNode != null && lastNode.val >= root.val) {
    //         isValid = false;
    //         return;
    //     }
    //     lastNode = root;
    //     inorder(root.right);
    // }
    
    //divide conquer
    public boolean isValidBST(TreeNode root) {
        return divideConquer(root).isValid;
    }
    
    private ResultType divideConquer(TreeNode root) {
        if (root == null) {
            return new ResultType(true);
        }
        ResultType left = divideConquer(root.left);
        ResultType right = divideConquer(root.right);
        
        if (!left.isValid || !right.isValid) {
            return new ResultType(false);
        }
        if (left.maxVal != null && left.maxVal.val >= root.val) {
            return new ResultType(false);
        }
        if (right.minVal != null && right.minVal.val <= root.val) {
            return new ResultType(false);
        }
        ResultType rt = new ResultType(true);
        rt.minVal = left.minVal != null ? left.minVal : root;
        rt.maxVal = right.maxVal != null ? right.maxVal : root;
        
        return rt;
    }
}

class ResultType {
    boolean isValid;
    TreeNode minVal, maxVal;
    
    public ResultType(boolean isValid) {
        this.isValid = isValid;
        minVal = null;
        maxVal = null;
    }
}

/* 算法：这种题，一定是拿到左边最大的和右边最小的，判断是否是valid.那么没有办法同时return两个值，那就可以想到用ResultType class */
