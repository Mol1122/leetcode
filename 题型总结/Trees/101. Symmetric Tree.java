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
  public boolean isSymmetric(TreeNode root) {
      if (root == null) {
          return true;
      }
      return helper(root.left, root.right);
  }
  
  private boolean helper(TreeNode left, TreeNode right) {
      if (left == null && right == null) {
          return true;
      } else if (left == null || right == null) {
          return false;
      } else if (left.key != right.key) {
          return false;
      }
      return helper(left.left, right.right) && helper(left.right, right.left);
  }
}

/* 时间复杂度：O(n)
** 空间复杂度：O(n) or O(height) 
                (5a, 5b)
                /      \
            (1a, 1b)   (3a, 3b)
            /     \        /   \
        (2a, 2b)  (4a, 4b) ......



*/