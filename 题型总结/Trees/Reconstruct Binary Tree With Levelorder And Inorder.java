/**
Given the levelorder and inorder traversal sequence of a binary tree, reconstruct the original tree.

Assumptions

The given sequences are not null and they have the same length
There are no duplicate keys in the binary tree
Examples

levelorder traversal = {5, 3, 8, 1, 4, 11}

inorder traversal = {1, 3, 4, 5, 8, 11}

the corresponding binary tree is

        5

      /    \

    3        8

  /   \        \

1      4        11      */

public class Solution {
  public TreeNode reconstruct(int[] in, int[] level) {
    if (in == null || level == null || in.length != level.length) {
        return null;
    }
    Map<Integer, Integer> map = getMap(in);
    List<Integer> list = new ArrayList<>();
    for (int num : level) {
        list.add(num);
    }
    return helper(list, map);
  }

  private TreeNode helper(List<Integer> level, Map<Integer, Integer> map) {
      if (level.size() == 0) {
          return null;
      }
      TreeNode root = new TreeNode(level.remove(0));
      int inMid = map.get(root.key);
      List<Integer> left = new ArrayList<>();
      List<Integer> right = new ArrayList<>();
      for (int num : level) {
          if (map.get(num) < inMid) {
              left.add(num);
          } else if (map.get(num) > inMid) {
              right.add(num);
          }
      }
      root.left = helper(left, map);
      root.right = helper(right, map);
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
//time: O(n^2), space: O(n^2)