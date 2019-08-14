/* Given a binary tree in which each node contains an integer number. The diameter is defined as the longest distance from one leaf node to another leaf node. The distance is the number of nodes on the path.

If there does not exist any such paths, return 0.

Examples

    5

  /    \

2      11

     /    \

    6     14

The diameter of this tree is 4 (2 → 5 → 11 → 14) */
public class Solution {
  public int diameter(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int[] max = {0};
    getPath(root, max);
    return max[0];
  }

  private int getPath(TreeNode root, int[] max) {
    if (root == null) {
      return 0;
    }
    int left = getPath(root.left, max);
    int right = getPath(root.right, max);
    
    if (root.left != null && root.right != null) {
      max[0] = Math.max(max[0], left + right + 1);
      return Math.max(left, right) + 1;
    }
    return root.left != null ? left + 1 : right + 1;
  }
}
//time: O(n), space: O(height)

//Diameter between two leaf nodes
/*public class Solution {
  int max = 0;
  public int diameter(TreeNode root) {
      if (root == null) {
          return 0;
      }
      dfs(root);
      return max;
  }
  
  private int height(TreeNode root) {
      if (root == null) {
          return 0;
      }
      int left = height(root.left);
      int right = height(root.right);
      if (root.left != null && root.right != null) {
          max = Math.max(max, 1 + left + right);
      }
      return 1 + Math.max(left, right);
  }
}   */

/* 算法：一棵树的周长就是对于每一个node找leftHeight + rightHeight + 1的最大值
** 难点：leaf不更新diameter, 只有一个child也不更新diameter,只是会把高度返回
** 时间复杂度：O(n)
** 空间复杂度：O(n) */
