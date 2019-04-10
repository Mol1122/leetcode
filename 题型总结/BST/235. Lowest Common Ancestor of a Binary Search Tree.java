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
  public TreeNode lca(TreeNode root, int p, int q) {
      if (root == null || root.key == p || root.key == q) {
          return root;
      }
      if (p < root.key && q < root.key) {
          return lca(root.left, p, q);
      } else if (p > root.key && q > root.key) {
          return lca(root.right, p, q);
      } else {
          return root;
      }
  }
}

/* 时间复杂度：O(height) = O(n)
** 空间复杂度：O(height) */
