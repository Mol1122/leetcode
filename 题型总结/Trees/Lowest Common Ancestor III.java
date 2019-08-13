/**
 Given two nodes in a binary tree, find their lowest common ancestor (the given two nodes are not guaranteed to be in the binary tree).

Return null If any of the nodes is not in the tree.

Assumptions

There is no parent pointer for the nodes in the binary tree

The given two nodes are not guaranteed to be in the binary tree

Examples

        5

      /   \

     9     12

   /  \      \

  2    3      14

The lowest common ancestor of 2 and 14 is 5

The lowest common ancestor of 2 and 9 is 9

The lowest common ancestor of 2 and 8 is null (8 is not in the tree) */

public class Solution {
  public TreeNode lowestCommonAncestor(TreeNode root,
      TreeNode one, TreeNode two) {
        TreeNode result = lca(root, one, two);
        if (result == one) {
            if (lca(one, two, two) == null) {
                return null;
            }
        } else if (result == two) {
            if (lca(two, one, one) == null) {
                return null;
            }
        }
        return result;
  }
  
  private TreeNode lca(TreeNode root, TreeNode one, TreeNode two) {
      if (root == null || root == one || root == two) {
          return root;
      }
      TreeNode left = lca(root.left, one, two);
      TreeNode right = lca(root.right, one, two);
    
      if (left != null && right != null) {
          return root;
      }
      return left == null ? right : left;
  }
}
//time: O(n), space: O(height)