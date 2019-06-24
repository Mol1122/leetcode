/* Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left 
as possible. It can have between 1 and 2h nodes inclusive at the last level h. */

public class Solution {
  public int countNodes(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int left = getLeftHeight(root);
    int right = getRightHeight(root);
    if (left == right) {
      return (int)Math.pow(2, left) - 1;
    }
    return countNodes(root.left) + countNodes(root.right) + 1;
  }

  private int getLeftHeight(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int height = 0;
    while (root != null) {
      height++;
      root = root.left;
    }
    return height;
  }

  private int getRightHeight(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int height = 0;
    while (root != null) {
      height++;
      root = root.right;
    }
    return height;
  }
}
//算法：可以利用complete binary tree的特性， leftHeight == rightHeight, 那么#node = 2^h - 1
//time: O(nlogn), space: O(logn)