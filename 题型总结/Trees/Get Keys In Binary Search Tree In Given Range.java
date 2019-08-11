/**
Get the list of keys in a given binary search tree in a given range[min, max] in ascending order, both min and max are inclusive.

Examples

        5

      /    \

    3        8

  /   \        \

 1     4        11

get the keys in [2, 5] in ascending order, result is  [3, 4, 5]

Corner Cases

What if there are no keys in the given range? Return an empty list in this case. */

public class Solution {
  public List<Integer> getRange(TreeNode root, int min, int max) {
      List<Integer> results = new ArrayList<>();
      if (root == null) {
          return results;
      }
      inorder(root, min, max, results);
      return results;
  }
  
  private void inorder(TreeNode root, int min, int max, List<Integer> results) {
      if (root == null) {
          return;
      }
      if (root.key > min) {
          inorder(root.left, min, max, results);
      }
      if (root.key >= min && root.key <= max) {
          results.add(root.key);
      }
      if (root.key < max) {
          inorder(root.right, min, max, results);
      }
  }
}

/* 时间复杂度：O(n)
** 空间复杂度：O(height) */
