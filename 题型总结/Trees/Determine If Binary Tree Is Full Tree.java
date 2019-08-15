/* Determine if a given binary tree is full.

A full binary tree is defined as a binary tree in which all nodes have either zero or two child nodes. 
Conversely, there is no node in a full binary tree, which has one child node.

If the root is null, return false. */

public class Solution {
  public boolean isFull(TreeNode root) {
    if (root == null) {
      return false;
    }
    return helper(root);
  }

  private boolean helper(TreeNode root) {
    if (root == null) {
      return true;
    }
    boolean left = helper(root.left);
    boolean right = helper(root.right);
    if (!left || !right) {
      return false;
    }
    if (root.left == null && root.right == null) {
      return true;
    }
    if (root.left == null || root.right == null) {
      return false;
    }
    return true;
  }
}
//time: O(n), space: O(height)       