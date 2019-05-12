/**
Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.
The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.
Return null if LCA does not exist.
 */


public class Solution {
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode a, TreeNode b) {
        TreeNode result = lca(root, a, b);
        if (result == a) {
            if (lca(a, b, b) == null) {
                return null;
            }
        } else if (result == b) {
            if (lca(b, a, a) == null) {
                return null;
            }
        }
        return result;
    }
    
    //assume both a and b are in the tree
    private TreeNode lca(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null || root == a || root == b) {
            return root;
        }
        TreeNode left = lca(root.left, a, b);
        TreeNode right = lca(root.right, a, b);
        
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }
}