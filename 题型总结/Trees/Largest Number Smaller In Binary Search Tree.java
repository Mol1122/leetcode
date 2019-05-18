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
      //recursive, time: O(h), space: O(h)
      //return helper(root, target);
    
      //iterative, time: O(h), space: O(1)
      int result = Integer.MIN_VALUE;
      while (root != null) {
          if (target <= root.key) {
              root = root.left;
          } else {
              result = root.key;
              root = root.right;
          }
      }
      return result;
  }
  
  private int helper(TreeNode root, int target) {
      if (root == null) {
          return Integer.MIN_VALUE;
      }
      if (target <= root.key) {
          return helper(root.left, target);
      }
      int right = helper(root.right, target);
      return right == Integer.MIN_VALUE ? root.key : right;
  }
}
