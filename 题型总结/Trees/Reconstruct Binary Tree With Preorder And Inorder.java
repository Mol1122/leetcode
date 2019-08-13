/**
Given the preorder and inorder traversal sequence of a binary tree, reconstruct the original tree.

Assumptions

The given sequences are not null and they have the same length
There are no duplicate keys in the binary tree
Examples

preorder traversal = {5, 3, 1, 4, 8, 11}

inorder traversal = {1, 3, 4, 5, 8, 11}

the corresponding binary tree is

        5

      /    \

    3        8

  /   \        \

1      4        11            */

public class Solution {
  public TreeNode reconstruct(int[] in, int[] pre) {
    if (in == null || pre == null || in.length != pre.length) {
        return null;
    }
    Map<Integer, Integer> map = getMap(in);
    return construct(in, 0, in.length - 1, pre, 0, pre.length - 1, map);
  }

  private TreeNode construct(int[] in, int inLeft, int inRight,
                             int[] pre, int preLeft, int preRight,
                             Map<Integer, Integer> map) {
    if (inLeft > inRight) {
        return null;
    }
    TreeNode root = new TreeNode(pre[preLeft]);
    int inMid = map.get(root.key);

    root.left = construct(in, inLeft, inMid - 1, pre, preLeft + 1, preLeft + inMid - inLeft, map);
    root.right = construct(in, inMid + 1, inRight, pre, preLeft + inMid - inLeft + 1, preRight, map);
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
