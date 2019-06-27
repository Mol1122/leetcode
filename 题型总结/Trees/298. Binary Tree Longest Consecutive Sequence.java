/**
Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. 
The longest consecutive path need to be from parent to child (cannot be the reverse).

For example,

   1
    \
     3
    / \
   2   4
        \
         5
Longest consecutive sequence path is 3-4-5, so return 3.

   2
    \
     3
    / 
   2    
  / 
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2. */

public class Solution {
  public int longestConsecutive(TreeNode root) {
    int[] max = {0};
    getLongest(root, max);
    return max[0];
  }

  private int getLongest(TreeNode root, int[] max) {
    if (root == null) {
      return 0;
    }
    int left = getLongest(root.left, max);
    int right = getLongest(root.right, max);
    int length = 1;
    if (root.left != null && root.left.key == root.key + 1) {
      length = Math.max(length, left + 1);
    }
    if (root.right != null && root.right.key == root.key + 1) {
      length = Math.max(length, right + 1);
    }
    max[0] = Math.max(max[0], length);
    return length;
  }
}
//time: O(n), space: O(height)