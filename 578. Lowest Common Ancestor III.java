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
    /*
     * @param root: The root of the binary tree.
     * @param A: A TreeNode
     * @param B: A TreeNode
     * @return: Return the LCA of the two nodes.
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        ResultType rt = helper(root, A, B);
        if (rt.a_exist && rt.b_exist) {
            return rt.node;
        }
        return null;
    }
    
    private ResultType helper(TreeNode root, TreeNode A, TreeNode B) {
        if (root == null) {
            return new ResultType(false, false, null);
        }
        ResultType left_rt = helper(root.left, A, B);
        ResultType right_rt = helper(root.right, A, B);
        
        boolean a_exist = left_rt.a_exist || right_rt.a_exist || root == A;
        boolean b_exist = left_rt.b_exist || right_rt.b_exist || root == B;
        
        if (root == A || root == B) {
            return new ResultType(a_exist, b_exist, root);
        }
        
        if (left_rt.node != null && right_rt.node != null) { 
            return new ResultType(a_exist, b_exist, root);
        }
        if (left_rt.node != null) {
            return new ResultType(a_exist, b_exist, left_rt.node);
        }
        if (right_rt.node != null) {
            return new ResultType(a_exist, b_exist, right_rt.node);
        }
        return new ResultType(a_exist, b_exist, null);
    }
}

class ResultType {
    boolean a_exist, b_exist;
    TreeNode node;
    
    public ResultType(boolean a, boolean b, TreeNode node) {
        this.a_exist = a;
        this.b_exist = b;
        this.node = node;
    }
}

/* 算法：divide conquer.与之前一题的区别在于，只有两个都存在时才能返回LCA. 因此，可以想着用ResultType去记录是否存在A and b

** 难点：要把a_exist, b_exist单独拿出来判断. 要把root == A, root == B单独拿出来判断 */