//Diameter between two leaf nodes
public class Solution {
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
}

/* 算法：一棵树的周长就是对于每一个node找leftHeight + rightHeight + 1的最大值
** 难点：leaf不更新diameter, 只有一个child也不更新diameter,只是会把高度返回
** 时间复杂度：O(n)
** 空间复杂度：O(n) */
