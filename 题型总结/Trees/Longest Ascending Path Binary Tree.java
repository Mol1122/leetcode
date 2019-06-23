/* Determine the length of the longest ascending path in the binary tree.

A valid path is a part of the path from root to any of the leaf nodes.

Examples:

        5

      /    \

    3        2

  /   \        \

1      0        11

the longest ascending path is 2 -> 11, length is 2.

How is the binary tree represented?

We use the level order traversal sequence with a special symbol "#" denoting the null node.

For Example:

The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

    1

  /   \

 2     3

      /

    4 */

public class Solution {
  public int longest(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int[] max = {1};
    getLongest(root, 1, max);
    return max[0];
  }

  private void getLongest(TreeNode root, int count, int[] max) {
    if (root == null) {
      return;
    }
    if (root.left != null) {
      if (root.key < root.left.key) {
        max[0] = Math.max(max[0], count + 1);
        getLongest(root.left, count + 1, max);
      } else {
        getLongest(root.left, 1, max);
      }
    }
    if (root.right != null) {
      if (root.key < root.right.key) {
        max[0] = Math.max(max[0], count + 1);
        getLongest(root.right, count + 1, max);
      } else {
        getLongest(root.right, 1, max);
      }
    }
  }
}
//time: O(n), space: O(height)