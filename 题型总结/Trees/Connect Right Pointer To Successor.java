
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
  public void connect(TreeNode root) {
      helper(root);
  }
  
  private TreeNode helper(TreeNode root) {
      if (root == null) {
          return null;
      }
      TreeNode left = helper(root.left);
      if (left != null) {
          left.right = root;
      }
      if (root.right == null) {
          return root;
      }
      return helper(root.right);
  }
}

/* 算法：用分治型dfs去思考，先去到左子树拿到left, 然后返回自己或者最右
** 时间复杂度：O(n)
** 空间复杂度：O(n) */
