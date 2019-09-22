/**
Given two nodes in a binary tree, find their lowest common ancestor 
(the given two nodes are not guaranteed to be in the binary tree).

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
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode a, TreeNode b) {
    TreeNode lca = getLCA(root, a, b);
    if (lca == a) {
      if (getLCA(a, b, b) == null) {
        return null;
      }
    } else if (lca == b) {
      if (getLCA(b, a, a) == null) {
        return null;
      }
    }
    return lca;
  }

  private TreeNode getLCA(TreeNode root, TreeNode a, TreeNode b) {
    if (root == null || root == a || root == b) {
      return root;
    }
    TreeNode left = getLCA(root.left, a, b);
    TreeNode right = getLCA(root.right, a, b);

    if (left != null && right != null) {
      return root;
    }
    return left != null ? left : right;
  }
}
//time: O(n), space: O(height)