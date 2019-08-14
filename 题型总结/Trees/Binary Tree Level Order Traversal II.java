/**
Given a binary tree, return the bottom-up level order traversal of its nodes' values, from left to right. 
Only need to return lowest level 

Example:

    Given the below binary tree

              5

          /        \

        3          8

      /    \           \

    1       4         11

    return its bottom-up level order traversal as:

      [1, 4, 11],                                     */

public class Solution {
  public List<Integer> levelOrderBottom(TreeNode root) {
      List<Integer> results = new ArrayList<>();
      int height = getHeight(root);
      getLevel(root, 0, results, height);
      return results;
  }
  
  private void getLevel(TreeNode root, int level, List<Integer> results, int height) {
      if (root == null) {
          return;
      }
      if (root.left == null && root.right == null && level == height - 1) {
          results.add(root.key);
      }
      getLevel(root.left, level + 1, results, height);
      getLevel(root.right, level + 1, results, height);
    //放在这里也可以
 /*     if (root.left == null && root.right == null && level == height - 1) {
          results.add(root.key);
      }  */
  }
  
  private int getHeight(TreeNode root) {
      if (root == null) {
          return 0;
      }
      return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
  }
}

/* 算法：用recursion的方法实现bfs, 
** 难点：每当level大于result size的时候，在result的最前面加新的list存值, 因为最底层的要放在最上面
** 时间复杂度：O(n)
** 空间复杂度：O(n) */
