/**
Given a binary tree in which each node contains an integer number. Find the maximum possible sum from one leaf node to another leaf node. If there is no such path available, return Integer.MIN_VALUE(Java)/INT_MIN (C++).

Examples

  -15

  /    \

2      11

     /    \

    6     14

The maximum path sum is 6 + 11 + 14 = 31.

How is the binary tree represented?

We use the level order traversal sequence with a special symbol "#" denoting the null node.

For Example:

The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

    1

  /   \

 2     3

      /

    4
 */
public class Solution {
  public int maxPathSum(TreeNode root) {
      int[] max = {Integer.MIN_VALUE};
      largestPath(root, max);
      return max[0];
  }
  
  private int largestPath(TreeNode root, int[] max) {
      if (root == null) {
          return 0;
      }
      int left = largestPath(root.left, max);
      int right = largestPath(root.right, max);
      
      if (root.left != null && root.right != null) {
          max[0] = Math.max(max[0], left + right + root.key);
          return Math.max(left, right) + root.key;
      }
      return root.left == null ? right + root.key : left + root.key;
  }
}

/* 
  1 (valid)  1           1             1
 /  \       /  \        /  \          /  \
3    4    null  3      3    null    null  null

time: O(n), space: O(height)
*/

/* 总结：这种人字形的tree问题最适合用分治法。最后return的一定是包含当前root, 因为是连续的 */
