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
     * @param root: the root of binary tree
     * @return: the root of the maximum average of subtree
     */
    TreeNode subtree = null;
    ResultType subtreeResult = null;
    public TreeNode findSubtree2(TreeNode root) {
        helper(root);
        return subtree;
    }
    
    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(0, 0);
        }
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        
        ResultType node = new ResultType(left.sum + right.sum + root.val, left.size + right.size + 1);
        if (subtree == null ||
            subtreeResult.sum * node.size < node.sum * subtreeResult.size) {
                subtree = root;
                subtreeResult = node;
            }
        return node;
    }
}

class ResultType {
    int sum, size;
    
    public ResultType (int sum, int size) {
        this.sum = sum;
        this.size = size;
    }
}

/* 算法：找带有某种性质的subtree都是用divide conquer + traverse来做。利用两个全局变量，一个是需要return 的subtree, 
**       另一个是用来储存subtree的性质的。是否需要定义ResultType取决于我们需要什么值 */