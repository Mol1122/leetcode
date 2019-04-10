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
  int index;
  public TreeNode reconstruct(int[] post) {
      if (post == null || post.length == 0) {
          return null;
      }
      index = post.length - 1;
      return helper(post, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }
  
  private TreeNode helper(int[] post, int min, int max) {
      if (index < 0) {
          return null;
      }
      TreeNode root = null;
      if (post[index] > min && post[index] < max) {
          root = new TreeNode(post[index]);
          index--;
   
          root.right = helper(post, root.key, max);
          root.left = helper(post, min, root.key);  
      }
      return root;
  }
}

/* 时间复杂度：O(n)
** 空间复杂度：O(n) */
