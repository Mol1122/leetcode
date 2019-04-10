/**
 * public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
public class Solution {
  int first = Integer.MIN_VALUE;
  int second = Integer.MIN_VALUE;
  public int secondLargest(TreeNode root) {
      if (root == null) {
          return second;
      }
      helper(root);
      return second;
  }
  
  private void helper(TreeNode root) {
      if (root == null) {
          return;
      }
      secondLargest(root.right);
      if (root.key > first) {
          second = first;
          first = root.key;
      } else if (root.key > second) {
          second = root.key;
          return;
      }
      secondLargest(root.left);
  }
}

/* 算法：inverse inorder traversal
** 时间复杂度：O(n), due to pruning, much less than O(n)
** 空间复杂度：O(n) */
