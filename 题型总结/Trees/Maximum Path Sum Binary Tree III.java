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
  public int maxPathSum(TreeNode root) {
      int[] max = {Integer.MIN_VALUE};
      largestPath(root, max);
      return max[0];
  }
  
  private int largestPath(TreeNode root, int[] max) {
      if (root == null) {
          return 0;
      }
      int left = largestPath(root.left, max);
      int right = largestPath(root.right, max);
      
      max[0] = Math.max(max[0], Math.max(left, right) + root.key);
    
      return Math.max(Math.max(left, right) + root.key, 0);
  }
}
//time: O(n), space: O(height)
