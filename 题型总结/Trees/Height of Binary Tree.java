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
  public int findHeight(TreeNode root) {
      if (root == null) {
          return 0;
      }
      int left = findHeight(root.left);
      int right = findHeight(root.right);
      return Math.max(left, right) + 1;
  }
}
