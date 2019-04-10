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
  public int largestSmaller(TreeNode root, int target) {
      if (root == null) {
          return Integer.MIN_VALUE;
      }
      TreeNode result = getLowerBound(root, target);
      if (result == null || result.key == target) {
          return Integer.MIN_VALUE;
      }
      return result.key;
  }
  
  private TreeNode getLowerBound(TreeNode root, int target) {
      if (root == null) {
          return null;
      }
      if (target <= root.key) {
          return getLowerBound(root.left, target);
      }
      TreeNode right = getLowerBound(root.right, target);
      if (right != null) {
          return right;
      }
      return root;
  }
}
//time: O(h)
