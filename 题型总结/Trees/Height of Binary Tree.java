/**
The height of above binary tree is 3.

How is the binary tree represented?

We use the level order traversal sequence with a special symbol "#" denoting the null node.

For Example:

The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

    1

  /   \

 2     3

      /

    4                 */
    
public class Solution {
  public int findHeight(TreeNode root) {
      if (root == null) {
          return 0;
      }
      int left = findHeight(root.left);
      int right = findHeight(root.right);
      return Math.max(left, right) + 1;
  }
}
//time: O(n), space: O(n)