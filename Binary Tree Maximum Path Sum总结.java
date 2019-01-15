/*  124. Binary Tree Maximum Path Sum https://leetcode.com/problems/binary-tree-maximum-path-sum/
Given a non-empty binary tree, find the maximum path sum.
For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6 */

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
        
        int singlePath = Math.max(left.singlePath + root.val, right.singlePath + root.val);
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

/* 475. Binary Tree Maximum Path Sum II
Given a binary tree, find the maximum path sum from root.

The path may end at any node in the tree and contain at least one node in it.

Example
Given the below binary tree:

  1
 / \
2   3
return 4. (1->3) */
public class Solution {
    /**
     * @param root: the root of binary tree.
     * @return: An integer
     */
    public int maxPathSum2(TreeNode root) {
       if (root == null) {
           return Integer.MIN_VALUE;
       }
       int left = maxPathSum2(root.left);
       int right = maxPathSum2(root.right);
       return root.val + Math.max(0, Math.max(left, right));
    }
}