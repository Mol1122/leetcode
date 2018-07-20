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
    public int maxPathSum(TreeNode root) {
        ResultType rt = helper(root);
        return rt.maxPath;
    }
    
    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, Integer.MIN_VALUE);
        }
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        
        int singlePath = Math.max(left.singlePath, right.singlePath) + root.val;
        singlePath = Math.max(singlePath, 0);
        
        int maxPath = Math.max(left.maxPath, right.maxPath);
        maxPath = Math.max(maxPath, left.singlePath + right.singlePath + root.val);
        
        return new ResultType(singlePath, maxPath);
    }
}

class ResultType {
    //singlePath:从当前节点往下走到任意点的最大路径
    //maxPath:从任意点到任意点的最大路径
    int singlePath, maxPath;
    public ResultType(int singlePath, int maxPath) {
        this.singlePath = singlePath;
        this.maxPath = maxPath;
    }
}

/* 算法：二叉树上的dfs路径问题。divide conquer + traverse. 利用ResultType记录左singlePath和右singlePath来计算
** 时间复杂度：O(n) */