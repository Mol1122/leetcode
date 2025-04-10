/**
 Given the preorder traversal sequence of a binary search tree, reconstruct the original tree.

Assumptions

The given sequence is not null
There are no duplicate keys in the binary search tree
Examples

preorder traversal = {5, 3, 1, 4, 8, 11}

The corresponding binary search tree is

        5

      /    \

    3        8

  /   \        \

1      4        11      */

//Method 1
public class Solution {
  int index = 0;
  public TreeNode reconstruct(int[] pre) {
      if (pre == null || pre.length == 0) {
          return null;
      }
      return helper(pre, Integer.MAX_VALUE);
  }
  
  private TreeNode helper(int[] pre, int bound) {
      if (index >= pre.length || pre[index] > bound) { //为了避免去到右子树
          return null;
      }
      TreeNode root = new TreeNode(pre[index++]);
      root.left = helper(pre, root.key);
      root.right = helper(pre, bound);
      return root;
  }
}
//time: O(n), space: O(n)

//Method 2
public class Solution {
  public TreeNode reconstruct(int[] pre) {
    if (pre == null || pre.length == 0) {
      return null;
    }
    int[] index = {0};
    return construct(pre, Integer.MAX_VALUE, index);
  }

  private TreeNode construct(int[] pre, int bound, int[] index) {
    if (index[0] >= pre.length || pre[index[0]] > bound) {
      return null;
    }
    TreeNode root = new TreeNode(pre[index[0]++]);
    root.left = construct(pre, root.key, index);
    root.right = construct(pre, bound, index);
    return root;
  }
}
