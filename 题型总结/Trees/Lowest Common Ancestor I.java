/**
 * Given two nodes in a binary tree, find their lowest common ancestor.

Assumptions

There is no parent pointer for the nodes in the binary tree

The given two nodes are guaranteed to be in the binary tree

Examples

        5

      /   \

     9     12

   /  \      \

  2    3      14

The lowest common ancestor of 2 and 14 is 5

The lowest common ancestor of 2 and 9 is 9  */
 
public class Solution {
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode a, TreeNode b) {
      if (root == null || root == a || root == b) {
          return root;
      }
      TreeNode left = lowestCommonAncestor(root.left, a, b);
      TreeNode right = lowestCommonAncestor(root.right, a, b);
      if (left != null && right != null) {
          return root;
      }
      return left == null ? right : left;
  }
}
//time: O(n), space: O(height)
