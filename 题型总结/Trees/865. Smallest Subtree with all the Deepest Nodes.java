/**
Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.

A node is deepest if it has the largest depth possible among any node in the entire tree.

The subtree of a node is that node, plus the set of all descendants of that node.

Return the node with the largest depth such that it contains all the deepest nodes in its subtree.


 */
class Solution {
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        ResultType rt = helper(root);
        return rt.node;
    }
    
    private ResultType helper(TreeNode root) {
        if (root == null) {
            return new ResultType(null, 0);
        }
        ResultType left = helper(root.left);
        ResultType right = helper(root.right);
        
        if (left.depth > right.depth) {
            return new ResultType(left.node, left.depth + 1);
        } else if (left.depth < right.depth) {
            return new ResultType(right.node, right.depth + 1);
        }
        return new ResultType(root, left.depth + 1);
    }
}

class ResultType {
    TreeNode node;
    int depth;
    
    public ResultType(TreeNode node, int depth) {
        this.node = node;
        this.depth = depth;
    }
}
//time: O(n), space: O(n)