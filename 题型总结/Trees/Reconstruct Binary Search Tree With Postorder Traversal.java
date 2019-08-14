/**
Given the postorder traversal sequence of a binary search tree, reconstruct the original tree.

Assumptions

The given sequence is not null
There are no duplicate keys in the binary search tree
Examples

postorder traversal = {1, 4, 3, 11, 8, 5}

the corresponding binary search tree is

        5

      /    \

    3        8

  /   \        \

1      4        11    */

public class Solution {
  int index;
  public TreeNode reconstruct(int[] post) {
    if (post == null || post.length == 0) {
        return null;
    }
    index = post.length - 1;
    return helper(post, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private TreeNode helper(int[] post, int min, int max) {
      if (index < 0 || post[index] <= min || post[index] >= max) {
          return null;
      }
      TreeNode root = new TreeNode(post[index--]);
      root.right = helper(post, root.key, max);
      root.left = helper(post, min, root.key);

      return root;
  }
}
//time: O(n), space: O(n)
//难点：必须要先root.right 再root.left, 因为index是递减的