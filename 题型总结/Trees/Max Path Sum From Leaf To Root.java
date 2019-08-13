/**
Given a binary tree in which each node contains an integer number. Find the maximum possible path sum from a leaf to root.



Assumptions

The root of given binary tree is not null.



Examples



         10

       /      \

    -2        7

  /     \

8      -4

The maximum path sum is 10 + 7 = 17. */

public class Solution {
  public int maxPathSumLeafToRoot(TreeNode root) {
    int[] max = {Integer.MIN_VALUE};
    largestPath(root, 0, max);
    return max[0];
  }

  private void largestPath(TreeNode root, int prefixSum, int[] max) {
    if (root == null) {
      return;
    }
    if (root.left == null && root.right == null) {
      max[0] = Math.max(max[0], prefixSum + root.key);
      return;
    }
    largestPath(root.left, prefixSum + root.key, max);
    largestPath(root.right, prefixSum + root.key, max);
  }
}
//time: O(n), space: O(height)