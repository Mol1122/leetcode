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
  int index = 0;
  public TreeNode reconstruct(int[] pre) {
      if (pre == null || pre.length == 0) {
          return null;
      }
      return helper(pre, Integer.MAX_VALUE);
  }
  
  private TreeNode helper(int[] pre, int bound) {
      if (index >= pre.length || pre[index] > bound) { //为了避免去到右子树
          return null;
      }
      TreeNode root = new TreeNode(pre[index++]);
      root.left = helper(pre, root.key);
      root.right = helper(pre, bound);
      return root;
  }
}
//time: O(n), space: O(n)
