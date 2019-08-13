/**
Given the postorder and inorder traversal sequence of a binary tree, reconstruct the original tree.

Assumptions

The given sequences are not null and they have the same length
There are no duplicate keys in the binary tree
Examples

postorder traversal = {1, 4, 3, 11, 8, 5}

inorder traversal = {1, 3, 4, 5, 8, 11}

the corresponding binary tree is

        5

      /    \

    3        8

  /   \        \

1      4        11       */

public class Solution {
  public TreeNode reconstruct(int[] in, int[] post) {
    if (in == null || post == null || in.length != post.length) {
        return null;
    }
    Map<Integer, Integer> map = getMap(in);
    return construct(in, 0, in.length - 1, post, 0, post.length - 1, map);
  }

  private TreeNode construct(int[] in, int inLeft, int inRight, 
                             int[] post, int postLeft, int postRight,
                             Map<Integer, Integer> map) {
    if (inLeft > inRight) {
        return null;
    }
    TreeNode root = new TreeNode(post[postRight]);
    int inMid = map.get(root.key);

    root.left = construct(in, inLeft, inMid - 1, post, postLeft, postLeft + inMid - inLeft - 1, map);
    root.right = construct(in, inMid + 1, inRight, post, postLeft + inMid - inLeft, postRight - 1, map);
    return root;
  }

  private Map<Integer, Integer> getMap(int[] in) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < in.length; i++) {
        map.put(in[i], i);
    }
    return map;
  }
}
//time: O(n), space: O(n)
